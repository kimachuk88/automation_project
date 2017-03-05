package com.epam.gmailtesting;

import com.epam.framework.utility.DataProvd;
import com.epam.gmail.bo.SendMessageBO;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class GmailFunctionalityTest extends BaseTest {

    private SendMessageBO sendMessageBO;

    @Test(dataProviderClass = DataProvd.class, dataProvider = "loginAndCreateMessage",priority = 1)
    public void login(String homeTitle, String accountName, String receiveEmail, String messageText){
        sendMessageBO = new SendMessageBO();
        String title = sendMessageBO.checkPageTitle();
        Assert.assertEquals(title, homeTitle);
        sendMessageBO.login();
        title = sendMessageBO.checkAccountName();
        Assert.assertEquals(title, accountName);
        sendMessageBO.createMessage(receiveEmail);
    }
}
