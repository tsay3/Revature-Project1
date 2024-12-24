package com.revature.pom;

import com.revature.utility.DatabasePlanets;
import org.openqa.selenium.By;
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

    public boolean planetListingIncludes(String planetName) {
        return listingsInclude("planet", planetName);
    }

    public boolean moonListingIncludes(String moonName) {
        return listingsInclude("moon", moonName);
    }

    private boolean listingsInclude(String bodyType, String name) {
        for (WebElement row : tableRows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            if (tds.size() == 4) {
                if (tds.get(0).getText().equals(bodyType)) {
                    if (tds.get(2).getText().equals(name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean noBodiesListed() {
        return (tableRows.size() <= 1);
    }

    public boolean noMoonsForPlanet(String planet) {
        // false if there is even one moon with the planet id
        int planetId = DatabasePlanets.getPlanetId(planet);
        for (WebElement row : tableRows) {
            List<WebElement> tds = row.findElements(By.tagName("td"));
            if (tds.size() == 4) {
                if (tds.get(0).getText().equals("moon")) {
                    if (Integer.parseInt(tds.get(3).getText()) == planetId) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
