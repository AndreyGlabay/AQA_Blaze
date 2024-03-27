package com.demoblaze.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PlaceOrderPropertiesReader {

    public static Properties readPropertiesFile(String filePath) throws IOException, IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        properties.load(fileInputStream);
        fileInputStream.close();
        return properties;
    }
}
