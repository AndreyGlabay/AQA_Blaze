package com.demoblaze.test;

import com.demoblaze.pageobject.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

public class HomePageElementsTests {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://demoblaze.com/";

    @AfterMethod
    public void stopInstance() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test (testName = "GC_CheckElements")
    public void findElementsChrome() throws InterruptedException, MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Make an instance of web page;
        SoftAssert softAssert = new SoftAssert();                   // Add Soft Assertion (too many assertions);
        softAssert.assertTrue(homePage.isHeaderBrandDisplayed(), "Header Brand is missing");
        softAssert.assertTrue(homePage.isHeaderLogoDisplayed(), "Header Logo is missing");
        softAssert.assertTrue(homePage.isNavBarDisplayed(), "Nav Bar is missing");
        softAssert.assertTrue(homePage.isNavButtonHomeDisplayed(), "Nav Button Home is missing");
        softAssert.assertTrue(homePage.isNavButtonContactDisplayed(), "Nav Button Contact is missing");
        softAssert.assertTrue(homePage.isNavButtonAboutUsDisplayed(), "Nav Button About Us is missing");
        softAssert.assertTrue(homePage.isNavButtonCartDisplayed(), "Nav Button Cart is missing");
        softAssert.assertTrue(homePage.isNavButtonLogInDisplayed(), "Nav Button Log In is missing");
        softAssert.assertTrue(homePage.isNavButtonSignUpDisplayed(), "Nav Button Sign Up is missing");
        softAssert.assertTrue(homePage.isCarouselDisplayed(), "Carousel is missing");
        softAssert.assertTrue(homePage.isCategoryMenuAreaDisplayed(), "Category Menu Area is missing");
        softAssert.assertTrue(homePage.isCategoryMenuTitleDisplayed(), "Category Menu Title is missing");
        softAssert.assertTrue(homePage.isCategoryMenuPhonesDisplayed(), "Category Menu Phones is missing");
        softAssert.assertTrue(homePage.isCategoryMenuLaptopsDisplayed(), "Category Menu Laptops is missing");
        softAssert.assertTrue(homePage.isCategoryMenuMonitorsDisplayed(), "Category Menu Monitors is missing");
        softAssert.assertTrue(homePage.isContentAreaDisplayed(), "Content Area is missing");
        softAssert.assertTrue(homePage.isPreviousButtonDisplayed(), "Previous Button is missing");
        softAssert.assertTrue(homePage.isNextButtonDisplayed(), "Next Button is missing");
        softAssert.assertTrue(homePage.isFooterAboutDisplayed(), "Footer About is missing");
        softAssert.assertTrue(homePage.isFooterContactsDisplayed(), "Footer Contacts is missing");
        softAssert.assertTrue(homePage.isFooterLogoDisplayed(), "Footer Logo is missing");
        softAssert.assertTrue(homePage.isFooterCopyrightDisplayed(), "Footer Copyright is missing");
        softAssert.assertAll();
    }

    @Test (testName = "FF_CheckElements")
    public void findElementsFirefox() throws InterruptedException, MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Make an instance of web page;
        SoftAssert softAssert = new SoftAssert();                   // Add Soft Assertion (too many assertions);
        softAssert.assertTrue(homePage.isHeaderBrandDisplayed(), "Header Brand is missing");
        softAssert.assertTrue(homePage.isHeaderLogoDisplayed(), "Header Logo is missing");
        softAssert.assertTrue(homePage.isNavBarDisplayed(), "Nav Bar is missing");
        softAssert.assertTrue(homePage.isNavButtonHomeDisplayed(), "Nav Button Home is missing");
        softAssert.assertTrue(homePage.isNavButtonContactDisplayed(), "Nav Button Contact is missing");
        softAssert.assertTrue(homePage.isNavButtonAboutUsDisplayed(), "Nav Button About Us is missing");
        softAssert.assertTrue(homePage.isNavButtonCartDisplayed(), "Nav Button Cart is missing");
        softAssert.assertTrue(homePage.isNavButtonLogInDisplayed(), "Nav Button Log In is missing");
        softAssert.assertTrue(homePage.isNavButtonSignUpDisplayed(), "Nav Button Sign Up is missing");
        softAssert.assertTrue(homePage.isCarouselDisplayed(), "Carousel is missing");
        softAssert.assertTrue(homePage.isCategoryMenuAreaDisplayed(), "Category Menu Area is missing");
        softAssert.assertTrue(homePage.isCategoryMenuTitleDisplayed(), "Category Menu Title is missing");
        softAssert.assertTrue(homePage.isCategoryMenuPhonesDisplayed(), "Category Menu Phones is missing");
        softAssert.assertTrue(homePage.isCategoryMenuLaptopsDisplayed(), "Category Menu Laptops is missing");
        softAssert.assertTrue(homePage.isCategoryMenuMonitorsDisplayed(), "Category Menu Monitors is missing");
        softAssert.assertTrue(homePage.isContentAreaDisplayed(), "Content Area is missing");
        softAssert.assertTrue(homePage.isPreviousButtonDisplayed(), "Previous Button is missing");
        softAssert.assertTrue(homePage.isNextButtonDisplayed(), "Next Button is missing");
        softAssert.assertTrue(homePage.isFooterAboutDisplayed(), "Footer About is missing");
        softAssert.assertTrue(homePage.isFooterContactsDisplayed(), "Footer Contacts is missing");
        softAssert.assertTrue(homePage.isFooterLogoDisplayed(), "Footer Logo is missing");
        softAssert.assertTrue(homePage.isFooterCopyrightDisplayed(), "Footer Copyright is missing");
        softAssert.assertAll();
    }

    @Test (testName = "GC_CheckTitle")
    public void checkTitleChrome() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var title = driver.getTitle();                              // Represent the title of web page;
        Assert.assertEquals(title, "STORE");
    }

    @Test (testName = "FF_CheckTitle")
    public void checkTitleFirefox() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var title = driver.getTitle();                              // Represent the title of web page;
        Assert.assertEquals(title, "STORE");
    }
}
