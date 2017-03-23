package com.epam.framework.utility;


import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.HttpCookie;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.SimpleTimeZone;

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
                case "ie":
                    System.setProperty("webdriver.ie.driver", new File(PATH_TO_DRIVERS_REPOSITORY +"IEDriverServer.exe").getPath());
                    DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                    caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                    caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                    instance = new InternetExplorerDriver(caps);
                    instance.manage().deleteAllCookies();
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
                instance.manage().deleteAllCookies();
                instance.quit();
            }

            log.info("Browser is closed");
        }
        instance = null;
    }


}

