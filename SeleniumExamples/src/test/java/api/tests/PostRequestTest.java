package api.tests;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostRequestTest {

    @Test
    public void createPost() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.useRelaxedHTTPSValidation();

        // Java 8 compatible request body
        String requestBody = "{\n" +
                "\"title\":\"Selenium API Test\",\n" +
                "\"body\":\"REST Assured training\",\n" +
                "\"userId\":1\n" +
                "}";

        given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("Selenium API Test"));
    }
}
