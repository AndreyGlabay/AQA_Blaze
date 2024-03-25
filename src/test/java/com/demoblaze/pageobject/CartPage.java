package com.demoblaze.pageobject;

public class CartPage {
    public static final String TABLE_TITLE = "//*[@id=\"page-wrapper\"]/div/div[1]/h2";

    // TABLE - ROW 1 ELEMENTS
    public static final  String ROW_1 = "//*[@id=\"tbodyid\"]/tr[1]";
    public static final String ROW_1_TITLE = "//*[@id=\"tbodyid\"]/tr[1]/td[2]";
    public static final String ROW_1_PRICE = "//*[@id=\"tbodyid\"]/tr[1]/td[3]";
    public static final String ROW_1_DELETE = "//*[@id=\"tbodyid\"]/tr[1]/td[4]";
    public static final String ROW_1_DELETE_LINK = "//*[@id=\"tbodyid\"]/tr[1]/td[4]/a";

    // TABLE - ROW 2 ELEMENTS
    public static final String ROW_2 = "//*[@id=\"tbodyid\"]/tr[2]";
    public static final String ROW_2_TITLE = "//*[@id=\"tbodyid\"]/tr[2]/td[2]";
    public static final String ROW_2_PRICE = "//*[@id=\"tbodyid\"]/tr[2]/td[3]";
    public static final String ROW_2_DELETE = "//*[@id=\"tbodyid\"]/tr[2]/td[4]";
    public static final String ROW_2_DELETE_LINK = "//*[@id=\"tbodyid\"]/tr[2]/td[4]/a";

    // TABLE - ROW 3 ELEMENTS
    public static final String ROW_3 = "//*[@id=\"tbodyid\"]/tr[3]";
    public static final String ROW_3_TITLE = "//*[@id=\"tbodyid\"]/tr[3]/td[2]";
    public static final String ROW_3_PRICE = "//*[@id=\"tbodyid\"]/tr[3]/td[3]";
    public static final String ROW_3_DELETE = "//*[@id=\"tbodyid\"]/tr[3]/td[4]";
    public static final String ROW_3_DELETE_LINK = "//*[@id=\"tbodyid\"]/tr[3]/td[4]/a";

    public static final String TOTAL_TITLE = "//*[@id=\"page-wrapper\"]/div/div[1]/h2";
    public static final String TOTAL_VALUE = "//*[@id=\"totalp\"]";
    public static final String PLACE_ORDER_BUTTON = "//*[@id=\"page-wrapper\"]/div/div[2]/button";

}
