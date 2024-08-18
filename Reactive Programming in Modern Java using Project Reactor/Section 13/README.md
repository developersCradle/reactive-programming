# Section 13: Implement Exception Handling in Movies Reactive Service.

Implement Exception Handling in Movies Reactive Service.

# What I Learned

# 44. Exception Handling in MoviesReactiveService using onErrorMap

- Example implementing exception handling.

```
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
    
```

# 45. Test Exception in MoviesReactiveService using Mockito

- Testing using exceptions and service with unit tests.

```
package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieReactiveServiceMockTest {

    @Mock
    MovieInfoService movieInfoService;

    @Mock
    ReviewService reviewService;

    @InjectMocks
    MovieReactiveService reactiveMovieService;

    @Test
    void getAllMovie() {

        //given
        when(movieInfoService.retrieveMoviesFlux()).thenCallRealMethod();
        when(reviewService.retrieveReviewsFlux(anyLong())).thenCallRealMethod();

        //when
        var movieFlux  =  reactiveMovieService.getAllMovies();

        //then
        StepVerifier.create(movieFlux)
                .expectNextCount(3)
                .verifyComplete();

    }
    
    @Test
    void getAllMovie_1() {

        //given
    	String errorMessage = "Exception occured in ReviewService";

    	when(movieInfoService.retrieveMoviesFlux()).thenCallRealMethod();
        
		when(reviewService.retrieveReviewsFlux(anyLong())).thenThrow(new RuntimeException(errorMessage));

        //when
        var movieFlux  =  reactiveMovieService.getAllMovies();

        //then
        StepVerifier.create(movieFlux)
                .expectError(RuntimeException.class)
                .verify();

    }

}
```