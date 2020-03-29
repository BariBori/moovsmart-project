package com.progmasters.moovsmart.seleniumtest;

import com.progmasters.moovsmart.selenium.OpenPropertyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        ClassLoader classLoader = LoginTest.class.getClassLoader();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bozsó-Fort Zsuzsanna\\IdeaProjects\\angular-moovsmart\\src\\test\\resources\\win\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1200");
        driver = new ChromeDriver(options);
    }

    @Test
    public void demo() {
        driver.get("http://localhost:4200/user-login");

        driver.findElement(By.id("email")).sendKeys("moovsmartaltenter@gmail.com");
        driver.findElement(By.id("password")).sendKeys("test");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //TODO ASSERT KELL

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

}
