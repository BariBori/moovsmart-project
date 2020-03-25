package com.progmasters.moovsmart.seleniumtest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Bozsó-Fort Zsuzsanna\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void demo() {
        driver.get("http://localhost:4200");

        driver.findElement(By.cssSelector("a[href='/user-login']")).click();
        //TODO
        String title = driver.findElement(By.id("loginForm")).getAttribute("innerHTML");
        assertEquals(title, "Login form");

        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("moovsmartaltenter@gmail.com");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("test");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String listPageTitle = driver.findElement(By.cssSelector("body > app-root > div > app-user-home > h2")).getAttribute("innerHTML");
        assertEquals(listPageTitle, "User home");

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
