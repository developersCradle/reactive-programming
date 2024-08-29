# Section 04: Introduction to Reactive Programming: 

Introduction to Reactive Programming.

# What I Learned

# 7. What is Reactive Programming?

<img src="whatIsReactiveProgramming.PNG" alt="reactive programming" width="700"/>

1. Any code we write will be **Asynchronous** and **non-blocking**.

<img src="reactiveProgramming1.PNG" alt="reactive programming" width="700"/>

1. As soon as app request serer for data, db return call imminently.
    - This is **not** blocking call anymore, thread is released for other business.

<img src="reactiveProgramming2.PNG" alt="reactive programming" width="700"/>

1. **App** tells to **db** that its ready for data. 

<img src="reactiveProgramming3.PNG" alt="reactive programming" width="700"/>

1. After request in made, data is send in form of reactor stream.

<img src="reactiveProgramming4.PNG" alt="reactive programming" width="600"/>

1. When all data is send to **App**. It will send **onComplete**.
2. Push Based data streams model. Data is **pushed** **source** to **caller**.

- Keep in mind these streams are not the same as **Stream API in Java 8**.

<img src="backpressure.PNG" alt="reactive programming" width="700"/>

1. When **source** don't need data anymore. It can cancel the data. Here example two data number. 

<img src="whenToUseReactiveProgramming.PNG" alt="reactive programming" width="700"/>

<br>

<img src="reactiveProgrammingArchitecture.PNG" alt="reactive programming" width="700"/>

1. **Netty** can handle requests in **non blocking style**. It is based on **Event Loop Model**.
2. Code also needs to be in **non-blocking** way.
3. Spring WebFlux uses **Netty** as default and **Project reactor**.

# 8. Introduction to Reactive Streams

- Reactive Streams are foundation for Reactive programming.

<img src="reactiveStreams.PNG" alt="reactive programming" width="700"/>

<br>

<img src="reativeStreamSpesification.PNG" alt="reactive programming" width="700"/>

1. Specification has 4 interfaces.

<img src="publisher.PNG" alt="reactive programming" width="700"/>

<br>

<img src="subscriber.PNG" alt="reactive programming" width="700"/>

<br>

<img src="subscription.PNG" alt="reactive programming" width="700"/>

<br>

<img src="reactiveStreamsSuccessScenario.PNG" alt="reactive programming" width="700"/>

<br>

<img src="reactiveStreamsErrorScenario.PNG" alt="reactive programming" width="700"/>

<br>

<img src="Processor.PNG" alt="reactive programming" width="700"/>

<br>

<img src="FlowApi.PNG" alt="reactive programming" width="700"/>
