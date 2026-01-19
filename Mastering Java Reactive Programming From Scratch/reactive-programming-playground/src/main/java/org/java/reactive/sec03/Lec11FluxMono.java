package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *  This lecture is about converting Flux <---> Mono.
 */
public class Lec11FluxMono {

    private static final Logger log = LoggerFactory.getLogger(Lec11FluxMono.class);

    public static void main(String[] args)
    {

        var mono = getUsername(1);
        save(Flux.from(mono));

//        save(getUsername(1)); Wont work!

    }

    private static Mono<String> getUsername(int userId)
    {
        return switch (userId)
        {
            case 1 -> Mono.just("sam!");
            case 2 -> Mono.empty(); // normally we would say null.
            default -> Mono.error(new RuntimeException("Invalid input")); // This block when there is error.
        };
    }

    private static void save(Flux<String> flux)
    {
        flux.subscribe(Util.subscriber());
    }

}