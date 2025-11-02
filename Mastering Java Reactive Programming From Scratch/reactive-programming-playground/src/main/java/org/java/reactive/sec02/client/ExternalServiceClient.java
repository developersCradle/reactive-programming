package org.java.reactive.sec02.client;

import org.java.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    /*
        Correction: When the method is invoked, we create a Mono which
        is capable of sending a request. But the actual HTTP request is sent, only when it is subscribed.
     */

    public Mono<String> getProductName(int productId) {
        return this.httpClient
                .get() // for Get.
                .uri("/demo01/product/" + productId) // The Base URI, will be getted.
                .responseContent() // Will be getting as Flux<ByteBuf>.
                .asString() // We need to tell that its Flux of the String.
                .next(); // We create the Flux to Mono, with the First Mono.
    }
}