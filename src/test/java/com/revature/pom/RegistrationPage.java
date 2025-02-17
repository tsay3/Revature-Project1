package com.revature.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    @FindBy(id="usernameInput")
    private WebElement usernameField;
    @FindBy(id="passwordInput")
    private WebElement passwordField;
    @FindBy(xpath = "//input[3]")
    private WebElement registerBtn;
    @FindBy(tagName = "a")
    private WebElement loginBtn;

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public boolean verifyPasswordFieldHidden() {
        return passwordField.getAttribute("type").equals("password");
    }

    public void goToRegisterPage() {
        driver.get("http://localhost:8080");
    }

    public void submitCredentials() {
        registerBtn.click();
    }
}
