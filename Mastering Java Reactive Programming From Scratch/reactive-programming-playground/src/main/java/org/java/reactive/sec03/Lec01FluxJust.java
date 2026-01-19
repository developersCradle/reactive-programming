package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec01FluxJust {

    private static final Logger log = LoggerFactory.getLogger(Lec01FluxJust.class);

    public static void main(String[] args) {

        List.of(1,2,3,4); // We can make List of using Java 8 feature.

        Flux.just(1,2 ,3 , "sam!") // Same concept applies for the Flux.
                .subscribe(Util.subscriber());
    }


}