package main.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenHomePageTest {
    @Test
    public void headlessTest() {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
//        firefoxBinary.addCommandLineOptions("--headless");
        System.setProperty("webdriver.gecko.driver", "lib/webdriver/geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
//        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);

        FirefoxDriver driver = new FirefoxDriver(firefoxOptions);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://www.google.com");
            WebElement queryBox = driver.findElement(By.name("q"));
            queryBox.sendKeys("headless firefox");
            wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
            WebElement searchBtn = driver.findElement(By.name("btnK"));
            searchBtn.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ires")));
            WebElement iresDiv = driver.findElement(By.id("ires"));
            iresDiv.findElements(By.tagName("a")).get(0).click();
            System.out.println(driver.getPageSource());
        }  finally {
            driver.quit();
        }
    }
    @Test
    public void site_header_is_on_home_page() {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        System.setProperty("webdriver.gecko.driver", "lib/webdriver/geckodriver.exe");
        FirefoxDriver browser = new FirefoxDriver(firefoxOptions);
        try {
            browser.get("http://saucelabs.com");
            WebElement header = browser.findElement(By.id("site-header"));
            assertTrue((header.isDisplayed()));
        }  finally {
            browser.quit();
//            browser.close();
        }
    }
}