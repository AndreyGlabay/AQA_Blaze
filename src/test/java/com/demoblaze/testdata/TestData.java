package com.demoblaze.testdata;

public class TestData {
    // URL CONSTANTS
    public static final String GRID_URL = "http://192.168.0.102:4444";
    public static final String BASE_URL = "https://demoblaze.com/";

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

    // VAR AND ACCESSORS FOR UNIQUE USERNAME VALUE (USING TIMESTAMP)
    public static String uniqueUsername;
    public static String getUniqueUsernameGC() {
        return uniqueUsername;
    }
    public static void setUniqueUsernameGC(String username) {
        uniqueUsername = username;
    }
}
