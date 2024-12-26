package com.revature.pom;

import com.revature.utility.DatabasePlanets;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage {

    private WebDriver driver;

    @FindBy(id = "greeting")
    private WebElement greetingHeader;

    public String getHomePageGreeting() {
        return greetingHeader.getText();
    }

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    public void clickLogout() { logoutButton.click(); }

    @FindBy(id = "locationSelect")
    private WebElement bodyTypeSelector;

    public void selectOption(String option) {
        Select select = new Select(bodyTypeSelector);
        select.selectByVisibleText(option);
    }

    @FindBy(id = "deleteInput")
    private WebElement deleteField;

    public void enterBodyToDelete(String body) {
        deleteField.sendKeys(body);
    }

    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    public void deleteCelestialBody() {
        deleteButton.click();
    }

    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;

    public void enterMoonName(String moon) {
        moonNameInput.sendKeys(moon);
    }

    @FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;

    public void enterOrbitedPlanet(String planet) {
        Integer planetId = DatabasePlanets.getPlanetId(planet);
        orbitedPlanetInput.sendKeys(planetId.toString());
    }

    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;

    public void enterMoonImage(String filename) {
        String moonPath = String.format(
                "C:\\Users\\thoma\\Documents\\Revature\\Project1\\Planetarium\\src\\main\\resources\\images\\%s",
                filename);
        moonImageInput.sendKeys(moonPath);
    }

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    public void enterPlanetName(String planet) {
        planetNameInput.sendKeys(planet);
    }

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    public void enterPlanetImage(String filename) {
        String planetPath = String.format(
                "C:\\Users\\thoma\\Documents\\Revature\\Project1\\Planetarium\\src\\main\\resources\\images\\%s",
                filename);
        planetImageInput.sendKeys(planetPath);
    }

    @FindBy(xpath = "//*[@class='submit-button']")
    private WebElement submitButton;

    public void submitPlanet() { submitButton.click(); }

    @FindBy(tagName = "tr")
    private List<WebElement> tableRows;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getNumberOfCelestialRows() {
        return tableRows.size() - 1;
    }

    public void tryDirectAccessToHomePage() {
        driver.get("http://localhost:8080/planetarium");
    }

    public boolean noBodiesListed() {
        return (getNumberOfCelestialRows() <= 0);
    }

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

    public boolean planetListingDoesNotInclude(String planet) {
        return !planetListingIncludes(planet);
    }

    public boolean moonListingDoesNotInclude(String moon) {
        return !moonListingIncludes(moon);
    }
}
