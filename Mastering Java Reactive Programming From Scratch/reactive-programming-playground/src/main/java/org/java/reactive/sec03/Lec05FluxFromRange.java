package org.java.reactive.sec03;

import com.github.javafaker.Faker;
import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05FluxFromRange {

    private static final Logger log = LoggerFactory.getLogger(Lec05FluxFromRange.class);

    public static void main(String[] args)
    {
        Flux.range(1,10)
                .subscribe(Util.subscriber());

        // Faker 10 random names
        Flux.range(1,10)
                .map(i -> Faker.instance().name().firstName())
                .subscribe(Util.subscriber());


    }
}