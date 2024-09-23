# Section 16: Bean Validation using Validators and ControllerAdvice

Bean Validation using Validators and ControllerAdvice.

# What I Learned

# 63. Bean Validation for Name,Year with @NotBlank and @Positive Annotation Validators.

- For Spring bean validation we need following POM. **spring-boot-starter-validation**.

```
<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

- We are going apply bean validation for **domain**.

- We are marking the data class for **bean validations**.

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

- We need add `@Valid` to the endpoint, as example.

```
    @PostMapping("/movieinfos")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieInfo> addMovieInfo(@RequestBody @Valid MovieInfo movieInfo) {
        
        return moviesInfoService.addMovieInfo(movieInfo)
            .doOnNext(savedInfo -> moviesInfoSink.tryEmitNext(savedInfo));
    }

```

- The test for this case.

```

    @Test
    void addMovieInfo_validation() {

        //given
        MovieInfo movieInfo = new MovieInfo(null, "",
            -2005, Arrays.asList(""), LocalDate.parse("2005-06-15"));

        //when
        webTestClient
            .post()
            .uri(MOVIES_INFO_URL)
            .bodyValue(movieInfo)
            .exchange()
            .expectStatus()
            .isBadRequest()
            .expectBody(String.class)
            .consumeWith(stringEntityExchangeResult -> {

                String responseBody = stringEntityExchangeResult.getResponseBody();
                String expectedErrorMessage = "movieInfo.cast must be present,movieInfo.name must be present,movieInfo.year must be a Positive value";
                assertEquals(expectedErrorMessage, responseBody);
            });

        //then
    }

```

# 64. Customize the Default Error handling using ControllerAdvice

- When doing **Bean Validation**, we would wan't to have proper error massages that have been implemented in domain class, not some random error message.
    - This approach to send clear error for customer.

- `ControllerAdvice` approach is one way to handle exceptions.
    - Is a specialized type of component in Spring that allows you to define global exception handling logic for multiple controllers in a single place.
 

```
@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {
    
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleResponseBodyError(WebExchangeBindException ex) {

        log.error("Exception Caught in handleRequestBodyError : {} ", ex.getMessage(), ex);
        String error = ex.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .sorted()
            .collect(Collectors.joining(","));
        log.error("Error is : {} ", error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
```

- `@ExceptionHandler(WebExchangeBindException.class)` these exceptions are catches here.

- We need to extract the errors, it can be possible that multiple fields have different error messages.

# 65. Bean Validation for List Field using @NotBlank Annotation

- List is having **bean validator** inside.
`private List<@NotBlank(message = "movieInfo.cast must be present") String> cast;`
