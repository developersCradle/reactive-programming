package org.java.reactive.sec09;


import org.java.reactive.common.Util;
import org.java.reactive.sec09.applications.Order;
import org.java.reactive.sec09.applications.OrderService;
import org.java.reactive.sec09.applications.PaymentService;
import org.java.reactive.sec09.applications.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/*

*/
public class Lec10MonoFlatMapMany {

    public static void main(String[] args)
    {
        /*
            We have username, we want to get all user orders!
         */
        
        UserService.getUserId("sam")
                .flatMap(userId -> OrderService.getUserOrders(userId));
    }

}
