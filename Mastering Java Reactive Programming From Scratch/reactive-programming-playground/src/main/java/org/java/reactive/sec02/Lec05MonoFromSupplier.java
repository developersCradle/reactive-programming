package org.java.reactive.sec02;


import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {

    /*
        To delay the execution using supplied / callable.
     */
    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {
         var list = List.of(1,2,3);
         Mono.just(sum(list))
                 .subscribe(Util.subscriber());


         // Since list on memory.

    }



    private  static int sum(List<Integer> list)
    {
        log.info("finding the sum of ", list);
        return list.stream().mapToInt(a -> a).sum();
    }
}