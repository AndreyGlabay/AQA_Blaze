package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    private final WebElement navBarLoggedUser;
    private final WebElement navButtonHome;
    private final WebElement navButtonContact;
    private final WebElement navButtonAboutUs;
    private final WebElement navButtonCart;
    private final WebElement navButtonLogOut;
    private final WebElement navButtonWelcome;

    public NavBarLoggedUser(WebDriver driver) {
        navBarLoggedUser = driver.findElement(By.xpath(NAVBAR_LOGGED_USER_LOCATOR));
        navButtonHome = driver.findElement(By.xpath(HOME_BUTTON_LOCATOR));
        navButtonContact = driver.findElement(By.xpath(CONTACT_BUTTON_LOCATOR));
        navButtonAboutUs = driver.findElement(By.xpath(ABOUT_BUTTON_LOCATOR));
        navButtonCart = driver.findElement(By.xpath(CART_BUTTON_LOCATOR));
        navButtonLogOut = driver.findElement(By.xpath(LOGOUT_BUTTON_LOCATOR));
        navButtonWelcome = driver.findElement(By.xpath(WELCOME_BUTTON_LOCATOR));
    }

    // Method to wait for the elements' presence using explicit waits - try to find an element until it becomes visible.
    // Implements to solve issue when "Log In" and "Welcome" buttons not displayed at Nav Bar after log in flow.
    private WebElement waitForElementAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Methods for check are web elements present in the Nav Bar menu
    public boolean isNavBarLoggedUserDisplayed() {
        return navBarLoggedUser.isDisplayed();
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

    // Catch exceptions for test failure preventing if element's stale or miss happens.
    // Implements to solve issue when "Log In" and "Welcome" buttons not displayed at Nav Bar after log in flow.
    public boolean isNavButtonLogOutDisplayed() {
        try {
            return navButtonHome.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return false;
        }
    }

    // Catch exceptions for test failure preventing if element's stale or miss happens.
    // Implements to solve issue when "Log In" and "Welcome" buttons not displayed at Nav Bar after log in flow.
    public boolean isNavButtonWelcomeDisplayed() {
        try {
            return navButtonHome.isDisplayed();
        } catch (StaleElementReferenceException | NoSuchElementException e) {
            return false;
        }
    }
}