package com.epam.framework;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Set;

/**
 * Created by Uliana Pizhanska on 27/02/2017.
 */
public class Driver {

    private static Logger log = Logger.getLogger("WD: ");
    private static WebDriver driver;

    private Driver(){}

    public static WebDriver getWebDriverInstance() {
        if (driver == null) {
            switch (Config.getProperty(Config.BROWSER)) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", WebDriver.class.getResource("/drivers/chromedriver.exe").getPath());
                    driver = new ChromeDriver();
                    log.info("Create instance of Chrome Driver");
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser is not supported:" + Config.getProperty(Config.BROWSER));
            }
        }
        return driver;
    }

    public void stopDriver() {
        if (driver != null) {
            Set<String> windowHandles = driver.getWindowHandles();
                for (String handle : windowHandles) {
                    driver.switchTo().window(handle);
                    driver.close();
                    driver.quit();
                }
                log.info("Browser is closed");
            }
            driver = null;
        }
}

