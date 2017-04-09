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

    private Logger log = Logger.getLogger("WD");
    private WebDriver driver;
    private final String PATH_TO_DRIVERS_REPOSITORY = "src/main/resources/drivers/";

    private Driver() {
    }

    private static Driver instance = new Driver();

    public static Driver getInstance() {
        return instance;
    }

    private ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            if (driver == null) {
                switch (Config.getProperty(Config.BROWSER)) {
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", new File(PATH_TO_DRIVERS_REPOSITORY + "chromedriver.exe").getPath());
                        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("start-maximized");
                        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                        driver = new ChromeDriver(capabilities);
                        log.info("Create instance of Chrome Driver");
                        break;
                    case "firefox":
                        driver = new FirefoxDriver();
                        log.info("Create instance of FF Driver");
                        break;
                    case "ie":
                        System.setProperty("webdriver.ie.driver", new File(PATH_TO_DRIVERS_REPOSITORY + "IEDriverServer.exe").getPath());
                        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                        caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                        caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                        driver = new InternetExplorerDriver(caps);
                        driver.manage().deleteAllCookies();
                        break;
                    default:
                        throw new IllegalArgumentException("Browser is not supported:" + Config.getProperty(Config.BROWSER));
                }
            }
            return driver;
        }
    };

    public WebDriver getDriver() {
        return threadLocal.get();
    }

    public void removeDriver() {
        driver = threadLocal.get();
            try {
                driver.manage().deleteAllCookies();
                driver.quit();
            } finally {
                threadLocal.remove();
        }
    }
}

