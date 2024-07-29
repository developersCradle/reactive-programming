package com.learnreactiveprogramming.service;


import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.test.StepVerifier;

public class MovieInfoServiceTest {

	    WebClient webClient = WebClient.builder()
	            .baseUrl("http://localhost:8080/movies")
	            .build();

	    MovieInfoService movieInfoService
	            =new MovieInfoService(webClient);

	    @Test
	    void retrieveAllMovieInfo_RestClient() {
	        //Given

	        //When
	        var movieInfoFlux = movieInfoService.retrieveAllMovieInfo_RestClient();

	        //Then
	        StepVerifier.create(movieInfoFlux)
	                .expectNextCount(7)
	                .verifyComplete();
	    }
	    
	    @Test
	    void retrieveMovieInfo_WithId_RestClient() {
	        
	    	//Given
	    	Long movieId = 1L;
	    	
	        //When
	        var movieInfoFlux = movieInfoService.retrieveAllMovieInfo_RestClient(movieId);

	        //Then
	        StepVerifier.create(movieInfoFlux)
	                .expectNextCount(1)
	                .verifyComplete();
	    }

	  
}
