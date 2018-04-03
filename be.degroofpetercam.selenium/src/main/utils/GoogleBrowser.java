package main.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GoogleBrowser {
    private static ChromeDriver browser = null;

    public static ChromeDriver getBrowser() {
        if(browser == null){
            initialiseBrowser();
        }
        return browser;
    }

    private static void initialiseBrowser() {
        System.setProperty("webdriver.chrome.driver", "lib/webdriver/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");

        browser = new ChromeDriver(chromeOptions);
    }



}
