package org.java.reactive.sec09;

import org.java.reactive.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.rmi.UnexpectedException;
import java.util.List;

import static reactor.core.publisher.Signal.subscribe;

/*
    To collect the items received via Flux. Assuming we will have finite items!
 */
public class Lec14CollectList {

    public static void main(String[] args) {

         Flux.range(1, 10)
                 .concatWith(Flux.error(new UnexpectedException("Test")))
                 .collectList()
                 .subscribe(Util.subscriber());

    }

}