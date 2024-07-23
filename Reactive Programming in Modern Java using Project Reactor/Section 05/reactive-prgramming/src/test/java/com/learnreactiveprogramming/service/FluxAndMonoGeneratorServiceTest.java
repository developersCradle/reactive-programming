package com.learnreactiveprogramming.service;

import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService =
            new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        //given

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        //then
        StepVerifier.create(namesFlux)
                //.expectNext("alex", "ben","chloe")
                //.expectNextCount(3)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();
    }
    
    @Test
    void namesMono() {
        //given

        //when
        var namesMono = fluxAndMonoGeneratorService.namesMono();

        //then
        StepVerifier.create(namesMono)
                .expectNext("alex")
                .expectNextCount(0)
                .verifyComplete();
    }
    
    @Test
    void namesFlux_map() {
    	//given
    	
    	//when
    	var namesFlux = fluxAndMonoGeneratorService.nameFlux_map();
    	
    	//then
    	
    	StepVerifier.create(namesFlux)
    	.expectNext("ALEX", "BEN", "CHLOE")    
    	.verifyComplete();
    }
    
    @Test
    public void nameFlux_map_with_filter() {
    	
    	//given
    	int stringLenght = 3;
    	
    	//when
    	var namesFlux = fluxAndMonoGeneratorService.nameFlux_map_with_filter(stringLenght);
    	
    	//then
    	StepVerifier.create(namesFlux)
    	.expectNext("4-ALEX","5-CHLOE")    
    	.verifyComplete();
    }

    
    @Test
    void nameFlux_immutability() 
    {
    	//given
    	
    	//when
    	var namesFlux = fluxAndMonoGeneratorService.nameFlux_immutability();
    	//then
    	StepVerifier.create(namesFlux)
    	.expectNext("alex", "ben","chloe")   
    	.verifyComplete();
    }
    
    @Test
    void namesMono_map_filter()
    {
    	
    // given
    int stringLenght = 3;
    	
    // when
    var namesMono = fluxAndMonoGeneratorService.namesMono_map_filter(stringLenght);

    // then
    StepVerifier.create(namesMono)
            .expectNext("ALEX")
            .expectNextCount(0)
            .verifyComplete();
    }
    
    
    
    
    @Test
    void namesFlux_flatmap() {

        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_flatmap(stringLength).log();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();

    }
    
    
    @Test
    void namesFlux_flatmap_async() {
    	
        //given
        int stringLength = 3;
        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_flatmap_async(stringLength).log();
        //then
        StepVerifier.create(namesFlux)
                /*.expectNext("0-A", "1-L", "2-E", "3-X")
                .expectNextCount(5)*/
                .expectNextCount(9)
                .verifyComplete();
    }
    
}
