# Section 01: Introduction.

Introduction.

# What I learned.

# Before You Enroll...

- This is having lot of hands on.

<div align="center">
    <img src="targeAudiance.JPG" alt="reactive programming" width="700"/>
</div>

- This is for you, if you are working on **Microservices**.

- This will be Slow-paced Course.

<div align="center">
    <img src="OOPpillars.JPG" alt="reactive programming" width="700"/>
</div>

1.  Mastering **Reactive Programming** comes mastering these concepts.
    - These **cannot** be solved by traditional programming.

<div align="center">
    <img src="beforeWeCode.PNG" alt="reactive programming" width="700"/>
</div>

- Before we start coding, we get comfortable first.

# [THEORY] - Process / Thread / CPU / RAM / Scheduler.

<div align="center">
    <img src="process.JPG" alt="reactive programming" width="700"/>
</div>

1. Instance of program is loaded into **memory**. This is executed in **CPU**. Process is **heavy weight**.

<div align="center">
    <img src="process2.JPG" alt="reactive programming" width="700"/>
</div>

<div align="center">
    <img src="thread.JPG" alt="reactive programming" width="700"/>
</div>

- **Threads** whiting process and it can **share** the memory space.

<div align="center">
    <img src="threadExample.PNG" alt="reactive programming" width="700"/>
</div>

1. You can see that **Process** has **Threads**.

<div align="center">
    <img src="scheduler.JPG" alt="reactive programming" width="700"/>
</div>

1. Your OS has scheduler, it will assign threads to the **CPU**.

<div align="center">
    <img src="switch.JPG" alt="reactive programming" width="700"/>
</div>

1. **Scheduler** keeps **switching** threads for process times. We call this **context switch**.
    - Old thread **state** will be stored.

2. We call these threads, **OS threads** or **kernel threads**.

<div align="center">
    <img src="javaThread.PNG" alt="reactive programming" width="700"/>
</div>

1. **Java Thread** is just wrapped to **OS Thread**.

<div align="center">
    <img src="applicationCode.JPG" alt="reactive programming" width="700"/>
</div>

1. `2.` These needs to be stored in **Stack Memory**.

<div align="center">
    <img src="heapVsStack.JPG" alt="reactive programming" width="700"/>
</div>

1. **Heap** stores what objects what we dynamically crate like **ArrayList**, **Method References** etc...
2. Local variables etc. Every Thread is having own **Stack memory**.
3. **Java** will assign different memory size for thread in different context.

<div align="center">
    <img src="problemStatement.JPG" alt="reactive programming" width="700"/>
</div>

1. We should be using threads and CPU efficiently.

> [!IMPORTANT]
> **Reactive Programming** is to simplify this io model.

# [THEORY] - IO Models.
 
- Different types of **Inbound** in **Outbound**.

<div align="center">
     <img src="inboundOutbound.JPG" alt="reactive programming" width="700"/>
</div>

<div align="center">
    <img src="io.JPG" alt="reactive programming" width="700"/>
</div>

1. This was the old way.
2. **Reactive Programming** is model to **simplify** thus non-blocking communication.
3. I will **call** **company**, they will tell "give your number and we will call u back".
    - I give my friends number, so **I** and **my friend** **non-block** or **free to do other tasks**.
        - **My friend** would get a call when **company** would be 
        free.

# [THEORY] - Communication Patterns.

 
<div align="center">
    <img src="whyNotVirtaulThreads.PNG" alt="reactive programming" width="700"/>
</div>

1. Do I need to use **Reactive Programming**, if we have **Virtual Threads**?
    - Below for thought process, which could be helpful.
        - This could be case when, if you **virtual threads** are not enough. 

<div align="center">
    <img src="CommunicationPattern.JPG" alt="reactive programming" width="700"/>
</div>

1. If the **Request** and **Response** is sufficient for your needs, then the **Virtual Threads** are more than enough.
    - Then no need to complicate things with **Reactive Programming**.

<div align="center">
    <img src="reactiveProgrammingOpens.jpeg" alt="reactive programming" width="500"/>
</div>

- But, the **main** difference is that we can achieve additional 4 different patterns with **Reactive Programming**.

<div align="center">
    <img src="CommunicationPatternWithReactive.PNG" alt="reactive programming" width="700"/>
</div>

1. **First one** is **Request** and **Response**.
    - Send **Request** and get **Response**.
2. **Second one** is **Request** and **Streaming Response**.
    - Send **Request** get multiple **Responses**.
3. **Third one** is the **Streaming Request** and **Response**.
     - We send **one requests**, which contains a **stream of messages**.
        - Example. Apple Watch, which sends **hearth rate** to the server.
4. **Fourth one** is the **Bidirectional Streaming**.
    - This two application can stream to each other.
        - Example. **WebSocket**

<div align="center">
    <img src="useCasesForTheDifferentCommunicationPatternsInTheReactiveProgramming.jpeg" alt="reactive programming" width="500"/>
</div>

- In **practice**, this will be tied, to how we will be using the **Mono**'s and **Flux**'s in the code!
    - 1️⃣ `Mono` → `Mono` : **request** → **response**.
    - 2️⃣ `Mono` → `Flux` : **request** → **streaming response**.
    - 3️⃣ `Flux` → `Mono` : **streaming request** → **response**.
    - 4️⃣ `Flux` → `Flux` : **bidirectional streaming**.

- Example in code:

````
// 1. Request → Response
@GetMapping("/user/{id}")
Mono<User> getUser(@PathVariable String id) { ... }

// 2. Request → Streaming Response
@GetMapping(value = "/prices", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
Flux<Price> streamPrices() { ... }
+
// 3. Streaming Request → Response
@PostMapping("/aggregate")
Mono<Result> uploadData(@RequestBody Flux<Input> data) { ... }

// 4. Bidirectional Streaming (WebSocket or RSocket)
@MessageMapping("chat")
Flux<Message> chat(Flux<Message> incoming) { ... }
````
#  [THEORY] - What Is Reactive Programming?

<div align="center">
    <img src="microserviceCommunication.PNG" alt="reactive programming" width="700"/>
</div>

1. There will be coming multiple **requests**!
    - **Client** will be sending the lot of streams of data.
2. **Request** and **Response** is not enough!

<div align="center">
    <img src="reactiveSpesification.PNG" alt="reactive programming" width="700"/>
</div>

1. There is **Specification** for the **Reactive programming model**.


-Todo jatka tästä


# [THEORY] - Reactive Streams Specification.

<div align="center">
    <img src="observerPattern.PNG" alt="reactive programming" width="700"/>
</div>

<div align="center">
    <img src="reactiveProgramming.JPG" alt="reactive programming" width="700"/>
</div>

1. **Reactive programming** 

# [THEORY] - What Is Reactive Programming?

<div align="center">
    <img src="reactiveStream.JPG" alt="reactive programming" width="700"/>
</div>
 
 - Back pressure is important also.

<div align="center">
    <img src="reactiveStream1.JPG" alt="reactive programming" width="700"/>
</div>

<div align="center">
    <img src="reactiveStream2.JPG" alt="reactive programming" width="700"/>
</div>

<div align="center">
    <img src="reactiveProgramming.JPG" alt="reactive programming" width="700"/>
</div>

1. **Reactive Programming** is for **IO** calls.

# [THEORY] - Reactive Streams Specification.

<div align="center">
    <img src="observerPattern.PNG" alt="reactive programming" width="700"/>
</div>

- **Reactive programming** is based on **Observer Pattern**.

- Example **Twitter/X**. One example below:

1. We write comment or re-tweet in **Twitter/X**.
2. My Flowers can see and **react** to written comments. 

- Just **Observer** and **React** for changes.
    - This in high end image.

<div align="center">
    <img src="reactiveStreamSpesification.PNG" alt="reactive programming" width="700"/>
</div>

- These interfaces models how communication will work.

1. `Subscriber` **subscribes** to the `Publisher`. Like in **Twitter/X**, user follows the user to get for latest tweets.
2. Relationship is represents as **Subscription** object.
3. If `Subscriber` wants **request** more updates from `Publisher`.  

- **Twitter/X** is good example for representing this scenario where you have `Subscription` and `Publisher` relationship in **reactive specification**.

<div align="center">
    <img src="reactiveStreamSpesificationProcessor.PNG" alt="reactive programming" width="700"/>
</div>

1. **Processor** is acting like **Subscriber** and **Publisher**.

<div align="center">
    <img src="ProcessorExample.PNG" alt="reactive programming" width="700"/>
</div>

1. This user would be **Processor**.
2. `1.` would be acting as **Subscriber** for the number **2**.
3. `1.` would be acting as **Publisher** for the `3.` the **two** subscribers.

- There will be **Publisher** in the top.
- There can be multiple **Processors** on middle.
- There will be **Subscriber** in the bottom.

<div align="center">
    <img src="hierarchy.PNG" alt="reactive programming" width="700"/>
</div>

1. There will be **Publisher**.
2. There can be multiple **Processors**.
3. There will be **Subscriber**
4. **Subscribes** which one is top.

<div align="center">
    <img src="goodCommunity.PNG" alt="reactive programming" width="700"/>
</div>

1. Reactor specification is having multiple implementations.

# [THEORY] - Publisher/Subscriber Communication - Step By Step.

# This concept is very important!

<div align="center">
    <img src="publisherAndSubscriber.JPG" alt="reactive programming" width="700"/>
</div>

1. There is **two** instances!  ***Subscriber** wants updates from **Publisher**. 

<div align="center">
    <img src="publisherAndSubscriber2.JPG" alt="reactive programming" width="700"/>
</div>

1. When **publisher** accepts the **Subscriber** object, it handles the **publisher** object to the **Subscriber**.

<div align="center">
    <img src="publisherAndSubscriber3.JPG" alt="reactive programming" width="700"/>
</div>

1. **Subscriber** talks to the **Publisher** via **Subscription object**.

<div align="center">
    <img src="publisherAndSubscriber4.JPG" alt="reactive programming" width="700"/>
</div>


1. Using **Subscription** object **Subscriber** gets items one by one to the **Subscriber**.
    - It will give only the **request amount** or less.

<div align="center">
    <img src="publisherAndSubscriber5.JPG" alt="reactive programming" width="700"/>
</div>

- **Publisher** has no items to send, it will invoke `onComplete()`.

<div align="center">
    <img src="publisherAndSubscriber6.JPG" alt="reactive programming" width="700"/>
</div>

- If **Publisher**, is having error returning items to **Subscriber**.

<div align="center">
    <img src="summary.JPG" alt="reactive programming" width="700"/>
</div>

<div align="center">
    <img src="terminologia.JPG" alt="reactive programming" width="700"/>
</div>

<div align="center">
    <img src="pubSub.JPG" alt="reactive programming" width="700"/>
</div>

1. We model this using **classes**.

# Summary.

<div align="center">
    <img src="summary2.JPG" alt="reactive programming" width="700"/>
</div>

1. If we use **non-blocking** communication, we just need **one Thread**.

<div align="center">
    <img src="summary3.JPG" alt="reactive programming" width="700"/>
</div>

1. **Reactive Programming** is having more communication pattern.

<div align="center">
    <img src="summary4.JPG" alt="reactive programming" width="700"/>
</div>



