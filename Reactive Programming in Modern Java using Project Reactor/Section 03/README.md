# Section 3: Why Reactive Programming

 Why Reactive Programming?

# What I Learned

# 5. Why Reactive Programming?

<img src="traditionalProgramming.PNG" alt="reactive programming" width="700"/>

1. On **traditional programming** we make call to db and thread get blocked. We call this **Synchronous or Blocking style** of writing code.
2. This type of code **won't** scale well for todays standards needs.

- These are **expectations** application which are developed today:

<img src="whatHasChanged.PNG" alt="reactive programming" width="600"/>

1. This was not case 15years ago!

<img src="RestAPI.PNG" alt="reactive programming" width="600"/>

1. Common that App nowadays has **embedded server!**
2. Common that App has multiple data sources!
3. If these users would ask **App** something. Response time would be `Latency = Summation of (DB + API + API) response times`.
    - Traditional way to achieve **concurrency** is to put **Thread pool** layer to server and provide thread per request. This supports till certain point.
4. When there would be for example **100000** threads for each user. Why would we not have some big thread pool as **100000?**

<img src="threadsInGeneral.PNG" alt="reactive programming" width="600"/>

- For this reason servers comes with fixed size of **thread pool**.

<img src="RestAPI2.PNG" alt="reactive programming" width="600"/>

1. Blocking a **thread** makes thread to wait for response and **not** do any meaningful work.
    - This won't scale todays needs.

# Ask the question, What are tools/api which Java provides?

- From **Java** with Asynchronous/Concurrency APIs, we have:
    - **CallBacks**.
    - **Future**.

<img src="Callbacks.PNG" alt="reactive programming" width="600"/>

- Summary: Callbacks have more **minuses** than **plusses**, for todays needs.

<img src="future.PNG" alt="reactive programming" width="600"/>

1. Allowed to write **Asynchronous Code**.
2. `Future.get()` is getting **actual Result** of asynchronous call.
    - Its **problem** itself. Its **blocking call**, which blocks calling thread.
- Summary: Great addition, but was not for perfect for supporting this programming model.

<img src="flowAPI.PNG" alt="reactive programming" width="600"/>

<br>

<img src="summary.PNG" alt="reactive programming" width="600"/>

- Are there any better way to solve this?
    - **YES**, **Reactive programming**

# 6. What is Reactive Programming ? 

<img src="reactiveProgramming.PNG" alt="reactive programming" width="500"/>

1. Data flows as **Event/Message** stream.
2. This is like Java 8, **Stream API**(lambdas). Reactive programming is **extensions** of functional programming.

<img src="reactiveProgrammingExample.PNG" alt="reactive programming" width="500"/>

- `requestForData()` is received by **db** call is returned **immediately**.
    - This is **not a blocking call** anymore.
    - Calling **thread** is released to do useful work.

1. `request(n)` Request is made and **App** is ready to consume the data.
2. Its followed with **stream of events** when data is ready. This is called the **Reactor stream**. This data is send over with `onNext(n)` function where, **n = number as data**.
3. When data is **finished** `onComplete()` is sent.

- From developers perspective all these **states** are handled by **Reactive library**.

- You can think about this, that **data is pushed from datasource to the caller**, meaning **Push Based data streams model**.
    - Data from -> **DB** -> **App**.

## Backpressure

- If you look previous concept we can see that **App** can easily **overwhelmed** with data.
    - **Backpressure** is to solve this problem.

<img src="backpressure.PNG" alt="reactive programming" width="500"/>

1. Concept where **App** is **controlling** the data flow, is called **Backpressure**. App is calling `cancel()` to end calling for more data. App also could be calling for more data.

<img src="modelsChanges.PNG" alt="reactive programming" width="500"/>

1. With **Backpressure** reactive programming is called **Push-Pull based data flow model**.

<img src="whenUsingReactiveProgramming.PNG" alt="reactive programming" width="500"/>

<br>

<img src="reactiveAppArhitecture.PNG" alt="reactive programming" width="650"/>

1. Requests at server level are needed to handle in **non-blocking** way. **Netty** is popular used for this.
2. Calls also needs to be non-blocking, we are using **Project Reactor**.
    - **Spring WebFlux** uses **Netty** and **Project Reactor** for building non blocking or reactive APIs.

# 7. Introduction to Reactive Streams

- **Reactive Streams** are the foundation for **Reactive programming**.

<img src="reactiveStreams.PNG" alt="reactive programming" width="650"/>

<br>