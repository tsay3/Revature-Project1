package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class CelestialViewingSteps {
    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        TestRunner.loginPage.setUpUser();
    }

    @And("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        TestRunner.homePage.tryDirectAccessToHomePage();
    }

    @Given("the user owns the planet {string}")
    public void theUserOwnsThePlanet(String planetName) {
        // find
    }
}
