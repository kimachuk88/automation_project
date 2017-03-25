package com.epam.gmailtesting;

import com.epam.framework.utility.Config;
import com.epam.framework.utility.DataProvd;
import com.epam.gmail.bo.SendMessageBO;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class GmailFunctionalityTest extends BaseTest {

    private SendMessageBO sendMessageBO;

    public void setup(){
        sendMessageBO = new SendMessageBO();
    }


    @Test(description = "Login, open custom search page\n",  dataProviderClass = DataProvd.class, dataProvider = "login")
    public void login(String homeTitle){
        setup();
        String title = sendMessageBO.checkPageTitle();
        Assert.assertEquals(title, homeTitle);
        sendMessageBO.login(Config.getProperty(Config.USERNAME),Config.getProperty(Config.PASSWORD));
    }

    @Test( dependsOnMethods = "login", description = "" +
            "1. Check account name and click on Compose Message\n" +
            "2. Set receiver, message subject and message body, click on Send\n" +
            "3. Go to Sent folder and check details of sent message\n",
            dataProviderClass = DataProvd.class, dataProvider = "createMessage")
    public void sendMessage(String accountName, String receiveEmail, String messageText){
        String title = sendMessageBO.checkAccountName();
        Assert.assertEquals(title, accountName);
        sendMessageBO.createMessage(receiveEmail,messageText);
        sendMessageBO.sendMessage();
        title = sendMessageBO.checkSentMessage();
        Assert.assertEquals(title,messageText);
    }

    @Test( dependsOnMethods = "login", description = "" +
            "1. Check account name and click on Compose Message\n" +
            "2. Set receiver, message subject and message body, click on Send\n" +
            "3. Go to Drafts folder and check if message is saved and delete it", dataProviderClass = DataProvd.class, dataProvider = "createMessage")
    public void saveAsDraftAndDelete(String accountName, String receiveEmail, String messageText){
        String title = sendMessageBO.checkAccountName();
        Assert.assertEquals(title, accountName);
        sendMessageBO.createMessage(receiveEmail,messageText);
        sendMessageBO.deleteFromDrafts();
    }
}
