package com.epam.gmailtesting;

import com.epam.framework.utility.Config;
import com.epam.framework.utility.DataProvd;
import com.epam.gmail.bo.LoginBO;
import com.epam.gmail.bo.SendMessageBO;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Uliana Pizhanska on 25/03/2017.
 */
public class LoginFunctionalityTest extends BaseTest {


    @Test(description = "\n" +
            "1. Go to https://mail.google.com/mail/\n" +
            "2. Set invalid username (non-existent user) and click on next\n" +
            "3.  Set invalid username (illegal characters) and click on next\n" +
            "4.  Set invalid username (over 256 characters ) and click on next\n" , dataProviderClass = DataProvd.class, dataProvider = "invalidLogin")
    public void loginWithInvalidEmail( String invalidUser, String errorMessage){
        loginBO.loginEmail(invalidUser);
        Assert.assertTrue(loginBO.getErrorMessage().contains(errorMessage));
    }


    @Test(description = "\n" +
            "1. Go to https://mail.google.com/mail /\n" +
            "2. Set valid username, but invalid password and click on next\n" , dataProviderClass = DataProvd.class, dataProvider = "invalidPasswd", priority = 1)
    public void loginWithInvalidPassword(String invalidPasswd, String errorInvPasswd){
        loginBO.loginEmail(Config.getProperty(Config.USERNAME));
        loginBO.loginPasswd(invalidPasswd);
        Assert.assertEquals(loginBO.getErrorMessagePasswd(), errorInvPasswd);

    }


    @Test(description = "\n" +
            "1. Check email/\n" +
            "2. Set valid password and click on next\n"+
            "3. Check account name\n" , dataProviderClass = DataProvd.class, dataProvider = "login", priority = 2)
    public void loginWithValidCredentials(String title, String email, String accountName){
        Assert.assertEquals(loginBO.checkPageTitle(),title);
        Assert.assertEquals(loginBO.getEmailDisplayed(),email);
        loginBO.loginPasswd(Config.getProperty(Config.PASSWORD));
        Assert.assertTrue(sendMessageBO.checkAccountName().contains(accountName));
    }
}
