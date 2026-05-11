package com.bitcoin.tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Scenario 3: Print Bitcoin rate details against USD currency.
 */
public class BitcoinUSDRateTest {

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

    @Test(description = "Scenario 3: Print Bitcoin rate against USD currency")
    public void printUSDRate() {

        // Extract USD fields from bpi.USD
        String code        = jsonPath.getString("bpi.USD.code");
        String symbol      = jsonPath.getString("bpi.USD.symbol");
        String rate        = jsonPath.getString("bpi.USD.rate");
        String description = jsonPath.getString("bpi.USD.description");
        String usdRate     = jsonPath.getString("bpi.USD.rate");
        float  rateFloat   = jsonPath.getFloat("bpi.USD.rate_float");

        System.out.println("============================================");
        System.out.println("   Bitcoin Rate Against USD Currency        ");
        System.out.println("============================================");
        System.out.println("Code        : " + code);
        System.out.println("Symbol      : " + symbol);
        System.out.println("Rate        : " + rate);
        System.out.println("Description : " + description);
        System.out.println("USD Rate    : " + usdRate);
        System.out.println("Rate Float  : " + rateFloat);
        System.out.println("============================================");
    }
}
