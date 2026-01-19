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
    <img src="FlatMapOperation.PNG" alt="reactive programming" width="400"/>
</div>

1. These operations will **not** work, if there are multiple sources of calls.

<div align="center">
    <img src="dependentSequantialCall.PNG" alt="reactive programming" width="400"/>
</div>

1. We want user **ID**, do we need to wait previous one finish to call the next microservice call?