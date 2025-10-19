package org.java.reactive.sec02;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class Lec01LazyStream {
    private static final Logger log = LoggerFactory.getLogger(Lec01LazyStream.class);


    public static void main(String[] args) {

        // This is not reactive programming
        // Streams are Lazy.

        Stream.of("One", "Two")
                .peek(action -> log.info(" received : {}", action))
                .toList(); // This will be resolved as soon as there is terminal operator.

    }


}