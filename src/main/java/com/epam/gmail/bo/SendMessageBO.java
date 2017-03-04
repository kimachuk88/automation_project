package com.epam.gmail.bo;

import com.epam.gmail.pages.LoginPage;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class SendMessageBO extends BaseBO{
    private LoginPage loginPage = new LoginPage();

    public void login(){
        loginPage.setEmail();
    }
}
