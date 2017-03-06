package com.epam.gmail.bo;

import com.epam.gmail.pages.LoginPage;
import com.epam.gmail.pages.MessagePage;
import com.epam.gmail.pages.SentMessagesPage;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class SendMessageBO extends BaseBO{
    private LoginPage loginPage = new LoginPage();
    private MessagePage messagePage = new MessagePage();
    private SentMessagesPage sentMessagesPage = new SentMessagesPage();

    public void login(){
        loginPage.setEmail();
        loginPage.clickNext();
        loginPage.setPassword();
        loginPage.clickSignIn();
        log.info("Credentials for sign in are entered");
    }

    public String checkPageTitle(){
        return loginPage.getPageTitle();

    }

    public String checkAccountName(){
        messagePage.clickOnAccountInfo();
        log.info("Click on Info icon");
        return messagePage.getAccountName();
    }

    public void createMessage(String receiveEmail, String msgText){
        messagePage.clickOnAccountInfo();
        messagePage.clickOnComposeMessage();
        log.info("Creating message");
        messagePage.setReceiver(receiveEmail);
        messagePage.setSubjectMessage(msgText);
        messagePage.setMessageBodyField(msgText);
        messagePage.clickOnSend();
        log.info("Sending message");
        messagePage.clickOnSentFolder();
    }

    public String checkSentMessage(){
        sentMessagesPage.clickOnViewMessage();
        log.info("Check Sent folder");
        return sentMessagesPage.getSubTitle();
    }

}
