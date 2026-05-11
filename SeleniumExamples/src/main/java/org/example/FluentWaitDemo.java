package org.example;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import com.google.common.base.Function;

public class FluentWaitDemo {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        // Click the Start button
        driver.findElement(By.cssSelector("#start button")).click();

        // FluentWait setup
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20)) // Max wait time
                .pollingEvery(Duration.ofSeconds(2)) // Poll every 2 secs
                .ignoring(Exception.class);          // Ignore exceptions

        WebElement finishElement = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                WebElement element = driver.findElement(By.id("finish"));
                if (element.isDisplayed() && !element.getText().isEmpty()) {
                    return element; // Found
                }
                return null; // Keep waiting
            }
        });

        System.out.println("Text is: " + finishElement.getText());

        driver.quit();
    }
}
