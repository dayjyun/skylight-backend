# Table Of Contents
- [SkyLight](#skylight)

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

# Endpoints
## User
| Request Type |                URL                |                  Functionality                  | Access  |
|:------------:|:---------------------------------:|:-----------------------------------------------:|:-------:|
|     POST     |        /api/auth/register         |              User creates account               | Public  |
|     POST     |          /api/auth/login          |             User logs into account              | Public  |
|     GET      |          /api/myProfile           |        Returns logged-in user’s account         | Private |
|     PUT      |          /api/myProfile           |                Edit user account                | Private |
|     GET      |      /api/myProfile/flights       |    Returns a list of flights the user booked    | Private |
|     GET      |        /api/myProfile/air         | Returns a list of flight the user has submitted | Private |

[//]: # (|     GET      | /api/myProfile/flights/{flightId} | Get details of a flight from list of flights purchased | Private |)

[//]: # (|    DELETE    | /api/myProfile/flights/{flightId} |      Delete flight from list of flights purchased      | Private |)

## Credit Cards
| Request Type | URL            | Functionality                         | Access  |
|--------------|----------------|---------------------------------------|---------|
| GET          | /api/cc        | Gets Credit Card details for the user | Private |
| POST         | /api/cc        | Create new credit card data           | Private |
| PUT          | /api/cc/{ccId} | User updates credit card data         | Private |
| DELETE       | /api/cc/{ccId} | User deletes credit card data on file | Private |

## Flights
| Request Type | URL                                        | Functionality                 | Access  |
|--------------|--------------------------------------------|-------------------------------|---------|
| GET          | /api/flights                               | Returns a list of all flights | Public  |
| POST         | /api/flights                               | Users create a new flight     | Private |
| GET          | /api/flights/{flightId}                    | Returns flight details        | Public  |
| DELETE       | /api/flights/{flightId}                    | Delete flight                 | Private |
| GET          | /api/flights/{flightId}/tickets            | Get all tickets for flight    | Private |
| POST         | /api/flights/{flightId}/tickets            | Purchase ticket for flight    | Private |
| DELETE       | /api/flights/{flightId}/tickets/{ticketId} | Delete ticket for flight      | Private |

## Tickets
| Request Type | URL                     | Functionality          | Access  |
|--------------|-------------------------|------------------------|---------|
| GET          | /api/tickets/{ticketId} | Returns ticket details | Private |
| DELETE       | /api/tickets/{ticketId} | Deletes ticket         | Private |

## Airports
| Request Type |            URL            |         Functionality         | Access |
|:------------:|:-------------------------:|:-----------------------------:|:------:|
|     GET      |       /api/airports       |  Returns a list of airports   | Public |
|     GET      | /api/airports/{airportId} | Returns details of an airport | Public |

# Diagram
![](https://skylight-project.s3.amazonaws.com/SkyLight_Diagram.png) 