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
    - We are simulating some `userTable`!
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

    - We need `.map()` here, since the `userTable.entrySet()` the emits `Map.Entry<String, Integer>`. We can transfer it to `User(id, username)` with the `.map()` 

    <div align="center">
        <img src="Endpoint_GetAllUsers_Working_Independently.gif" alt="reactive programming" width="600"/>
    </div>

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
    - Order object will have fields:
        - Integer `userId`.
        - String `productName`.
        - Integer `price`.


- We are having following `OrderService` **class**:

<details>

<summary id="Thread progress
" open="true" alt="reactive programming"> <b>Order Service implementation!</b> </summary>

````Java


````
</details>

