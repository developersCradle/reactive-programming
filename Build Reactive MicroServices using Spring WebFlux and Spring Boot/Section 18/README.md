# Section 18: Writing Custom Queries using ReactiveMongoRepository. 

Writing Custom Queries using ReactiveMongoRepository.

# What I Learned

# 68. Implement a Custom Query to retrieve MovieInfo by Year

- Custom queries with name.

```

public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String> {

    Flux<MovieInfo> findByYear(Integer year);
    Mono<MovieInfo> findOneByName(String name);
}

```

- To test this one.

```

    @Test
    void findByYear() {
        
        //given

        //when
        Flux<MovieInfo> moviesInfoFlux = movieInfoRepository.findByYear(2005).log();

        //then
        StepVerifier.create(moviesInfoFlux)
            .expectNextCount(1)
            .verifyComplete();
    }
```
# 69. GET Endpoint to retrieve a MovieInfo by Year - Using @RequestParam

- Adding year to the find by year. Test to this.

```
    @Test
    void getAllMovieInfoByYear() {
        
        URI uri = UriComponentsBuilder.fromUriString(MOVIES_INFO_URL)
            .queryParam("year", 2005)
            .buildAndExpand().toUri();

        webTestClient
            .get()
            .uri(uri)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBodyList(MovieInfo.class)
            .hasSize(1);
    }
```

- From point of REST:full API there is three commons ways to include data.
    - `public Flux<MovieInfo> getAllMovieInfos(@RequestParam(value = "year", required = false) Integer year)`. **@RequestParam**.
    - `public Mono<ResponseEntity<MovieInfo>> getMovieInfoById(@PathVariable String id) {`. **@PathVariable**.
    - `public Mono<MovieInfo> addMovieInfo(@RequestBody @Valid MovieInfo movieInfo) {`. **@RequestBody**.


# Assignment 5: Build a Custom Repository function to retrieve MovieInfo by name

```
Use the same technique that we used to implement a custom function to retrieve a MovieInfos by Year.



But this function will only return a single MovieInfo. Reason being there could be possibly one single movie for a given name.

So the return type of this function will be Mono<MovieInfo>



Questions for this assignment
    1. Write a new repository function to retrieve a MovieInfo by name in MovieInfoRepository interface.

    2. Write an integration test thats going to assert that the MovieInfo is returned in the MoviesInfoRepositoryIntgTest class.
```

```
Mono<MovieInfo> findOneByName(String name);
```

```
    @Test
    void findByName() {
        
        //given

        //when
        Mono<MovieInfo> moviesInfoMono = movieInfoRepository.findOneByName("Dark Knight Rises").log();

        //then
        StepVerifier.create(moviesInfoMono)
            .assertNext(movieInfo -> {
                assertEquals("Dark Knight Rises", movieInfo.getName());
            })
            .verifyComplete();
    }
```