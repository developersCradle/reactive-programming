package com.learnreactiveprogramming.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.learnreactiveprogramming.domain.Movie;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MovieReactiveServiceTest {
	
//    WebClient webClient = WebClient.builder()
//            .baseUrl("http://localhost:8080/movies")
//            .build();
    MovieInfoService mis = new MovieInfoService();
    ReviewService rs = new ReviewService();
//  RevenueService revenueService = new RevenueService();
    MovieReactiveService movieReactiveService = new MovieReactiveService(mis, rs);

	
    @Test
    void getAllMovieInfo() {
        //given

        //when
        var moviesInfo = movieReactiveService.getAllMovies();

        //then
        StepVerifier.create(moviesInfo)
                .assertNext(movieInfo -> {
                    assertEquals("Batman Begins", movieInfo.getMovieInfo().getName());
                    assertEquals(movieInfo.getReviewList().size(), 2);

                })
                .assertNext(movieInfo -> {
                    assertEquals("The Dark Knight", movieInfo.getMovieInfo().getName());
                    assertEquals(movieInfo.getReviewList().size(), 2);
                })
                .assertNext(movieInfo -> {
                    assertEquals("Dark Knight Rises", movieInfo.getMovieInfo().getName());
                    assertEquals(movieInfo.getReviewList().size(), 2);
                })
                .verifyComplete();

    }
    
    @Test
    void getMovieById() {

    	// Given
        long movieId = 1L;
        // When
        Mono<Movie> movieMono = movieReactiveService.getMovieById(movieId);
        // Then
        StepVerifier.create(movieMono)
                .assertNext(movieInfo -> {
                    assertEquals("Batman Begins", movieInfo.getMovieInfo().getName());
                    assertEquals(movieInfo.getReviewList().size(), 2);
                })
                .verifyComplete();
    }

    
    @Test
    void getMovieById_usingFlatMap() {

        //given
        long movieId = 1L;

        //when
        Mono<Movie> movieMono = movieReactiveService.getMovieById_usingFlatMap(movieId);

        //then
        StepVerifier.create(movieMono)
                .assertNext(movieInfo -> {
                    assertEquals("Batman Begins", movieInfo.getMovieInfo().getName());
                    assertEquals(movieInfo.getReviewList().size(), 2);
                })
                .verifyComplete();
    }
    
    
}
