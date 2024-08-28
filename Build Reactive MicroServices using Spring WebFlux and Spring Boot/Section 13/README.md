# Section 13: Reactive Programming in Mongo DB for the MovieInfo Service.

Reactive Programming in Mongo DB for the MovieInfo Service.

# What I Learned.

# 44. Set up the MovieInfo Document.

<img src="weAreImplementingSuchScenario.PNG " alt="reactive programming" width="600"/>

- The **Entity** in **MongoDB**.

```
package com.reactivespring.domain;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class MovieInfo {

    @Id
    private String movieInfoId;
    @NotBlank(message = "movieInfo.name must be present")
    private String name;
    @NotNull
    @Positive(message = "movieInfo.year must be a Positive value")
    private Integer year;
    private List<@NotBlank(message = "movieInfo.cast must be present") String> cast;
    private LocalDate release_date;
}

```

- **@Document** in MongoDB represents an **Entity**.

# 45. Configure the ReactiveMongoDB Repository for MovieInfo Document.

- We are using Reactive programming with **MongoDB.**

```
package com.reactivespring.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.reactivespring.domain.MovieInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface MovieInfoRepository extends ReactiveMongoRepository<MovieInfo, String> {

    Flux<MovieInfo> findByYear(Integer year);
    Mono<MovieInfo> findOneByName(String name);
}

```

# 46. Configure the Mongo DB server details in the application.yml file.


- We are going use **Spring Boot** auto-configuration feature.
    - We are using **.YAML** file for providing **.properties** for the application.

- **.YAML** can be more powerful than **.properties**, it leverages hierarchy.

- This is one profile with following `---`.

```
---
spring:
    application:
        name: movies-info-service
    profiles:
        active: local
---
```

- We can configure different ports for different production environment.

### application.yaml

```
spring:
    application:
        name: movies-info-service
    profiles:
        active: local
---
spring:
    config:
        activate:
            on-profile:
                - local
    data:
        mongodb:
            host: ${MONGODB_HOST:localhost}
            port: 27017
            database: local
---
spring:
    config:
        activate:
            on-profile:
                - non-prod
    data:
        mongodb:
            host: ${MONGODB_HOST:localhost}
            port: 27017
            database: local
---
spring:
    config:
        activate:
            on-profile:
                - prod
    data:
        mongodb:
            host: ${MONGODB_HOST:localhost}
            port: 27017
            database: local
```

- Other way to name environments **.yaml** `environment-prod.yml`. These are `.yaml` based for environments.

# 47. Setup the Integration Test using @DataMongoTest.

- We are going to spin up **embedded mongoDB**, before start of **integration tests**.
    - We don't need to install **mongoDB** separately.

-  We are using `@DataMongoTest` to tell integration tests for use **repository** classes. We don't need setup whole **Spring contexts**.

- `@ActiveProfiles("test")` its important to have profile where the **mongoDB** integration test will pick up the usernames and passwords.

```
@DataMongoTest
@ActiveProfiles("test")
class MovieInfoRepositoryIntgTest {

    @Autowired
    MovieInfoRepository movieInfoRepository;

}
```

# 48. Write Integration Test for findAll() MovieInfo Document

```
package com.reactivespring.repository;

import com.reactivespring.domain.MovieInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ActiveProfiles("test")
class MovieInfoRepositoryIntgTest {

    @Autowired
    MovieInfoRepository movieInfoRepository;

    @BeforeEach
    void setUp() {
        var movieinfos = List.of(new MovieInfo(null, "Batman Begins",
                        2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15")),
                new MovieInfo(null, "The Dark Knight",
                        2008, List.of("Christian Bale", "HeathLedger"), LocalDate.parse("2008-07-18")),
                new MovieInfo("abc", "Dark Knight Rises",
                        2012, List.of("Christian Bale", "Tom Hardy"), LocalDate.parse("2012-07-20")));

        movieInfoRepository.saveAll(movieinfos)
                .blockLast();
    }

    @AfterEach
    void tearDown() {
        movieInfoRepository.deleteAll().block();
    }

    @Test
    void findAll() {
        //given

        //when
        var moviesInfoFlux = movieInfoRepository.findAll().log();

        //then
        StepVerifier.create(moviesInfoFlux)
                .expectNextCount(3)
                .verifyComplete();
    }
}
```

- We are saving these entries to mongoDB with following.
    
```

        movieInfoRepository.saveAll(movieinfos)
                .blockLast();
```

- `.blockLast();` will these wait for these to happen before proceeding, because nature of asynchronous. This is only in **test** cases.

# 49. Write Integration Test for findById() MovieInfo Document

- Find by id integration test.

```

    @Test
    void findById() {
        //given

        //when
        var moviesInfoMono = movieInfoRepository.findById("abc").log();

        //then
        StepVerifier.create(moviesInfoMono)
                //.expectNextCount(1)
                .assertNext(movieInfo -> {
                    assertEquals("Dark Knight Rises", movieInfo.getName());
                })
                .verifyComplete();
    }
```

# 50. Write Integration Test for saving the MovieInfo Document

- Saving new document to repository.

```

    @Test
    void saveMovieinfo() {
        //given
        var movieInfo = new MovieInfo(null, "Batman Begins1",
                2005, List.of("Christian Bale", "Michael Cane"), LocalDate.parse("2005-06-15"));

        //when
        var moviesInfoMono = movieInfoRepository.save(movieInfo).log();

        //then
        StepVerifier.create(moviesInfoMono)
                //.expectNextCount(1)
                .assertNext(movieInfo1 -> {
                    assertNotNull(movieInfo1.getMovieInfoId());
                    assertEquals("Batman Begins1", movieInfo1.getName());
                })
                .verifyComplete();
    }

```

# 51. Write Integration Test for updating the MovieInfo Document

- Update year on Document.

```

    @Test
    void updateMovieInfo() {
        //given
        var movieInfo = movieInfoRepository.findById("abc").block();
        movieInfo.setYear(2021);

        //when
        var moviesInfoMono = movieInfoRepository.save(movieInfo).log();

        //then
        StepVerifier.create(moviesInfoMono)
                //.expectNextCount(1)
                .assertNext(movieInfo1 -> {

                    assertEquals(2021, movieInfo1.getYear());
                })
                .verifyComplete();
    }

```

# 52. Write Integration Test for deleting the MovieInfo Document


```

    @Test
    void deleteMovieInfo() {
        //given

        //when
        movieInfoRepository.deleteById("abc").block();
        var moviesInfoFlux = movieInfoRepository.findAll().log();

        //then
        StepVerifier.create(moviesInfoFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
```