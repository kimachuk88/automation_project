package com.epam.gmail.bo;

import com.epam.framework.utility.Config;
import com.epam.framework.utility.Driver;
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
        messagePage.waitForPageLoad();
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
    }

    public void sendMessage(){
        messagePage.clickOnSend();
        log.info("Sending message");
    }

    public String checkSentMessage(){
        if (! Config.getProperty(Config.BROWSER).equalsIgnoreCase("ie")) {
            messagePage.clickOnSentFolder();
        }
        sentMessagesPage.clickOnViewMessage();
        log.info("Check Sent folder");
        return sentMessagesPage.getSubTitle();
    }

    public void deleteFromDrafts() {
        messagePage.clickOnCloseMessage();
        messagePage.clickOnDraftsFolder();
        log.info("Go to Drafts");
       // messagePage.checkDraftMessage();
    }


}
