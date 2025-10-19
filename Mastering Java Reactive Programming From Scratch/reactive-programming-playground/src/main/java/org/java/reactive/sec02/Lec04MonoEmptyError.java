package org.java.reactive.sec02;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {

    private static final Logger log = LoggerFactory.getLogger(Lec04MonoEmptyError.class);

    public static void main(String[] args) {

    }


    private static Mono<String> getUsername(int userId)
    {
//        return switch (userId)
//        {
//            case 1 -> Mono.just("same");
//            case 2 -> Mono.empty();
//            case 3 ->
//        }
        return null;
    }



}