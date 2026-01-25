package org.java.reactive.sec09;


import org.java.reactive.common.Util;
import org.java.reactive.sec09.applications.Order;
import org.java.reactive.sec09.applications.OrderService;
import org.java.reactive.sec09.applications.PaymentService;
import org.java.reactive.sec09.applications.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/*
    Sequential non-blocking IO calls!
    flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
    Mono is supposed to be 1 item - what if the flatMap returns multiple items!?
 */
public class Lec10MonoFlatMapMany {

    public static void main(String[] args)
    {

        /*
            We have username, we want to get all user orders!
            This example has multiple publishers, which is not wanted!
         */
        Mono<Flux<Order>> sam = UserService.getUserId("sam")
                .map(userId -> OrderService.getUserOrders(userId));

        /*
            We have username, we want to get all user orders!
            This case is not working, since the inner publisher is the Flux, and we are using .flatMap(...).
            This will be giving the error!
         */
//        UserService.getUserId("sam")
//                .flatMap(userId -> OrderService.
//                        getUserOrders(userId));

    /*
            We have username, we want to get all user orders!
            We can solve this using Mono.flatMapMany(...) to return the Flux.
         */
        UserService.getUserId("sam")
                .flatMapMany(userId -> OrderService.getUserOrders(userId))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

}
