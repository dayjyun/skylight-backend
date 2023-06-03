# Table Of Contents
- [SkyLight](#skylight)
- [Technologies](#technologies)
- [Features](#features)
- [Run Application](#run-application)
- [Approach](#approach)
- [Entity-Relationship Diagram](#erd)
- [Endpoints](#endpoints)
    - [User](#user)
    - [Airports](#airports)
    - [Flights](#flights)
    - [Tickets](#tickets)
- [Future Features](#future-features)
- [Acknowledgements](#acknowledgements)

# SkyLight

# Technologies

# Features

# Run Application

<details>
    <summary>Dependencies</summary>

- [Spring Boot Starter Data REST](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-rest)
- [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
- [H2 Database Engine](https://mvnrepository.com/artifact/com.h2database/h2)
- [Spring Boot Starter JDBC](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-jdbc)
- [Spring Boot Starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)
- [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
- [Spring Boot Starter Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
- [JUnit](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
- [Cucumber Java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java)
- [Cucumber JUnit](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
- [Cucumber Spring](https://mvnrepository.com/artifact/io.cucumber/cucumber-spring)
- [REST Assured](https://mvnrepository.com/artifact/io.rest-assured/rest-assured)
- [Spring Boot Starter Validation](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation)
- [jjwt-api](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api)
- [jjwt-impl](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl)
- [jjwt-jackson](https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson)
</details>

# Approach
[User Stories](https://github.com/dayjyun/skylight-backend/wiki/SkyLight)

[Kanban Board](https://github.com/users/dayjyun/projects/8])

# ERD
Link to [diagram](https://dbdiagram.io/d/64668923dca9fb07c45a10b8)
![](https://skylight-project.s3.amazonaws.com/skyLight_diagram_2.png)

# Endpoints
## User
| Request Type |           URL            |                  Functionality                   | Access  |
|:------------:|:------------------------:|:------------------------------------------------:|:-------:|
|     POST     |    /api/auth/register    |               User creates account               | Public  |
|     POST     |     /api/auth/login      |              User logs into account              | Public  |
|     GET      |      /api/myProfile      |         Returns logged-in user’s account         | Private |
|     PUT      |      /api/myProfile      |                Edit user account                 | Private |
|     GET      | /api/myProfile/myTickets |    Returns a list of flights the user booked     | Private |
|     GET      |    /api/myProfile/air    | Returns a list of flight the admin has submitted | Private |

[//]: # (|     DELETE      | /api/myProfile/logout | User logs out of account | Private |)

## Airports
| Request Type |                 URL                  |                        Functionality                         | Access |
|:------------:|:------------------------------------:|:------------------------------------------------------------:|:------:|
|     GET      |            /api/airports             |                  Returns a list of airports                  | Public |
|     GET      |      /api/airports/{airportId}       |                Returns details of an airport                 | Public |
|     GET      |   /api/airports/code/{airportCode}   | Returns details of an airport by searching with airport code | Public |
|     GET      |  /api/airports/{airportId}/arrivals  |      Returns a list of arriving flights for an airport       | Public |
|     GET      | /api/airports/{airportId}/departures |      Returns a list of departing flights for an airport      | Public |

## Flights
| Request Type | URL                             | Functionality                        | Access  |
|--------------|---------------------------------|--------------------------------------|---------|
| GET          | /api/flights                    | Returns a list of all flights        | Public  |
| POST         | /api/flights                    | Admin creates a new flight           | Private |
| GET          | /api/flights/{flightId}         | Returns flight details               | Public  |
| DELETE       | /api/flights/{flightId}         | Admin deletes flight                 | Private |
| GET          | /api/flights/{flightId}/tickets | Get all tickets available for flight | Private |
| POST         | /api/flights/{flightId}/tickets | Admin creates ticket  for flight     | Private |

## Tickets
| Request Type | URL                                | Functionality          | Access  |
|--------------|------------------------------------|------------------------|---------|
| GET          | /api/tickets/{ticketId}            | Returns ticket details | Public  |
| DELETE       | /api/tickets/{ticketId}            | Deletes booked ticket  | Private |
| POST         | /api/tickets/{ticketId}/bookFlight | User books flight      | Private |

# Future Features
 - [ ] Set up a payment service
### Credit Cards
| Request Type | URL            | Functionality                         | Access  |
|--------------|----------------|---------------------------------------|---------|
| GET          | /api/cc        | Gets Credit Card details for the user | Private |
| POST         | /api/cc        | Create new credit card data           | Private |
| PUT          | /api/cc/{ccId} | User updates credit card data         | Private |
| DELETE       | /api/cc/{ccId} | User deletes credit card data on file | Private |

- [ ] Create miniature banking application for Credit Cards
- [ ] Create a model for planes
  - [ ] Airplane model/type
  - [ ] Number of seats
  - [ ] Belongs to pilot
- [ ] Generate class type:
  - [ ] First Class 
  - [ ] Premium
  - [ ] Business 
  - [ ] Economy 
- [ ] Calendar Integration

# Acknowledgements