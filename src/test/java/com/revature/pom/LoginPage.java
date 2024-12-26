package com.revature.pom;

import com.revature.TestRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage {

    @FindBy(id="usernameInput")
    private WebElement usernameField;
    @FindBy(id="passwordInput")
    private WebElement passwordField;
    @FindBy(tagName = "a")
    private WebElement registerLink;
    @FindBy(xpath = "//input[3]")
    private WebElement loginBtn;

    public void clickRegisterLink() {
        registerLink.click();
    }
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void setUpUser() {
        goToLoginPage();
        setUsername("Batman");
        setPassword("Iamthenight1939");
        loginBtn.click();
        TestRunner.wait.until(ExpectedConditions.titleIs("Home"));
    }

    public void goToLoginPage() {
        driver.get("http://localhost:8080");
    }

    public void clickLoginLink() {
        loginBtn.click();
    }

    public boolean verifyPasswordFieldHidden() {
        return passwordField.getAttribute("type").equals("password");
    }
}
