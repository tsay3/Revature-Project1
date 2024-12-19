package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        TestRunner.driver.get("PLANETARIUM_URL");
    }

    @When("the user provides username {string}")
    public void theUserProvidesUsername(String username) {

    }

    @And("the user provides password {string}")
    public void theUserProvidesPassword(String password) {
    }
}
