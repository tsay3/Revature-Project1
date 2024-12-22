package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class RegistrationSteps {

    @Given("the user clicks on the register link")
    public void theUserClicksOnTheRegisterLink() {
        TestRunner.loginPage.clickRegisterLink();
    }

    @Given("the user is on the register page")
    public void theUserIsOnTheRegisterPage() {
        TestRunner.registrationPage.goToRegisterPage();
    }

    @And("the user submits the credentials")
    public void theUserSubmitsTheCredentials() {
//        TestRunner.driver.findElement()
    }
}
