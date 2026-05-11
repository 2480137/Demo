package api.tests;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DeletePostTest {

    @Test
    public void deletePost() {

        // Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.useRelaxedHTTPSValidation();
        // DELETE request
        when()
                .delete("/posts/1")
                .then()
                .statusCode(200);
    }
}
