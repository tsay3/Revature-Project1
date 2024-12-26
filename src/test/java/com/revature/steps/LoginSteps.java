package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.DatabaseUsers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @Then("the login password field should be hidden")
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

    @Given("the user {string} has the password {string}")
    public void theUserHasThePassword(String username, String password) {
        if(!DatabaseUsers.forceUserAndPassword(username, password)) {
            Assert.fail(String.format("Database error: Could not force the username '%s' with the password '%s' into the database",
                    username, password));
        }
    }

    @Then("the user should be redirected to the home page")
    public void theUserShouldBeRedirectedToTheHomePage() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @And("the user should see the greeting {string}")
    public void theUserShouldSeeTheGreeting(String text) {
        Assert.assertEquals(text, TestRunner.homePage.getHomePageGreeting());
    }

    @When("the user logs out")
    public void theUserLogsOut() {
        TestRunner.homePage.clickLogout();
    }

    @Then("the user should be redirected to the login page")
    public void theUserShouldBeRedirectedToTheLoginPage() {
        TestRunner.wait.until(ExpectedConditions.titleIs("Planetarium Login"));
        Assert.assertEquals("Planetarium Login", TestRunner.driver.getTitle());
    }

    @And("the user should be logged out")
    public void theUserShouldBeLoggedOut() {
        TestRunner.homePage.tryDirectAccessToHomePage();
        Assert.assertNotEquals("Home", TestRunner.driver.getTitle());
    }

    @Given("the user is not logged in")
    public void theUserIsNotLoggedIn() {
        TestRunner.homePage.tryDirectAccessToHomePage();
        if (TestRunner.driver.getTitle().equals("Home")) {
            theUserLogsOut();
        }
    }

    @When("the user navigates to the home page")
    public void theUserNavigatesToTheHomePage() {
        TestRunner.homePage.tryDirectAccessToHomePage();
    }
}
