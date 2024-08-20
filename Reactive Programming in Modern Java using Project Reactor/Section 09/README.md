# Section 9: Combining Flux and Mono

Combining Flux and Mono

# What I Learned

# 27. Introduction to Combining Reactive Streams

<img src="whyCombining.PNG" alt="reactive programming" width="600"/>

1. We need combine results from multiple sources. This is ver common in **microservice architecture**.
2. These are combined for result. **Project Reactor** has many operations for combining.


# 28. concat() & concatWith() operator

<img src="concat.PNG" alt="reactive programming" width="600"/>

1. We can combine **fluxes** or **monos** into one stream.   

<br>

<img src="concatAndConcatWith.PNG" alt="reactive programming" width="600"/>

1.  These happen in **sequence**, meaning **first** one finishes and after this one second starts.
2. Mono does not need this.

- Difference between these are, other one is instance method.

- Logic with concat().

````
    // "A", "B", "C", "D", "E", "F"
    public Flux<String> explore_concat() {

        var abcFlux = Flux.just("A", "B", "C");

        var defFlux = Flux.just("D", "E", "F");

        return Flux.concat(abcFlux, defFlux).log();

    }
 
    // "A", "B", "C", "D", "E", "F"
    public Flux<String> explore_concatWith() {

        var abcFlux = Flux.just("A", "B", "C");

        var defFlux = Flux.just("D", "E", "F");

        return abcFlux.concatWith(defFlux).log();


    }
````


- And tests for these.

````
    
    @Test
    void explore_concat() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_concat();

        //then
        StepVerifier.create(value)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();

    }
    

    @Test
    void explore_concatWith() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_concatWith();

        //then
        StepVerifier.create(value)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();

    }

````

- We can combine results to one. This is common use case.

- Logic for making combination.

````  

    public Flux<String> explore_concatWith_mono() {

        var aMono = Mono.just("A");

        var bMono = Flux.just("B");

        return aMono.concatWith(bMono);

    }

````

- Test for this.

````

    @Test
    void explore_concat_mono() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_concatWith_mono();

        //then
        StepVerifier.create(value)
                .expectNext("A", "B")
                .verifyComplete();

    }    @Test
    void explore_concat_mono() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_concatWith_mono();

        //then
        StepVerifier.create(value)
                .expectNext("A", "B")
                .verifyComplete();

    }

````


# Assignment 4: Assignment for Writing JUnit5 Test Cases for concatWith() using Flux and Mono


```

Write the test case for explore_concatwith() and explore_concatwith_mono() methods in the FluxAndMonoGeneratorService class.



Questions for this assignment
    1. Write the test case for explore_concatwith() method in  FluxAndMonoGeneratorService class.



    2. Write the test case for explore_concatwith_mono() method in FluxAndMonoGeneratorService class.

```

- My answer:

````

    @Test
    void explore_concat_mono() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_concatWith_mono();

        //then
        StepVerifier.create(value)
                .expectNext("A", "B")
                .verifyComplete();

    }


        @Test
    void explore_concatWith() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_concatWith();

        //then
        StepVerifier.create(value)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();

    }

````

# 29. Combining Reactive Streams using merge() and mergeWith() Operators

- Todo

# 31. Combining Reactive Streams using zip and zipWith() Operator

- `zip()` is used to zip multiple publishers into one.

<img src="zip.PNG" alt="reactive programming" width="600"/>

1. Source **Flux**.
2. Combinator lambda.

- First one will be `AD` second `BE` third one `CF`.

- Difference with `zip()` is that, first emitting is waited for all element then its moved to second element.

<img src="zip2.PNG" alt="reactive programming" width="600"/>

<br>

<img src="zip3.PNG" alt="reactive programming" width="600"/>

- todo 2:15.