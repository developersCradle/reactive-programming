package org.java.reactive.sec09;

import org.java.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;

import java.time.Duration;

/*
    Zip:
    - We will subscribe to all the producers at the same time.
    - All or nothing.
    - All producers will have to emit an item.
*/
public class Lec07Zip {

    record Car(String body, String engine, String tires)
    {

    }

    public static void main(String[] args)
    {
        Flux.zip(getBody(), getEngine(), getTires())
                .map(tuple -> new Car(tuple.getT1(), tuple.getT2(), tuple.getT3()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }


    /**
     * This will emmit Body!
     */
    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    /**
     * This emmit Engine!
     */
    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(200));
    }

    /**
     * This emmit Tires!
     */
    private static Flux<String> getTires() {
        return Flux.range(1, 10)
                .map(i -> "tires-" + i)
                .delayElements(Duration.ofMillis(300));
    }
}
