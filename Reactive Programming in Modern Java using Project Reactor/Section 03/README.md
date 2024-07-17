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