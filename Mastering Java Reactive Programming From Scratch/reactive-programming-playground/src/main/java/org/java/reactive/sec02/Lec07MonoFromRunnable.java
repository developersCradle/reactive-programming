package org.java.reactive.sec02;


import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec07MonoFromRunnable {

    private static final Logger log = LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    /*
     Emitting empty after some method invocation.
     */
    public static void main(String[] args) {

    getProductName(2)
            .subscribe(Util.subscriber());

    }


    private static Mono<String> getProductName(int productId)
    {
        if (productId == 1)
        {
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        // Returning empty is not always the best from the business side.
        // return Mono.empty();
        // we can use Runnable for logging
        return Mono.fromRunnable(()-> notifyBusiness(productId));
    }


    /*
    Rather than returning, we can notify with following method.
     */
    private  static void notifyBusiness(int productId)
    {
        log.info("notifying business on unlivable product {} ", productId);
    }

}