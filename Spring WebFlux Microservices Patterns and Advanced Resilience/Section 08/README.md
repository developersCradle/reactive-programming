# Section 08: Reactive Resilience: Retry Pattern for Fault Tolerance.

Section 08: Reactive Resilience: Retry Pattern for Fault Tolerance.

# What I Learned.

# Retry Pattern - Intro.

<div align="center">
    <img src="Retry_Pattern_Intro.PNG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. Retry is just one resilience pattern!

<div align="center">
    <img src="Retry_Properties.PNG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. In microservice world, there can be **multiple network** calls! There can be multiple calls.

<div align="center">
    <img src="Retry.PNG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. Service.
2. Load Balancer.
3. If one instance of service is down, might try to make the **retry** to **another instance**!
    - Same error is not happing from same service!

<div align="center">
    <img src="Product_Details_Page.PNG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. There will be **some issues** in ether to review service!
    - We will be implementing the retry pattern!

# External Services.

<div align="center">
    <img src="Endpoint_How_This_Will_Be_Tested.PNG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. We will be testing this endpoint:
    - `70%` of the requests will fail. All requests take up to `30ms`!

<div align="center">
    <img src="Testing_The_Endpoint_Of_70_Percent_Of_The_Failing.gif" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. We can see most of these failing, it should be around the **70%** times!

# Project Setup.

- We are testing this, with the external service:

<div align="center">
    <img src="Testing_The_Endpoint_Of_70_Percent_Of_The_Failing_In_The_PostMan_Second.gif" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. We can see it fails sometimes!


- add code here!

# Retry Pattern Implementation Demo.

- We are testing this, with the external service:

<div align="center">
    <img src="Testing_The_Endpoint_Of_70_Percent_Of_The_Failing_In_The_PostMan.gif" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

- We can see that this endpoint not always returning something!
    - It is `70%` of the **time failing**!

<div align="center">
    <img src="Example_Of_Failing_The_Query_Which_Is_Not_Found.PNG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. We can see that there is no point of **retrying** something that is not found!
    - Such as client side of error!

# 4XX Issue Fix.

# Quick Note On Retry Spec.

# Summary.