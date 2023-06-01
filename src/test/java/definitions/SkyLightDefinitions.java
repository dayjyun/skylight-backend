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
      requestBody.put("email", "e@email.com");
      requestBody.put("password", "pw");
      request.header("Content-Type", "application/json");
      response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/users/login");
      return response.jsonPath().getString("message");
   }

   @Given("a list of airports are available")
   public void aListOfAirportsAreAvailable() {
      RestAssured.baseURI = BASE_URL;
      RequestSpecification request = RestAssured.given();
      response = request.get(BASE_URL + port+  "/api/airports");
   }
}
