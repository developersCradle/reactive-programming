package org.java.reactive.sec02;


import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {

    private static final Logger log = LoggerFactory.getLogger(Lec04MonoEmptyError.class);

    public static void main(String[] args) {

        getUsername(3)
                .subscribe(s -> System.out.println(s));
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



}