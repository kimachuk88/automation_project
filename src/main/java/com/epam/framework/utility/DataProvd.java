package com.epam.framework.utility;

import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Uliana Pizhanska on 05/03/2017.
 */
public class DataProvd {


    public static Object[][] loginTitl(){
        return new Object[][]{
                {"Gmail", "Uliana Hutnikevych", "pizhanska29@gmail.com", "TestMessage"}
        };
    }
    @DataProvider(name="loginAndCreateMessage")
    public static Object[][] loginTitle() {
        Properties cred = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/main/resources/input.properties");
            cred.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[][] creds = new Object[1][4];
        creds[0][0] = cred.getProperty("title");
        creds[0][1] = cred.getProperty("account");
        creds[0][2] = cred.getProperty("email");
        creds[0][3] = cred.getProperty("subj");
        return creds;
    }
}
