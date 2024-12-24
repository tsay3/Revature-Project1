package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    @FindBy(id = "greeting")
    private WebElement greetingHeader;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "locationSelect")
    private WebElement bodyTypeSelector;

    @FindBy(id = "deleteInput")
    private WebElement deleteInput;

    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;

    @FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;

    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    @FindBy(tagName = "tr")
    private List<WebElement> tableRows;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getHomePageGreeting() {
        return greetingHeader.getText();
    }

    public int getNumberOfCelestialRows() {
        return tableRows.size() - 1;
    }

    public void tryDirectAccessToHomePage() {
        driver.get("http://localhost:8080/planetarium");
    }

    public void clickLogout() { logoutButton.click(); }
}
