package com.bitcoin.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Scenario 5: Print Bitcoin rate details against EUR currency.
 */
public class BitcoinEURRateTest {

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

    @Test(description = "Scenario 5: Print Bitcoin rate against EUR currency")
    public void printEURRate() {

        // Extract EUR fields from bpi.EUR
        String code        = jsonPath.getString("bpi.EUR.code");
        String symbol      = jsonPath.getString("bpi.EUR.symbol");
        String rate        = jsonPath.getString("bpi.EUR.rate");
        String description = jsonPath.getString("bpi.EUR.description");
        String eurRate     = jsonPath.getString("bpi.EUR.rate");
        float  rateFloat   = jsonPath.getFloat("bpi.EUR.rate_float");

        System.out.println("============================================");
        System.out.println("   Bitcoin Rate Against EUR Currency        ");
        System.out.println("============================================");
        System.out.println("Code        : " + code);
        System.out.println("Symbol      : " + symbol);
        System.out.println("Rate        : " + rate);
        System.out.println("Description : " + description);
        System.out.println("EUR Rate    : " + eurRate);
        System.out.println("Rate Float  : " + rateFloat);
        System.out.println("============================================");
    }
}
