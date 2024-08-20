package com.learnreactiveprogramming.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

public class MovieReactiveServiceRestClientTest {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/movies")
            .build();
    
    MovieInfoService mis = new MovieInfoService(webClient);
    ReviewService rs = new ReviewService(webClient);
    MovieReactiveService movieReactiveService = new MovieReactiveService(mis, rs);

    @Test
    void getAllMovies_RestClient() {
        //given

        //when
        var moviesFlux = movieReactiveService.getAllMovies_restClient();

        //then
        StepVerifier.create(moviesFlux)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    void getMovieById_RestClient() {

        //given
        var movieInfoId = 1 ;

        //when
        var movieMono = movieReactiveService.getMovieById_RestClient(movieInfoId);

        //then
        StepVerifier.create(movieMono)
                .assertNext(movie -> {
                    assertEquals("Batman Begins", movie.getMovieInfo().getName());
                    assertEquals(movie.getReviewList().size(), 1);
                    //assertNotNull(movie.getRevenue());
                })
                .verifyComplete();
    }
}