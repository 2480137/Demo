package TestNgDemo;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStream;

public class LoginTest2 {

    @Test
    public void loginTestUsingJson() {

        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("loginData.json");

        if (is == null) {
            throw new RuntimeException("loginData.json not found");
        }

        JSONObject json = new JSONObject(new JSONTokener(is));

        String username = json.getString("username");
        String password = json.getString("password");
        String expected = json.getString("expected");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.radius")).click();

        String message = driver.findElement(By.id("flash")).getText();

        if (expected.equalsIgnoreCase("success")) {
            Assert.assertTrue(message.contains("You logged into"));
        } else {
            Assert.assertTrue(message.contains("invalid"));
        }

        driver.quit();
    }
}