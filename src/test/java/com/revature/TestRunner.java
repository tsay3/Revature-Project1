package com.revature;

import com.revature.utility.*;
import io.cucumber.core.cli.Main;

import com.revature.pom.HomePage;
import com.revature.pom.LoginPage;
import com.revature.pom.RegistrationPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.revature.steps",
        plugin = {"pretty",
                "html:src/test/resources/reports/html-report.html",
                "json:src/test/resources/reports/json-report.json"
        }
)
public class TestRunner {
    public static WebDriver driver = null;
    public static WebDriverWait wait;
    public static LoginPage loginPage;
    public static RegistrationPage registrationPage;
    public static HomePage homePage;

//    public static void main(String[] args) {
//        DatabaseSetup.main(args);
//        setUp();
////        Main.main(args);
//        tearDown();
//    }

    @BeforeClass
    public static void setUp() {
        DatabaseSetup.main(new String[]{});
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
//        System.out.println("setUp ran");
    }
    @AfterClass
    public static void tearDown() {
        DatabasePlanets.removeAllDummyPlanets();
        DatabaseUsers.removeAllDummyUsers();

        if (driver != null) {
            driver.quit();
        }
    }
}
