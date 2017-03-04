package com.epam.framework.controls.extension;

import com.epam.framework.controls.base.WebControlImpl;
import org.openqa.selenium.WebElement;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class Button extends WebControlImpl {

    private WebElement element;

    public Button(WebElement element) {
        super(element);
    }

}
