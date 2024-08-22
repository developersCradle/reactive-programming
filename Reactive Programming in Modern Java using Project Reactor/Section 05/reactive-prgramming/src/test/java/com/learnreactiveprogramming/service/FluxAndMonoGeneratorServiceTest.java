package com.learnreactiveprogramming.service;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.learnreactiveprogramming.exception.ReactorException;

import lombok.var;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
    void explore_zip() {
    	//given
    	
    	//when
    	var value = fluxAndMonoGeneratorService.explore_zip();
    	
    	//then
    	
    	StepVerifier.create(value)
    	.expectNext("AD", "BE", "CF")    
    	.verifyComplete();
    }
    
    @Test
    void explore_zip_1() {
    	//given
    	
    	//when
    	var value = fluxAndMonoGeneratorService.explore_zip_1();
    	
    	//then
    	
    	StepVerifier.create(value)
    	.expectNext("AD14", "BE25", "CF36")    
    	.verifyComplete();
    }
    


    @Test
    void namesFlux_map_withDoOn() {

        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_map(stringLength).log();

        //then
        StepVerifier.create(namesFlux)
                //.expectNext("ALEX", "BEN", "CHLOE")
                .expectNext("4-ALEX", "5-CHLOE")
                .verifyComplete();
    }

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
    void namesMono_flatmap() {

        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesMono_flatmap(stringLength).log();

        //then
        StepVerifier.create(namesFlux)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();

    }
    

    @Test
    void namesMono_flatmapMany() {

        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesMono_flatmapMany(stringLength).log();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }
    
    @Test
    void namesFlux_transform() {

        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_transform(stringLength).log();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X")
                .expectNextCount(5)
                .verifyComplete();
    }


    @Test
    void namesFlux_transform_1() {

        //given
        int stringLength = 6;

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_transform(stringLength).log();

        
//       Flux.empty() this represents empty value.
        //then
        StepVerifier.create(namesFlux)
        		.expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
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

    @Test
    void explore_zipWith() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_zipWith();

        //then
        StepVerifier.create(value)
                .expectNext("AD","BE","CF")
                .verifyComplete();
    }

    @Test
    void explore_ZipWith_mono(){

    	//given

        //when
        var value = fluxAndMonoGeneratorService.explore_ZipWith_mono();

        //then
        StepVerifier.create(value)
                .expectNext("AB")
                .verifyComplete();

    }

    
    
    @Test
    void explore_merge() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_merge();

        //then
        StepVerifier.create(value)
                .expectNext("A", "D", "B","E", "C", "F")
                .verifyComplete();

    }


    
    @Test
    void explore_mergeWith() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_mergeWith();

        //then
        StepVerifier.create(value)

                .expectNext("A", "D", "B", "E", "C", "F")
                .verifyComplete();

    }

    
    @Test
    void explore_mergeSequential() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_mergeSequential();

        //then
        StepVerifier.create(value)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    
    @Test
    void explore_mergeWith_mono() {

        //given

        //when
        var value = fluxAndMonoGeneratorService.explore_mergeWith_mono();

        //then
        StepVerifier.create(value)
                .expectNext("A", "B")
                .verifyComplete();

    }
    
    
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
    
    @Test
    void namesFlux_concatMap() {

        //given
        int stringLength = 3;

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux_concatmap(stringLength).log();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X")
                //expectNext("0-A", "1-L", "2-E", "3-X")
                .expectNextCount(5)
                .verifyComplete();

    }
    
    @Test
    void explore_OnErrorContinue() {

        //given

        //when
        var flux = fluxAndMonoGeneratorService.explore_OnErrorContinue().log();

        //then
        StepVerifier.create(flux)
                .expectNext("A", "C", "D")
                .verifyComplete();

    }

    
    @Test
    void explore_OnErrorMap() {

        //given
        var e = new RuntimeException("Not a valid state");

        //when
        var flux = fluxAndMonoGeneratorService.explore_OnErrorMap(e)
                .log();

        //then
        StepVerifier.create(flux)
                .expectNext("A")
                .expectError(ReactorException.class)
                .verify();
    }
    
    
    @Test
    void exception_mono_onErrorReturn() {

        //given


        //when
        var mono = fluxAndMonoGeneratorService.exception_mono_onErrorReturn();

        //then
        StepVerifier.create(mono)
                .expectNext("abc")
                .verifyComplete();
    }
    
    @Test
    void exception_mono_onErrorContinue() {

        //given
        var input = "abc";

        //when
        var mono = fluxAndMonoGeneratorService.exception_mono_onErrorContinue(input);

        //then
        StepVerifier.create(mono)
                .verifyComplete();
    }
    
    @Test
    void exception_mono_onErrorContinue_1() {

        //given
        var input = "reactor";

        //when
        var mono = fluxAndMonoGeneratorService.exception_mono_onErrorContinue(input);

        //then
        StepVerifier.create(mono)
                .expectNext(input)
                .verifyComplete();
    }
    @Test
    void doOnError() {

        //given
        var e = new RuntimeException("Not a valid state");

        //when
        var flux = fluxAndMonoGeneratorService.explore_doOnError(e);

        //then
        StepVerifier.create(flux)
                .expectNext("A", "B", "C")
                .expectError(RuntimeException.class)
                .verify();


    }
}
