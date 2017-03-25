package com.epam.gmailtesting;

import com.epam.framework.utility.Config;
import com.epam.framework.utility.DataProvd;
import com.epam.gmail.bo.SendMessageBO;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Uliana Pizhanska on 25/03/2017.
 */
public class LoginFunctionalityTest {

    private SendMessageBO sendMessageBO;

    public void setup(){
        sendMessageBO = new SendMessageBO();
    }


    @Test(description = "Login, valid credentials\n",  dataProviderClass = DataProvd.class, dataProvider = "login")
    public void login(String homeTitle){
        setup();
        String title = sendMessageBO.checkPageTitle();
        Assert.assertEquals(title, homeTitle);
        sendMessageBO.login(Config.getProperty(Config.USERNAME),Config.getProperty(Config.PASSWORD));
    }

    @Test(description = "Try to login with invalid password\n",  dataProviderClass = DataProvd.class, dataProvider = "login")
    public void loginWithInvalidPassword(String homeTitle){
        setup();
        String title = sendMessageBO.checkPageTitle();
        Assert.assertEquals(title, homeTitle);
        sendMessageBO.login(Config.getProperty(Config.USERNAME),Config.getProperty(Config.PASSWORD));
    }
}
