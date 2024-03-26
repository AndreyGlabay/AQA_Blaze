package com.demoblaze.testdata;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestData {
    // URL CONSTANTS
    public static final String GRID_URL = "http://192.168.0.102:4444";
    public static final String BASE_URL = "https://demoblaze.com/";
    public static final String CART_URL = "https://demoblaze.com/cart.html";
    public static final String PRODUCT1_URL = "https://demoblaze.com/prod.html?idp_=1";
    public static final String PRODUCT2_URL = "https://demoblaze.com/prod.html?idp_=2";
    public static final String PRODUCT3_URL = "https://demoblaze.com/prod.html?idp_=3";
    public static final String PRODUCT4_URL = "https://demoblaze.com/prod.html?idp_=4";
    public static final String PRODUCT5_URL = "https://demoblaze.com/prod.html?idp_=5";
    public static final String PRODUCT6_URL = "https://demoblaze.com/prod.html?idp_=6";
    public static final String PRODUCT7_URL = "https://demoblaze.com/prod.html?idp_=7";
    public static final String PRODUCT8_URL = "https://demoblaze.com/prod.html?idp_=8";
    public static final String PRODUCT9_URL = "https://demoblaze.com/prod.html?idp_=9";

    // USERNAME CONSTANTS
    public static final String USERNAME = "JohnJohnson";
    public static final String USERNAME_1 = "timhce4eNP"; // Generated with www.random.org
    public static final String USERNAME_2 = "x81DfDaelO"; // Generated with www.random.org
    public static final String USERNAME_3 = "WQ8T1HyUUD"; // Generated with www.random.org
    public static final String USERNAME_4 = "ESpc8dds9Y"; // Generated with www.random.org
    public static final String USERNAME_5 = "kWFUYNaAlc"; // Generated with www.random.org
    public static final String USERNAME_WRONG = "WrongUsername";

    //PASSWORD CONSTANTS
    public static final String PASSWORD = "Test-12345";
    public static final String PASSWORD_1 = "Test-123abc";
    public static final String PASSWORD_2 = "Test-abcde";
    public static final String PASSWORD_3 = "123581321";
    public static final String PASSWORD_4 = "R0S0T8wZHc"; // Generated with www.random.org
    public static final String PASSWORD_5 = "vIq3YCdNxi"; // Generated with www.random.org
    public static final String PASSWORD_WRONG = "WrongPassword";

    // UI TESTS - VAR AND ACCESSORS FOR UNIQUE USERNAME VALUE
    public static String uniqueUsernameGC;

    public static String getUniqueUsernameGC() {
        return uniqueUsernameGC;
    }

    public static void setUniqueUsernameGC(String username) {
        uniqueUsernameGC = username;
    }

    // CUCUMBER TESTS - VAR AND ACCESSORS FOR UNIQUE USERNAME VALUE
    public static String validUsername;

    public static String getValidUsername() {
        return validUsername;
    }

    public static void setValidUsername(String username) {
        validUsername = username;
    }
}
