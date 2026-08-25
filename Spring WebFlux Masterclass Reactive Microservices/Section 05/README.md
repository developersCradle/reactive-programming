# Section 05: Reactive CRUD APIs.

Reactive CRUD APIs.

# What I learned.

# FAQ - Do We Need DTOs?

<img src="doWeNeedDTO.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>

1. **Entity**. They represent database table. 
2. **DTO**. API/data we share with the clients.

- These can look the same as Entity. They look duplicate!

<img src="ifSomeThingIsDuplicate.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="500"/>

- So question is not about reusability. If we `name` for different classes, they still represent something different.

<img src="weCanHaveTwoDto.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>

1. We can have two versions of **DTO** for different versions of API!

- So in not question of usability.

- Some kind of Advantages of different **DTO**.

<img src="pluses.PNG" Build Reactive MicroServices using Spring WebFlux/SpringBoot width="700"/>


# DTO / Entity / Repository.