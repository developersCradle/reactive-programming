package org.java.reactive.sec09;


import org.java.reactive.common.Util;
import org.java.reactive.sec09.applications.Order;
import org.java.reactive.sec09.applications.OrderService;
import org.java.reactive.sec09.applications.UserService;
import reactor.core.publisher.Flux;

/*
    Sequential non-blocking IO calls!
    flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
 */
public class Lec11FluxFlatMap {

    public static void main(String[] args)
    {
        /*
            The requirement is to get all the Users and then get all the Orders, which there are!
         */

        /*
            We can explore the that there is two Fluxes publishers inside.
        */
//        Flux<Flux<Order>> map = UserService.getAllUsers()
//                .map(user -> OrderService.getUserOrders(user.id()));

        /*
            Flux.flatMap can flatten Flux<Flux<Order>> into Flux<Order>
        */
        Flux<Order> orderFlux = UserService.getAllUsers()
                .flatMap(user -> OrderService.getUserOrders(user.id()));



        /*
            Flux.flatMap can flatten Flux<Flux<Order>> into Flux<Order>.
            Just more elegant version!

        */
        UserService.getAllUsers()
                .map(user -> user.id())
                .flatMap(userId -> OrderService.getUserOrders(userId))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

}
