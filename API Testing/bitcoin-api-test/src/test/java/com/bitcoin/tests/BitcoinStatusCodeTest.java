package com.bitcoin.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Scenario 1 & 2: Verify Status Code 200 (success) and 404 (failure)
 * for the Bitcoin current price endpoint.
 *
 * Endpoint: https://api.coindesk.com/v1/bpi/currentprice.json
 *
 * The endpoint returns Bitcoin rates against USD and non-USD currencies
 * (EUR, GBP) in a single JSON response.
 */
public class BitcoinStatusCodeTest {

    private static final String BASE_URI  = "https://api.coindesk.com";
    private static final String ENDPOINT  = "/v1/bpi/currentprice.json";

    // Shared response fetched once per class to avoid duplicate network calls
    private Response response;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;

        // Send GET request and store the response for use in all test methods
        response = RestAssured
                .given()
                    .log().uri()          // logs the full request URL
                .when()
                    .get(ENDPOINT)
                .then()
                    .log().status()       // logs the received HTTP status line
                    .extract()
                    .response();
    }

    // -----------------------------------------------------------------
    // Scenario 1 – Verify SUCCESS status code 200
    // -----------------------------------------------------------------

    /**
     * Verifies that the Bitcoin price API returns HTTP 200 OK.
     * This assertion is expected to PASS.
     */
    @Test(description = "Scenario 1: Verify status code 200 for Bitcoin rate endpoint")
    public void verifyStatusCode200() {
        int actualStatusCode = response.getStatusCode();

        System.out.println("=== Scenario 1: Verify Status Code 200 ===");
        System.out.println("Request  URL : " + BASE_URI + ENDPOINT);
        System.out.println("Expected     : 200");
        System.out.println("Actual       : " + actualStatusCode);

        Assert.assertEquals(
                actualStatusCode,
                200,
                "FAILED – Expected status 200 but received: " + actualStatusCode
        );

        System.out.println("RESULT       : PASS – Status code 200 verified successfully.");
    }

    // -----------------------------------------------------------------
    // Scenario 2 – Verify FAILURE status code 404
    // -----------------------------------------------------------------

    /**
     * Intentionally verifies that the API does NOT return 404.
     * The assertion below asserts for 404, so it is EXPECTED TO FAIL,
     * demonstrating how TestNG reports a status-code mismatch.
     *
     * This is a negative/failure scenario as described in the requirement.
     */
    @Test(description = "Scenario 2: Verify status code 404 for Bitcoin rate endpoint (expected to fail)")
    public void verifyStatusCode404() {
        int actualStatusCode = response.getStatusCode();

        System.out.println("=== Scenario 2: Verify Status Code 404 ===");
        System.out.println("Request  URL : " + BASE_URI + ENDPOINT);
        System.out.println("Expected     : 404");
        System.out.println("Actual       : " + actualStatusCode);

        Assert.assertEquals(
                actualStatusCode,
                404,
                "FAILED – Expected status 404 but received: " + actualStatusCode
                        + ". The endpoint is valid and returns 200, not 404."
        );

        System.out.println("RESULT       : PASS – Status code 404 verified.");
    }
}
