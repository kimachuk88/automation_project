package com.epam.gmailtesting;

import com.epam.gmail.bo.SendMessageBO;
import org.testng.annotations.Test;

/**
 * Created by Uliana Pizhanska on 04/03/2017.
 */
public class SendMessageTest extends BaseTest {

    private SendMessageBO sendMessageBO;

    @Test
    public void loginAndCreateMessage(){
        sendMessageBO = new SendMessageBO();
        sendMessageBO.login();
    }
}
