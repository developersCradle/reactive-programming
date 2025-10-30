package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec02MultipleSubscribers {

    private static final Logger log = LoggerFactory.getLogger(Lec02MultipleSubscribers.class);

    public static void main(String[] args) {

        var flux = Flux.just(1,2 ,3 , 4);

        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
        // This Subscription will do some extra logic!
        flux.filter(i -> i % 2 == 0)
                .subscribe(Util.subscriber("sub3"));

    }


}