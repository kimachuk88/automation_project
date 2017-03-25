package com.epam.gmail.pages;

import com.epam.framework.controls.extension.Button;
import com.epam.framework.controls.extension.CheckBox;
import com.epam.framework.controls.extension.Input;
import com.epam.framework.controls.extension.Label;
import com.epam.framework.utility.Config;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class LoginPage extends BasePage {
    @FindBy(id = "Email")
    private Input emailField;

    @FindBy(id = "next")
    private Button btnNext;

    @FindBy(id = "profile-name")
    private Label profileName;

    @FindBy(id = "Passwd")
    private Input passwordField;

    @FindBy(id = "signIn")
    private Button btnSignIn;

    @FindBy(id = "PersistentCookie")
    private CheckBox chkStaySignedIn;

    public void setEmail(String email) {
        emailField.setText(email);
    }

    public void clickNext() {
        btnNext.click();
    }

    public String getProfileName() {
        profileName.moveToElement();
        return Label.getText(profileName);
    }

    public void setPassword(String password) {
        waitForControl(passwordField);
        passwordField.setText(password);
    }

    public void clickSignIn(){
        btnSignIn.submit();
    }

    public void uncheckStaySignedIn(){
        waitForControl(chkStaySignedIn);
        chkStaySignedIn.uncheck();
    }

}
