# Section 15: Unit Testing in Spring WebFlux

Section 15: Unit Testing in Spring WebFlux.

# What I Learned

# 59. Setting up the UnitTest in WebFlux.

<img src="unitTests.PNG " alt="reactive programming" width="600"/>

1. Our test will be like this.

<img src="benifitsOfUnitTests.PNG " alt="reactive programming" width="600"/>

- Unit tests.
    - Are handy for **Bean validation**.
    - Faster than Integration Tests.
    - Necessary for any serious application.

- When using **Mockito** we would use something following.
    - `@Mock` and `@InjectMocks`.
``` 

    @Mock
    ReviewService reviewService;

    @InjectMocks
    MovieReactiveService reactiveMovieService;

```

- In **Spring**, `@MockBean` loads into Spring context. Use this if need Spring context!  
    - `@MockBean` **ís not** `@Mock.`

```

 @MockBean
private MoviesInfoService moviesInfoServiceMock;

```

- Test after this.

```

@WebFluxTest(controllers = MoviesInfoController.class)
@AutoConfigureWebTestClient
public class MoviesInfoControllerUnitTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MoviesInfoService moviesInfoServiceMock;
}

```

 # 60. Unit Test for GetAllMovieInfos endpoint - GET

- One benefit of this that we don't need embedded mongoDB, when mocking.

```
package com.reactivespring.controller;

import com.reactivespring.domain.MovieInfo;
import com.reactivespring.service.MoviesInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = MoviesInfoController.class)
@AutoConfigureWebTestClient
public class MoviesInfoControllerUnitTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MoviesInfoService moviesInfoServiceMock;

    static String MOVIES_INFO_URL = "/v1/movieinfos";

    @Test
    void getAllMoviesInfo() {

        var movieinfos = List.of(new MovieInfo(null, "Batman Begins",
                        2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("abc", "Dark Knight Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20")));

        when(moviesInfoServiceMock.getAllMovieInfos()).thenReturn(Flux.fromIterable(movieinfos));

        webTestClient
                .get()
                .uri(MOVIES_INFO_URL)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(MovieInfo.class)
                .hasSize(3);
    }

    @Test
    void addMovieInfo() {
        //given
        var movieInfo = new MovieInfo(null, "Batman Begins1",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        when(moviesInfoServiceMock.addMovieInfo(isA(MovieInfo.class))).thenReturn(
                Mono.just(new MovieInfo("mockId", "Batman Begins1",
                        2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")))
        );

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
                    assertEquals("mockId",savedMovieInfo.getMovieInfoId());
                });


        //then
    }

    @Test
    void updateMovieInfo() {
        //given
        var movieInfoId = "abc";
        var movieInfo = new MovieInfo(null, "Dark Knight Rises1",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        when(moviesInfoServiceMock.updateMovieInfo(isA(MovieInfo.class), isA(String.class))).thenReturn(
                Mono.just(new MovieInfo(movieInfoId, "Dark Knight Rises1",
                        2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")))
        );

        //when
        webTestClient
                .put()
                .uri(MOVIES_INFO_URL+"/{id}", movieInfoId)
                .bodyValue(movieInfo)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(MovieInfo.class)
                .consumeWith(movieInfoEntityExchangeResult -> {
                    var updatedMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                    assert updatedMovieInfo!=null;
                    assert updatedMovieInfo.getMovieInfoId()!=null;
                    assertEquals("Dark Knight Rises1", updatedMovieInfo.getName());
                });


        //then
    }
}

```

# Assignment 2: Unit Test for getMovieInfoById

```
Follow the same patterns as the other one unit test that we wrote for getAllMovieInfos

Questions for this assignment
    1.Write the Unit Test for getMovieInfoById
```

- My answer:

```
    @Test
    void getMovieInfoById() {
        
        String movieInfoId = "abc";
        MovieInfo movieInfo = new MovieInfo("abc", "Dark Knight Rises", 2012, Arrays.asList("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20"));

        when(moviesInfoServiceMock.getMovieInfoById(movieInfoId)).thenReturn(Mono.just(movieInfo));

        webTestClient
            .get()
            .uri(MOVIES_INFO_URL + "/{id}", movieInfoId)
            .exchange()
            .expectStatus()
            .is2xxSuccessful()
            .expectBody()
            .jsonPath("$.name").isEqualTo("Dark Knight Rises");
    }

```

 # 61. Unit Test for the create MovieInfo Endpoint - POST
 
 - Mock and test for this method.

 ```
     @Test
    void addMovieInfo() {

        //given
        MovieInfo movieInfo = new MovieInfo(null, "Batman Begins1",
            2005, Arrays.asList("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        when(moviesInfoServiceMock.addMovieInfo(isA(MovieInfo.class))).thenReturn(
            Mono.just(new MovieInfo("mockId", "Batman Begins1",
                2005, Arrays.asList("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")))
        );

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

                MovieInfo savedMovieInfo = movieInfoEntityExchangeResult.getResponseBody();
                assert savedMovieInfo!=null;
                assert savedMovieInfo.getMovieInfoId()!=null;
                assertEquals("mockId", savedMovieInfo.getMovieInfoId());
            });

        //then
    }

 ```

# 62. Unit Test for the update MovieInfo Endpoint - PUT

- Mock and test for this method.

```
  
    @Test
    void updateMovieInfo() {

        //given
        String moveiInfoId = "abc";
        MovieInfo movieInfo = new MovieInfo(null, "Dark Knight Rises1",
            2005, Arrays.asList("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        when(moviesInfoServiceMock.updateMovieInfo(isA(MovieInfo.class), isA(String.class))).thenReturn(
            Mono.just(new MovieInfo(moveiInfoId, "Dark Knight Rises1",
                2005, Arrays.asList("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")))
        );

        //when
        webTestClient
            .put()
            .uri(MOVIES_INFO_URL + "/{id}", moveiInfoId)
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

# Assignment 3: Assignment : Unit Test for Delete Movie Info

```
Use the same pattern as the other Unit tests and mock the deleteMovieInfo method call in the MoviesInfoService

Questions for this assignment
    1. Write the Unit test for the deleteMovieInfoById in the MoviesInfoController class.
```

```

    @Test
    void deleteMovieInfo() {

        //given
        String movieInfoId = "abc";

        when(moviesInfoServiceMock.deleteMovieInfo(isA(String.class))).thenReturn(Mono.empty());

        //when
        webTestClient
            .delete()
            .uri(MOVIES_INFO_URL + "/{id}", movieInfoId)
            .exchange()
            .expectStatus()
            .isNoContent();
    }

```