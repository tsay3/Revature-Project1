package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlanetariumUser {

    WebDriver driver = null;
    public PlanetariumUser(WebDriver driver) {
        this.driver = driver;
    }
}
