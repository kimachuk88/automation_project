package com.epam.framework;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Uliana Pizhanska on 27/02/2017.
 */
public class Config {

    private static final String PROPERTY_FILE = "%s.properties";
    public static final String URL = "url";
    private static Properties config;
    protected static String DEFAULT_CONFIG_NAME = "web_driver";
    public static final String BROWSER = "browser";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private static Properties loadProperties(String fileName) throws Exception {
        Properties result = new Properties();
        InputStream in = Config.class.getClassLoader().getResourceAsStream(fileName);
        result.load(in);
        return result;
    }

    public static String getProperty(String key) {
        try {
            if (config == null) {
                String configName = System.getenv("TEST_CONFIG");
                if (configName == null) configName = DEFAULT_CONFIG_NAME;
                String fileName = String.format(PROPERTY_FILE, configName);
                config = loadProperties(fileName);
            }
            if (config.containsKey(key)) {
                return config.getProperty(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
