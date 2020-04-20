package com.progmasters.moovsmart.search;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class MessageTest {

    private WebDriver driver;

    @BeforeEach
     void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bozsó-Fort Zsuzsanna\\IdeaProjects\\angular-moovsmart\\src\\test\\resources\\win\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void message() {
        driver.get("http://localhost:4200");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String homeTitle = driver.findElement(By.cssSelector("body > app-root > div > app-home > div.container.marketing > div.row > div:nth-child(1) > h2")).getAttribute("innerHTML");
        assertEquals(homeTitle, "Töltsd fel az ingatlant!");
        driver.findElement(By.cssSelector("#myNavbar > ul.nav.navbar-nav.navbar-right > li:nth-child(2)")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.id("email")).sendKeys("moovsmartaltenter@gmail.com");
        driver.findElement(By.id("password")).sendKeys("test");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String loginTitle = driver.findElement(By.cssSelector("body > app-root > div > app-user-login > div > h1")).getAttribute("innerHTML");
        assertEquals(loginTitle, "Bejelentkezés");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector("#sidebar > ul > li:nth-child(2) > a > span")).click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String messageTitle = driver.findElement(By.cssSelector("body > div > div > div > div.inbox_people > div.headind_srch > div > h4")).getAttribute("innerHTML");
        assertEquals(messageTitle, "Üzenetváltásaim hirdetés szerint");


        driver.findElement(By.cssSelector("body > div > div > div > div.inbox_people > div.inbox_chat > div > div > div > div > span.message-topic-title")).click();


        driver.findElement(By.cssSelector("body > div > div > div > div.mesgs > div.type_msg > div > input")).sendKeys("Szia, érdeklődnék...");
        driver.findElement(By.cssSelector("body > div > div > div > div.mesgs > div.type_msg > div > button > fa-icon")).click();

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
