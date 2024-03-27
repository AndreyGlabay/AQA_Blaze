package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderCompleteModal {
    public static final String ORDER_COMPLETE_MODAL = "/html/body/div[10]";
    public static final String ORDER_COMPLETE_SIGN = "/html/body/div[10]/div[4]/div[1]";
    public static final String ORDER_COMPLETE_THANKS = "/html/body/div[10]/h2";
    public static final String ORDER_COMPLETE_DATA = "/html/body/div[10]/p";
    public static final String ORDER_COMPLETE_OK_BUTTON = "/html/body/div[10]/div[7]/div/button";

    private final WebDriver driver;

    public OrderCompleteModal(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getOrderCompleteModal() {
        return driver.findElement(By.xpath(ORDER_COMPLETE_MODAL));
    }

    public WebElement getOrderCompleteSign() {
        return getOrderCompleteModal().findElement(By.xpath(ORDER_COMPLETE_SIGN));
    }

    public WebElement getOrderCompleteThanks() {
        return getOrderCompleteModal().findElement(By.xpath(ORDER_COMPLETE_THANKS));
    }

    public WebElement getOrderCompleteData() {
        return getOrderCompleteModal().findElement(By.xpath(ORDER_COMPLETE_DATA));
    }

    public WebElement getOrderCompleteOkButton() {
        return getOrderCompleteModal().findElement(By.xpath(ORDER_COMPLETE_OK_BUTTON));
    }


    // METHODS FOR CHECK IF ELEMENTS ARE DISPLAYED
    public boolean isOrderCompleteModalDisplayed() {
        return getOrderCompleteModal().isDisplayed();
    }

    public boolean isOrderCompleteSignDisplayed() {
        return getOrderCompleteSign().isDisplayed();
    }


    // METHODS FOR GET TEXT
    public String getOrderCompleteThanksText() {
        return getOrderCompleteThanks().getText();
    }

    public String getOrderCompleteDataText() {
        return getOrderCompleteData().getText();
    }


    // METHOD FOR SUBMIT PLACE ORDER MODAL BY PURCHASE BUTTON
    public void submitOrderCompleteByOkButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ORDER_COMPLETE_OK_BUTTON)));
        driver.findElement(By.xpath(ORDER_COMPLETE_OK_BUTTON)).click();
    }
}
