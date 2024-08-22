package com.learnreactiveprogramming.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.learnreactiveprogramming.domain.Movie;
import com.learnreactiveprogramming.exception.ReactorException;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {

        return Flux.fromIterable(List.of("alex", "ben","chloe"))
        		.log(); // db or a remote service call
        // Project Reactor library provides .log() to peek trough events between Publisher and Subscriber. 
    }

    public Mono<String> namesMono(){

        return Mono.just("alex");
    }

    public Flux<String> nameFlux_map() {
    	return Flux.fromIterable(List.of("alex", "ben", "chloe"))
//    			.map(String::toUpperCase)  // We are transforming data to UPPER CASE.
    			.map(s -> s.toUpperCase()) // Same using Lambda.
    			.log();
    }


    public Flux<String> nameFlux_map_with_filter(int stringLenght) {
    	return Flux.fromIterable(List.of("alex", "ben", "chloe"))
//    			.map(String::toUpperCase)  // We are transforming data to UPPER CASE.
    			.map(s -> s.toUpperCase()) // Same using Lambda.
    			.filter(s -> s.length() > stringLenght)
    			.map(s -> s.length() + "-" + s)
    			.log();
    }

    
    public static void main(String[] args) {

        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> {
                    System.out.println("Name is : " + name);
                });

        fluxAndMonoGeneratorService.namesMono()
                .subscribe(name->{
                    System.out.println("Mono name is : " + name);
                });

    }
    
    
    public Flux<String> nameFlux_immutability() {
    	var nameFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
    	nameFlux.map(String::toUpperCase);
    	return nameFlux;
    }

    
    public Mono<String> namesMono_map_filter(int stringLength) {
		return Mono.just("alex")
		.map(String::toUpperCase)
		.filter(s -> s.length() > stringLength);
		
	}
    
    public Mono<List<String>> namesMono_flatmap(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono); //Mono<List of A, L, E  X>
    }

    public Flux<String> namesMono_flatmapMany(int stringLength) {
        return Mono.just("alex")
                //.map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .flatMapMany(this::splitString_withDelay);
    }

    
    

    public Flux<String> namesFlux_transform(int stringLength) {

        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength);

        var namesList = List.of("alex", "ben", "chloe"); // a, l, e , x
        return Flux.fromIterable(namesList)
                .transform(filterMap) // gives u the opportunity to combine multiple operations using a single call.
                .flatMap(this::splitString)
                .defaultIfEmpty("default");
        //using "map" would give the return type as Flux<Flux<String>

    }


    public Flux<String> namesFlux_transform_switchifEmpty(int stringLength) {

        Function<Flux<String>, Flux<String>> filterMap = name -> name.map(String::toUpperCase)
                .filter(s -> s.length() > stringLength);

        
        var namesList = List.of("alex", "ben", "chloe"); // a, l, e , x

        Flux.just("default")
        .transform(filterMap);
        // todo jäin 4:30
        return Flux.fromIterable(namesList)
                .transform(filterMap) // gives u the opportunity to combine multiple operations using a single call.
                .flatMap(this::splitString)
                .switchIfEmpty();
        //using "map" would give the return type as Flux<Flux<String>

    }
    public Flux<String> explore_merge() {

        var abcFlux = Flux.just("A", "B", "C")
        		.delayElements(Duration.ofMillis(100));

        var defFlux = Flux.just("D", "E", "F")
        		.delayElements(Duration.ofMillis(120));;

        return Flux.merge(abcFlux, defFlux).log();

    }
 
    public Flux<String> explore_mergeWith() {

        var abcFlux = Flux.just("A", "B", "C")
        		.delayElements(Duration.ofMillis(100));

        var defFlux = Flux.just("D", "E", "F")
        		.delayElements(Duration.ofMillis(120));;

        return abcFlux.mergeWith(defFlux).log(); // Yields same result as merge()

    }
    
    public Flux<String> explore_mergeSequential() {

        var abcFlux = Flux.just("A", "B", "C")
        		.delayElements(Duration.ofMillis(100));

        var defFlux = Flux.just("D", "E", "F")
        		.delayElements(Duration.ofMillis(120));;

        return Flux.mergeSequential(abcFlux, defFlux).log(); // Yields same result as merge()

    }
    
    public Flux<String> explore_mergeWith_mono() {

        var aMono = Mono.just("A");

        var bMono = Flux.just("B");

        return aMono.mergeWith(bMono).log();

    }
 
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

    
    public Flux<String> explore_concatWith_mono() {

        var aMono = Mono.just("A");

        var bMono = Flux.just("B");

        return aMono.concatWith(bMono);

    }

    /**
    * @param stringLength
    */
    public Flux<String> namesFlux_flatmap(int stringLength) {
        var namesList = List.of("alex", "ben", "chloe"); // a, l, e , x
        return Flux.fromIterable(namesList)
                //.map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                // ALEX,CHLOE -> A, L, E, X, C, H, L , O, E
                .flatMap(s -> splitString(s));
    }
    
    

    public Flux<String> exception_flux() {

        var flux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                		.concatWith(Flux.just("D"));
     
        return flux;

    }
    

    /**
     * This provides a single fallback value
     *
     * @return
     */
    public Flux<String> explore_OnErrorReturn() {

        var flux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(new IllegalStateException("Exception Occurred")))
                .onErrorReturn("D"); // defualt value

        return flux;

    }
    /**
     * This provides a fallback value as a Reactive Stream
     *
     * @param e
     * @return
     */
    public Flux<String> explore_OnErrorResume(Exception e) {

        var recoveryFlux = Flux.just("D", "E", "F");

        var flux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(e))
                .onErrorResume((exception) -> {
                    log.error("Exception is ", exception);
                    if (exception instanceof IllegalStateException)
                        return recoveryFlux;
                    else
                        return Flux.error(exception);
                });

        return flux;

    }
    
    public Flux<String> explore_zip(){

        var abcFlux = Flux.just("A","B","C");

        var defFlux = Flux.just("D","E","F");

        return Flux.zip(abcFlux,defFlux,(first, second)-> first+second)
                .log(); //AD, BE, CF

    }

    public Flux<String> explore_zipWith(){

        var abcFlux = Flux.just("A","B","C");

        var defFlux = Flux.just("D","E","F");
        
        return abcFlux.zipWith(defFlux, (first, second) -> first+second).log();
        // return will be AD, BE, CF

    }

    public Flux<String> explore_zip_1(){

        var abcFlux = Flux.just("A","B","C");

        var defFlux = Flux.just("D","E","F");
        
        var _123Flux = Flux.just("1","2","3");
        var _456Flux = Flux.just("4","5","6");

        return Flux.zip(abcFlux,defFlux, _123Flux, _456Flux)
        		.map(tuple4 -> tuple4.getT1()+tuple4.getT2()+tuple4.getT3()+tuple4.getT4())
                .log(); 
        
        // Results will be AD14, BE25, CF36

    }
    

    
    public Mono<String> explore_ZipWith_mono(){

        var aMono = Mono.just("A"); //A

        var bMono = Mono.just("B"); //B

        return aMono.zipWith(bMono)
                .map(t2-> t2.getT1()+t2.getT2()) //AB
                .log(); // A, B

    }

    
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
    
    /**
     * Used to tranform the error from one type to another
     *
     * @param e
     * @return
     */
    public Flux<String> explore_OnErrorMap(Exception e) {

        var flux = Flux.just("A", "B", "C")
                .map(name -> {
                    if (name.equals("B")) {
                        throw new IllegalStateException("Exception Occurred");
                    }
                    return name;
                })
                .onErrorMap((exception) -> {
                    // log.error("Exception is : " , exception);
                    // difference between errorResume and this one is that you dont need to add
                    // Flux.error() to throw the exception
                    return new ReactorException(exception, exception.getMessage());
                });

        return flux;

    }
    

    public Flux<String> explore_doOnError(Exception e) {

        var flux = Flux.just("A", "B", "C")
                .concatWith(Flux.error(e))
                .doOnError((exception) -> {
                    System.out.println("Exception is : " + e);
                    //Write any logic you would like to perform when an exception happens
                });

        return flux;

    }

    
    /**
     * This operator can be used to provide a default value when an error occurs
     *
     * @return
     */
    public Mono<Object> exception_mono_onErrorReturn() {

        return Mono.just("B")
                .map(value -> {
                    throw new RuntimeException("Exception Occurred");
                }).onErrorReturn("abc");
    }



    public Mono<String> exception_mono_onErrorContinue(String input) {
        return Mono.just(input).
                map(data -> {
                    if (data.equals("abc"))
                        throw new RuntimeException("Exception Occurred");
                    else
                        return data;
                }).
                onErrorContinue((ex, val) -> {
                    log.error("Exception is " + ex);
                    log.error("Value that caused the exception is " + val);

                });
    	
    }
    

    public Flux<String> namesFlux_map(int stringLength) {
        var namesList = List.of("alex", "ben", "chloe");
        //return Flux.just("alex", "ben", "chloe");

        //Flux.empty()
        return Flux.fromIterable(namesList)
                //.map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .delayElements(Duration.ofMillis(500))
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length() + "-" + s)
                .doOnNext(name -> {
                    System.out.println("name is : " + name);
                    name = name.toLowerCase();
                })
                .doOnSubscribe(s -> {
                    System.out.println("Subscription  is : " + s);
                })
                .doOnComplete(() -> {
                    System.out.println("Completed sending all the items.");
                })
                .doFinally((signalType) -> {
                    System.out.println("value is : " + signalType);
                })
                .defaultIfEmpty("default");
    }

    
    /***
     * ALEX -> FLux(A,L,E,X)
     * @param name
     * @return
     */
    private Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }
    
    private Flux<String> splitString_withDelay(String name) {
        var delay = new Random().nextInt(1000);
        var charArray = name.split("");
        return Flux.fromArray(charArray)
                .delayElements(Duration.ofMillis(delay));
    }
    
    private Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        return Mono.just(List.of(charArray))
                .delayElement(Duration.ofSeconds(1));
    }
    
    public Flux<String> namesFlux_flatmap_async(int stringLength) {
        var namesList = List.of("alex", "ben", "chloe"); // a, l, e , x
        return Flux.fromIterable(namesList)
                //.map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitString_withDelay);
    }

    
    public Flux<String> namesFlux_concatmap(int stringLength) {
        var namesList = List.of("alex", "ben", "chloe"); // a, l, e , x
        return Flux.fromIterable(namesList)
                //.map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                //.flatMap((name)-> splitString(name));
                .concatMap(this::splitString_withDelay);
    }

    
 
 
     
}