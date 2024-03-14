package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Create the test class and implement Page Object for the Home Page after success Sign Up
public class NavBarLoggedUser {
    private WebDriver driver;
    public static final String NAVBAR_LOGGED_USER_LOCATOR = "//*[@id=\"navbarExample\"]";
    public static final String HOME_BUTTON_LOCATOR = "//*[@id=\"navbarExample\"]/ul/li[1]/a";
    public static final String CONTACT_BUTTON_LOCATOR = "//*[@id=\"navbarExample\"]/ul/li[2]/a";
    public static final String ABOUT_BUTTON_LOCATOR = "//*[@id=\"navbarExample\"]/ul/li[3]/a";
    public static final String CART_BUTTON_LOCATOR = "//*[@id=\"cartur\"]";
    public static final String LOGOUT_BUTTON_LOCATOR = "//*[@id=\"logout2\"]";
    public static final String WELCOME_BUTTON_LOCATOR = "//*[@id=\"nameofuser\"]";

    private WebElement navBar;
    private WebElement navButtonHome;
    private WebElement navButtonContact;
    private WebElement navButtonAboutUs;
    private WebElement navButtonCart;
    private WebElement navButtonLogOut;
    private WebElement navButtonWelcome;


    public WebElement getNavButtonHome() {
        return navButtonHome;
    }

    public WebElement getNavButtonContact() {
        return navButtonContact;
    }

    public WebElement getNavButtonAboutUs() {
        return navButtonAboutUs;
    }

    public WebElement getNavButtonCart() {
        return navButtonCart;
    }

    public WebElement getNavButtonLogOut() {
        return navButtonLogOut;
    }

    public WebElement getNavButtonWelcome() {
        return navButtonWelcome;
    }

    public void navBarLoggedUser(WebDriver driver) {
        this.driver = driver;
        navButtonHome = driver.findElement(By.xpath(HOME_BUTTON_LOCATOR));
        navButtonContact = driver.findElement(By.xpath(CONTACT_BUTTON_LOCATOR));
        navButtonAboutUs = driver.findElement(By.xpath(ABOUT_BUTTON_LOCATOR));
        navButtonCart = driver.findElement(By.xpath(CART_BUTTON_LOCATOR));
        navButtonLogOut = driver.findElement(By.xpath(LOGOUT_BUTTON_LOCATOR));
        navButtonWelcome = driver.findElement(By.xpath(WELCOME_BUTTON_LOCATOR));
    }

    // Methods for check are web elements present in the Nav Bar menu
    public boolean isNavBarLoggedUserDisplayed() {
        return navBar.isDisplayed();
    }

    public boolean isNavButtonHomeDisplayed() {
        return navButtonHome.isDisplayed();
    }

    public boolean isNavButtonContactDisplayed() {
        return navButtonContact.isDisplayed();
    }

    public boolean isNavButtonAboutUsDisplayed() {
        return navButtonAboutUs.isDisplayed();
    }

    public boolean isNavButtonCartDisplayed() {
        return navButtonCart.isDisplayed();
    }

    public boolean isNavButtonLogOutDisplayed() {
        return navButtonLogOut.isDisplayed();
    }

    public boolean isNavButtonWelcomeDisplayed() {
        return navButtonWelcome.isDisplayed();
    }
}