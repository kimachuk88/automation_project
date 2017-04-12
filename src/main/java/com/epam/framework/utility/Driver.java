package com.epam.framework.utility;


import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
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
    private String browserName = System.getProperty("browserName");

    private Driver() {
    }

    private static Driver instance = new Driver();

    public static Driver getInstance() {
        return instance;
    }

    private ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>() {
        @Override
        protected WebDriver initialValue() {
            DesiredCapabilities capabilities;
            if (driver == null) {
                switch (browserName) {
                    case "chrome":
                        System.setProperty("webdriver.chrome.driver", new File(PATH_TO_DRIVERS_REPOSITORY + "chromedriver.exe").getPath());
                        capabilities = DesiredCapabilities.chrome();
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
                        capabilities = DesiredCapabilities.internetExplorer();
                        capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                        driver = new InternetExplorerDriver(capabilities);
                        driver.manage().deleteAllCookies();
                        break;
                    case "edge":
                        System.setProperty("webdriver.edge.driver", new File(PATH_TO_DRIVERS_REPOSITORY + "MicrosoftWebDriver.exe").getPath());
                        capabilities = DesiredCapabilities.edge();
                        driver = new EdgeDriver(capabilities);
                        driver.manage().deleteAllCookies();
                    default:
                        throw new IllegalArgumentException("Browser is not supported:" + browserName);
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

        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            String[] processes = {"iexplorer.exe", "ie.exe", "chromedriver.exe", "ie32.exe"};
            try {
                for (String process : processes) {
                    Runtime.getRuntime().exec("taskkill /f /t /im " + process).waitFor();
                }
            } catch (IOException e) {
                log.error("Failed to kill process: " + e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

