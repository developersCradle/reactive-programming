
# Section 14: Build MovieInfo Service using Rest Controller Approach.

Build MovieInfo Service using Rest Controller Approach.

# What I Learned.

# 53. Build a POST endpoint to create a new MovieInfo.

- We can use `@ResponseStatus(HttpStatus.CREATED)` for more robust way of returning HTTP codes.
    - It also can be used **declarative** way.
    - Just be **Consistent** about the styles, which every u choose.

### Controller.

```

@RestController
@RequestMapping("/v1")
@Slf4j
public class MoviesInfoController {

    private MoviesInfoService moviesInfoService;


    public MoviesInfoController(MoviesInfoService moviesInfoService) {
        this.moviesInfoService = moviesInfoService;
    }

    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {
        
        return movieInfoRepository.save(movieInfo);
    }
}


```

- As you can see, its recommended to use **Construction Injection pattern** rather than `@Autowired`.

### Service.

```
package com.reactivespring.service;

import org.springframework.stereotype.Service;

import com.reactivespring.domain.MovieInfo;
import com.reactivespring.repository.MovieInfoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MoviesInfoService {

    private MovieInfoRepository movieInfoRepository;
    
    public MoviesInfoService(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    public Mono<MovieInfo> addMovieInfo(MovieInfo movieInfo) {
        
        return movieInfoRepository.save(movieInfo);
    }

}

```

# 54. Integration Test for the POST end point using JUnit5.

- Testing persisting method.

```

    @Test
    void addMovieInfo() {
        //given
        var movieInfo = new MovieInfo(null, "Batman Begins1",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        //when
        webTestClient
                .post()
                .uri(MOVIES_INFO_URL)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {

                    var savedMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                    assert savedMovieInfo!=null;
                    assert savedMovieInfo.getMovieInfoId()!=null;
                });


        //then
    }
```

# 55. Build a GET Endpoint to get all the MoviesInfo.

### Controller.

```
   @GetMapping("/movieinfos")
    public Flux<MovieInfo> getAllMovieInfos() {
        return moviesInfoService.getAllMovieInfos();
    }

```

### Service.

```
    public Flux<MovieInfo> getAllMovieInfos() {
        
        return movieInfoRepository.findAll();
    }

```

- The test for this one.

```

    @Test
    void getAllMoviesInfo() {

        webTestClient
            .get()
            .uri(MOVIES_INFO_URL)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBodyList(MovieInfo.class)
            .hasSize(3);
    }
```

# 56. Build a GET Endpoint to retrieve a MovieInfo by ID.

### Controller.

```
@GetMapping("/movieinfos/{id}")
    public Mono<ResponseEntity<MovieInfo>> getMovieInfoById(@PathVariable String id) {
        return moviesInfoService.getMovieInfoById(id).log();
    }
```

### Service.

```
    public Mono<MovieInfo> getMovieInfoById(String id) {
        
        return movieInfoRepository.findById(id);
    }
```

- Test for this. This can be done in two ways.

```
    @Test
    void getMovieInfoById() {

        var movieInfoId = "abc";
        webTestClient
                .get()
                .uri(MOVIES_INFO_URL+"/{id}", movieInfoId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Dark Knight Rises");
                /*.expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {
                    var movieInfo = movieInfoEntityExchangeResult.getResponseBody();
                    assertNotNull(movieInfo);
                });*/

    }
```

# 57. Build a PUT Endpoint to update a MovieInfo by ID.

# Controller.

```

    @PutMapping("/movieinfos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<MovieInfo>> updateMovieInfo(@RequestBody MovieInfo movieInfo, @PathVariable String id) {

        var updatedMovieInfoMono =  moviesInfoService.updateMovieInfo(movieInfo, id);
        return updatedMovieInfoMono
                .map(movieInfo1 -> ResponseEntity.ok()
                        .body(movieInfo1))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }
```

 # Service.

 ```
     public Mono<MovieInfo> updateMovieInfo(MovieInfo updatedMovieInfo, String id) {
        
        return movieInfoRepository.findById(id)
            .flatMap(movieInfo -> {
                movieInfo.setCast(updatedMovieInfo.getCast());
                movieInfo.setName(updatedMovieInfo.getName());
                movieInfo.setRelease_date(updatedMovieInfo.getRelease_date());
                movieInfo.setYear(updatedMovieInfo.getYear());
                return movieInfoRepository.save(movieInfo);
            });
    }

 ```

 - When there is returned reactor type, we need to use `flatMap`.

 - Test.

 ```
     @Test
    void updateMovieInfo() {

        //given
        String movieInfoId = "abc";
        MovieInfo movieInfo = new MovieInfo(null, "Dark Knight Rises1",
                        2005, Arrays.asList("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        //when
        webTestClient
            .put()
            .uri(MOVIES_INFO_URL + "/{id}", movieInfoId)
            .bodyValue(movieInfo)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody(MovieInfo.class)
            .consumeWith(movieInfoEntityExchangeResult -> {

                MovieInfo updatedMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                assert updatedMovieInfo!=null;
                assert updatedMovieInfo.getMovieInfoId()!=null;
                assertEquals("Dark Knight Rises1", updatedMovieInfo.getName());
            });

        //then
    }

 ```

# 58. Build the DELETE endpoint to delete a MovieInfo by ID.

# Controller.

```
    @DeleteMapping("/movieinfos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public Mono<Void> deleteMovieInfoById(@PathVariable String id){
        return moviesInfoService.deleteMovieInfoById(id);

    }
```

- In **Reactor programming** empty type is **Void**.
    - Also it means the content for **Void** is not needed.

# Service.

```
    public Mono<Void> deleteMovieInfo(String id) {

        return movieInfoRepository.deleteById(id);
    }

```

# Assignment 1: Write an Integration Test for Delete Endpoint.

```
Use the same testing techniques that we have been following so far.

Use the delete method in the webTestClient.

Questions for this assignment
    1. Write the Integration Test for Delete Endpoint.
```

- My answer:

```
    @Test
    void deleteMovieInfo() {

        //given
        String movieInfoId = "abc";

        //when
        webTestClient
            .delete()
            .uri(MOVIES_INFO_URL + "/{id}", movieInfoId)
            .exchange()
            .expectStatus()
            .isNoContent();
    }

```

