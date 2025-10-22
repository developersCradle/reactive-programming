package org.java.reactive.sec02;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/*
    If you have a CompleteFuture already, then we can convert that into a Mono.
 */

public class Lec08MonoFromFuture {

    private static final Logger log = LoggerFactory.getLogger(Lec08MonoFromFuture.class);

    public static void main(String[] args) {

    }

    private static CompletableFuture<String> getName()
    {
        return CompletableFuture.supplyAsync(() ->
        {
            log.info("genrating name");
            return Util.faker().name().firstName();
        });
    }


}