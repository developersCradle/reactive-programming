# Section 09: Combining Publishers.

Combining Publishers.

# What I learned.

# Introduction.

<div align="center">
    <img src="multiplePublishers.PNG" alt="reactive programming" width="700"/>
</div>

1. Often times **microarchitecture** has multiple sources of data.
    - Many times backend will ask from **multiple sources** and collects aggregate data for the **front end**.

- The **point** here is that:
    - For one front end request.
        - There is usually multiple smaller request in backend.
            - These minor request can have specific order and shape of data.
            
- Some popular options for transforming data.

<div align="center">
    <img src="options.PNG" alt="reactive programming" width="400"/>
</div>

# FlatMap - Introduction.

<div align="center">
    <img src="What_Is_Flat_Map_Operation.PNG" alt="reactive programming" width="500"/>
</div>

1. These operations will **not** work for the **dependent sequential calls!** 
    - These are producing results as **independent**! Meaning the results, **don't rely** on an others results!
        - Example of independent producers:
            - `startWith`.
            - `concat`.
            - `merge`.
            - `zip`.

- What **independent** means in this context:
    - **Request A** does not need data from **Request B**.
    - **Request B** does not need data from **Request A**.

2. What is happening when we want something from **previous call**? We can use `.flatMap()` in such cases!

- In **project reactor** `.flatMap()` is used when the next call depends on a **value produced** by the previous call. Example below:

 ````Java
getUser()
.flatMap(user -> getOrdersByUserId(user.getId()))
.flatMap(orders -> calculateDiscount(orders))
.flatMap(discount -> saveDiscount(discount))
.subscribe();
````

- `getUser()` is executed and **emits** a `User`
    - The **first** `.flatMap()`:
        - Receives the `User`
        extracts `user.getId()` and
        calls `getOrdersByUserId(user.getId())` and emits `Orders`.
    - The **second** `.flatMap()`:
        - Receives the `Orders`
        passes them to `calculateDiscount(orders)` and emits a `Discount`
    - The **third** `.flatMap()`:
        - Receives the `Discount`
        passes it to `saveDiscount(discount)` emits the final result!

<br>

- Todo make links for these operations

<div align="center">
    <img src="dependentSequantialCall.PNG" alt="reactive programming" width="400"/>
</div>
