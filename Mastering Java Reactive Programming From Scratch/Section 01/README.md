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

1.  Mastering **Reactive programming** comes mastering these concepts.
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

- Do I need to use **Reactive Programming**.

<div align="center">
    <img src="CommunicationPattern.JPG" alt="reactive programming" width="700"/>
</div>

1. For **One** and **One** Response. If this is simple case, no need to get with complicated with **reactive programming**.

<div align="center">
    <img src="CommunicationPatternWithReactiveJPG.JPG" alt="reactive programming" width="700"/>
</div>

- We can achieve additional 4 different patterns with **Reactive Programming**.

1. We send request for making pizza. 
    - We get **stream** like answers, pizza is in making.
    - Pizza is being delivered.
- We send **one** request and getting.

2. Messages are sent as stream to remote server.

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
2. `1.` would be acting as **Subscriber** for the the number **2**.
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



