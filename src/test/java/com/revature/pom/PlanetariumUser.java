package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PlanetariumUser {
    private WebDriver driver;
    private String username;
    private String password;

    public PlanetariumUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToLoginPage() {driver.get("PLANETARIUM_URL");}
}
