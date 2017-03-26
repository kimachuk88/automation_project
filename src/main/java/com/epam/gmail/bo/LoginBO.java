package com.epam.gmail.bo;

import com.epam.gmail.pages.LoginPage;

/**
 * Created by Uliana Pizhanska on 25/03/2017.
 */
public class LoginBO extends BaseBO{

    private LoginPage loginPage = new LoginPage();

    public void loginPasswd(String password){
        loginPage.setPassword(password);
        loginPage.uncheckStaySignedIn();
        loginPage.clickSignIn();
        log.info("Password is entered");
    }

    public void loginEmail(String email){
        loginPage.setEmail(email);
        loginPage.clickNext();
        log.info("Email is entered");
    }

    public String checkPageTitle(){
        return loginPage.getPageTitle();

    }

    public String getErrorMessage(){
        return loginPage.getTextError();
    }

    public String getErrorMessagePasswd(){
        return loginPage.getTextErrorPasswd();
    }

    public String getEmailDisplayed(){
        return loginPage.getDisplayedEmail();
    }

}
