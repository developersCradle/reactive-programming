# Section 3: Why Reactive Programming ? 

Why Reactive Programming ?

# What I Learned

# 5. Why Reactive Programming - Part 1?

<img src="evolutionOfProgramming.PNG" alt="reactive programming" width="700"/>

- This is today, how application is today.

<img src="expectationOfApplication.PNG" alt="reactive programming" width="700"/>

<br>

<img src="restfullApi.PNG" alt="reactive programming" width="700"/>

1. This type of API blocking API. Thread will be assignment for query when requested from server software.

<img src="restfullApi2.PNG" alt="reactive programming" width="700"/>

- Lets see what limitations **Spring MVC** has.

<img src="limationOfSpringMVC.PNG" alt="reactive programming" width="700"/>

1. There is default limit for thread pool size **200**.

- Lets see why there is no infinite amount of threads.

<img src="threadsAndLimitations.PNG" alt="reactive programming" width="700"/>

<br>

<img src="redusingLatencyOfTheApp.PNG" alt="reactive programming" width="700"/>

1. We can also reduce latency of the app, by improving indie our app.

# 6. Why Reactive Programming - Part 2?

- To make Java calls work in parallel we can use.
    - Callbacks.
    - Futures.

<img src="callbacks.PNG" alt="reactive programming" width="700"/>

<br>

<img src="concurrencyAPI.PNG" alt="reactive programming" width="700"/>

<br>

<img src="drawbackOfSpirgMVC.PNG" alt="reactive programming" width="700"/>

1. Even thought you write efficient code, **Servlet API** will be the bottleneck.

- **Reactive programming** will come to solve this problem.

<img src="summary.PNG" alt="reactive programming" width="700"/>

1. This does not mean that Spring MVC is still bad.


