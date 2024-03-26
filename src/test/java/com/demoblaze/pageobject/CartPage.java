package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    public static final String TABLE_TITLE = "//*[@id=\"page-wrapper\"]/div/div[1]/h2";

    // TABLE - ROW 1 ELEMENTS
    public static final  String ROW_1 = "//*[@id=\"tbodyid\"]/tr[1]";
    public static final String ROW_1_TITLE = "//*[@id=\"tbodyid\"]/tr[1]/td[2]";
    public static final String ROW_1_PRICE = "//*[@id=\"tbodyid\"]/tr[1]/td[3]";
    public static final String ROW_1_DELETE = "//*[@id=\"tbodyid\"]/tr[1]/td[4]";
    public static final String ROW_1_DELETE_LINK = ROW_1_DELETE + "/a";

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

    // METHOD FOR IF BOTH PRODUCT TITLE & PRICE ARE PRESENT IN THE CART PAGE TABLE
    public boolean isProductAddedToCart(String productName, String productPrice) {
        String productTitleXPath = "//*[text()='" + productName + "']"; // XPath for the product TITLE in the cart table;
        String productPriceXPath = "//*[text()='" + productPrice + "']"; // XPath for the product PRICE in the cart table;
        return !driver.findElements(By.xpath(ROW_1_TITLE)).isEmpty() && // Check if product TITLE is present in the table &&
                !driver.findElements(By.xpath(ROW_1_PRICE)).isEmpty();  // Check if product PRICE is present in the table.
    }

    // METHOD FOR CLICK TOTAL VALUE
    public String getTotalValue() {
        return driver.findElement(By.xpath(TOTAL_VALUE)).getText();
    }

    // METHOD FOR CLICK ON PLACE ORDER BUTTON
    public void placeOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PLACE_ORDER_BUTTON)));
        driver.findElement(By.xpath(PLACE_ORDER_BUTTON)).click();
    }

    // METHOD TO CLICK ON THE DELETE LINK IN THE CART PAGE TABLE
    public void clickDeleteLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ROW_1_DELETE_LINK)));
        driver.findElement(By.xpath(ROW_1_DELETE_LINK)).click();
    }
}
