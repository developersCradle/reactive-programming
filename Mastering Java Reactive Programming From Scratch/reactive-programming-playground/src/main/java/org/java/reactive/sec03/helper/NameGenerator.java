package org.java.reactive.sec03.helper;


import com.github.javafaker.Faker;
import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    private static final Logger log = LoggerFactory.getLogger(NameGenerator.class);

    // Reactive style - My version.
    public static Mono<List<String>> getNameListAsReactive(int count) {
        return Flux.range(1, count)
                .map(number -> generateName())
                .collectList();
    }

    // Reactive style - Teacher version.
    public static Flux<String> getNameListAsReactiveSecond(int count) {
        return Flux.range(1, count)
                .map(number -> generateName());
    }


    // Traditional way
    public static List<String> getNameList(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(number -> generateName())
                .toList();
    }

    private static String generateName() {
        Util.sleepSeconds(1);
        return Faker.instance().name().firstName();
    }


    public static void main(String[] args) {


        getNameListAsReactive(2).subscribe(Util.subscriber());
    }
}
