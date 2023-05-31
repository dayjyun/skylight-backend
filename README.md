# Endpoints

## User
| Request Type |          URL           |                  Functionality                  | Access  |
|:------------:|:----------------------:|:-----------------------------------------------:|:-------:|
|     POST     |   /api/auth/register   |              User creates account               | Public  |
|     POST     |    /api/auth/login     |             User logs into account              | Public  |
|     GET      |     /api/myProfile     |        Returns logged-in user’s account         | Private |
|     PUT      |     /api/myProfile     |                Edit user account                | Private |
|     GET      | /api/myProfile/flights |  Returns a list of flights the user purchased   | Private |
|     GET      |   /api/myProfile/air   | Returns a list of flight the user has submitted | Private |

## Credit Cards
| Request Type | URL            | Functionality                         | Access  |
|--------------|----------------|---------------------------------------|---------|
| GET          | /api/cc        | Gets Credit Card details for the user | Private |
| POST         | /api/cc        | Create new credit card data           | Private |
| PUT          | /api/cc/{ccId} | User updates credit card data         | Private |
| DELETE       | /api/cc/{ccId} | User deletes credit card data on file | Private |

## Flights
| Request Type | URL                                                                                                       | Functionality                                                        | Access  |
|--------------|-----------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|---------|
| GET          | /api/flights                                                                                              | Returns a list of all flights                                        | Public  |
| POST         | /api/flights                                                                                              | Users create a new flight                                            | Private |
| GET          | /api/flights/{flightId}                                                                                   | Returns flight details                                               | Public  |
| POST         | /api/flights/{flightId}                                                                                   | Purchase ticket for flight                                           | Private |
| DELETE       | /api/flights/{flightId}                                                                                   | Delete flight                                                        | Private |

## Airports
| Request Type |           URL            |         Functionality         | Access |
|:------------:|:------------------------:|:-----------------------------:|:------:|
|     GET      |      /api/airports       |  Returns a list of airports   | Public |
|     GET      | /api/airport/{airportId} | Returns details of an airport | Public |

# Diagram
![](https://skylight-project.s3.amazonaws.com/SkyLight_Diagram.png)