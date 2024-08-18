package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.exception.NetworkException;
import com.learnreactiveprogramming.exception.ServiceException;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    void getAllMovie_retry() {

        //given
    	String errorMessage = "Exception occured in ReviewService";

    	when(movieInfoService.retrieveMoviesFlux()).thenCallRealMethod();
        
		when(reviewService.retrieveReviewsFlux(anyLong())).thenThrow(new RuntimeException(errorMessage));

        //when
        var movieFlux  =  reactiveMovieService.getAllMovies_retry();

        //then
        StepVerifier.create(movieFlux)
                .expectError(RuntimeException.class)
                .verify();

        verify(reviewService, times(4)).retrieveReviewsFlux(isA(Long.class));
    }
    
    @Test
    void getAllMovie_retryWhen() {

        //given
    	String errorMessage = "Exception occured in ReviewService";

    	when(movieInfoService.retrieveMoviesFlux()).thenCallRealMethod();
        
		when(reviewService.retrieveReviewsFlux(anyLong())).thenThrow(new NetworkException(errorMessage));

        //when
        var movieFlux  =  reactiveMovieService.getAllMovies_retryWhen();

        //then
        StepVerifier.create(movieFlux)
                .expectError(RuntimeException.class)
                .verify();

        verify(reviewService, times(4)).retrieveReviewsFlux(isA(Long.class));
    }
    
    @Test
    public void getAllMovies_repeat() {
    	 //given
        var errorMessage = "Exception Occurred in Review Service";
        when(movieInfoService.retrieveMoviesFlux()).thenCallRealMethod();
        when(reviewService.retrieveReviewsFlux(anyLong())).thenCallRealMethod();

        //when
        var movieFlux = reactiveMovieService.getAllMovies_repeat().log();

        //then
        StepVerifier.create(movieFlux)
                .expectNextCount(6)
                .thenCancel()
                .verify();

        verify(reviewService, times(6)).retrieveReviewsFlux(isA(Long.class));

    }
    
    @Test
    public void getAllMovies_repeat_n() {
    	 //given
        var errorMessage = "Exception Occurred in Review Service";
        when(movieInfoService.retrieveMoviesFlux()).thenCallRealMethod();
        when(reviewService.retrieveReviewsFlux(anyLong())).thenCallRealMethod();

        var noOfTImes = 2L;
        
        //when
        var movieFlux = reactiveMovieService.getAllMovies_repeat_n(noOfTImes).log();

        //then
        StepVerifier.create(movieFlux)
                .expectNextCount(9)
                .expectComplete();

        verify(reviewService, times(6)).retrieveReviewsFlux(isA(Long.class));

    }
    /*
     * Test that re-try should not be happened
     */
    @Test
    void getAllMovies_retry_when_1() {

        //given
        var errorMessage = "Exception Occurred in Review Service";
        when(movieInfoService.retrieveMoviesFlux()).thenCallRealMethod();
        when(reviewService.retrieveReviewsFlux(anyLong())).thenThrow(new ServiceException(errorMessage));

        //when
        var movieFlux = reactiveMovieService.getAllMovies_retryWhen().log();

        //then
        StepVerifier.create(movieFlux)
                .expectErrorMessage(errorMessage)
                .verify();

        verify(reviewService, times(1)).retrieveReviewsFlux(isA(Long.class));

    }
}