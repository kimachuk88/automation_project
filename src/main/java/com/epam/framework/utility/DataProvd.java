package com.epam.framework.utility;

import org.testng.annotations.DataProvider;

/**
 * Created by Uliana Pizhanska on 05/03/2017.
 */
public class DataProvd {

    @DataProvider(name="loginTitle")
    public static Object[][] loginTitle(){
        return new Object[][]{
                {"Gmail", "Uliana Hutnikevych"}
        };
    }
}
