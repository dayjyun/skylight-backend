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
SkyLight would not be able to takeoff without any of its flight crew and ground support
- **IDE**: [IntelliJ](https://www.jetbrains.com/idea/) is the integrated development environment for software development.
- **Programming Language**: The API is developed using [Java 17](https://www.java.com/en/).
- **BDD**: [Cucumber](https://cucumber.io/) Testing framework is used for Behavior-Driven Development using Gherkin syntax
- **Framework**: The API is built on [Spring Boot](https://spring.io/projects/spring-framework) version 2.7.8.
  - **Server**: The application runs on the Tomcat server.
- **Project Management**: [GitHub Projects](https://github.com/users/dayjyun/projects/8) is utilized for project management and tracking progress.
- **Documentation Tool**: Google Docs is used for planning and documenting.
- **Version Control**: GitHub is used for version control. The codebase can be found at [GitHub Repository](https://github.com/dayjyun/skylight-backend/commits/main).
- **Documentation**: The API documentation is maintained in the [GitHub Wiki](https://github.com/dayjyun/skylight-backend/wiki/SkyLight), providing additional resources and 
  information.
- **Entity Relationship Diagram (ERD)**: [Dbdiagram.io](https://dbdiagram.io/d/64668923dca9fb07c45a10b8) is used to create the ERD for the database design.
- **Database**: The [H2 Database Engine](https://www.geeksforgeeks.org/spring-boot-h2-database/) is used during development.
- **Web Browser**: Google Chrome Browser was used to access the H2 Database Engine.
- **Secret Key Generation**: The secret key generator provided by [GRC](https://www.grc.com/passwords.htm) is used to generate secure secret keys.
- **Application Generator**: [Spring Initializer](https://start.spring.io/) is used to boostrap the project structure. The project is built using Maven.
  - **Packaging**: The spring boot application uses Jar packaging.
- **API Testing**: [Postman](https://www.postman.com/) is used to test the API endpoints and manage the workspace.
- **Markdown Table Generator**: The Markdown table generator available at [Tables Generator](https://www.tablesgenerator.com/markdown_tables) is used to create Markdown tables.
- **AWS**: [Amazon S3](https://aws.amazon.com/) for cloud storage

# Features
- 


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

[Kanban Board](https://github.com/users/dayjyun/projects/8)

# ERD
Take an in-depth look at the diagram's details [link](https://dbdiagram.io/d/64668923dca9fb07c45a10b8)
![](https://skylight-project.s3.amazonaws.com/skyLight_diagram.png)

# Endpoints
## User
| Request Type |               URL               |                  Functionality                   | Access  |
|:------------:|:-------------------------------:|:------------------------------------------------:|:-------:|
|     POST     |       /api/auth/register        |               User creates account               | Public  |
|     POST     |         /api/auth/login         |              User logs into account              | Public  |
|     GET      |         /api/myProfile          |         Returns logged-in user’s account         | Private |
|     PUT      |         /api/myProfile          |                Edit user account                 | Private |
|     GET      |    /api/myProfile/myTickets     |    Returns a list of flights the user booked     | Private |
|     POST     |   /api/myProfile/flyTheSkies    |         User requests to become a pilot          | Private |
|     GET      |       /api/myProfile/air        | Returns a list of flight the admin has submitted | Private |

[//]: # (|     DELETE      | /api/myProfile/logout | User logs out of account | Private |)

## Airports
| Request Type |                     URL                      |                     Functionality                      | Access  |
|:------------:|:--------------------------------------------:|:------------------------------------------------------:|:-------:|
|     GET      |                /api/airports                 |               Returns a list of airports               | Public  |
|     GET      |          /api/airports/{airportId}           |             Returns details of an airport              | Public  |
|     GET      |       /api/airports/code/{airportCode}       | Returns airport details by searching with airport code | Public  |
|     POST     |   /api/airports/code/{airportCode}/origin    |     Admin creates flight origin from airport code      | Private |
|     POST     | /api/airports/code/{airportCode}/destination |   Admin creates flight destination from airport code   | Private |
|     GET      |      /api/airports/{airportId}/arrivals      |   Returns a list of arriving flights for an airport    | Public  |
|     GET      |     /api/airports/{airportId}/departures     |   Returns a list of departing flights for an airport   | Public  |

## Flights
| Request Type | URL                             | Functionality                        | Access  |
|--------------|---------------------------------|--------------------------------------|---------|
| GET          | /api/flights                    | Returns a list of all flights        | Public  |
| GET          | /api/flights/{flightId}         | Returns flight details               | Public  |
| DELETE       | /api/flights/{flightId}         | Admin deletes flight                 | Private |
| GET          | /api/flights/{flightId}/tickets | Get all tickets available for flight | Public  |
| POST         | /api/flights/{flightId}/tickets | Admin creates ticket  for flight     | Private |

## Tickets
| Request Type | URL                                | Functionality          | Access         |
|--------------|------------------------------------|------------------------|----------------|
| GET          | /api/tickets/{ticketId}            | Returns ticket details | Public/Private |
| DELETE       | /api/tickets/{ticketId}            | Deletes booked ticket  | Private        |
| POST         | /api/tickets/{ticketId}/bookFlight | User books flight      | Private        |

# Future Features
 - [ ] Google Maps API for dynamic location (latitude and longitude) and distance rendering
 - [ ] Search airports by city name
 - [ ] More HTTP Requests by using airport code
 - [ ] Set up a payment service
   - [ ] Determine ticket price based on distance  
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
- [ ] Generate class type for tickets:
  - [ ] First Class 
  - [ ] Premium
  - [ ] Business 
  - [ ] Economy 
- [ ] Calendar Integration

# Acknowledgements
A sincere thank you to these developers for providing feedback or simply being a part of fun conversations
- [Suresh Sigera](https://github.com/sureshmelvinsigera)
- [Maksym Zinchenko](https://github.com/maklaut007)
- [Jeff Ou](https://github.com/pophero110)
- [Jaime Padilla](https://github.com/Jaypad07)
- [Dominique Akers](https://github.com/Dommy99)
- [Kim Nguyen](https://github.com/knnguyen2410)
- [Edgar Zambrana](https://github.com/EdgarJoell)