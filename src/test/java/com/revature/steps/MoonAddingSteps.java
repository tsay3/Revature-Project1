package com.revature.steps;

import com.revature.utility.DatabaseMoons;
import io.cucumber.java.en.Given;
import org.junit.Assert;

public class MoonAddingSteps {
    @Given("the moon {string} is not in the database")
    public void theMoonIsNotInTheDatabase(String moon) {
        DatabaseMoons.forceMoonRemoval(moon);
    }
}
