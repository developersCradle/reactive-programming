# Section 09: Combining Publishers.

Combining Publishers.

# What I learned.

# Introduction.

<div align="center">
    <img src="Combining_Publishers.PNG" alt="reactive programming" width="700"/>
</div>

1. It will be **super** important to **combine** the **multiple publishers**!

<div align="center">
    <img src="Multiple_Publishers_Microservices.PNG" alt="reactive programming" width="700"/>
</div>

1. Let's image that the **front end** will be making one **request** to the `product aggregator` **microservice**. 
    - Let's say, its **product view** request!

2. Often times **microservice architecture** has **multiple sources of data**.
    - Many times' backend will ask from **multiple sources** and **aggregates** data and send it back to the **front end**!
    - Also, notice that returned types can be different, **Flux** or **Mono**!

> [!TIP]
> 💡 There is usually **multiple** smaller request in backend for **one** front end request! 💡
>    - These smaller requests can be in different shapes and sizes!  

<div align="center">
    <img src="Different_Options_That_Project_Reactor_Provides.PNG" alt="reactive programming" width="400"/>
</div>

1. **Project Reactor** provides, operations to achieve the business requirements in **specific order**!

# Start With.

# Start With - Usecases.

# Concat With.


# Concat Delay Error.

# Merge.

# Merge - Usecases.

# Zip.

# Zip - Assignment.

# FlatMap - Introduction.

<div align="center">
    <img src="What_Is_Flat_Map_Operation.PNG" alt="reactive programming" width="600"/>
</div>

1. These operations will **not** work for the **dependent sequential calls!** 
    - These are producing results as **independent**! Meaning the results, **don't rely** on an others results!
        - Example of independent producers:
            - `.startWith(...)`. [startWith operation](https://github.com/developersCradle/reactive-programming/tree/main/Mastering%20Java%20Reactive%20Programming%20From%20Scratch/Section%2009#start-with).
            - `.concat(...)`. [Concat operation](https://github.com/developersCradle/reactive-programming/tree/main/Mastering%20Java%20Reactive%20Programming%20From%20Scratch/Section%2009#concat-with).
            - `.merge(...)`. [Merge operation](https://github.com/developersCradle/reactive-programming/tree/main/Mastering%20Java%20Reactive%20Programming%20From%20Scratch/Section%2009#merge).
            - `.zip(...)`. [Zip operation](https://github.com/developersCradle/reactive-programming/tree/main/Mastering%20Java%20Reactive%20Programming%20From%20Scratch/Section%2009#zip).

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

<div align="center">
    <img src="What_About_Dependent_Sequential_Calls.PNG" alt="reactive programming" width="600"/>
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

<summary id="reactive programming
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
    - Endpoint `getUserOrders(Integer userId)`:
        
        ````Java
        public static Flux<Order> getUserOrders(Integer userId) {
            return Flux.fromIterable(orderTable.get(userId))
                    .delayElements(Duration.ofMillis(500))
                    .transform(Util.fluxLogger("order-for-user" + userId));
        }
        ````

    - Example of `getUserOrders(Integer userId)` endpoint working:

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

    - Example of `getUserBalance(Integer userId)` in context of **reactive programming** working:

    <div align="center">
        <img src="Endpoint_GetUserBalance_Working_Independently.gif" alt="reactive programming" width="600"/>
    </div>

<details>

<summary id="Reactive programming
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

# Mono - flatMap.

<div align="center">
    <img src="Mono_FlatMap_Operation_In_Project_Reactor.png" alt="reactive programming" width="400"/>
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
> 💡 In context of **reactive programming** the operation `.map` is perfect for **in-memory**, **synchronous transformations** of the value **inside a Mono**. 💡

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

- Illustration of `.map` how it should be used, in the context of `UserService.getUserId(...)`:

<div align="center">
    <img src="Using_Map_As_Intermediate_Operation.gif" alt="reactive programming" width="600"/>
</div>

1. You can see that `received: Hello there user ID: 1` is successfully manipulated! 

- Another use case, where we make **Mono** inside of **Mono**, as below:

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

- Example of `getUserId()` **not** working, where the **Mono** inside of **Mono**:

<div align="center">
    <img src="Subscribing_Mono_Of_Mono_In_Two_Sequential_Call.gif" alt="reactive programming" width="600"/>
</div>

1. When **Mono** inside **Mono**, the log `received: MonoSupplier`.

- We are using `.flatMap(...)` to flatten the inner publisher! Which makes from `Mono<Mono<String>>` to `Mono<String>`.  Example code below:

````Java
        /*
        * We are using flatMap to flatten the inner Publisher.
        */
        UserService.getUserId("sam")
                // In memory computing
                .flatMap(userId -> Mono.just("Hello there user ID: " + userId))
                .subscribe(Util.subscriber());
````

- Example of `.flatMap(...)` in context of **reactive programming** working:

<div align="center">
    <img src="Using_FlatMap_With_The_Publisher.gif" alt="reactive programming" width="600"/>
</div>

1. You can see that `.getUserId(...)` is working as it should. The returned value `received: Hello there user ID: 1` as **and not** the `received: MonoSupplier`.

<div align="center">
    <img src="FlatMap_In_The_IDE_With_The_Two_Publishers.PNG" alt="reactive programming" width="700"/>
</div>

1. You can also see that the **IDE** is giving hint of the `.flatMap(...)` working. One can see the **two** publisher, being flattened as it written as `Mono<String>`!

- We are using the `.flatMap(...)` we were planning to use it in the beginning! 

````Java
/*
            Get user ID then fetch balance asynchronously, flattening nested streams with flatMap.
        */
        UserService.getUserId("sam")
                // In memory computing
                .flatMap(userId -> PaymentService.getUserBalance(userId))
                .subscribe(Util.subscriber());
````

- Example of using `.flatMap(..)` with the **two** different services being called! 

<div align="center">
    <img src="Using_FlatMap_With_The_Another_Service_Succesfull.gif" alt="reactive programming" width="700"/>
</div>

1. We can see that `.flatMap(...)` working, with `UserService.getUserId("sam")`, which returns the `userId` and feeds it to the `PaymentService.getUserBalance(userId)`, which in regards checks the **User Balance Service**, which returns as it was defined in the `Map<Integer, Integer> userBalanceTable` 
    - The returned value: `received: 100`.


<details>

<summary id="reactive programming
" open="true" alt="reactive programming"> <b> Mono .flatMap(...) working source code! </b> </summary>

````Java

package org.java.reactive.sec09;


import org.java.reactive.common.Util;
import org.java.reactive.sec09.applications.PaymentService;
import org.java.reactive.sec09.applications.UserService;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Signal.subscribe;


/*
    We are demonstrating here .flatmap() operation with different microservice calls!
*/
public class Lec09MonoFlatMap {

    public static void main(String[] args) {

        /*
            We have the example of calling with the .map, which is wrong!
         */
//       UserService.getUserId("sam")
//                // Here it will be Mono<Interger>
//                .map(userId -> PaymentService.getUserBalance(userId))
//                .subscribe(Util.subscriber());

        /*
            We have the example of calling with the .map, where is supposed to be used! This is as
         */
//       UserService.getUserId("sam")
//                // In memory computing
//                .map(userId -> "Hello there user ID: " + userId)
//                .subscribe(Util.subscriber());

        /*
             We have the example of calling with the .map, with making it inside Mono, which is  also wrong!
        */
//       UserService.getUserId("sam")
//                // In memory computing
//                .map(userId -> Mono.just("Hello there user ID: " + userId))
//                .subscribe(Util.subscriber());

        /*
            Get user ID then fetch balance asynchronously, flattening nested streams with flatMap.
        */
        UserService.getUserId("sam")
                // In memory computing
                .flatMap(userId -> PaymentService.getUserBalance(userId))
                .subscribe(Util.subscriber());
    }
}
````
</details>


# Mono - flatMapMany.

<div align="center">
    <img src="Mono_FlatMapMany_Operation_In_Project_Reactor.png" alt="reactive programming" width="400"/>
</div>


- Illustration for the **Receive types** below:

````Java
Mono<Flux<Order>> example = UserService.getUserId("sam")
                .map(userId -> OrderService.getUserOrders(userId));
````

OrderService.getUserOrders(

- `.flatMap(...)` assum

Your source is a Mono
Your lambda returns a Flux
You want a Flux as the result

<details>

<summary id="reactive programming
" open="true" alt="reactive programming"> <b> Mono .flatMapMany(...) working source code! </b> </summary>

````Java

````
</details>


# Flux - flatMap.

<div align="center">
    <img src="Flux_FlatMap_Operation_In_Project_Reactor.png" alt="reactive programming" width="400"/>
</div>

# FlatMap - How it works.

# FlatMap - Assignment.

# ConcatMap.

# Operator - Collect List.

# Operator - Then.

# *** Assignment ***.

# Summary.

# Quiz 08.

<details>

<summary id="reactive programming
" open="true"> <b>Question 01.</b> </summary>

````Yaml
Question 01:
Add here the question
````

````Java
add here the code
````

- My answer:

<div align="center">
    <img src="Quiz 05/Q1.PNG" width="700"/>
</div>


1. Add here the explanation

</details>

<details>

<summary id="Reactive programming course" open="true"> <b>Question 02.</b> </summary>

````Yaml
Question 02:
The question goes here
````

````Java
add here the code

````

- My answer:

<div align="center">
    <img src="Quiz 05/Q2.PNG" width="400"/>
</div>

1. Add here the explanation.

</details>

<details>

<summary id="Reactive programming course" open="true"> <b>Question 03.</b> </summary>

````Yaml
Question 03:
Add here the question
````

````Java
add here the code
````
- My answer:

<div align="center">
    <img src="Quiz 05/Q3.PNG" width="700"/>
</div>


1. add here the answer

</details>


<details>

<summary id="Reactive programming course" open="true"> <b>Question 04.</b> </summary>

````Yaml
Question 04:
Add here the question.
````

````Java
 add here the code
````
- My answer:

<div align="center">
    <img src="Quiz 05/Q4.PNG" width="700"/>
</div>

1. Add here the answer.

</details>