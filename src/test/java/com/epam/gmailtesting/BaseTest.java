package com.epam.gmailtesting;

import com.epam.framework.utility.Config;
import com.epam.framework.utility.Driver;
import com.epam.gmail.bo.LoginBO;
import com.epam.gmail.bo.SendMessageBO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by Uliana Pizhanska on 28/02/2017.
 */
public abstract class BaseTest {

    protected Logger log = Logger.getLogger("WD: ");
    protected SendMessageBO sendMessageBO;
    protected LoginBO loginBO;
    private File logFile;

    @BeforeClass(alwaysRun = true)
    public void beforeClass()  {
        logFile = new File("logfile.log");
        try {
            FileUtils.forceDelete(logFile);
                log.info("Clean up logs");
            } catch (IOException e) {
                log.info("Failed to clean up logs");
            }

        WebDriver driver = Driver.getInstance().getDriver();
        driver.get(Config.getProperty(Config.URL));
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        sendMessageBO = new SendMessageBO();
        loginBO = new LoginBO();
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
       Driver.getInstance().removeDriver();
    }

}
