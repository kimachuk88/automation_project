package com.epam.gmail.pages;
import com.epam.framework.utility.CustomFieldDecorator;
import com.epam.framework.utility.Driver;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Uliana Pizhanska on 27/02/2017.
 */
public abstract class BasePage {

    protected Logger log = Logger.getLogger("WD");
    protected Wait<WebDriver> wait;

    public BasePage() {
        PageFactory.initElements(new CustomFieldDecorator(Driver.getInstance().getDriver()), this);
    }

    public boolean waitForControl(WebElement webControl) {
        log.info("Wait for get control of element");
        wait = getWait(120);
        return wait.until(webDriver -> webControl != null && webControl.isEnabled() && webControl.isDisplayed());
    }

    public void waitElementToClick(WebElement webElement){
        waitForControl(webElement);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }


    public void waitForPageLoad() {
        new WebDriverWait(Driver.getInstance().getDriver(), 60).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        log.info("Wait for page load");
    }

    private FluentWait getWait(int timeout) {
        return new FluentWait<>(Driver.getInstance().getDriver())
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    public void waitForAlert(int sec){

        WebDriverWait wait = new WebDriverWait(Driver.getInstance().getDriver(), sec, 100);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getPageTitle(){
        String title = Driver.getInstance().getDriver().getTitle();
        log.info("Title of current page: " + title);
        return title;
    }

}

