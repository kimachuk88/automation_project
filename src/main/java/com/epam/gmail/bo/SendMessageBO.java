package com.epam.gmail.bo;

import com.epam.gmail.pages.LoginPage;
import com.epam.gmail.pages.MessagePage;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class SendMessageBO extends BaseBO{
    private LoginPage loginPage = new LoginPage();
    private MessagePage messagePage = new MessagePage();

    public void login(){
        loginPage.setEmail();
        loginPage.clickNext();
        loginPage.setPassword();
        loginPage.clickSignIn();
    }

    public String checkPageTitle(){
        return loginPage.getPageTitle();
    }

    public String checkAccountName(){
        messagePage.clickOnAccountInfo();
        return messagePage.getAccountName();
    }

    public void createMessage(String receiveEmail){
        messagePage.clickOnAccountInfo();
        messagePage.clickOnComposeMessage();
        messagePage.setReceiver(receiveEmail);
    }
}
