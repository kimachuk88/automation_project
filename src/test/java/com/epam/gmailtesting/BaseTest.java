package com.epam.gmailtesting;

import com.epam.framework.utility.Config;
import com.epam.framework.utility.Driver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Uliana Pizhanska on 28/02/2017.
 */
public abstract class BaseTest {

    protected Logger log = Logger.getLogger("WD: ");


    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        Driver.getWebDriverInstance();
        Driver.instance.get(Config.getProperty(Config.URL));
        Driver.instance.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Driver.instance.manage().window().maximize();
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
        try {
            Driver.stopDriver();
        } catch (Exception e) {
            log.warning("WebDriver stop fail");
        }
    }
}
