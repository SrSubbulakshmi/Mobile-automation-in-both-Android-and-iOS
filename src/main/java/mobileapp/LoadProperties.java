package mobileapp;

import org.apache.commons.lang3.ObjectUtils;


import java.io.InputStream;
import java.util.Properties;


public class LoadProperties {


    private Properties defaultProperties;
    private Properties properties;
    private String env;
    public LoadProperties()
    {
        System.out.println("Loading Properties");
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("default.properties");
            defaultProperties = new Properties();
            defaultProperties.load(inputStream);

            env = ObjectUtils.firstNonNull(System.getenv("env"), System.getProperty("env"),
                    defaultProperties.getProperty("env"), "staging");

            InputStream propertyInputStream = classLoader.getResourceAsStream(String.format("%s.properties", env));
            properties = new Properties();
            properties.load(propertyInputStream);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getProperty(String key) {
        return ObjectUtils.firstNonNull(System.getenv(key),
                System.getProperty(key),
                properties.getProperty(key),
                defaultProperties.getProperty(key)
        );
    }
}
