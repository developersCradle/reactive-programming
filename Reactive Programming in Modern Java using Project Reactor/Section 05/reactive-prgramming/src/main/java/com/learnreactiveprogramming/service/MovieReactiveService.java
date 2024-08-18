package com.learnreactiveprogramming.service;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.domain.Review;
import com.learnreactiveprogramming.exception.MovieException;
import com.learnreactiveprogramming.exception.NetworkException;
import com.learnreactiveprogramming.exception.ServiceException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;


@Slf4j
public class MovieReactiveService {

	
	
    private MovieInfoService movieInfoService;
    private ReviewService reviewService;
    
    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
    }
    
 

    public Flux<Movie> getAllMovies() {

        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                    .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo,reviewsList));
                })
                .onErrorMap( (ex) ->{
                	log.error("Expection is : ", ex);
                	throw new MovieException(ex.getMessage());
                })
                .log();
    }
    
    
    public Flux<Movie> getAllMovies_retry() {

        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                    .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo,reviewsList));
                })
                .onErrorMap( (ex) ->{
                	log.error("Expection is : ", ex);
                	throw new MovieException(ex.getMessage());
                })
                .retry(3)
                .log();
    }


    public Flux<Movie> getAllMovies_retryWhen() {

    	var retryWhen = Retry.fixedDelay(3, Duration.ofMillis(500))
    			.filter(ex -> ex instanceof MovieException) // We will try only if Exceptions is MoviesExpection
    			.onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
    			Exceptions.propagate(retrySignal.failure())
    					);
    	
//    	var retryWhen = Retry.fixedDelay(3, Duration.ofMillis(500))
        
    	var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                    .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo,reviewsList));
                })
                .onErrorMap((ex) -> {
                	log.error("Expection is : ", ex);
                	if (ex instanceof NetworkException) {
                		throw new MovieException(ex.getMessage());
					}
                	else
                		throw new ServiceException(ex.getMessage());
                })
                .retryWhen(retryWhen)
                .log();
    }
    
    public Flux<Movie> getAllMovies_repeat() {

    	var retryWhen = Retry.fixedDelay(3, Duration.ofMillis(500))
    			.filter(ex -> ex instanceof MovieException) // We will try only if Exceptions is MoviesExpection
    			.onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
    			Exceptions.propagate(retrySignal.failure())
    					);
    	
//    	var retryWhen = Retry.fixedDelay(3, Duration.ofMillis(500))
        
    	var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                    .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo,reviewsList));
                })
                .onErrorMap((ex) -> {
                	log.error("Expection is : ", ex);
                	if (ex instanceof NetworkException) {
                		throw new MovieException(ex.getMessage());
					}
                	else
                		throw new ServiceException(ex.getMessage());
                })
                .repeat()
                .log();
    }
    
    
    
    public Flux<Movie> getAllMovies_repeat_n(long n) {

    	var retryWhen = Retry.fixedDelay(3, Duration.ofMillis(500))
    			.filter(ex -> ex instanceof MovieException) // We will try only if Exceptions is MoviesExpection
    			.onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
    			Exceptions.propagate(retrySignal.failure())
    					);
    	
//    	var retryWhen = Retry.fixedDelay(3, Duration.ofMillis(500))
        
    	var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                    .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo,reviewsList));
                })
                .onErrorMap((ex) -> {
                	log.error("Expection is : ", ex);
                	if (ex instanceof NetworkException) {
                		throw new MovieException(ex.getMessage());
					}
                	else
                		throw new ServiceException(ex.getMessage());
                })
                .repeat(n)
                .log();
    }
    
    //MovieId is only one, so Mono
    public Mono<Movie> getMovieById(long movieId)
    {
    	var movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
    	var reviewsFlux = reviewService.retrieveReviewsFlux(movieId)
    			.collectList();
    	
    	return movieInfoMono.zipWith(reviewsFlux, (movieInfo, reviews) -> new Movie(movieInfo, reviews));
    }
    

    public Mono<Movie> getMovieById_usingFlatMap(long movieId) {

        var movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        return movieInfoMono
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(movieList -> new Movie(movieInfo, movieList));

                });
    }
    

}
