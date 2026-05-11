package stepdefinitions;

import io.cucumber.java.en.*;

public class LoginSteps {

    @Given("user is on login page")
    public void user_is_on_login_page() {
        System.out.println("On login page");
    }

    @When("user enters valid credentials")
    public void user_enters_valid_credentials() {
        System.out.println("Entering valid credentials");
    }

    @Then("user should be logged in")
    public void user_should_be_logged_in() {
        System.out.println("Logged in successfully");
    }
}
