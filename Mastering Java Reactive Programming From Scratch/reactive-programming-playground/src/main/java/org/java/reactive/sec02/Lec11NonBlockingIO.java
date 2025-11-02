package org.java.reactive.sec02;

import org.java.reactive.common.Util;
import org.java.reactive.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    To demo non-blocking IO
    Ensure that the external service is up and running!
 */
public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        log.info("starting");

        // Making single query.
        client.getProductName(5)
                .subscribe(Util.subscriber());



        // Making multiple queries!
        for (int i = 1; i <= 100; i++) {
            var name = client.getProductName(i)
                    .block();

            log.info(name);
        }
        Util.sleepSeconds(2);
    }
}
