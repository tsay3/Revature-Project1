package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class User {
    private WebDriver driver;

    public User(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToLoginPage() {driver.get("PLANETARIUM_URL");}
}
