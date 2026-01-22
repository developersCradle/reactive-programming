# Section 09: Combining Publishers.

Combining Publishers.

# What I learned.

# Introduction.

<div align="center">
    <img src="multiplePublishers.PNG" alt="reactive programming" width="700"/>
</div>

1. Often times **microarchitecture** has multiple sources of data.
    - Many times backend will ask from **multiple sources** and collects aggregate data for the **front end**.

- The **point** here is that:
    - For one front end request.
        - There is usually multiple smaller request in backend.
            - These minor request can have specific order and shape of data.
            
- Some popular options for transforming data.

<div align="center">
    <img src="options.PNG" alt="reactive programming" width="400"/>
</div>

# FlatMap - Introduction.

<div align="center">
    <img src="What_Is_Flat_Map_Operation.PNG" alt="reactive programming" width="500"/>
</div>

1. These operations will **not** work for the **dependent sequential calls!** 
    - These are producing results as **independent**! Meaning the results, **don't rely** on an others results!
        - Example of independent producers:
            - `startWith`.
            - `concat`.
            - `merge`.
            - `zip`.

- What **independent** means in this context:
    - **Request A** does not need data from **Request B**.
    - **Request B** does not need data from **Request A**.

2. What is happening when we want something from **previous call**? We can use `.flatMap()` in such cases!

- In **Project Reactor** `.flatMap()` is **often** used when the next call depends on a **value produced** by the previous call. Example below:

 ````Java
getUser()
.flatMap(user -> getOrdersByUserId(user.getId()))
.flatMap(orders -> calculateDiscount(orders))
.flatMap(discount -> saveDiscount(discount))
.subscribe();
````

- `getUser()` is executed and **emits** a `User`
    - The **first** `.flatMap()`:
        - Receives the `User`
        extracts `user.getId()` and
        calls `getOrdersByUserId(user.getId())` and emits `Orders`.
    - The **second** `.flatMap()`:
        - Receives the `Orders`
        passes them to `calculateDiscount(orders)` and emits a `Discount`
    - The **third** `.flatMap()`:
        - Receives the `Discount`
        passes it to `saveDiscount(discount)` emits the final result!

<br>

- Todo make links for these operations

<div align="center">
    <img src="What_About_Dependent_Sequential_Calls.PNG" alt="reactive programming" width="500"/>
</div>

1. **User Service**, the endpoints:
    - Get user ID for the given username.
    - Get all users.

2. **Payment Service**
    - Get user balance for the given user ID.

3. **Order Service**
    - Get user orders for the given user ID.

4. The business scenario, as following:
    - I have a `username`, this is provided!
    - I need to get the user’s `orders`.
        - Here is the call sequence, to the **User's Orders** 
            -  First we need to get `user id`, with the `username`, from the service in `1.`.
            - Second we need to the **Orders** from the service `3.`.

> [!TIP]
> As reminder, Java **records** are **classes** whose **state does not change after creation**.

- The `User` Java `record` as following:
    ````Java
    package org.java.reactive.sec09.applications;

    public record User(Integer id, String username) {
    }
    ````
    - User object will have fields:
        - Integer `id`.
        - String `username`.

- We are having following `UserService` **class**:
    - We are simulating some `userTable`, where there will be **User** contained in!

    ````Java
        // We are simulating some user table!
        private static final Map<String, Integer> userTable = Map.of(
                "sam", 1,
                "mike", 2,
                "jake", 3
        );
    ````

    - Endpoint `Flux<User> getAllUsers()`:
    
        ````Java
            public static Flux<User> getAllUsers() {
                return Flux.fromIterable(userTable.entrySet())
                            // We need map here, since the entrySet is Map.Entry<String, Integer> from the map!
                            // We are mapping into User(id, username)
                        .map(entry -> new User(entry.getValue(), entry.getKey()));
            }
        ````
        - We need `.map()` here, since the `userTable.entrySet()` the emits `Map.Entry<String, Integer>`. We can transfer it to `User(id, username)` with the `.map()`.

    - Example of `getAllUsers()` working:

        <div align="center">
            <img src="Endpoint_GetAllUsers_Working_Independently.gif" alt="reactive programming" width="600"/>
        </div>

        1. As you can see there are **three** different users returned!

        ````Bash
        User found: User[id=3, username=jake]
        User found: User[id=1, username=sam]
        User found: User[id=2, username=mike]
        ````

    - Endpoint `Mono<Integer> getUserId(String username)`:
        
        ````Java
        public static Mono<Integer> getUserId(String username){

            return Mono.fromSupplier(() -> userTable.get(username));
        }
        ````

    - Example of `getUserId()` working:

    <div align="center">
        <img src="Endpoint_GetUserId_Working_Independently.gif" alt="reactive programming" width="600"/>
    </div>

    1. As you can see the `"Jake"`, is returning `3` as defined in the table!

<details>

<summary id="Thread progress
" open="true" alt="reactive programming"> <b> User Service implementation </b> </summary>

````Java
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

    // Simulations of the webservice call!
    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(userTable.entrySet())
                    // We need map here, since the entrySet is Map.Entry<String, Integer> from the map!
                    // We need map the Entry to the User(id, username)
                   .map(entry -> new User(entry.getValue(), entry.getKey()));
    }

    public static Mono<Integer> getUserId(String username){

        return Mono.fromSupplier(() -> userTable.get(username));
    }

}
````

</details>

- The `Order` Java `record` as following:
    ````Java
    package org.java.reactive.sec09.applications;

    // Just for demo.
    // We have user id in the order to show that it belongs to the user.

    public record Order(Integer userId,
                        String productName,
                        Integer price) {
    }
    ````    
    - Order object, will have fields:
        - Integer `userId`.
        - String `productName`.
        - Integer `price`.


- We are having following `OrderService` **class**:
    - We are simulating some `userTorderTableable`, where there will be **Orders** contained in!
        ````Java
        private static final Map<Integer, List<Order>> orderTable = Map.of(
                1, List.of(
                        new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100)),
                        new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100))
                ),
                2, List.of(
                        new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100)),
                        new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100)),
                        new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100))
                ),
                3, List.of()
        );
        ````
    - Endpoint `Flux<Order> getUserOrders(Integer userId)`:
        
        ````Java
        public static Flux<Order> getUserOrders(Integer userId) {
            return Flux.fromIterable(orderTable.get(userId))
                    .delayElements(Duration.ofMillis(500))
                    .transform(Util.fluxLogger("order-for-user" + userId));
        }
        ````
    
    <div align="center">
        <img src="Endpoint_GetUserOrders_Working_Independently.gif" alt="reactive programming" width="600"/>
    </div>

    1. As you can see there are **three** orders returned!

    ````Bash
    18:33:39.875 INFO  [           main] org.java.reactive.common.Util  : subscribing to order-for-user2
    82
    73
    42
    18:33:39.880 INFO  [           main] org.java.reactive.common.Util  : order-for-user2 completed
    ````

- We are having following `PaymentService` **class**:
    - We are simulating some `userBalanceTable`, where there will be **Balances** contained in!

        ````Java
            // We are simulating some userBalanceTable table!
            private static final Map<Integer, Integer> userBalanceTable = Map.of(
                    1, 100,
                    2, 200,
                    3, 300
            );
        ````

    - Endpoint `Flux<User> getAllUsers()`:
    
        ````
        public static Mono<Integer> getUserBalance(Integer userId)
        {
            return Mono.fromSupplier(() -> userBalanceTable.get(userId));
        }
        ````

    <div align="center">
        <img src="Endpoint_GetUserBalance_Working_Independently.gif" alt="reactive programming" width="600"/>
    </div>

<details>

<summary id="Thread progress
" open="true" alt="reactive programming"> <b>Order Service implementation!</b> </summary>

````Java
package org.java.reactive.sec09.applications;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/*
    Just for demo.
    Imagine order-service, as an application, has an endpoint.
    This is a client class to make a call to the endpoint (IO request).
 */
public class OrderService {

    private static final Map<Integer, List<Order>> orderTable = Map.of(
            1, List.of(
                    new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100)),
                    new Order(1, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100))
            ),
            2, List.of(
                    new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100)),
                    new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100)),
                    new Order(2, Util.faker().commerce().productName(), Util.faker().random().nextInt(10, 100))
            ),
            3, List.of()
    );

    public static Flux<Order> getUserOrders(Integer userId) {
        return Flux.fromIterable(orderTable.get(userId))
                   .delayElements(Duration.ofMillis(500))
                   .transform(Util.fluxLogger("order-for-user" + userId));
    }
}
````
</details>

<div align="center">
    <img src="Flatmap_Operation_In_Project_Reactor.png" alt="reactive programming" width="400"/>
</div>

- Next we will be illustrating the **two sequential** calls:
    - **First** `UserService.getUserId(...)`
    - **Second** with that's **result** the `PaymentService.getUserBalance(...)` in called next!
        
- Next example is illustrating, when using the `.map` operation when calling two different services:

````Java
        /*
            We have the example of calling with the .map.
         */
        Mono<Mono<Integer>> problemCallingExampleOne = UserService.getUserId("sam")
                // Here it will be Mono<Interger>
                .map(userId -> PaymentService.getUserBalance(userId));
````

- We will be **subscribing** into such **Mono** of **Mono**, example below:

<div align="center">
    <img src="Subscribing_Mono_Of_Mono_In_Two_Sequential_Call.gif" alt="reactive programming" width="600"/>
</div>

1. We are **not** getting the **user balance values**, we are getting the **inner publisher** `received: MonoSupplier`! 

- Same thing form **IDE**, it's trying to give hint:

<div align="center">
    <img src="Mono_Inside_Mono.PNG" alt="reactive programming" width="600"/>
</div>

1. You can see the `Mono<Mono<...>>` coming out of the `.map(...)` and the log `received: MonoSupplier`.

> [!TIP]
> 💡 `.map` in context of reactive programming, is perfect for **in-memory**, **synchronous transformations** of the value **inside a Mono**. Illustration below: 💡

- Next, we will explore how `.map` should be used in **project reactor** context:

````Java
        /*
            We have the example of calling with the .map, where is supposed to be used! This is as 
         */
        UserService.getUserId("sam")
                // In memory computing
                .map(userId -> "Hello there user ID: " + userId)
                .subscribe(Util.subscriber());
````

- Illustration of `.map` how it should be used:

<div align="center">
    <img src="Using_Map_As_Intermediate_Operation.gif" alt="reactive programming" width="600"/>
</div>

1. You can see that `received: Hello there user ID: 1` is successfully manipulated! 

- Another use case where we make **Mono** inside of **Mono**:

````Java
   /*
             We have the example of calling with the .map, with making it inside Mono, which is  also wrong!
        */
        UserService.getUserId("sam")
                // In memory computing
                .map(userId -> Mono.just("Hello there user ID: " + userId))
                .subscribe(Util.subscriber());
    }
````

<div align="center">
    <img src="Subscribing_Mono_Of_Mono_In_Two_Sequential_Call.gif" alt="reactive programming" width="600"/>
</div>

1. When **Mono** inside **Mono**, the log `received: MonoSupplier`.
