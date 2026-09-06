# Section 04: Orchestrator Saga: Distributed Transactions & Parallel Workflow.

Section 04: Orchestrator Saga: Distributed Transactions & Parallel Workflow.

# What I Learned.


<div align="center">
    <img src="Orchestrator_Saga_Distributed_Transactions_And_Parallel_Workflow_Intro.JPG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. 

<div align="center">
    <img src="Service_Orchestrator.JPG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. The **order** is **being queried**, the is **multiple downstream services**!
2. There is different order service logic regards to these

<div align="center">
    <img src="Service_Orchestrator_With_Service_Orchestrator.JPG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. There will be some **validation** and **saved into database**!
    - Notice the order is saved as `created`, not `successful` or `failed`! 
2. We can **separate logic** into another service!
    - This can make as **parallel calls**! 
    - Now, if in the `2.1` some service fails, example of the `inventory` is failing!
        - The **orchestrator** needs to coordinate to **roll back** the `shipping` and the `payment`!
            - Cancel the **shipping** and refund the **payment**!

<div align="center">
    <img src="We_Will_Be_Implementing.JPG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

1. We will be implementing this **orchestrator**!

<div align="center">
    <img src="Service_Orchestrator_Menu.JPG" alt="
    Spring webFlux: microservices patterns and advanced resilience!" width="700"/>
</div>

# Orchestrator Pattern - Intro.

# Orchestrator Scope.

# External Services.

# Creating DTO - Part 01.

# Creating DTO - Part 02.

# Creating Service Clients - Part 01.

# Creating Service Clients - Part 02.

# Orchestrator Request Context.

# Util Class.

# Orchestrator Pattern Implementation - High Level Architecture.

# Payment Handler.

# Inventory and Shipping Handlers.

# Order Fulfillment Service.

# Order Cancellation Service.

# Order Orchestrator Service.






- The project code, for **add here** below!


<details>
<summary id="changeThis_patter" open="true"> <b> change this pattern project files!</b> </summary>

#### ReviewClient.java

````Java

````

#### ProductClient.java

````Java

````

#### ProductAggregatorService.java

```Java

```

#### ProductAggregateController.java

```Java

```

#### Product.java

````Java

````

#### ProductAggregate.java

````Java

````

#### Review.java 

````Java

````

</details>
