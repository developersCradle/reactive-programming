package org.java.reactive.sec09;

import org.java.reactive.common.Util;
import org.java.reactive.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*
    Ensure that the external service is up and running!
 */
public class Lec13ConcatMap {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        Flux.range(1, 10)
            .concatMap(number -> client.getProduct(number))
            .subscribe(Util.subscriber());

        Util.sleepSeconds(20);

    }

}