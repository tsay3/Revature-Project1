package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.DatabaseSetup;
import com.revature.utility.DatabaseUsers;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationSteps {

    @Given("the user is on the register page")
    public void theUserIsOnTheRegisterPage() {
        TestRunner.registrationPage.goToRegisterPage();
    }

    @And("the user submits the credentials")
    public void theUserSubmitsTheCredentials() {
        TestRunner.registrationPage.submitCredentials();
    }

    @Then("the user should get a browser alert saying {string}")
    public void theUserShouldGetABrowserAlertSaying(String alertMessage) {
        try {
            TestRunner.wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = TestRunner.driver.switchTo().alert();
            Assert.assertEquals(alert.getText(), alertMessage);
        } catch (TimeoutException e) {
            Assert.fail(String.format("Alert did not appear, should have reported '%s'",
                    alertMessage));
        }
    }

    @Then("the user should be redirected to the {string} page")
    public void theUserShouldBeRedirectedToThePage(String page) {
        switch (page) {
            case "login":
                Assert.assertEquals(TestRunner.driver.getTitle(), "Planetarium Login");
            case "registration":
                Assert.assertEquals(TestRunner.driver.getTitle(), "Account Creation");
            default:
        }
    }

    @Given("the username {string} with the password {string} is in the database")
    public void theUsernameWithThePasswordIsInTheDatabase(String username, String password) {
        if (!DatabaseUsers.forceUserAndPassword(username, password)) {
            Assert.fail(String.format("Database error: could not force user '%s' with password '%s' into the database",
                    username, password));
        }
    }

    @And("the user {string} is not in the database")
    public void theUserIsNotInTheDatabase(String username) {
        if (!DatabaseUsers.forceUserRemoval(username)) {
            Assert.fail(String.format("Database error: could not remove the user '%s' from the database",
                    username));
        }
    }

    @Then("the registration password field should be hidden")
    public void theRegistrationPasswordFieldShouldBeHidden() {
        Assert.assertTrue(TestRunner.registrationPage.verifyPasswordFieldHidden());
    }
}
