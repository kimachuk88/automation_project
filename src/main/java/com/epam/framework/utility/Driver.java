package com.epam.framework.utility;

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
    public static WebDriver instance;

    private Driver(){}

    public static WebDriver getWebDriverInstance() {
        if (instance == null) {
            switch (Config.getProperty(Config.BROWSER)) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Uliana Pizhanska\\Downloads\\cdp\\automation_project\\src\\main\\resources\\drivers\\chromedriver.exe");
                    instance = new ChromeDriver();
                    log.info("Create instance of Chrome Driver");
                    break;
                case "firefox":
                    instance = new FirefoxDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Browser is not supported:" + Config.getProperty(Config.BROWSER));
            }
        }
        return instance;
    }

    public static void stopDriver() {
        if (instance != null) {
            Set<String> windowHandles = instance.getWindowHandles();
                for (String handle : windowHandles) {
                    instance.switchTo().window(handle);
                    instance.close();
                    instance.quit();
                }
                log.info("Browser is closed");
            }
            instance = null;
        }
}

