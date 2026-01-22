package org.java.reactive.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/*
    Just for demo.
    Imagine user-service, as an application, has 2 endpoints.
    This is a client class to make a call to those 2 endpoints (IO requests).
 */
public  class UserService {

    // We are simulating some user table!
    private static final Map<String, Integer> userTable = Map.of(
            "sam", 1,
            "mike", 2,
            "jake", 3
    );

    /**
     *  Endpoint to get all users!
     */
    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(userTable.entrySet())
                    // We need map here, since the entrySet is Map.Entry<String, Integer> from the map!
                    // We need map the Entry to the User(id, username)
                   .map(entry -> new User(entry.getValue(), entry.getKey()));
    }

    /**
     *  Endpoint to get the {@code userId} for the given {@code username}!
     */
    public static Mono<Integer> getUserId(String username){

        return Mono.fromSupplier(() -> userTable.get(username));
    }

}
