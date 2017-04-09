package com.epam.framework.controls.extension;

import com.epam.framework.controls.base.WebControlImpl;
import com.epam.framework.utility.Driver;
import com.google.common.base.Predicate;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Uliana Pizhanska on 16/03/2017.
 */
public class CheckBox extends WebControlImpl {

    private WebElement element;
    private int wait = 5;
    private WebDriverWait driverWait = new WebDriverWait(Driver.getInstance().getDriver(), wait);

    public CheckBox(WebElement element) {
        super(element);
    }

    public void check() {
        Predicate<WebDriver> condition = (input) -> this.getAttribute("checked") != null;
        repeatUntil(condition, true);
    }


    public void uncheck() {
        Predicate<WebDriver> condition = (input) -> this.getAttribute("checked") == null;
        repeatUntil(condition, false);
    }

    private void repeatUntil(Predicate<WebDriver> verificationPredicate, boolean requiredCheckboxState) {
        for (int i = 0; i < 60 / wait; i++) {
            if (this.isSelected() != requiredCheckboxState) {
                this.clickJS();
            }
            try {
                driverWait.until(verificationPredicate);
                return;
            } catch (TimeoutException e) {
            }
        }
    }
}
