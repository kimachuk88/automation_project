package com.epam.framework.utility;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Uliana Pizhanska on 05/03/2017.
 */
public class DataProvd {

    private static Logger log = Logger.getLogger("WD");

    @DataProvider(name = "login")
    public static Object[][] loginTitle() {
        return new Object[][]{
                {"Gmail", "ulianahutnikevych@gmail.com", "Uliana"}
        };
    }

    @DataProvider(name = "invalidPasswd")
    public static Object[][] invalidPasswd (){
        return new Object[][]{
                {"1jj33","Wrong password. Try again."}
        };
    }

    @DataProvider(name = "createMessage")
    public static Object[][] createMessage() {
        return readData("src/main/resources/sendMessageData.csv");
    }

    @DataProvider(name = "invalidLogin")
    public static Object[][] invalidLogin() {
        return readData("src/main/resources/invalidLoginData.csv");
    }


    private static Object[][] readData(String path){
        String[][] data = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            List<String[]> list = bufferedReader.lines()
                    .map(line -> line.split(";"))
                    .collect(Collectors.toList());
            data = list.toArray(new String[list.size()][]);

        }
        catch (FileNotFoundException e){
            log.info("Oops, fle is not found");
        }
        return data;
    }
}
