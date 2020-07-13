package com.nexogichealthcare.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyLoader {
    static String env = System.getProperty("NEXOGIC_ENV")!= null ? System.getProperty("NEXOGIC_ENV") : "local" ;
    static Properties props = new Properties();
    static Logger log = LoggerFactory.getLogger(PropertyLoader.class);
    
    public static Properties loadProperties() {
        log.info("Server env: {}", env);
        InputStream configFile = PropertyLoader.class.getClassLoader().getResourceAsStream( env + ".properties");
        // load a properties file from class path, inside static method
        try {
            props.load(configFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return props;
    }
}
