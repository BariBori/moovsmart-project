//package com.progmasters.moovsmart.selenium;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//class OpenPropertyTest {
//
//    private WebDriver driver;
//
//    @BeforeEach
//    void startBrowser() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bozsó-Fort Zsuzsanna\\IdeaProjects\\angular-moovsmart\\src\\test\\resources\\win\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//        driver = new ChromeDriver(options);
//    }
//
//    @Test
//    void toPropertyDetails() {
//        driver.get("http://localhost:4200");
//        driver.findElement(By.id("property-list-link")).click();
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        driver.findElement(By.cssSelector("body > app-root > div > app-property-list > div > div.table-container.mat-elevation-z8 > mat-table > mat-row:nth-child(2) > mat-cell.mat-cell.cdk-cell.click.cdk-column-address.mat-column-address.ng-star-inserted")).click();
//
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        String title = driver.findElement(By.cssSelector("body > app-root > div > app-property-details > div > div > div:nth-child(4) > div:nth-child(2) > p:nth-child(1)")).getAttribute("innerHTML");
//        assertEquals(title, "Szobaszám");
//
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }
//
//
//}
