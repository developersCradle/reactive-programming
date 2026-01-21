package org.java.reactive.sec09;


import org.java.reactive.sec09.applications.UserService;

public class Lec01StartWith {

    public static void main(String[] args) {

        UserService.getAllUsers()
                .doOnNext(user -> System.out.println("User found: " + user))
                .subscribe();

    }



    // some start here
}
