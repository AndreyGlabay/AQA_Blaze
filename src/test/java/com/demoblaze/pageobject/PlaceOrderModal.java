package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PlaceOrderModal {
    public static final String PLACE_ORDER_MODAL = "//*[@id=\"orderModal\"]/div/div";
    public static final String PLACE_ORDER_TITLE = "//*[@id=\"orderModalLabel\"]";
    public static final String PLACE_ORDER_X_BUTTON = "//*[@id=\"orderModal\"]/div/div/div[1]/button";
    public static final String PLACE_ORDER_TOTAL_PRICE = "//*[@id=\"totalm\"]";
    public static final String PLACE_ORDER_NAME_INPUT = "//*[@id=\"name\"]";
    public static final String PLACE_ORDER_COUNTRY_INPUT = "//*[@id=\"country\"]";
    public static final String PLACE_ORDER_CITY_INPUT = "//*[@id=\"city\"]";
    public static final String PLACE_ORDER_CREDIT_CARD_INPUT = "//*[@id=\"card\"]";
    public static final String PLACE_ORDER_MONTH_INPUT = "//*[@id=\"month\"]";
    public static final String PLACE_ORDER_YEAR_INPUT = "//*[@id=\"year\"]";
    public static final String PLACE_ORDER_CLOSE_BUTTON = "//*[@id=\"orderModal\"]/div/div/div[3]/button[1]";
    public static final String PLACE_ORDER_PURCHASE_BUTTON = "//*[@id=\"orderModal\"]/div/div/div[3]/button[2]";

    // METHOD FOR CHECK - PLACE ORDER MODAL APPEARS
    public static boolean isModalDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));                    // Wait 1 sec max;
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PLACE_ORDER_MODAL))); // Wait appearance;
            return true;                                                                            // Modal appears;
        } catch (Exception e) {
            return false;                                                                           // Modal not appears;
        }
    }
}
