package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.DatabasePlanets;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PlanetDeletingSteps {
    @Given("the planet {string} is not in the database")
    public void thePlanetIsNotInTheDatabase(String planet) {
        DatabasePlanets.forcePlanetRemoval(planet);
    }

    @When("the user tries to delete the planet")
    public void theUserTriesToDeleteThePlanet() {
        TestRunner.homePage.deleteCelestialBody();
    }

    @When("the user has entered the planet {string} to delete")
    public void theUserHasEnteredThePlanetToDelete(String planet) {
        TestRunner.homePage.enterBodyToDelete(planet);
    }

    @Then("the planet {string} should not be in the database")
    public void thePlanetShouldNotBeInTheDatabase(String planet) {
        System.out.println(String.format("Planet ID is %d", DatabasePlanets.getPlanetId(planet)));
        Assert.assertTrue(DatabasePlanets.getPlanetId(planet) == -1);
    }

    @Given("the user is not the owner of the planet {string}")
    public void theUserIsNotTheOwnerOfThePlanet(String planet) {
        DatabasePlanets.forceNotOwningPlanet(planet);
    }

    @And("the planet {string} is not in their list of planets and moons")
    public void thePlanetIsNotInTheirListOfPlanetsAndMoons(String planet) {
        Assert.assertFalse(TestRunner.homePage.planetListingIncludes(planet));
    }
}
