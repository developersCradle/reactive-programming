# Section 12: Exception/Error Handling in Flux and Mono

Exception/Error Handling in Flux and Mono

# What I Learned

# 36. Exceptions in Reactive Streams

<img src="expectioThrowing.PNG" alt="reactive programming" width="600"/>

- We are going to assume exception throwing is happing from such scenario.

- When **Exceptions** are thrown, we are not getting eny other event from the flux.

- Logic itself:

```
    public Flux<String> exception_flux() {

        var flux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                		.concatWith(Flux.just("D"));
     
        return flux;

    }

```

- Test itself:

```
    @Test
    void exception_flux() {

        //given

        //when
        var flux = fluxAndMonoGeneratorService.exception_flux();

        //then
        StepVerifier.create(flux)
                .expectNext("A", "B", "C")
                .expectError(RuntimeException.class)
                .verify(); // you cannot do a verify complete in here

    }
    
```

<img src="expectioThrowingInFlux.PNG" alt="reactive programming" width="600"/>

1. As you can see the the `D` us not coming from **flux**, since after exception flux is finished.
    - Notice, that we will use `.verify();` not `.verifyComplete();`, since there will be no complete message coming after exceptions.

- The other way write `@Test` for exception handling.

```
    # We don't need to provide the executions class.

    @Test
    void exception_flux_1() {

        //given

        //when
        var flux = fluxAndMonoGeneratorService.exception_flux();

        //then
        StepVerifier.create(flux)
                .expectNext("A", "B", "C")
                .expectError()
                .verify(); // you cannot do a verify complete in here

    }

# Or we can specify what kind of exception is being expected.


    @Test
    void exception_flux_2() {

        //given

        //when
        var flux = fluxAndMonoGeneratorService.exception_flux();

        //then
        StepVerifier.create(flux)
                .expectNext("A", "B", "C")
                .expectErrorMessage("Exception Occurred")
                .verify(); // you cannot do a verify complete in here

    }

```

<img src="AnyExpectionsWillEndTheStream.PNG" alt="reactive programming" width="600"/>

# 37. Introduction to Exception Handling Operators

<img src="expectionHandlingInProjectReactor.PNG" alt="reactive programming" width="600"/>

1. There is two main ways to handle exceptions:
    - **Category 1**: **Recovering**
    - **Category 2**: **Taking action**, this is something like throwing exception in traditional way.

<img src="expectionCategories.PNG" alt="reactive programming" width="600"/>

# 38. onErrorReturn() : Exception Handling Operator

<img src="onErrorReturn.PNG" alt="reactive programming" width="700"/>

1. We can return **default value** for fallback method. This can be useful for **fault tolerance**.

- This is most **simplest** way how to recovery form exceptions.

- The Logic:

```
    public Flux<String> explore_OnErrorReturn() {

        var flux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IllegalStateException("Exception Occurred")))
                .onErrorReturn("D"); // defualt value

        return flux;

    }

```

- Returning default exception `.onErrorReturn("D"); // defualt value`.

- The Test:

```
    @Test
    void explore_OnErrorReturn() {

        //given

        //when
        var flux = fluxAndMonoGeneratorService.explore_OnErrorReturn().log();

        //then
        StepVerifier.create(flux)
                .expectNext("A", "B", "C", "D")
                .verifyComplete();

    }
```

- This is for if you have **remote** service and you wish to call fall back service and deal with exceptions.

# 39. onErrorResume() : Exception Handling Operator


- `onErrorResume()` we can handle errors, how we want. We return **recovery flux**  with implement conditional recovery.

- The Logic:

```
    public Flux<String> explore_OnErrorResume(Exception e) {

        var recoveryFlux = Flux.just("D", "E", "F");

        var flux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(e))
                .onErrorResume((exception) -> {
                    log.error("Exception is ", exception);
                    if (exception instanceof IllegalStateException)
                        return recoveryFlux; // We go with the recovery flux
                    else
                        return Flux.error(exception); // We throw error back
                });

        return flux;

    }
```

- The test:

```

    @Test
    void explore_OnErrorResume() {

        //given
        var e = new IllegalStateException("Not a valid state");

        //when
        var flux = fluxAndMonoGeneratorService.explore_OnErrorResume(e).log();

        //then
        StepVerifier.create(flux)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();

    }

    @Test
    void explore_OnErrorResume_1() {

        //given
        var e = new RuntimeException("Not a valid state");

        //when
        var flux = fluxAndMonoGeneratorService.explore_OnErrorResume(e).log();

        //then
        StepVerifier.create(flux)
                .expectNext("A", "B", "C")
                .expectError(RuntimeException.class)
                .verify();

    }
```

<img src="expectionsAreRecovering.PNG" alt="reactive programming" width="600"/>

1. You can ee that flux is coming normally.
2. After exaction is noticed, `.onErrorResume` then recovery **flux** is being supplied. 

# 40. onErrorContinue() : Exception Handling Operator

<img src="onErrorContinue.PNG" alt="reactive programming" width="700"/>

1. This one drops the exception and continues the emitting. Normally if **flux** fail, whole stream fails. With this one we can continue the **Stream**.

- The Logic:

```
    public Flux<String> explore_OnErrorContinue() {

        var flux = Flux.just("A", "B", "C")
                .map(name -> {
                    if (name.equals("B")) {
                        throw new IllegalStateException("Exception Occurred");
                    }
                    return name;
                })
                .concatWith(Flux.just("D"))
                .onErrorContinue((exception, value) -> {
                    System.out.println("Value is : " + value);
                    System.out.println("Exception is : " + exception.getMessage());
                });


        return flux;

    }
```

- The Test:

```
```