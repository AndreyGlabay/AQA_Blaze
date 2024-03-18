package com.demoblaze.test;

import com.demoblaze.pageobject.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

import static com.demoblaze.pageobject.HomePage.FOOTER_ABOUT_LOCATOR;
import static com.demoblaze.pageobject.HomePage.FOOTER_CONTACTS_LOCATOR;

public class HomePageElementsTests {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://demoblaze.com/";

    @AfterMethod
    public void stopInstance() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    // CHECK THE HOME PAGE ENTIRE (CHROME BROWSER)
    @Test (testName = "TC900-A_CheckElements_GC", priority = 1)
    public void findElementsChrome() throws MalformedURLException {
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

    // CHECK THE HOME PAGE ENTIRE (FIREFOX BROWSER)
    @Test (testName = "TC900-B_CheckElements_FF", priority = 1)
    public void findElementsFirefox() throws MalformedURLException {
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

    // CHECK THE HOME PAGE'S TITLE (CHROME BROWSER)
    @Test (testName = "TC904-A_Check_Title_GC", priority = 5)
    public void checkTitleChrome() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var title = driver.getTitle();                              // Represent the title of web page;
        Assert.assertEquals(title, "STORE");
    }

    // CHECK THE HOME PAGE'S TITLE (FIREFOX BROWSER)
    @Test (testName = "TC904-B_Check_Title_FF", priority = 5)
    public void checkTitleFirefox() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var title = driver.getTitle();                              // Represent the title of web page;
        Assert.assertEquals(title, "STORE");
    }

    // CHECK THE TEXT IN FOOTER'S SECTION "ABOUT AS" MATCHES PRESET (CHROME BROWSER)
    @Test (testName = "TC902-A_Check_Footer_AboutUs_GC", priority = 4)
    public void checkFooterAboutUsChrome() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        WebElement footerAbout = driver.findElement(By.xpath(FOOTER_ABOUT_LOCATOR));  // Get the section "About us";
        Assert.assertEquals(footerAbout.getText(),
                "About Us\n" +
                        "We believe performance needs to be validated at every stage of the software development " +
                        "cycle and our open source compatible, massively scalable platform makes that a reality.",
                "Text in the footer's section  \"About us\" doesn't match the expected text, which is: "
                        + footerAbout.getText());
    }

    // CHECK THE TEXT IN FOOTER'S SECTION "ABOUT AS" MATCHES PRESET (FIREFOX BROWSER)
    @Test (testName = "TC902-B_Check_Footer_AboutUs_FF", priority = 4)
    public void checkFooterAboutUsFirefox() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        WebElement footerAbout = driver.findElement(By.xpath(FOOTER_ABOUT_LOCATOR));  // Get "About us" section;
        Assert.assertEquals(footerAbout.getText(),
                "About Us\n" +
                        "We believe performance needs to be validated at every stage of the software development " +
                        "cycle and our open source compatible, massively scalable platform makes that a reality.",
                "Text in the footer's section  \"About us\" doesn't match the expected text, which is: "
                        + footerAbout.getText());
    }

    // CHECK THE TEXT IN FOOTER'S SECTION "GET IN TOUCH" MATCHES PRESET (CHROME BROWSER)
    @Test (testName = "TC903-A_Check_Footer_GetInTouch_GC", priority = 3)
    public void checkFooterGetInTouchChrome() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        WebElement footerContacts = driver.findElement(By.xpath(FOOTER_CONTACTS_LOCATOR)); // Get "Get in Touch" section.
        Assert.assertEquals(footerContacts.getText(),
                "Get in Touch\n" +
                        "Address: 2390 El Camino Real\n" +
                        "Phone: +440 123456\n" +
                        "Email: demo@blazemeter.com",
                "Text in the footer's section  \"Get in Touch\" doesn't match the expected text, which is: "
                        + footerContacts.getText());
    }

    // CHECK THE TEXT IN FOOTER'S SECTION "GET IN TOUCH" MATCHES PRESET (FIREFOX BROWSER)
    @Test (testName = "TC903-A_Check_Footer_GetInTouch_GC", priority = 3)
    public void checkFooterGetInTouchFirefox() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();               // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        WebElement footerContacts = driver.findElement(By.xpath(FOOTER_CONTACTS_LOCATOR)); // Get "Get in Touch" section.
        Assert.assertEquals(footerContacts.getText(),
                "Get in Touch\n" +
                        "Address: 2390 El Camino Real\n" +
                        "Phone: +440 123456\n" +
                        "Email: demo@blazemeter.com",
                "Text in the footer's section  \"Get in Touch\" doesn't match the expected text, which is: "
                        + footerContacts.getText());
    }
}
