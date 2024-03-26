package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    public static final String PRODUCT_TITLE = "//*[@id=\"tbodyid\"]/h2";
    public static final String PRODUCT_PRICE = "//*[@id=\"tbodyid\"]/h3";
    public static final String PRODUCT_DESCRIPTION = "//*[@id=\"more-information\"]/p";
    public static final String ADD_TO_CART_BUTTON = "//*[@id=\"tbodyid\"]/div[2]/div/a";

    private WebElement productTitle;
    private WebElement productPrice;
    private WebElement productDescription;
    private WebElement addToCartButton;

    // PRODUCT PAGE
    public ProductPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRODUCT_TITLE)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRODUCT_PRICE)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PRODUCT_DESCRIPTION)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ADD_TO_CART_BUTTON)));

        productTitle = driver.findElement(By.xpath(PRODUCT_TITLE));;
        productPrice = driver.findElement(By.xpath(PRODUCT_PRICE));;
        productDescription = driver.findElement(By.xpath(PRODUCT_DESCRIPTION));;
        addToCartButton = driver.findElement(By.xpath(ADD_TO_CART_BUTTON));;
    }

    // Methods for check are web elements present on the page
    public boolean isProductTitleDisplayed() {
        return productTitle.isDisplayed();
    }
    public boolean isProductPriceDisplayed() {
        return productPrice.isDisplayed();
    }
    public boolean isProductDescriptionDisplayed() {
        return productDescription.isDisplayed();
    }
    public boolean isAddToCartButtonDisplayed() {
        return addToCartButton.isDisplayed();
    }


    public WebElement getProductTitle() {
        return productTitle;
    }

    public WebElement getProductPrice() {
        return productPrice;
    }

    public WebElement getProductDescription() {
        return productDescription;
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }
}
