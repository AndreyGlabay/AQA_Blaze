package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageContent1 {
    @FindBy(xpath = "//a[text()='Samsung galaxy s6']")
    private WebElement linkProductId1;

    @FindBy(xpath = "//a[text()='Nokia lumia 1520']")
    private WebElement linkProductId2;

    @FindBy(xpath = "//a[text()='Nexus 6']")
    private WebElement linkProductId3;

    @FindBy(xpath = "//a[text()='Samsung galaxy s7']")
    private WebElement linkProductId4;

    @FindBy(xpath = "//a[text()='Iphone 6 32gb']")
    private WebElement linkProductId5;

    @FindBy(xpath = "//a[text()='Sony xperia z5']")
    private WebElement linkProductId6;

    @FindBy(xpath = "//a[text()='HTC One M9']")
    private WebElement linkProductId7;

    @FindBy(xpath = "//a[text()='Sony vaio i5']")
    private WebElement linkProductId8;

    @FindBy(xpath = "//a[text()='Sony vaio i7']")
    private WebElement linkProductId9;

    // Constructor
    public HomePageContent1(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Accessors
    public WebElement getLinkProductId1() {
        return linkProductId1;
    }
    public WebElement getLinkProductId2() {
        return linkProductId2;
    }
    public WebElement getLinkProductId3() {
        return linkProductId3;
    }
    public WebElement getLinkProductId4() {
        return linkProductId4;
    }
    public WebElement getLinkProductId5() {
        return linkProductId5;
    }
    public WebElement getLinkProductId6() {
        return linkProductId6;
    }
    public WebElement getLinkProductId7() {
        return linkProductId7;
    }
    public WebElement getLinkProductId8() {
        return linkProductId8;
    }
    public WebElement getLinkProductId9() {
        return linkProductId9;
    }


    // Methods to check if links are displayed
    public boolean isLinkProductId1displayed() {
        return linkProductId1.isDisplayed();
    }

    public boolean isLinkProductId2displayed() {
        return linkProductId2.isDisplayed();
    }

    public boolean isLinkProductId3displayed() {
        return linkProductId3.isDisplayed();
    }

    public boolean isLinkProductId4displayed() {
        return linkProductId4.isDisplayed();
    }

    public boolean isLinkProductId5displayed() {
        return linkProductId5.isDisplayed();
    }

    public boolean isLinkProductId6displayed() {
        return linkProductId6.isDisplayed();
    }

    public boolean isLinkProductId7displayed() {
        return linkProductId7.isDisplayed();
    }

    public boolean isLinkProductId8displayed() {
        return linkProductId8.isDisplayed();
    }

    public boolean isLinkProductId9displayed() {
        return linkProductId9.isDisplayed();
    }
}
