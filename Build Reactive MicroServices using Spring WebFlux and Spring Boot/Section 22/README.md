# Section 22: Unit Testing Functional Web. 

Unit Testing Functional Web.

# What I Learned

# 82. Setting up Test Class for Unit Testing

- Todo these ones.

# Assignment 7: Unit Test Cases for the Reviews Service in the ReviewsUnitTest class

```

Use the same pattern that we followed in the MoviesInfoService.

Questions for this assignment

    1. Write Unit Test case for get all reviews endpoint.

    2. Write Unit Test case for the PUT endpoint to update a Review.

    3. Write  Unit Test case for the DELETE endpoint to delete a Review.

```

```
    @Test
    void getAllReviews() {

        //given
        List<Review> reviewsList = Arrays.asList(
            new Review(null, 1L, "Awesome Movie", 9.0),
            new Review(null, 1L, "Awesome Movie1", 9.0),
            new Review("123456", 2L, "Excellent Movie", 8.0));

        when(reviewReactiveRepository.findAll())
            .thenReturn(Flux.fromIterable(reviewsList));

        //when
        webTestClient
            .get()
            .uri(REVIEWS_URL)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBodyList(Review.class)
            .hasSize(3);
    }

```

```

    @Test
    void updateReview() {

        //given
        String reviewId = "123456";
        Review review = new Review(null, 1L, "Awesome Movie1 Updated", 9.8);

        when(reviewReactiveRepository.findById(isA(String.class)))
            .thenReturn(Mono.just(new Review()));
        
        when(reviewReactiveRepository.save(isA(Review.class)))
            .thenReturn(Mono.just(new Review(null, 1L, "Awesome Movie1 Updated", 9.8)));

        //when
        webTestClient
            .put()
            .uri(REVIEWS_URL + "/{id}", reviewId)
            .bodyValue(review)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(Review.class)
            .consumeWith(reviewEntityExchangeResult -> {

                Review updatedReview = reviewEntityExchangeResult.getResponseBody();
                assert updatedReview != null;
                assertEquals(1L, updatedReview.getMovieInfoId());
                assertEquals("Awesome Movie1 Updated", updatedReview.getComment());
            });
    }

```

```
    @Test
    void deleteReview() {

        //given
        String reviewId = "123456";

        when(reviewReactiveRepository.findById(isA(String.class)))
            .thenReturn(Mono.just(new Review()));
        
        when(reviewReactiveRepository.deleteById(isA(String.class)))
            .thenReturn(Mono.empty());

        //when
        webTestClient
            .delete()
            .uri(REVIEWS_URL + "/{id}", reviewId)
            .exchange()
            .expectStatus()
            .isNoContent();
    }

```