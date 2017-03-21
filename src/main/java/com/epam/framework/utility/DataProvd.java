package com.epam.framework.utility;

import org.testng.annotations.DataProvider;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Created by Uliana Pizhanska on 05/03/2017.
 */
public class DataProvd {

    @DataProvider(name = "login")
    public static Object[][] loginTitle() {
        return new Object[][]{
                {"Gmail"}
        };
    }

    @DataProvider(name = "createMessage")
    public static Object[][] createMessage() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/data.csv"));
        List<String[]> list = bufferedReader.lines()
                .map(line -> line.split(","))
                .collect(Collectors.toList());
        String[][] data = list.toArray(new String[list.size()][]);
        return data;
    }
}
