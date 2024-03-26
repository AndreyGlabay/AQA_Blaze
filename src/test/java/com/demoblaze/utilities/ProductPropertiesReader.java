package com.demoblaze.utilities;

import com.demoblaze.testdata.ProductData;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ProductPropertiesReader {
    public static Map<String, ProductData> readProductProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = ProductPropertiesReader.class.getClassLoader().getResourceAsStream("product.properties");
        properties.load(inputStream);

        Map<String, ProductData> productDataMap = new HashMap<>();
        for (int i = 1; i <= 15; i++) {
            String productId = "PRODUCT_" + i;
            String title = properties.getProperty(productId + "_TITLE");
            String price = properties.getProperty(productId + "_PRICE");
            String description = properties.getProperty(productId + "_DESCRIPTION");
            productDataMap.put(productId, new ProductData(title, price, description));
        }
        return productDataMap;
    }
}