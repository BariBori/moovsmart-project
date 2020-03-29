package com.progmasters.moovsmart.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OpenPropertyTest {

    private WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        ClassLoader classLoader = OpenPropertyTest.class.getClassLoader();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bozsó-Fort Zsuzsanna\\IdeaProjects\\angular-moovsmart\\src\\test\\resources\\win\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void demo() {
        driver.get("http://localhost:4200");

        driver.findElement(By.id("property-list-link")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElements(By.cssSelector("body > app-root > div > app-property-list > div > div > mat-table > mat-row:nth-child(2)"));
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    @AfterEach
    public void tearDown() {
        driver.quit();
    }


}
