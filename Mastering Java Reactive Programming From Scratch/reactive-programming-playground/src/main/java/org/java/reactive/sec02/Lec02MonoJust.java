package org.java.reactive.sec02;


import org.java.reactive.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

public class Lec02MonoJust {

    private static final Logger log = LoggerFactory.getLogger(Lec02MonoJust.class);

    public static void main(String[] args) {

        //Project Reactors logging.

        //Mono.just(1); // Mono.just can be publisher of any type.
//        System.out.println(mono); // does not work yet!

        // The Project reactor way to log.
        //Mono<String> mono = Mono.just("First");
        //mono.subscribe(t -> System.out.println(t));

        // We implement our own Reactive Stream and log it.

        Mono<String> mono = Mono.just("First");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);
        subscriber.getSubscription().request(3);









    }


}