package com.epam.framework.controls.base;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public interface WebControl extends WebElement,WrapsElement,Locatable {

    default boolean elementWired(WebElement element) {
        return (element != null);
    }
}
