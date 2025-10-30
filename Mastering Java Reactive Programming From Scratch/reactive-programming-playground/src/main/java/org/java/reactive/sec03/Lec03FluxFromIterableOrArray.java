package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {

    private static final Logger log = LoggerFactory.getLogger(Lec03FluxFromIterableOrArray.class);

    public static void main(String[] args) {

        // From Java collection:
        var list = List.of("a","b","c");
        Flux.fromIterable(list)
                .subscribe(Util.subscriber());
        // Form Java array:
        Integer[] arr = {1,2,3,4,5,6,7};
        Flux.fromArray(arr)
                .subscribe(Util.subscriber());

    }


}