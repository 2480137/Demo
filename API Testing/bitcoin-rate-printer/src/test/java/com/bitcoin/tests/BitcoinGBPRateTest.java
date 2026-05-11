package com.bitcoin.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Scenario 4: Print Bitcoin rate details against GBP currency.
 */
public class BitcoinGBPRateTest {

    private static final String BASE_URI = "https://api.coindesk.com";
    private static final String ENDPOINT = "/v1/bpi/currentprice.json";

    private JsonPath jsonPath;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URI;

        Response response = RestAssured
                .given()
                    .log().uri()
                .when()
                    .get(ENDPOINT)
                .then()
                    .log().status()
                    .extract()
                    .response();

        jsonPath = response.jsonPath();
    }

    @Test(description = "Scenario 4: Print Bitcoin rate against GBP currency")
    public void printGBPRate() {

        // Extract GBP fields from bpi.GBP
        String code        = jsonPath.getString("bpi.GBP.code");
        String symbol      = jsonPath.getString("bpi.GBP.symbol");
        String rate        = jsonPath.getString("bpi.GBP.rate");
        String description = jsonPath.getString("bpi.GBP.description");
        String gbpRate     = jsonPath.getString("bpi.GBP.rate");
        float  rateFloat   = jsonPath.getFloat("bpi.GBP.rate_float");

        System.out.println("============================================");
        System.out.println("   Bitcoin Rate Against GBP Currency        ");
        System.out.println("============================================");
        System.out.println("Code        : " + code);
        System.out.println("Symbol      : " + symbol);
        System.out.println("Rate        : " + rate);
        System.out.println("Description : " + description);
        System.out.println("GBP Rate    : " + gbpRate);
        System.out.println("Rate Float  : " + rateFloat);
        System.out.println("============================================");
    }
}
