package com.epam.gmailtesting;

import com.epam.framework.utility.Config;
import com.epam.framework.utility.DataProvd;
import com.epam.gmail.bo.LoginBO;
import com.epam.gmail.bo.SendMessageBO;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class SendFunctionalityTest extends BaseTest {

    @Test(description = "Login, open custom search page\n",  dataProviderClass = DataProvd.class, dataProvider = "login")
    public void login(String homeTitle, String email,String accountName){
        Assert.assertEquals(loginBO.checkPageTitle(), homeTitle);
        loginBO.loginEmail(Config.getProperty(Config.USERNAME));
        Assert.assertEquals(loginBO.getEmailDisplayed(),email);
        loginBO.loginPasswd(Config.getProperty(Config.PASSWORD));
        Assert.assertTrue(sendMessageBO.checkAccountName().contains(accountName));
    }

    @Test( dependsOnMethods = "login", description = "\n" +
            "1. Check account name and click on Compose Message\n" +
            "2. Set receiver, message subject and message body, click on Send\n" +
            "3. Go to Sent folder and check details of sent message\n",
            dataProviderClass = DataProvd.class, dataProvider = "createMessage")
    public void sendMessage( String receiveEmail, String messageText){
        sendMessageBO.createMessage(receiveEmail,messageText);
        sendMessageBO.sendMessage();
        Assert.assertEquals(sendMessageBO.checkSentMessage(),messageText);
    }


    //TO_DO: should be completed

   /* @Test( dependsOnMethods = "sendMessage", description = "\n" +
            "1. Check account name and click on Compose Message\n" +
            "2. Set receiver, message subject and message body, click on Send\n" +
            "3. Go to Drafts folder and check if message is saved and delete it", dataProviderClass = DataProvd.class, dataProvider = "createMessage")
    public void moveToInboxAndDelete(String receiveEmail, String messageText){
        sendMessageBO.checkMessage();
        sendMessageBO.moveToInbox();
    }*/

}
