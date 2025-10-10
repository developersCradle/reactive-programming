# Section 3: Why Reactive Programming? 

Why Reactive Programming?

# What I Learned.

# Why Reactive Programming - Part 1?

<div align="center">
    <img src="evolutionOfProgramming.PNG" alt="reactive programming" width="700"/>
</div>

- This is today, how application is today.

<div align="center">
    <img src="expectationOfApplication.PNG" alt="reactive programming" width="700"/>
</div>

<br>

<div align="center">
    <img src="restfullApi.PNG" alt="reactive programming" width="700"/>
</div>

1. This type of API blocking API. Thread will be assignment for query when requested from server software.

<div align="center">
    <img src="restfullApi2.PNG" alt="reactive programming" width="700"/>
</div>

- Let's see what limitations **Spring MVC** has.

<div align="center">
    <img src="limationOfSpringMVC.PNG" alt="reactive programming" width="700"/>
</div>

1. There is default limit for thread pool size **200**.

- Let's see why there is no infinite amount of threads.

<div align="center">
    <img src="threadsAndLimitations.PNG" alt="reactive programming" width="700"/>
</div>

<br>

<div align="center">
    <img src="redusingLatencyOfTheApp.PNG" alt="reactive programming" width="700"/>
</div>

1. We can also reduce latency of the app, by improving communication inside our app.

# Why Reactive Programming - Part 2?

- To make Java calls work in parallel we can use.
    - Callbacks.
    - Futures.

<div align="center">
    <img src="callbacks.PNG" alt="reactive programming" width="700"/>
</div>

<br>

<div align="center">
    <img src="concurrencyAPI.PNG" alt="reactive programming" width="700"/>
</div>

<br>

<div align="center">
    <img src="drawbackOfSpirgMVC.PNG" alt="reactive programming" width="700"/>
</div>

1. Even thought you write efficient code, **Servlet API** will be the bottleneck.

- **Reactive programming** will come to solve this problem.

<div align="center">
    <img src="summary.PNG" alt="reactive programming" width="700"/>
</div>

1. This does not mean that Spring MVC is still bad.


