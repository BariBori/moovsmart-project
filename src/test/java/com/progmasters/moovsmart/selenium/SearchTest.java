package com.progmasters.moovsmart.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchTest {

    private WebDriver driver;

    @BeforeEach
    void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bozsó-Fort Zsuzsanna\\IdeaProjects\\angular-moovsmart\\src\\test\\resources\\win\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1720,1200");
        driver = new ChromeDriver(options);
    }

    @Test
    void filterTest() {


        driver.get("http://localhost:4200/property-list");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String listTitle = driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > div.filter-header > p")).getAttribute("innerHTML");
        assertEquals(listTitle, "Szűrés");

        driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > div.filter-header > mat-form-field")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //TODO FILTER NOT WORKING

//        driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > div.filter-header > mat-form-field")).clear();
//        driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > div.filter-header > mat-form-field")).sendKeys("a");
//        driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > div.filter-header > mat-form-field")).sendKeys(Keys.RETURN);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void searchFullTest() {

        driver.get("http://localhost:4200/property-list");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String searchButton = driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > button")).getAttribute("innerHTML");
        assertEquals(searchButton, "Részletes keresés");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > button")).click();

        String searchTitle = driver.findElement(By.cssSelector("body > app-root > div > app-property-list-search > div > app-search > div > h2")).getAttribute("innerHTML");
        assertEquals(searchTitle, "Részletes keresés");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Select(driver.findElement(By.cssSelector("#city"))).selectByVisibleText("Budapest");
        new Select(driver.findElement(By.cssSelector("#propertyTypes"))).selectByVisibleText("Lakás");
        new Select(driver.findElement(By.cssSelector("#propertyConditionTypes"))).selectByVisibleText("Újépítésű");
        driver.findElement(By.cssSelector("#minPrice")).sendKeys("10");
        driver.findElement(By.cssSelector("#maxPrice")).sendKeys("35");
        driver.findElement(By.cssSelector("#minArea")).sendKeys("15");
        driver.findElement(By.cssSelector("#maxArea")).sendKeys("65");
        driver.findElement(By.cssSelector("#minRooms")).sendKeys("1");
        driver.findElement(By.cssSelector("#maxRooms")).sendKeys("6");
        driver.findElement(By.cssSelector("body > app-root > div > app-property-list-search > div > app-search > div > form > div:nth-child(10) > button")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void searchEmptyTest() {
        driver.get("http://localhost:4200/property-list-search");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String searchTitle = driver.findElement(By.cssSelector("body > app-root > div > app-property-list-search > div > app-search > div > h2")).getAttribute("innerHTML");
        assertEquals(searchTitle, "Részletes keresés");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("body > app-root > div > app-property-list-search > div > app-search > div > form > div:nth-child(10) > button")).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
