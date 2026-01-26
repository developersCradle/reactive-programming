package org.java.reactive.sec09;

import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    Zip:
    - we will subscribe to all the producers at the same time
    - all or nothing
    - all producers will have to emit an item
*/
public class Lec07Zip {

    public static void main(String[] args)
    {

    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTimes() {
        return Flux.range(1, 10)
                .map(i -> "tires-" + i)
                .delayElements(Duration.ofMillis(300));
    }
}
