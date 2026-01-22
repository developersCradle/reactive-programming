package org.java.reactive;

import org.java.reactive.sec09.applications.OrderService;

public class Main {
    public static void main(String[] args)
    {

        OrderService.getUserOrders(2)
                .doOnNext(order -> System.out.println(order.price()))
                .subscribe();
    }


}