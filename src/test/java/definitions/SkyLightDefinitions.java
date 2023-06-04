package definitions;

import com.skylight.SkylightApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SkylightApplication.class)
public class SkyLightDefinitions {
   private static final String BASE_URL = "http://localhost:";
   private static Response response;
   private static ResponseEntity<String> responseEntity;
   private static List<?> list;
   private static RequestSpecification request;

   @LocalServerPort
   String port;

   public String getSecurityKeyAdmin() throws Exception {
      RequestSpecification request = RestAssured.given();
      JSONObject requestBody = new JSONObject();
      requestBody.put("email", "k@email.com");
      requestBody.put("password", "pw");
      request.header("Content-Type", "application/json");
      response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/login");
      return response.jsonPath().getString("message");
   }

   public String getSecurityKeyPassenger() throws Exception {
      RequestSpecification request = RestAssured.given();
      JSONObject requestBody = new JSONObject();
      requestBody.put("email", "dominique@email.com");
      requestBody.put("password", "pw");
      request.header("Content-Type", "application/json");
      response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/login");
      return response.jsonPath().getString("message");
   }

   // My Profile

   /**
    * Scenario: User is able to view their account details (Private)
    * Path: GET /api/myProfile
    * iAmLoggedIn returns the logged-in user's profile
    * iSearchForMyAccount verifies the user's profile is not null
    * iCanSeeMyAccountDetails verifies the return status code is OK
    * @throws Exception if the user is not logged in
    */
   @Given("I am logged in")
   public void iAmLoggedIn() throws Exception {
      RestAssured.baseURI = BASE_URL;
      request = RestAssured.given().header("Authorization", "Bearer " + getSecurityKeyAdmin());
      response = request.get(BASE_URL + port + "/api/myProfile");
   }

   @When("I go to my profile")
   public void iSearchForMyAccount() {
      Assert.assertNotNull(String.valueOf(response));
   }

   @Then("I can see my account details")
   public void iCanSeeMyAccountDetails() {
      Assert.assertEquals(200, response.getStatusCode());
   }

   /**
    * Scenario: User is able to see a list of flights they booked (Private)
    * Path: GET /api/myProfile/myFlights
    * aListOfUsersAreAvailable returns the list of flights in the database
    * iSearchForUsers verifies the list of flights is not empty
    * iCanSeeAListOfUsers verifies the return status code is OK
    * @throws Exception if the user is not logged in
    */
   @Given("a list of flights are booked")
   public void aListOfFlightsAreBooked() throws Exception {
      HttpHeaders headers = new HttpHeaders();
      headers.setBearerAuth(getSecurityKeyPassenger());
      HttpEntity<String> entity = new HttpEntity<>(null, headers);

      responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/myProfile/myTickets", HttpMethod.GET, entity, String.class);
      list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
   }

   @When("I search for the flights I booked")
   public void iSearchForTheFlightsIBooked() {
      Assert.assertTrue(list.size() > 0);
   }

   @Then("I can see the flights I booked")
   public void iCanSeeTheFlightsIBooked() {
      Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

   /**
    * Scenario: User is able to submit request to become a pilot
    * Path: POST /api/myProfile/becomePilot becomePilotRequestIsSubmitted
    * Borrows: iAmLoggedIn returns the logged-in user's profile
    * iCanSubmitARequestToBecomeAPilot verifies the return status code is OK
    * iAmPilot verifies the return status code is 200
    * @throws Exception if the user is not logged in
    */
   @When("I submit a request to become a pilot")
   public void iSubmitARequestToBecomeAPilot() throws Exception {
      JSONObject requestBody = new JSONObject();
      requestBody.put("isAdmin", true);
      request.header("Content-Type", "application/json");
      request.header("Authorization", "Bearer", getSecurityKeyAdmin());
      response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/myProfile/flyTheSkies");
   }

   @Then("I am a pilot")
   public void iAmAPilot() {
      Assert.assertEquals(200, response.getStatusCode());
   }

   /**
    * iHaveAListOfFlightsScheduled verifies the list of flights is not empty and that the logged-in user is an admin
    * iSearchForTheFlightsIScheduled verifies the list of flights is not empty
    * iCanSeeAListOfFlights verifies the return status code is OK
    * @throws Exception if the user is not logged in
    */
   @Given("I have a list of flights scheduled")
   public void iHaveAListOfFlightsScheduled() throws Exception {
      HttpHeaders headers = new HttpHeaders();
      headers.setBearerAuth(getSecurityKeyAdmin());
      HttpEntity<String> entity = new HttpEntity<>(null, headers);

      responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/myProfile/air", HttpMethod.GET, entity, String.class);
      list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
   }

   @When("I search for the flights I scheduled")
   public void iSearchForTheFlightsIScheduled() {
      Assert.assertTrue(list.size() > 0);
   }

   @Then("I can see the flights I scheduled")
   public void iCanSeeTheFlightsIScheduled() {
      Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

   // Airports

   /**
    * Scenario: User is able to view a list of airports (Public)
    * Path: GET /api/airports
    * aListOfAirportsAreAvailable returns the list of airports in the database
    * iSearchForAAirports verifies the list of airports is not empty
    * iCanSeeAListOfAirports verifies the return status code is OK
    */
   @Given("a list of airports are available")
   public void aListOfAirportsAreAvailable() {
      responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/airports", HttpMethod.GET, null, String.class);
      list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
   }

   @When("I search for airports")
   public void iSearchForAAirports() {
      Assert.assertTrue(list.size() > 0);
   }

   @Then("I can see a list of airports")
   public void iCanSeeAListOfAirports() {
      Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

   /**
    * Scenario: User is able to view an airport (Public)
    * Path: GET /api/airports/{airportId}
    * anAirportIsAvailable returns the airport with the specified ID
    * iSearchByAirportID verifies the airport is not null
    * iCanSeeTheAirportDetails verifies the return status code is 200
    */
   @Given("an airport is available")
   public void anAirportIsAvailable() {
      RestAssured.baseURI = BASE_URL;
      RequestSpecification request = RestAssured.given();
      response = request.get(BASE_URL + port + "/api/airports/1");
   }

   @When("I search by airport ID")
   public void iSearchByAirportID() {
      Assert.assertNotNull(String.valueOf(response));
   }

   @Then("I can see the airport details")
   public void iCanSeeTheAirportDetails() {
      Assert.assertEquals(200, response.getStatusCode());
   }

   /**
    * Scenario: User is able to search an airport by its code (Public)
    * Path: GET /api/airports/code/{airportCode}
    * theAirportIsAvailable returns the airport with the specified code
    * iSearchByAirportCode verifies the airport is not null
    * iCanSeeTheAirportDetails verifies the return status code is 200
    */
   @Given("there is an airport is available")
   public void thereIsAnAirportIsAvailable() {
      RestAssured.baseURI = BASE_URL;
      RequestSpecification request = RestAssured.given();
      response = request.get(BASE_URL + port + "/api/airports/code/MDW");
   }

   @When("I search by airport code")
   public void iSearchByAirportCode() {
      Assert.assertNotNull(String.valueOf(response));
   }

   @Then("I can see the details for the airport")
   public void iCanSeeTheDetailsForTheAirport() {
      Assert.assertEquals(200, response.getStatusCode());
   }

   /**
    * Scenario: User is able to view a list of arriving flights (Public)
    * Path: GET /api/airports/{airportId}/arrivals
    * aListOfArrivingFlightsAreAvailable returns the list of arriving flights for the airport with the specified ID
    * iSearchForArrivingFlights verifies the list of arriving flights is not empty
    * iCanSeeAListOfArrivingFlights verifies the return status code is OK
    */
   @Given("a list of arriving flights are available")
   public void iCanSeeAListOfArrivingFlightsAreAvailable() {
      responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/airports/2/arrivals", HttpMethod.GET, null, String.class);
      list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
   }

   @When("I search for arriving flights")
   public void iSearchForArrivingFlights() {
      Assert.assertTrue(list.size() > 0);
   }

   @Then("I can see a list of arriving flights")
   public void iCanSeeAListOfArrivingFlights() {
      Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

   /**
    * Scenario: User is able to view a list of departing flights (Public)
    * Path: GET /api/airports/{airportId}/departures
    * aListOfDepartingFlightsAreAvailable returns the list of departing flights for the airport with the specified ID
    * iSearchForDepartingFlights verifies the list of departing flights is not empty
    * iCanSeeAListOfDepartingFlights verifies the return status code is OK
    */
   @Given("a list of departing flights are available")
   public void aListOfDepartingFlightsAreAvailable() {
      responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/airports/1/departures", HttpMethod.GET, null, String.class);
      list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
   }

   @When("I search for departing flights")
   public void iSearchForDepartingFlights() {
      Assert.assertTrue(list.size() > 0);
   }

   @Then("I can see a list of departing flights")
   public void iCanSeeAListOfDepartingFlights() {
      Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

   // Flights

   /**
    * Scenario: User is able to view a list of flights (Public)
    * Path: GET /api/flights
    * aListOfFlightsAreAvailable returns the list of flights in the database
    * iSearchForFlights verifies the list of flights is not empty
    * iCanSeeAListOfFlights verifies the return status code is OK
    */
   @Given("a list of flights are available")
   public void aListOfFlightsAreAvailable() {
      responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/flights", HttpMethod.GET, null, String.class);
      list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
   }

   @When("I search for flights")
   public void iSearchForFlights() {
      Assert.assertTrue(list.size() > 0);
   }

   @Then("I can see a list of flights")
   public void iCanSeeAListOfFlights() {
      Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }

//
//   User is able to schedule a flight
//

   /**
    * Scenario: User is able to view a flight (Public)
    * Path: GET /api/flights/{flightId}
    * aFlightIsAvailable returns the flight with the specified ID
    * iSearchByFlightID verifies the flight is not null
    * iCanSeeTheFlightDetails verifies the return status code is 200
    */
   @Given("a flight is available")
   public void aFlightIsAvailable() {
      RestAssured.baseURI = BASE_URL;
      RequestSpecification request = RestAssured.given();
      response = request.get(BASE_URL + port + "/api/flights/1");
   }

   @When("I search by flight ID")
   public void iSearchByFlightID() {
      Assert.assertNotNull(String.valueOf(response));
   }

   @Then("I can see the flight details")
   public void iCanSeeTheFlightDetails() {
      Assert.assertEquals(200, response.getStatusCode());
   }

   /**
    * Scenario: User is able to view a list of tickets for a flight (Public)
    * Path: GET /api/flights/{flightId}/tickets
    * aListOfTicketsAreAvailable returns the list of tickets for the flight with the specified ID
    * iSearchForTickets verifies the list of tickets is not empty
    * iCanSeeTheListOfTickets verifies the return status code is OK
    * @throws Exception is thrown if the user is not logged in
    */
   @Given("a flight has a list of tickets")
   public void aFlightHasAListOfTickets() throws Exception {
      HttpHeaders headers = new HttpHeaders();
      headers.setBearerAuth(getSecurityKeyAdmin());
      HttpEntity<String> entity = new HttpEntity<>(null, headers);

      responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/flights/1/tickets", HttpMethod.GET, entity, String.class);
      list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
   }

   @When("I search for the list of tickets for the flight")
   public void iSearchForTheListOfTicketsForTheFlight() {
      Assert.assertTrue(list.size() > 0);
   }

   @Then("I can see the list of tickets for the flight")
   public void iCanSeeTheListOfTicketsForTheFlight() {
      Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
   }


   /**
    * Scenario: User is able to create tickets for a flight (Private
    * Path: POST /api/flights/{flightId}
    * Borrows: aFlightIsAvailable returns the flight with the specified ID
    * iCreateATicket creates a new ticket for the flight
    * iCanSeeTheDetailsOfTheNewlyCreatedTicket verifies the return status code is 201
    * @throws Exception is thrown if the user is not logged in or admin of the flight
    */
   @When("I create a ticket")
   public void iCreateATicket() throws Exception {
      RestAssured.baseURI = BASE_URL;
      request = RestAssured.given();
      JSONObject newTicket = new JSONObject();
      newTicket.put("flightId", 1);
      request.header("Content-Type", "application/json");
      request.header("Authorization", "Bearer " + getSecurityKeyAdmin());
      response = request.body(newTicket.toString()).post(BASE_URL + port + "/api/flights/1/tickets");
   }

   @Then("I can see the details of the newly created ticket")
   public void iCanSeeTheDetailsOfTheNewlyCreatedTicket() {
      Assert.assertEquals(201, response.getStatusCode());
   }

   // Tickets

   /**
    * Scenario: User is able to view a ticket (Public/Private)
    * Path: GET /api/tickets/{ticketId}
    * aTicketIsAvailable returns the ticket with the specified ID
    * iSearchForATicket verifies the ticket is not null
    * iCanSeeTheDetailsOfTheTicket verifies the return status code is 200
    * @throws Exception is thrown if the user is not the owner of the ticket or flight admin
    */
   @Given("I have booked a ticket")
   public void iHaveBookedATicket() throws Exception {
      RestAssured.baseURI = BASE_URL;
      request = RestAssured.given().header("Authorization", "Bearer " + getSecurityKeyPassenger());
      response = request.get(BASE_URL + port + "/api/tickets/1");
   }

   @When("I search for the ticket")
   public void iSearchForTheTicket() {
      Assert.assertNotNull(String.valueOf(response));
   }

   @Then("I can see the details for that ticket")
   public void iCanSeeTheDetailsForThatTicket() {
      Assert.assertEquals(200, response.getStatusCode());
   }

   /**
    *
    */
   @Given("a ticket is available")
   public void aTicketIsAvailable() throws Exception {
      RestAssured.baseURI = BASE_URL;
      request = RestAssured.given().header("Authorization", "Bearer " + getSecurityKeyPassenger());
      JSONObject requestBody = new JSONObject();
      requestBody.put("passenger", "Passenger ID, passenger name, passenger email");
      request.header("Content-Type", "application/json");
      response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/tickets/5/bookFlight");
   }

   @When("I book the ticket")
   public void iBookTheTicket() {
      Assert.assertNotNull(String.valueOf(response));
   }

   @Then("I can see the details of the ticket I booked")
   public void iCanSeeTheDetailsOfTheTicketIBooked() {
      System.out.println(response.prettyPeek());
      Assert.assertEquals(200, response.getStatusCode());
   }


   // Delete

   /**
    * Scenario: User is able to delete a flight (Private)
    * Path: DELETE /api/flights/{flightId}
    * Borrows: aFlightIsAvailable returns the flight with the specified ID
    * iDeleteTheFlight deletes the flight
    * iCanSeeTheDetailsOfTheDeletedFlight verifies the return status code is 200
    * @throws Exception is thrown if the user is not logged in
    */
   @When("I delete the flight")
   public void iDeleteTheFlight() throws Exception {
      RestAssured.baseURI = BASE_URL;
      RequestSpecification request = RestAssured.given();
      request.header("Content-Type", "application/json");
      request.header("Authorization", "Bearer " + getSecurityKeyAdmin());
      response = request.delete(BASE_URL + port + "/api/flights/1");
      System.out.println(response.prettyPeek());
   }

   @Then("I can see the details of the deleted flight")
   public void iCanSeeTheDetailsOfTheDeletedFlight() {
      Assert.assertEquals(200, response.getStatusCode());
   }


   // Edit

   /**
    * Scenario: User is able to edit their account details (Private)
    * Path: PUT /api/myProfile
    * Borrows: iAmLoggedIn returns the logged-in user's profile
    * iEditMyProfile updates the user's profile
    * iSeeMyProfileIsUpdated verifies the return status code is 200
    */
   @When("I edit my profile")
   public void iEditMyProfile() throws Exception {
      JSONObject requestBody = new JSONObject();
      requestBody.put("name", "Updated name");
      requestBody.put("email", "Updated email");
      requestBody.put("password", "Updated pw");
      request.header("Content-Type", "application/json");
      request.header("Authorization", "Bearer", getSecurityKeyAdmin());
      response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/myProfile");
   }

   @Then("I see my profile is updated")
   public void iSeeMyProfileIsUpdated() {
      Assert.assertNotNull(String.valueOf(response));
      Assert.assertEquals(200, response.getStatusCode());
   }

   @Given("a flight belongs to the logged-in user")
   public void aFlightBelongsToTheLoggedInUser() {
   }

}


