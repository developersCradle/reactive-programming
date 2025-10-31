package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06Log {

    private static final Logger log = LoggerFactory.getLogger(Lec06Log.class);

    public static void main(String[] args)
    {
        Flux.range(1,3)
                .log("log-map")
                //We can have here processors.
                .map(i -> Util.faker().name().firstName())
                .subscribe(Util.subscriber());
    }
}