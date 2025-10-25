package org.java.reactive.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHttpClient {

    private static final String BASE_URL = "http://localhost:7070";
    protected HttpClient httpClient;

    public AbstractHttpClient() {
        var loopResources = LoopResources.create("ScoopiDoo", 1 , true);

        // We can provide the other attributes in the HttpClient.create() section.
        this.httpClient = HttpClient.create().runOn(loopResources).baseUrl(BASE_URL);

    }
}
