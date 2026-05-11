package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class NavigationalCommands {
    public static void main(String args[]){
        URL url = null;
        try {
            url = new URL("https://demo.nopcommerce.com");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        WebDriver driver = new ChromeDriver() ;
        driver.navigate().to(url);

        driver.get("https://www.flipkart.com");

        driver.navigate().back();
        driver.navigate().forward();

        driver.navigate().refresh();
    }
}

