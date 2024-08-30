# Section 17: Using ResponseEntity with Reactive Types. 

Using ResponseEntity with Reactive Types.

# What I Learned

# 66. Need for ResponseEntity in Spring WebFlux.

- We can have **two** approaches to answers **Status Entity**.
    - We write ResponseEntity into return call, like such `return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);`.
    - With annotations `@ResponseStatus(HttpStatus.CREATED)`, top of controller methods.

- [ResponseEntity](https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/responseentity.html).

- We can use **Mono** and **Flux** with response entity.

- Test case for this.

```    @Test
    void updateMovieInfo_notFound() {
        var id = "abc1";
        var updatedMovieInfo = new MovieInfo("abc", "Dark Knight Rises 1",
                2013, List.of("Christian Bale1", "Tom Hardy1"), LocalDate.parse("2012-07-20"));

        webTestClient
                .put()
                .uri(MOVIES_INFO_URL + "/{id}", id)
                .bodyValue(updatedMovieInfo)
                .exchange()
                .expectStatus()
                .isNotFound();
    }
```

# 67. Using ResponseEntity to dynamically change the Response Status.

- We are going to embedded our **MovieInfo** into the body of **ResponseBody** which is wrapped into the **ResponseEntity**. This is done using `.map(ResponseEntity.ok()::body)`.

- To add **Not Found** message to the **Response Entity** using `.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))`.
    - This better than returning empty one.

```
    @PutMapping("/movieinfos/{id}")
    public Mono<ResponseEntity<MovieInfo>> updateMovieInfo(@RequestBody MovieInfo updatedMovieInfo, @PathVariable String id) {
        
        return moviesInfoService.updateMovieInfo(updatedMovieInfo, id)
            .map(ResponseEntity.ok()::body)
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
            .log();
    }
```

- Custom response whenever data is returned or not.

# Assignment 4: Implement 404 Response for the getMovieInfoById Endpoint.

```
Use the same technique that's been used for the updateMovieInfo.

Use the switchIfEmpty operator after this call.

moviesInfoService.getMovieInfoById(id);


Questions for this assignment
    1. Respond with NotFound status if the passed in id does not find a valid document in the MongoDB

    2. Write a Integration test case to assert the same.
```

- My answer.

```
    @GetMapping("/movieinfos/{id}")
    public Mono<ResponseEntity<MovieInfo>> getMovieInfoById(@PathVariable String id) {
        
        return moviesInfoService.getMovieInfoById(id)
            .map(ResponseEntity.ok()::body)
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

```

```
    @Test
    void getMovieInfoById_notfound() {
        
        String movieInfoId = "def";
        webTestClient
            .get()
            .uri(MOVIES_INFO_URL + "/{id}", movieInfoId)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

```