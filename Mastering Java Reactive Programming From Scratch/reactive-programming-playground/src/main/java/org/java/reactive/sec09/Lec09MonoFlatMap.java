package org.java.reactive.sec09;


import org.java.reactive.common.Util;
import org.java.reactive.sec09.applications.PaymentService;
import org.java.reactive.sec09.applications.UserService;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Signal.subscribe;


/*
    We are demonstrating here .flatmap() operation with different microservice calls!
*/
public class Lec09MonoFlatMap {

    public static void main(String[] args) {

        /*
            We have the example of calling with the .map, which is wrong!
         */
//       UserService.getUserId("sam")
//                // Here it will be Mono<Interger>
//                .map(userId -> PaymentService.getUserBalance(userId))
//                .subscribe(Util.subscriber());

        /*
            We have the example of calling with the .map, where is supposed to be used! This is as
         */
//       UserService.getUserId("sam")
//                // In memory computing
//                .map(userId -> "Hello there user ID: " + userId)
//                .subscribe(Util.subscriber());

        /*
             We have the example of calling with the .map, with making it inside Mono, which is  also wrong!
        */
//       UserService.getUserId("sam")
//                // In memory computing
//                .map(userId -> Mono.just("Hello there user ID: " + userId))
//                .subscribe(Util.subscriber());

        /*
            Get user ID then fetch balance asynchronously, flattening nested streams with flatMap.
        */
        UserService.getUserId("sam")
                // In memory computing
                .flatMap(userId -> PaymentService.getUserBalance(userId))
                .subscribe(Util.subscriber());
    }


}
