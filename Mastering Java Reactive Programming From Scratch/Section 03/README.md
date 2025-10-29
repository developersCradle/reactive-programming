# Section 03: Flux.

Flux.

# What I learned.

# Flux - Just.

<div align="center">
    <img src="fluxEmmitting.JPG" alt="reactive programming" width="500"/>
</div>

1. **Flux** can emit 0 or more items followed by the `.onComplete()` or the `.onError()`!

<div align="center">
    <img src="fluxMethods.JPG" alt="reactive programming" width="500"/>
</div>

1. Some useful **Flux** factory methods, we can easily create **Flux** from:
    - `Flux.just(...)`. *Data already from the memory*.
    - `Flux.fromIterable(...)`. From *Java List*. 
    - `Flux.fromArray(...)`. From *Java Array*. 
    - `Flux.fromStream(...)`. . From *Java 8 Stream*.



- We can make **multiple** of something:
    - We can make **List** of using **Java 8** feature `List.of(1,2,3,4); `
    - Same, with the **Flux** 
    ```
    Flux.just(1,2 ,3 ) 
        .subscribe(Util.subscriber());`
    ````

- We are getting:

````
20:09:08.518 INFO  [           main] o.j.r.common.DefaultSubscriber :  received: 1
20:09:08.524 INFO  [           main] o.j.r.common.DefaultSubscriber :  received: 2
20:09:08.524 INFO  [           main] o.j.r.common.DefaultSubscriber :  received: 3
20:09:08.524 INFO  [           main] o.j.r.common.DefaultSubscriber :  received: sam
20:09:08.530 INFO  [           main] o.j.r.common.DefaultSubscriber :  received complete!
````

- The Exercise `Lec01FluxJust.java`:

````
package org.java.reactive.sec03;

import org.java.reactive.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec01FluxJust {

    private static final Logger log = LoggerFactory.getLogger(Lec01FluxJust.class);

    public static void main(String[] args) {

        List.of(1,2,3,4); // We can make List of using Java 8 feature.

        Flux.just(1,2 ,3 , "sam") // Same concept applies for the Flux.
                .subscribe(Util.subscriber());
    }
}
````


# Flux - Multiple Subscribers.

- The Exercise `Lec02MultipleSubscribers.java`:

````


````


# Flux - From Array / List.

# Flux - From Stream.

# Flux - Range.

# Log Operator. 

# Flux Vs List.

# ChatGPT vs Gemini.

# FAQ - Are Mono & Flux Data Structures?

# Flux - Non-Blocking IO Stream - Demo.

# Flux - Interval.

# Flux - Empty / Error.

# Flux - Defer.

# Mono/Flux Conversion.

# Assignment.

# Assignment Solution.

