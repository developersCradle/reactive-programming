package org.java.reactive.sec09;

import org.java.reactive.common.Util;
import org.java.reactive.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*
    Ensure that the external service is up and running!
 */
public class Lec12FluxFlatMapExerciseAssignment {


    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        // new way
        Flux.range(1, 10)
                .flatMap(number -> client.getProduct(number), 3)
                .subscribe(Util.subscriber());

        // old way
//        for (int i = 1; i < 10; i++) {
//            client.getProduct(i)
//                    .subscribe(Util.subscriber());
//        }

        Util.sleepSeconds(5);
    }

}
