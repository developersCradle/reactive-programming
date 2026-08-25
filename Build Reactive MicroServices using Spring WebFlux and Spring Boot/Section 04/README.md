# Section 04: Introduction to Reactive Programming: 

Introduction to Reactive Programming.

# What I Learned,

# What is Reactive Programming?

<div align="center">
    <img src="whatIsReactiveProgramming.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

1. Any code we write will be **Asynchronous** and **non-blocking**.

<div align="center">
    <img src="reactiveProgramming1.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

1. As soon as app request serer for data, database return call imminently.
    - This is **not** blocking call anymore, thread is released for other business.

<div align="center">
    <img src="reactiveProgramming2.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

1. **App** tells to **database** that it's ready for data. 

<div align="center">
    <img src="reactiveProgramming3.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

1. After request in made, data is sent in the form of the reactor stream.

<div align="center">
    <img src="reactiveProgramming4.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="600"/>
</div>

1. When all data is send to **App**. It will send **onComplete**.
2. Push Based data streams model. Data is **pushed** **source** to **caller**.

- Keep in mind these streams are not the same as **Stream API in Java 8**.

<div align="center">
    <img src="backpressure.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

1. When **source** don't need data anymore. It can cancel the data. Here example two data number. 

<div align="center">
    <img src="whenToUseReactiveProgramming.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

<br>

<div align="center">
    <img src="reactiveProgrammingArchitecture.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBootes using Spring WebFlux/SpringBoot width="700"/>
</div>

1. **Netty** can handle requests in **non blocking style**. It is based on **Event Loop Model**.
2. Code also needs to be in **non-blocking** way.
3. Spring WebFlux uses **Netty** as default and **Project reactor**.

# Introduction to Reactive Streams.

- Reactive Streams are foundation for Reactive programming.

<div align="center">
    <img src="reactiveStreams.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

<br>

<div align="center">
    <img src="reativeStreamSpesification.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

1. Specification has 4 interfaces.

<div align="center">
    <img src="publisher.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

<br>

<div align="center">
    <img src="subscriber.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

<br>

<div align="center">
    <img src="subscription.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

<br>

<div align="center">
    <img src="reactiveStreamsSuccessScenario.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

<br>

<div align="center">
    <img src="reactiveStreamsErrorScenario.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

<br>

<div align="center">
    <img src="Processor.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

<br>

<div align="center">
    <img src="FlowApi.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>
</div>

