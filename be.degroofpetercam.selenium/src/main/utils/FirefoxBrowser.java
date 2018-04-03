package main.utils;

import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowser{
    private static FirefoxDriver browser = null;

    public static FirefoxDriver getBrowser() {
        if(browser == null){
            initialiseBrowser();
        }
        return browser;
    }

    private static void initialiseBrowser() {
        System.setProperty("webdriver.gecko.driver", "lib/webdriver/geckodriver.exe");

        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless"); // uncomment this line to see browser
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        // firefoxOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);

        browser = new FirefoxDriver(firefoxOptions);
    }



}
