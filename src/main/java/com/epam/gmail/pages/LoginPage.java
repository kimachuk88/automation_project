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

    @FindBy(id = "errormsg_0_Email")
    private Label errorInvalidUser;

    @FindBy(id = "errormsg_0_Passwd")
    private Label errorInvalidPasswd;

    @FindBy(id = "email-display")
    private Label emailDisplayed;

    public void setEmail(String email) {
        emailField.setText(email);
    }

    public void clickNext() {
        waitForControl(btnNext);
        btnNext.click();
        waitForPageLoad();
    }

    public String getProfileName() {
        profileName.moveToElement();
        return Label.getText(profileName);
    }

    public String getDisplayedEmail() {
        waitForPageLoad();
        waitForControl(emailDisplayed);
        emailDisplayed.moveToElement();
        return Label.getText(emailDisplayed);
    }

    public String getTextError(){
        waitForPageLoad();
        waitForControl(errorInvalidUser);
        errorInvalidUser.moveToElement();
        return Label.getText(errorInvalidUser);
    }

    public String getTextErrorPasswd(){
        errorInvalidPasswd.moveToElement();
        return Label.getText(errorInvalidPasswd);
    }

    public void setPassword(String password) {
        waitForControl(passwordField);
        passwordField.setText(password);
        waitForPageLoad();
    }

    public void clickSignIn(){
        btnSignIn.submit();
    }

    public void uncheckStaySignedIn(){
        waitForControl(chkStaySignedIn);
        chkStaySignedIn.uncheck();
    }

}
