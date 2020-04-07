package com.progmasters.moovsmart.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddPropertyTest {

    private WebDriver driver;

    @BeforeEach
    void startBrowser() {
        ClassLoader classLoader = AddPropertyTest.class.getClassLoader();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bozsó-Fort Zsuzsanna\\IdeaProjects\\angular-moovsmart\\src\\test\\resources\\win\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1720,1200");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    void addNewAdvertFull() {

        driver.get("http://localhost:4200/home");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String homeTitle = driver.findElement(By.cssSelector("body > app-root > div > app-home > div.container.marketing > div.row > div:nth-child(1) > h2")).getAttribute("innerHTML");
        assertEquals(homeTitle, "Töltsd fel az ingatlant!");
        driver.findElement(By.cssSelector("#myNavbar > ul.nav.navbar-nav.navbar-right > li:nth-child(2)")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("email")).sendKeys("moovsmartaltenter@gmail.com");
        driver.findElement(By.id("password")).sendKeys("test");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String loginTitle = driver.findElement(By.cssSelector("body > app-root > div > app-user-login > div > h1")).getAttribute("innerHTML");
        assertEquals(loginTitle, "Bejelentkezés");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("#myNavbar > ul.nav.navbar-nav.navbar-right > button")).click();

        String addPropertyTitle = driver.findElement(By.cssSelector("body > app-root > div > app-property-form > div > h2")).getAttribute("innerHTML");
        assertEquals(addPropertyTitle, "Új ingatlan feltöltése");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("body > app-root > div > app-property-form > div > form > div:nth-child(1) > input")).sendKeys("Gábor Áron utca Budapest, Magyarország");
        driver.findElement(By.cssSelector("body > app-root > div > app-property-form > div > form > div:nth-child(1) > input")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("#title")).sendKeys("Eladó lakás a parkban");
        driver.findElement(By.cssSelector("#price")).sendKeys("30");
        driver.findElement(By.cssSelector("#area")).sendKeys("30");
        driver.findElement(By.cssSelector("#numberOfRooms")).sendKeys("30");
        new Select(driver.findElement(By.cssSelector("#propertyTypes"))).selectByVisibleText("Lakás");
        new Select(driver.findElement(By.cssSelector("#propertyConditionTypes"))).selectByVisibleText("Újépítésű");
        new Select(driver.findElement(By.cssSelector("#parkingTypes"))).selectByVisibleText("Garázs");
        driver.findElements(By.cssSelector("body > app-root > div > app-property-form > div > form > div:nth-child(9) > label")).get(0).click();
        driver.findElement(By.cssSelector("#description")).sendKeys("Eladásra kínálunk egy szép, tágas lakást a park mellett. 4 szoba és garázs.");
        driver.findElement(By.cssSelector("body > app-root > div > app-property-form > div > form > button")).click();


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String searchTitle = driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > button")).getAttribute("innerHTML");
        assertEquals(searchTitle, "Részletes keresés");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    void addNewAdvertEmpty() {

        driver.get("http://localhost:4200/home");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String homeTitle = driver.findElement(By.cssSelector("body > app-root > div > app-home > div.container.marketing > div.row > div:nth-child(1) > h2")).getAttribute("innerHTML");
        assertEquals(homeTitle, "Töltsd fel az ingatlant!");
        driver.findElement(By.cssSelector("#myNavbar > ul.nav.navbar-nav.navbar-right > li:nth-child(2)")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("email")).sendKeys("moovsmartaltenter@gmail.com");
        driver.findElement(By.id("password")).sendKeys("test");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String loginTitle = driver.findElement(By.cssSelector("body > app-root > div > app-user-login > div > h1")).getAttribute("innerHTML");
        assertEquals(loginTitle, "Bejelentkezés");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("#myNavbar > ul.nav.navbar-nav.navbar-right > button")).click();

        String addPropertyTitle = driver.findElement(By.cssSelector("body > app-root > div > app-property-form > div > h2")).getAttribute("innerHTML");
        assertEquals(addPropertyTitle, "Új ingatlan feltöltése");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("body > app-root > div > app-property-form > div > form > button")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String addProperty = driver.findElement(By.cssSelector("body > app-root > div > app-property-form > div > h2")).getAttribute("innerHTML");
        assertEquals(addProperty, "Új ingatlan feltöltése");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }


}
