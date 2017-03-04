package com.epam.framework.controls.extension;

import com.epam.framework.controls.base.WebControlImpl;
import org.openqa.selenium.WebElement;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public  class Input extends WebControlImpl {

    private WebElement element;

    public Input(WebElement element) {
        super(element);
    }

    public void setText(String text) {
        element = getWrappedElement();
        element.clear();
        element.sendKeys(text);
    }

    public String getText() {
        return getWrappedElement().getAttribute("value");
    }
}
