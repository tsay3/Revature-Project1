package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LoginSteps {
    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        TestRunner.loginPage.goToLoginPage();
    }

    @Given("the user clicks on the register link")
    public void theUserClicksOnTheRegisterLink() {
        TestRunner.loginPage.clickRegisterLink();
    }

    @When("the user provides username {string}")
    public void theUserProvidesUsername(String username) {
        TestRunner.loginPage.setUsername(username);
    }

    @And("the user provides password {string}")
    public void theUserProvidesPassword(String password) {
        TestRunner.loginPage.setPassword(password);
    }

    @Then("the password field should be hidden")
    public void thePasswordFieldShouldBeHidden() {
        Assert.assertTrue(TestRunner.loginPage.verifyPasswordFieldHidden());
    }

    @And("the user submits the login credentials")
    public void theUserSubmitsTheLoginCredentials() {
        TestRunner.loginPage.clickLoginLink();
    }

    @Then("the user should be denied access to the home page")
    public void theUserShouldBeDeniedAccessToTheHomePage() {
        Assert.assertNotEquals("Home", TestRunner.driver.getTitle());
    }
}
