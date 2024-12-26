package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.DatabaseMoons;
import com.revature.utility.DatabasePlanets;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

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
        // find the planet's id, force user 1 to own it
        Assert.assertTrue(DatabasePlanets.forceOwningPlanet(planetName));
    }

    @Given("the user owns no planets")
    public void theUserOwnsNoPlanets() {
        // force all of user 1's planets to be owned by user 2
        Assert.assertTrue(DatabasePlanets.forceUserDisowningPlanets(1));
    }

    @Given("the planet {string} owns the moon {string}")
    public void thePlanetOwnsTheMoon(String planet, String moon) {
        DatabaseMoons.forceOwningMoon(planet, moon);
    }

    @Given("the planet {string} does not own any moons")
    public void thePlanetDoesNotOwnAnyMoons(String planet) {
        int planetId = DatabasePlanets.getPlanetId(planet);
        DatabaseMoons.forcePlanetDisowningMoons(planetId);
    }

    @Then("the user should see the planet {string} on the home page")
    public void theUserShouldSeeThePlanetOnTheHomePage(String planetName) {
        Assert.assertTrue(TestRunner.homePage.planetListingIncludes(planetName));
    }

    @Then("the user should see the moon {string} on the home page")
    public void theUserShouldSeeTheMoonOnTheHomePage(String moonName) {
        Assert.assertTrue(TestRunner.homePage.moonListingIncludes(moonName));
    }

    @Then("the user should not see any planets on the home page")
    public void theUserShouldNotSeeAnyPlanetsOnTheHomePage() {
        // no planets also means no moons
        Assert.assertTrue(TestRunner.homePage.noBodiesListed());
    }

    @Then("the user should not see any moons for {string}")
    public void theUserShouldNotSeeAnyMoonsFor(String planet) {
        Assert.assertTrue(TestRunner.homePage.noMoonsForPlanet(planet));
    }

    @And("the user selects {string} from the options")
    public void theUserSelectsFromTheOptions(String option) {
        TestRunner.homePage.selectOption(option);
    }

    @And("the user should see the planet {string} in their list of planets and moons")
    public void theUserShouldSeeThePlanetInTheirListOfPlanetsAndMoons(String planet) {
        TestRunner.homePage.planetListingIncludes(planet);
    }

    @And("the user should see the moon {string} in their list of planets and moons")
    public void theUserShouldSeeTheMoonInTheirListOfPlanetsAndMoons(String moon) {
        TestRunner.homePage.moonListingIncludes(moon);
    }
}
