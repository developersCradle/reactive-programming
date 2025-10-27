# Section 05: Introduction to Spring WebFlux

Introduction to Spring WebFlux.

# What I Learned.

# Non-Blocking(Reactive) RestFul API using Spring WebFlux.

<div align="center">
    <img src="nonBlocking.PNG" alt="reactive programming" width="700"/>
</div>

- **Non-blocking** means that requests thread are not blocked when there is **request** or **response**.

<div align="center">
    <img src="SpringWebFluxApp.PNG" alt="reactive programming" width="700"/>
</div>

1. **Netty** is important to enabled in server level.
2. Other point that needs to be addressed by non-blocking. Is database and APIClient. This is taken care by **Project Reactor**.