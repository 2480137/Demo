package api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequestTest {

    @Test
    public void getAllPosts() {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.useRelaxedHTTPSValidation();
        Response response =
                RestAssured
                        .given()
                        .when()
                        .get("/posts")
                        .then()
                        .extract().response();

        System.out.println("Response Body:");
        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
