package com.learnreactiveprogramming.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import lombok.var;

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