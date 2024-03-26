package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductAddedToCart(String productName, String productPrice) {
        // Construct the XPath for the product title and price in the cart table
        String productTitleXPath = "//*[text()='" + productName + "']";
        String productPriceXPath = "//*[text()='" + productPrice + "']";

        // Check if both the product title and price elements are present in the cart table
        return !driver.findElements(By.xpath(ROW_1_TITLE)).isEmpty() &&
                !driver.findElements(By.xpath(ROW_1_PRICE)).isEmpty();
    }

    public String getTotalValue() {
        return driver.findElement(By.xpath(TOTAL_VALUE)).getText();
    }

    public void placeOrder() {
        driver.findElement(By.xpath(PLACE_ORDER_BUTTON)).click();
    }

}
