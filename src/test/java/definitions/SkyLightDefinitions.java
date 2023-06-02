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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

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

   public String getSecurityKey() throws Exception {
      RequestSpecification request = RestAssured.given();
      JSONObject requestBody = new JSONObject();
      requestBody.put("email", "a@email.com");
      requestBody.put("password", "pw");
      request.header("Content-Type", "application/json");
      response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/login");
      return response.jsonPath().getString("message");
   }

   /**
    * Scenario: User is able to view a list of airports (Public)
    * Path: GET /api/airports
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
      Assert.assertEquals(HttpStatus.OK,  responseEntity.getStatusCode());
   }

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
      Assert.assertEquals(200,  response.getStatusCode());
   }
   
   @Given("I can see a list of arriving flights are available")
   public void iCanSeeAListOfArrivingFlightsAreAvailable() {
      responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/airports/1/arrivals", HttpMethod.GET, null, String.class);
      list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
   }

   @When("I search for arriving flights")
   public void iSearchForArrivingFlights() {
      Assert.assertTrue(list.size() > 0);
   }

   @Then("I can see a list of arriving flights")
   public void iCanSeeAListOfArrivingFlights() {
      Assert.assertEquals(HttpStatus.OK,  responseEntity.getStatusCode());
   }
}
