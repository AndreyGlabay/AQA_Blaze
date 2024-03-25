package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageContent2 {
    @FindBy(css = "a[href='prod.html?idp_=10']")
    private WebElement linkProductId10;

    @FindBy(css = "a[href='prod.html?idp_=11']")
    private WebElement linkProductId11;

    @FindBy(css = "a[href='prod.html?idp_=12']")
    private WebElement linkProductId12;

    @FindBy(css = "a[href='prod.html?idp_=13']")
    private WebElement linkProductId13;

    @FindBy(css = "a[href='prod.html?idp_=14']")
    private WebElement linkProductId14;

    @FindBy(css = "a[href='prod.html?idp_=15']")
    private WebElement linkProductId15;

    public HomePageContent2(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isLinkProductId10displayed() {
        return linkProductId10.isDisplayed();
    }

    public boolean isLinkProductId11displayed() {
        return linkProductId11.isDisplayed();
    }

    public boolean isLinkProductId12displayed() {
        return linkProductId12.isDisplayed();
    }

    public boolean isLinkProductId13displayed() {
        return linkProductId13.isDisplayed();
    }

    public boolean isLinkProductId14displayed() {
        return linkProductId14.isDisplayed();
    }

    public boolean isLinkProductId15displayed() {
        return linkProductId15.isDisplayed();
    }
}
