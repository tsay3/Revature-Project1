package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.DatabaseMoons;
import com.revature.utility.DatabasePlanets;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class MoonDeletingSteps {
    @Given("the moon {string} is owned by the planet {string}")
    public void theMoonIsOwnedByThePlanet(String moon, String planet) {
        DatabaseMoons.forceOwningMoon(planet, moon);
    }

    @And("the user does not own the planet {string}")
    public void theUserDoesNotOwnThePlanet(String planet) {
        DatabasePlanets.forceNotOwningPlanet(planet);
    }

    @And("the moon {string} does not exist in the database")
    public void theMoonDoesNotExistInTheDatabase(String moon) {
        DatabaseMoons.forceMoonRemoval(moon);
    }

    @And("the user tries to delete the moon")
    public void theUserTriesToDeleteTheMoon() {
        TestRunner.homePage.deleteCelestialBody();
    }

    @Then("the user should not see the moon {string} in their list of planets and moons")
    public void theUserShouldNotSeeTheMoonInTheirListOfPlanetsAndMoons(String moon) {
        Assert.assertFalse(TestRunner.homePage.moonListingIncludes(moon));
    }

    @When("the user has entered the moon {string} to delete")
    public void theUserHasEnteredTheMoonToDelete(String moon) {
        TestRunner.homePage.enterBodyToDelete(moon);
    }

    @And("the planet {string} should not be in their list of planets and moons")
    public void thePlanetShouldNotBeInTheirListOfPlanetsAndMoons(String planet) {
        Assert.assertFalse(TestRunner.homePage.planetListingIncludes(planet));
    }

    @Then("all moons that were owned by {string} should be removed from the database")
    public void allMoonsThatWereOwnedByShouldBeRemovedFromTheDatabase(String planet) {
        // there should not be any moons with an ownerid belonging to planet
        int planetId = DatabasePlanets.getPlanetId(planet);
        Assert.assertEquals(0, DatabaseMoons.getNumberOfMoonsOwnedBy(planetId));
    }
}
