package com.demoblaze.test;

import com.demoblaze.pageobject.HomePage;
import com.demoblaze.pageobject.HomePageLoggedIn;
import com.demoblaze.pageobject.NavBarLoggedUser;
import com.demoblaze.testdata.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static com.demoblaze.pageobject.LogInModal.LOGIN_MODAL_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_TITLE_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_X_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_USERNAME_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_PASSWORD_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_CLOSE_LOCATOR;
import static com.demoblaze.pageobject.NavBarLoggedUser.LOGOUT_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.NavBarLoggedUser.NAVBAR_LOGGED_USER_LOCATOR;
import static com.demoblaze.pageobject.NavBarLoggedUser.WELCOME_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_MODAL_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_PASSWORD_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_USERNAME_LOCATOR;

import static com.demoblaze.testdata.TestData.PASSWORD;

public class LogInTest {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://demoblaze.com/";
    SignUpTest signUp = new SignUpTest();

    @BeforeClass
    public void signUpInstance() throws MalformedURLException, InterruptedException {
        signUp.successSignUpChrome();                               // Call the signup method before each test method

    }

    @BeforeMethod
    public void logInInstance() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
    }

    @AfterMethod
    public void stopInstance() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test(testName = "TC101_Success_LogIn_Check_Elements", priority = 1)
    public void successLogInCheckElements() {
        HomePage homePage = new HomePage(driver);                           // Create an instance of Home page;
        WebElement navButtonLogIn = homePage.getNavButtonLogIn();           // Get the Login button from the Home page;
        navButtonLogIn.click();                                             // Click on the Login button.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000)); // Initialize WebDriverWait object;
        WebElement logInModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL_LOCATOR)));

        WebElement usernameInput = logInModal.findElement(By.xpath(LOGIN_USERNAME_LOCATOR)); // Find the Username input;
        String uniqueUsernameGC = TestData.getUniqueUsernameGC();                       // Get Username from Test Data;
        usernameInput.sendKeys(uniqueUsernameGC);                                       // Send the username to the input.
        WebElement passwordInput = logInModal.findElement(By.xpath(LOGIN_PASSWORD_LOCATOR)); // Find the Password input;
        passwordInput.sendKeys(TestData.PASSWORD);                                      // Send the password to the input.
        WebElement logInButton = logInModal.findElement(By.xpath(LOGIN_BUTTON_LOCATOR)); // Find the LogIn button;
        logInButton.click();                                                             // Click on the button.

        System.out.println("LOGGED IN WITH CREDENTIALS:");
        System.out.println("  Logged with Username: " + TestData.getUniqueUsernameGC());
        System.out.println("  Logged with Password: " + PASSWORD);
        System.out.println();

        HomePageLoggedIn homePageLoggedIn = new HomePageLoggedIn(driver); // Initialize the object for logged-in home page;
        NavBarLoggedUser navBarLoggedUser = homePageLoggedIn.getNavBarLoggedUser(driver); // Get NavBar from the page.

        SoftAssert softAssert = new SoftAssert(); // Add Soft Assertion (too many assertions);
        // Check the presence of the Nav Bar and its elements:
        softAssert.assertTrue(navBarLoggedUser.isNavBarLoggedUserDisplayed(), "Nav Bar is missing");
        softAssert.assertTrue(navBarLoggedUser.isNavButtonHomeDisplayed(), "Nav Button Home is missing");
        softAssert.assertTrue(navBarLoggedUser.isNavButtonContactDisplayed(), "Nav Button Contact is missing");
        softAssert.assertTrue(navBarLoggedUser.isNavButtonAboutUsDisplayed(), "Nav Button About Us is missing");
        softAssert.assertTrue(navBarLoggedUser.isNavButtonCartDisplayed(), "Nav Button Cart is missing");
        softAssert.assertTrue(navBarLoggedUser.isNavButtonLogOutDisplayed(), "Nav Button Log Out is missing");
        softAssert.assertTrue(navBarLoggedUser.isNavButtonWelcomeDisplayed(), "Nav Button Welcome is missing");
        // Check the presence of the rest of Home Page elements:
        softAssert.assertTrue(homePageLoggedIn.isHeaderBrandDisplayed(), "Carousel is missing");
        softAssert.assertTrue(homePageLoggedIn.isHeaderLogoDisplayed(), "Carousel is missing");
        softAssert.assertTrue(homePageLoggedIn.isCarouselDisplayed(), "Carousel is missing");
        softAssert.assertTrue(homePageLoggedIn.isCategoryMenuAreaDisplayed(), "Category Menu Area is missing");
        softAssert.assertTrue(homePageLoggedIn.isCategoryMenuTitleDisplayed(), "Category Title is missing");
        softAssert.assertTrue(homePageLoggedIn.isCategoryMenuPhonesDisplayed(), "Category Phones is missing");
        softAssert.assertTrue(homePageLoggedIn.isCategoryMenuLaptopsDisplayed(), "Category Laptops is missing");
        softAssert.assertTrue(homePageLoggedIn.isCategoryMenuMonitorsDisplayed(), "Category Monitors is missing");
        softAssert.assertTrue(homePageLoggedIn.isContentAreaDisplayed(), "Content Area is missing");
        softAssert.assertTrue(homePageLoggedIn.isPreviousButtonDisplayed(), "Previous Button is missing");
        softAssert.assertTrue(homePageLoggedIn.isNextButtonDisplayed(), "Next Button is missing");
        softAssert.assertTrue(homePageLoggedIn.isFooterAboutDisplayed(), "Footer About is missing");
        softAssert.assertTrue(homePageLoggedIn.isFooterContactsDisplayed(), "Footer Contacts is missing");
        softAssert.assertTrue(homePageLoggedIn.isFooterLogoDisplayed(), "Footer Logo is missing");
        softAssert.assertTrue(homePageLoggedIn.isFooterCopyrightDisplayed(), "Footer Copyright is missing");
        softAssert.assertAll();
    }


    @Test(testName = "TC102_Success_LogIn_Check_Username", priority = 2)
    public void successLogInCheckUsername() {
        HomePage homePage = new HomePage(driver);                           // Create an instance of Home page;
        WebElement navButtonLogIn = homePage.getNavButtonLogIn();           // Get the Login button from the Home page;
        navButtonLogIn.click();                                             // Click on the Login button.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000)); // Initialize WebDriverWait object;
        WebElement logInModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL_LOCATOR)));

        WebElement usernameInput = logInModal.findElement(By.xpath(LOGIN_USERNAME_LOCATOR)); // Find the Username input;
        String uniqueUsernameGC = TestData.getUniqueUsernameGC();                       // Get Username from Test Data;
        usernameInput.sendKeys(uniqueUsernameGC);                                       // Send the username to the input.
        WebElement passwordInput = logInModal.findElement(By.xpath(LOGIN_PASSWORD_LOCATOR)); // Find the Password input;
        passwordInput.sendKeys(TestData.PASSWORD);                                      // Send the password to the input.
        WebElement logInButton = logInModal.findElement(By.xpath(LOGIN_BUTTON_LOCATOR)); // Find the LogIn button;
        logInButton.click();                                                             // Click on the button.

        System.out.println("LOGGED IN WITH CREDENTIALS:");
        System.out.println("  Logged with Username: " + TestData.getUniqueUsernameGC());
        System.out.println("  Logged with Password: " + PASSWORD);
        System.out.println();

        // Check the text in the web element Welcome Button is matched with the pattern "Welcome (username)"
        WebElement welcomeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WELCOME_BUTTON_LOCATOR)));
        String expectedText = TestData.getUniqueUsernameGC();
        String actualText = welcomeButton.getText();
        Assert.assertEquals(actualText, "Welcome " + expectedText, "Welcome Button: wrong username");
    }

    @Test(testName = "TC103_Success_LogOut_Check_Elements", priority = 2)
    public void successLogOutCheckElements() {
        HomePage homePage = new HomePage(driver);                           // Create an instance of Home page;
        WebElement navButtonLogIn = homePage.getNavButtonLogIn();           // Get the Login button from the Home page;
        navButtonLogIn.click();                                             // Click on the Login button.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000)); // Initialize WebDriverWait object;
        WebElement logInModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL_LOCATOR)));

        WebElement usernameInput = logInModal.findElement(By.xpath(LOGIN_USERNAME_LOCATOR)); // Find the Username input;
        String uniqueUsernameGC = TestData.getUniqueUsernameGC();                       // Get Username from Test Data;
        usernameInput.sendKeys(uniqueUsernameGC);                                       // Send the username to the input.
        WebElement passwordInput = logInModal.findElement(By.xpath(LOGIN_PASSWORD_LOCATOR)); // Find the Password input;
        passwordInput.sendKeys(TestData.PASSWORD);                                      // Send the password to the input.
        WebElement logInButton = logInModal.findElement(By.xpath(LOGIN_BUTTON_LOCATOR)); // Find the LogIn button;
        logInButton.click();                                                             // Click on the button.

        System.out.println("LOGGED IN WITH CREDENTIALS:");
        System.out.println("  Logged with Username: " + TestData.getUniqueUsernameGC());
        System.out.println("  Logged with Password: " + PASSWORD);
        System.out.println();

        WebElement logOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_BUTTON_LOCATOR)));
        logOutButton.click();

        HomePage homePageAfterLogout = new HomePage(driver); // New instance of the Home Page after logs out;

        SoftAssert softAssert = new SoftAssert();                   // Add Soft Assertion (too many assertions);
        softAssert.assertTrue(homePageAfterLogout.isHeaderBrandDisplayed(), "Header Brand is missing");
        softAssert.assertTrue(homePageAfterLogout.isHeaderLogoDisplayed(), "Header Logo is missing");
        softAssert.assertTrue(homePageAfterLogout.isNavBarDisplayed(), "Nav Bar is missing");
        softAssert.assertTrue(homePageAfterLogout.isNavButtonHomeDisplayed(), "Nav Button Home is missing");
        softAssert.assertTrue(homePageAfterLogout.isNavButtonContactDisplayed(), "Nav Button Contact is missing");
        softAssert.assertTrue(homePageAfterLogout.isNavButtonAboutUsDisplayed(), "Nav Button About Us is missing");
        softAssert.assertTrue(homePageAfterLogout.isNavButtonCartDisplayed(), "Nav Button Cart is missing");
        softAssert.assertTrue(homePageAfterLogout.isNavButtonLogInDisplayed(), "Nav Button Log In is missing");
        softAssert.assertTrue(homePageAfterLogout.isNavButtonSignUpDisplayed(), "Nav Button Sign Up is missing");
        softAssert.assertAll();
    }


}
