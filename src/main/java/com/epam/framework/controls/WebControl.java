package com.epam.framework.controls;

import com.epam.framework.DriverConfig;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Uliana Pizhanska on 28/02/2017.
 */
public class WebControl {
    protected final String name;
    protected final String page;
    protected WebDriver driver;
    protected DriverConfig driverConfig;
    protected WebElement webElement;
    protected Logger log = Logger.getLogger("WD: ");
    protected Actions actions;

    public WebControl(final WebElement webElement, final String name, final String page) {
        driverConfig = new DriverConfig();
        driver = driverConfig.getDriver();
        this.name = name;
        this.page = page;
        this.webElement = webElement;
        actions = new Actions(driver);
    }

    public String getName() {
        return name;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public void click() {
        webElement.click();
        log.info("Click on: '" + name + "' on '" + page + "' page.");
    }

    public Boolean isEnabled() {
        try {
            return webElement.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public void moveMouseToMe() {
        actions.moveToElement(webElement).perform();
    }
}
