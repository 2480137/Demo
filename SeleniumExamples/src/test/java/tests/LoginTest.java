package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginTestData() {
        return new Object[][] {
                {"tomsmith",    "SuperSecretPassword!", "success"},
                {"invalidUser", "secret123",            "failure"},
                {"tomsmith",    "wrongPass",             "failure"}
        };
    }

    @Test(dataProvider = "loginData")
    public void herokuLoginTest(String username, String password, String expectedStatus) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        String flashMsg = loginPage.getFlashMessage();

        if (expectedStatus.equals("success")) {
            Assert.assertTrue(
                    flashMsg.contains("You logged into a secure area!"),
                    "Login failed for valid credentials!"
            );
            loginPage.clickLogout();
        } else {
            Assert.assertTrue(
                    flashMsg.contains("invalid"),
                    "Error message not displayed for invalid credentials!"
            );
        }
    }
}