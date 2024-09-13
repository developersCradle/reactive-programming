# Section 01: Introduction.

Introduction.

# What i learned.

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