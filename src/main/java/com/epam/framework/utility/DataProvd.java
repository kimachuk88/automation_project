package com.epam.framework.utility;

import org.testng.annotations.DataProvider;

/**
 * Created by Uliana Pizhanska on 05/03/2017.
 */
public class DataProvd {

    @DataProvider(name="loginAndCreateMessage")
    public static Object[][] loginTitle(){
        return new Object[][]{
                {"Gmail", "Uliana Hutnikevych", "pizhanska29@gmail.com", "TestMessage"}
        };
    }
}
