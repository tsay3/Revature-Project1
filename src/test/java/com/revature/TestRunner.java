package com.revature;

import com.revature.pom.PlanetariumUser;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "com.revature.steps",
        plugin = {"pretty","html:src/test/resources/reports/html-report.html"}
)
public class TestRunner {
    public static WebDriver driver = null;
    public static PlanetariumUser user;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        user = new PlanetariumUser(driver);
    }
    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
