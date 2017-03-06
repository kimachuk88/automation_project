package com.epam.framework.utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Uliana Pizhanska on 27/02/2017.
 */
public class Driver {

    private static Logger log = Logger.getLogger("WD");
    public static WebDriver instance;
    private static final String PATH_TO_DRIVERS_REPOSITORY = "src/main/resources/drivers/";

    private Driver(){}

    public static WebDriver getWebDriverInstance()  {
        if (instance == null) {
            switch (Config.getProperty(Config.BROWSER)) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver",new File(PATH_TO_DRIVERS_REPOSITORY + "chromedriver.exe").getPath());
                    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                    ChromeOptions options = new ChromeOptions();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    instance = new ChromeDriver(capabilities);
                    log.info("Create instance of Chrome Driver");
                    break;
                case "firefox":
                    instance = new FirefoxDriver();
                    log.info("Create instance of FF Driver");
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

