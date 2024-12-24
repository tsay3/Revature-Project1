package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.DatabasePlanets;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PlanetAddingSteps {

    @Given("the planet {string} does not exist in the database")
    public void thePlanetDoesNotExistInTheDatabase(String planet) {
        DatabasePlanets.forcePlanetRemoval(planet);
    }

    @When("the user provides a planet name {string}")
    public void theUserProvidesAPlanetName(String planet) {
        TestRunner.homePage.enterPlanetName(planet);
    }
    @And("the user tries to add the planet")
    public void theUserTriesToAddThePlanet() {
        TestRunner.homePage.submitPlanet();
    }

    @Given("the planet {string} is in the database")
    public void thePlanetIsInTheDatabase(String arg0) {
    }
}
