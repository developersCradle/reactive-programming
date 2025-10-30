package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStream {

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxFromStream.class);

    public static void main(String[] args) {

    var list = List.of(1,2,3,4);
    var stream = list.stream();

    // This will be having error, when subscribing to multiple
//    var flux = Flux.fromStream(stream);

        var flux = Flux.fromStream(() -> List.of().stream());
    flux.subscribe(Util.subscriber("sub1"));
    flux.subscribe(Util.subscriber("sub2"));
    }
}