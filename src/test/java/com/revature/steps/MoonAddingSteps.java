package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.DatabaseMoons;
import com.revature.utility.DatabasePlanets;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class MoonAddingSteps {
    @Given("the moon {string} is not in the database")
    public void theMoonIsNotInTheDatabase(String moon) {
        DatabaseMoons.forceMoonRemoval(moon);
    }

    @Given("the moon {string} exists in the database")
    public void theMoonExistsInTheDatabase(String moon) {
        DatabaseMoons.addMoon(moon);
    }

    @When("the user provides a moon name {string}")
    public void theUserProvidesAMoonName(String moon) {
        TestRunner.homePage.enterMoonName(moon);
    }

    @And("the user tries to add the moon")
    public void theUserTriesToAddTheMoon() {
        TestRunner.homePage.submitPlanet();
    }

    @And("the user provides a moon image {string}")
    public void theUserProvidesAMoonImage(String moonImage) {
        TestRunner.homePage.enterMoonImage(moonImage);
    }

    @And("the user provides the planet {string} that owns the moon")
    public void theUserProvidesThePlanetThatOwnsTheMoon(String moon) {
        TestRunner.homePage.enterMoonName(moon);
    }

    @Then("the moon {string} should be added to the database")
    public void theMoonShouldBeAddedToTheDatabase(String moon) {
        Assert.assertTrue(DatabaseMoons.getMoonId(moon) > 0);
    }

    @And("the owner of {string} should be {string}")
    public void theOwnerOfShouldBe(String moon, String planet) {
        int planetId = DatabasePlanets.getPlanetId(planet);
        Assert.assertEquals(planetId, DatabaseMoons.getMoonOwner(moon));
    }
}
