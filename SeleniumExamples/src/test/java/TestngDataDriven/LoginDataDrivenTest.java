package TestngDataDriven;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import java.time.Duration;

public class LoginDataDrivenTest {

    WebDriver driver;

    @BeforeClass

    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // URL for the Heroku Login Practice Page

        driver.get("https://the-internet.herokuapp.com/login");

    }

    @DataProvider(name = "loginData")

    public Object[][] loginTestData() {

        return new Object[][] {

                {"tomsmith", "SuperSecretPassword!", "success"},

                {"invalidUser", "secret123", "failure"},

                {"tomsmith", "wrongPass", "failure"}

        };

    }

    @Test(dataProvider = "loginData")

    public void herokuLoginTest(String username, String password, String expectedStatus) {

        // 1. Locate and fill Username

        driver.findElement(By.id("username")).clear();

        driver.findElement(By.id("username")).sendKeys(username);

        // 2. Locate and fill Password

        driver.findElement(By.id("password")).clear();

        driver.findElement(By.id("password")).sendKeys(password);

        // 3. Click Login Button

        driver.findElement(By.cssSelector("button.radius")).click();

        // 4. Verification Logic

        if (expectedStatus.equals("success")) {

            String successMsg = driver.findElement(By.id("flash")).getText();

            Assert.assertTrue(successMsg.contains("You logged into a secure area!"), "Login failed for valid credentials!");

            // Logout to reset for the next data row

            driver.findElement(By.linkText("Logout")).click();

        } else {

            String errorMsg = driver.findElement(By.id("flash")).getText();

            Assert.assertTrue(errorMsg.contains("invalid"), "Error message not displayed for invalid credentials!");

        }

    }

    @AfterClass

    public void tearDown() {

        if (driver != null) {

            driver.quit();

        }

    }

}
