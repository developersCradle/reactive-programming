package com.learnreactiveprogramming.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import reactor.test.StepVerifier;

public class ReviewServiceTest {

	    WebClient webClient = WebClient.builder()
	            .baseUrl("http://localhost:8080/movies")
	            .build();
	    
	    ReviewService reviewService = new ReviewService(webClient);
	    
	    @Test
	    void retrieveReviewsFlux_RestClient() {
		        
		    	//Given
		    	Long movieInfoId = 1L;
		    	
		        //When
		        var reviewsFlux = reviewService.retrieveReviewsFlux_RestClient(movieInfoId);

		        //Then
		        StepVerifier.create(reviewsFlux)
		                .assertNext(reviews -> {
		                	assertEquals("Nolan is the real superhero", reviews.getComment());
		                })
		                .expectNextCount(0)
		                .verifyComplete();
		}
}
