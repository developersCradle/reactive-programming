package com.learnreactiveprogramming;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

import static com.learnreactiveprogramming.util.CommonUtil.delay;
import static reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST;

public class ColdAndHotPublisherTest {

    @Test
    public void coldPublisherTest() throws InterruptedException {

        var flux = Flux.range(1, 10);

        flux.subscribe(s -> System.out.println("Subscriber 1 : " + s)); //emits the value from beginning
        flux.subscribe(s -> System.out.println("Subscriber 2 : " + s));//emits the value from beginning
    }

    @Test
    public void hotPublisherTest() throws InterruptedException {

        Flux<Integer> stringFlux = Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1));

        ConnectableFlux<Integer> connectableFlux = stringFlux.publish();
        connectableFlux.connect();
        Thread.sleep(3000);
        connectableFlux.subscribe(s -> System.out.println("Subscriber 1 : " + s));
        Thread.sleep(1000);
        connectableFlux.subscribe(s -> System.out.println("Subscriber 2 : " + s)); // does not get the values from beginning
        Thread.sleep(10000);

    }

}