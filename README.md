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
Skylight is the fly-share service and the ultimate destination for effortless flight exploration and booking. With a user-friendly platform, personalized profiles, and 
comprehensive features,
your flight experience has never been more convenient. Jump on board to your next destination or even have the chance to fulfill your dream of becoming a pilot. Discover, book, 
and go! Skylight Airways - your window to the sky.

Be sure to checkout the [Frontend Repo](https://github.com/dayjyun/skylight-frontend)!

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
- User Registration and Login: Users can create an account on Skylight Airways by registering with their personal details. Once registered, users can log in to their accounts to access personalized features.

- User Profile Management: Users have access to a personalized profile where they can view and edit their account information, ensuring their details are up-to-date.

- Flight Booking Management: Users can browse through a list of available flights, view flight schedules, destinations, and other relevant information, and book tickets for their preferred flights.

- Flight Tracking: Users have the convenience of accessing a list of flights they have booked, allowing them to easily track and manage their travel plans.

- Pilot Request Submission: Aspiring pilots can submit a request through the website, expressing their interest in becoming pilots and starting their journey towards achieving 
their dream.

- Airport Information: Users can explore comprehensive information about various airports, including location, facilities, and services provided. They can also search for 
airports based on their unique airport codes.

- Administrative Privileges: Administrators have exclusive privileges to create and manage flights. They can add new flights to the system, update flight information, and delete 
flights if necessary.

- Ticket Cancellation: Users can easily cancel their booked tickets if needed. They can navigate to their booked tickets and initiate the cancellation process, receiving prompt 
updates on the status of their cancellation.

- User Authentication and Security: Certain functionalities and features require users to be authenticated and logged in to access them, ensuring privacy and security of 
sensitive information.


# Run Application
To get started, simply clone the website, run the application in your preferred Java Environment, and you're all set! Be sure to check the `pom.xml` file contains the list of dependencies below. 

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
Effective planning was crucial for the success of the project. While I had a clear vision of the end goal and a starting point, I knew that the journey would be the most 
challenging part. To streamline my thoughts and ideas, I created a Google Doc as a scratch file, allowing me to capture and refine my implementation strategies. It often 
required clarity and restructuring, but the diagram below represents the culmination of the entire thought process. Check out the 
[user Stories](https://github.com/dayjyun/skylight-backend/wiki/SkyLight) for a full list of things to do. While you're at it, sneak in to the [Kanban Board]
(https://github.com/users/dayjyun/projects/8)
to see the entire work structure! 
# ERD
The successful outcome of the project hinged on the central role of the **Flights** model, which served as the driving component connecting all other models. **Flights** played a 
pivotal role, as without them, airports would be abandoned, air travel for users would be impossible, and ticket sales would be nonexistent.

The relationship between **Flights** and **Airports** is uniquely characterized by two one-to-many connections. This design choice preserves a distinct relationship between the 
two 
objects within the same model, ensuring the integrity and coherence of the data.
Take an in-depth look at the diagram's details [here](https://dbdiagram.io/d/64668923dca9fb07c45a10b8)
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

While the endpoints may seem like a lot, each one of these endpoints play a crucial role in managing as a collective structure. Besides, airports aren't a walk in the park either .
<img src="https://skylight-project.s3.amazonaws.com/frontend/bk-destination.png" width="400" alt="Destination Image">
<img src="https://skylight-project.s3.amazonaws.com/frontend/bk-flight-details.png" width="400" alt="Flight Details Image">

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