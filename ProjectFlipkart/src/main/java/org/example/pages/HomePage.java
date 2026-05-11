package org.example.pages;

import org.example.base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;

    public HomePage(WebDriver driver, WebDriverWait wait, WebDriverWait shortWait) {
        this.driver    = driver;
        this.wait      = wait;
        this.shortWait = shortWait;
    }

    public void openSite() {
        driver.get("https://www.flipkart.com");
        BaseTest.takeScreenshot(driver, "01_homepage");
    }

    public void closeLoginPopup() {
        try {
            shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[@role='button']"))).click();
        } catch (Exception ignored) {}
    }

    public void search(String searchKey) throws InterruptedException {
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.sendKeys(searchKey);
        Thread.sleep(300);
        searchBox.clear();
        searchBox.sendKeys("mobiles under 15000");
        Thread.sleep(300);
        searchBox.sendKeys(Keys.ENTER);
    }
}
