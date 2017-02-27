package com.epam.framework;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Uliana Pizhanska on 27/02/2017.
 */
public class DriverConfig {

    private WebDriver getDriver( DesiredCapabilities dc){
        WebDriver driver;
        switch (Config.getProperty(Config.BROWSER)){
            case "chrome":
                driver = new ChromeDriver(dc);
                break;
            case "firefox":
                driver = new FirefoxDriver(dc);
            default:
                throw new IllegalArgumentException("Browser is not supported:" + dc.getBrowserName());
        }
        return driver;

        }

    private DesiredCapabilities getDesireCapabilities(String browser) {
        DesiredCapabilities dc;
        switch (browser) {
            case "firefox":
                dc = DesiredCapabilities.firefox();
                dc.setPlatform(Platform.ANY);
                dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                break;
            case "chrome":
                dc = DesiredCapabilities.chrome();
                dc.setPlatform(Platform.ANY);
                dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
                break;
            default:
                throw new IllegalArgumentException("Browser is not supported:" + browser);
        }
        return dc;
    }

    public WebDriver getDriver() {
        String browser = Config.getProperty(Config.BROWSER);
        System.setProperty("webdriver.chrome.driver", getClass().getResource("/drivers/chromedriver.exe").getPath());
        DesiredCapabilities dc = getDesireCapabilities(browser);
        WebDriver driver = null;
        driver = getDriver(dc);
        return driver;
    }
    }

