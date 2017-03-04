package com.epam.gmail.pages;

import com.epam.framework.controls.extension.Input;
import com.epam.framework.utility.Config;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class LoginPage extends BasePage{
    @FindBy(id = "Email")
    private Input emailField;

    public void setEmail(){
        emailField.setText(Config.getProperty(Config.USERNAME));
    }
}
