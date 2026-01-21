package org.java.reactive.sec09.applications;

// Just for demo.
// We have user id in the order to show that it belongs to the user.

public record Order(Integer userId,
                    String productName,
                    Integer price) {
}
