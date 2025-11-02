package org.java.reactive.sec03.client;

import org.java.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getNames() {
        return this.httpClient
                .get() // for Get.
                .uri("/demo02/name/stream" ) // The Base URI, will be getted.
                .responseContent() // Will be getting as Flux<ByteBuf>.
                .asString(); // We need to tell that its Flux of the String.
    }
}