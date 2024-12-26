package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.DatabasePlanets;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

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
    public void thePlanetIsInTheDatabase(String planet) {
        if (DatabasePlanets.getPlanetId(planet) == -1)
            DatabasePlanets.addNewPlanet(planet);
    }

    @When("the user provides planet name {string}")
    public void theUserProvidesPlanetName(String planet) {
        TestRunner.homePage.enterPlanetName(planet);
    }

    @And("the user provides a planet image {string}")
    public void theUserProvidesAPlanetImage(String planetImage) {
        TestRunner.homePage.enterPlanetImage(planetImage);
    }

    @Then("the planet {string} should be added to the database")
    public void thePlanetShouldBeAddedToTheDatabase(String planet) {
        Assert.assertTrue(DatabasePlanets.getPlanetId(planet) > 0);
    }

    @And("the planet's image is set to {string}")
    public void thePlanetSImageIsSetTo(String planetImage) {
        TestRunner.homePage.enterPlanetImage(planetImage);
    }
}
