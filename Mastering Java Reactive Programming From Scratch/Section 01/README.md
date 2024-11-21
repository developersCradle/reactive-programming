# Section 01: Introduction.

Introduction.

# What i learned.


# 1. Before You Enroll...

- This is having lot of hands on.

<img src="targeAudiance.JPG" alt="reactive programming" width="700"/>

- This is for you, if you are working on **Microservices**.

- This will be Slow-paced Course.

<img src="OOPpillars.JPG" alt="reactive programming" width="700"/>

1.  Mastering **reactive programming** comes mastering these concepts.
    - These **cannot** be solved by traditional programming.

# 2. [THEORY] - Process / Thread / CPU / RAM / Scheduler.

<img src="process.JPG" alt="reactive programming" width="700"/>

1. Instance of program is loaded into **memory**. This is executed in **CPU**. Process is **heavy weight**.

<img src="process2.JPG" alt="reactive programming" width="700"/>

<img src="thread.JPG" alt="reactive programming" width="700"/>

- **Threads** whit in process **share** the memory space.

<img src="scheduler.JPG" alt="reactive programming" width="700"/>

1. Your OS has scheduler, it will assigns time for threads to process inside **CPU**.

<img src="switch.JPG" alt="reactive programming" width="700"/>

1. **Scheduler** keeps **switching** threads for process times. We call this **context switch**.

- Old thread **state** will be stored.

2. We call these **threads** OS threads or kernel threads.

<img src="javaThread.JPG" alt="reactive programming" width="700"/>

- **Java Thread** is just wrapped to **OS Thread**.

<img src="applicationCode.JPG" alt="reactive programming" width="700"/>

1. `2.` These needs to be stored in **Stack Memory**.

<img src="heapVsStack.JPG" alt="reactive programming" width="700"/>

1. **Heap** stores what objects what we dynamically crate. **ArrayList**, **Method References** etc... .
2. Local variables etc. Every Thread is having own **Stack memory**.
3. **Java** will assign different memory size for thread in different context.

<img src="problemStatement.JPG" alt="reactive programming" width="700"/>

# 3. [THEORY] - IO Models.
 
 - Different types of **Inbound** in **Outbound**.

 <img src="inboundOutbound.JPG" alt="reactive programming" width="700"/>

<img src="io.JPG" alt="reactive programming" width="700"/>

1. This was the old way.
2. **Reactive Programming** is model to **simplify** thus non-blocking communication.
3. I will **call** **company**, they will tell "give your number and we will call u back".
    - I give my friends number, so **I** and **my friend** **non-block** or **free to do other tasks**.
        - **My friend** would get a call when **company** would be 
        free.

# 4. [THEORY] - Communication Patterns.

- Do i need to use **Reactive Programming**.

<img src="CommunicationPattern.JPG" alt="reactive programming" width="700"/>

1. For **One** and **One** Response. If this is siple case, no need to get with complicated with **reactive programming**.

<img src="CommunicationPatternWithReactiveJPG.JPG" alt="reactive programming" width="700"/>

- We can achieve additional 4 different patterns with **Reactive Programming**.

1. We send request for making pizza. 
    - We get **stream** like answers, pizza is in making.
    - Pizza is being delivered.
- We send **one** request and getting.

2. Messages are sent as stream to remote server.

<img src="observerPattern.PNG" alt="reactive programming" width="700"/>

<img src="reactiveProgramming.JPG" alt="reactive programming" width="700"/>

1. **reactive programming** 

# 5. [THEORY] - What Is Reactive Programming?

<img src="reactiveStream.JPG" alt="reactive programming" width="700"/>
 
 - Back pressure is important also.

<img src="reactiveStream1.JPG" alt="reactive programming" width="700"/>

<img src="reactiveStream2.JPG" alt="reactive programming" width="700"/>

<img src="reactiveProgramming.JPG" alt="reactive programming" width="700"/>

1. **Reactive Programming** is for **IO** calls.

# 6. [THEORY] - Reactive Streams Specification.

<img src="observerPattern.PNG" alt="reactive programming" width="700"/>

- **Reactive programming** is based on **Observer Pattern**.

- Example **Twitter/X**. One example below:

1. We write comment or re-tweet in **Twitter/X**.
2. My Flowers can see and **react** to written comments. 

- Just **Observer** and **React** for changes.
    - This in high end image.

<img src="reactiveStreamSpesification.PNG" alt="reactive programming" width="700"/>

- These interfaces models how communication will work.

1. `Subscriber` **subscribes** to the `Publisher`. Like in **Twitter/X**, user follows the user to get for latest tweets.
2. Relationship is represents as **Subscription** object.
3. If `Subscriber` wants **request** more updates from `Publisher`.  

- **Twitter/X** is good example for representing this scenario where you have `Subscription` and `Publisher` relationship in **reactive specification**.

<img src="reactiveStreamSpesificationProcessor.PNG" alt="reactive programming" width="700"/>

1. **Processor** is acting like **Subscriber** and **Publisher**.

<img src="ProcessorExample.PNG" alt="reactive programming" width="700"/>

1. This user would be **Processor**.
2. `1.` would be acting as **Subscriber** for the the number **2**.
3. `1.` would be acting as **Publisher** for the `3.` the **two** subscribers.

- There will be **Publisher** in the top.
- There can be multiple **Processors** on middle.
- There will be **Subscriber** in the bottom.

<img src="hierarchy.PNG" alt="reactive programming" width="700"/>

1. There will be **Publisher**.
2. There can be multiple **Processors**.
3. There will be **Subscriber**
4. **Subscribes** which one is top.

<img src="goodCommunity.PNG" alt="reactive programming" width="700"/>

1. Reactor specification is having multiple implementations.

# 7. [THEORY] - Publisher/Subscriber Communication - Step By Step.

# This concept is very important!

<img src="publisherAndSubscriber.JPG" alt="reactive programming" width="700"/>

1. There is **two** instances!  ***Subscriber** wants updates from **Publisher**. 

<img src="publisherAndSubscriber2.JPG" alt="reactive programming" width="700"/>

1. When **publisher** accepts the **Subscriber** object, it handles the **publisher** object to the **Subscriber**.

<img src="publisherAndSubscriber3.JPG" alt="reactive programming" width="700"/>

1. **Subscriber** talks to the **Publisher** via **Subscription object**.

<img src="publisherAndSubscriber4.JPG" alt="reactive programming" width="700"/>

1. Using **Subscription** object **Subscriber** gets items one by one to the **Subscriber**.
    - It will give only the **request amount** or less.

<img src="publisherAndSubscriber5.JPG" alt="reactive programming" width="700"/>

- **Publisher** has no items to send, it will invoke `onComplete()`.

<img src="publisherAndSubscriber6.JPG" alt="reactive programming" width="700"/>

- If **Publisher**, is having error returning items to **Subscriber**.

<img src="summary.JPG" alt="reactive programming" width="700"/>

<img src="terminologia.JPG" alt="reactive programming" width="700"/>

<img src="pubSub.JPG" alt="reactive programming" width="700"/>

1. We model this using **classes**.

# 8. Summary

<img src="summary2.JPG" alt="reactive programming" width="700"/>

1. If we use **non-blocking** communication, we just need **one Thread**.

<img src="summary3.JPG" alt="reactive programming" width="700"/>

1. **Reactive Programming** is having more communication pattern.

<img src="summary4.JPG" alt="reactive programming" width="700"/>


