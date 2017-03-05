package com.epam.framework.controls.extension;

import com.epam.framework.controls.base.WebControlImpl;
import org.openqa.selenium.WebElement;

/**
 * Created by Uliana Pizhanska on 05/03/2017.
 */
public class Label extends WebControlImpl {

    private WebElement element;

    public Label(WebElement element) {
        super(element);
    }

    public static String getText(WebControlImpl webControl) {
        if (webControl != null) {
            String containerValue = webControl.getText();
            return containerValue;
        }
        return null;
    }
}
