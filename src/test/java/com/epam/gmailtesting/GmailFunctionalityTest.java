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

    @Test(dataProviderClass = DataProvd.class, dataProvider = "loginTitle")
    public void login(String homeTitle, String accountName){
        sendMessageBO = new SendMessageBO();
        String title = sendMessageBO.checkPageTitle();
        Assert.assertEquals(title, homeTitle);
        sendMessageBO.login();
    }

    @Test(dataProviderClass = DataProvd.class, dataProvider = "loginTitle")
    public void SendMessage(String accountName){
        String title = sendMessageBO.checkMessagePageTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains(accountName));
    }
}
