/*
package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.java.reactive.sec03.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec08NonBlockingStreamingMessages {

    private static final Logger log = LoggerFactory.getLogger(Lec07FluxList.class);

    public static void main(String[] args)
    {
        var client = new ExternalServiceClient();

        client.getNames()
                .subscribe(Util.subscriber("Sub1"));

        client.getNames()
                .subscribe(Util.subscriber("Sub2"));

        Util.sleepSeconds(12);

        // This example there will be long waiting line and we don't know what Producer will be doing.
//        var list = NameGenerator.getNameList(10);
//        System.out.println(list);



        // This is the reactive way of logging
//        NameGenerator.getNameListAsReactiveSecond(10)
//                .subscribe(Util.subscriber());


//         This is the reactive way of logging
//        NameGenerator.getNameListAsReactiveSecond(10)
//                .subscribe(Util.subscriber());


        // They way where we can cancel the elements.
//        var subscriber = new SubscriberImpl();
//        NameGenerator.getNameListAsReactiveSecond(10)
//                .subscribe(subscriber);
//
//        subscriber.getSubscription().request(3);
//        subscriber.getSubscription().request(3);
//        subscriber.getSubscription().cancel();

    }
}



 */