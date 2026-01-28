package org.java.reactive.sec09.client;

import org.java.reactive.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.zip.ZipEntry;

import static reactor.core.publisher.Flux.zip;

public class ExternalServiceClient extends AbstractHttpClient {

    public record Product(String productName, String review, String price)
    {

    }

    public Mono<Product> getProduct(int ammount) {

        for (int i = 0; i < ammount; i++) {

            return Mono.zip(
                    this.httpClient
                            .get() // for Get.
                            .uri("/demo05/product/" + ammount) // The Base URI, will be getted.
                            .responseContent() // Will be getting as Flux<ByteBuf>.
                            .asString() // We need to tell that its Flux of the String.
                            .next()
                    ,
                    this.httpClient
                            .get() // for Get.
                            .uri("/demo05/review/" + ammount) // The Base URI, will be getted.
                            .responseContent() // Will be getting as Flux<ByteBuf>.
                            .asString() // We need to tell that its Flux of the String.
                            .next()
                    ,
                    this.httpClient
                            .get() // for Get.
                            .uri("/demo05/price/" + ammount) // The Base URI, will be getted.
                            .responseContent() // Will be getting as Flux<ByteBuf>.
                            .asString() // We need to tell that its Flux of the String.
                            .next()

            ).map(tuple -> new Product(tuple.getT2(), tuple.getT3(), tuple.getT1()));

        }
        return null;
    }
}