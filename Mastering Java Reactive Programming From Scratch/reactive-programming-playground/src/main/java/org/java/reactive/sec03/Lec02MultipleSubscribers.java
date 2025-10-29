package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec02MultipleSubscribers {

    private static final Logger log = LoggerFactory.getLogger(Lec02MultipleSubscribers.class);

    public static void main(String[] args) {

        var flux = Flux.just(1,2 ,3 , "sam");

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
        flux.subscribe(Util.subscriber("sub3"));
    }


}