package com.epam.framework.component;

import com.epam.framework.Config;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Uliana Pizhanska on 28/02/2017.
 */
public abstract class BaseTest {
/*
    protected Logger log = Logger.getLogger("WD: ");
    protected WebDriver driver;
//    protected DriverConfig driverConfig;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        driverConfig = new DriverConfig();
        driver = driverConfig.getDriver();
        driver.get(Config.getProperty(Config.URL));
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        Test testAnnotation = method.getAnnotation(Test.class);
        if (testAnnotation != null) {
            log.info(method.getName() + " - " + method.getAnnotation(Test.class).description());
        }
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() throws IOException {
        driverConfig = new DriverConfig();
        try {
            driverConfig.stopDriver();
        } catch (Exception e) {
            log.warn("WebDriver stop fail");
        }
    }*/
}
