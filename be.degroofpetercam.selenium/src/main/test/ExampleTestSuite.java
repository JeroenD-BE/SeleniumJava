package main.test;

import main.utils.FirefoxBrowser;
import main.utils.GoogleBrowser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExampleTestSuite {
    private static RemoteWebDriver browser;

    @BeforeAll
    private static void setup() {
        browser = FirefoxBrowser.getBrowser();
//        browser = GoogleBrowser.getBrowser();
    }

    @AfterAll
    private static void cleanup() {
        browser.quit();
    }

    @Test
    public void elementDisplayedTest() {
        browser.get("http://saucelabs.com");
        WebElement header = browser.findElement(By.id("site-header"));
        assertTrue((header.isDisplayed()),"Header element of website is displayed correctly");
    }

    @Test
    public void googleSearchTest() throws Exception {

        browser.get("http://www.google.com");
        WebElement queryBox = browser.findElement(By.name("q"));
        queryBox.sendKeys("headless firefox");

        WebElement searchBtn = browser.findElement(By.name("btnK"));

        // click as user - fails!
//      wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("btnK")));
//      wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
//      searchBtn.click();

        // click programmatically instead..
        browser.executeScript("arguments[0].click();", searchBtn);

        // wait for results to appear
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ires")));

        // check if results contains some links to pages
        WebElement iresDiv = browser.findElement(By.id("ires"));
        List<WebElement> links = iresDiv.findElements(By.tagName("a"));
        assertNotNull(links,"Google search returned some results.");

        // open the first link
        links.get(0).click();
    }


}