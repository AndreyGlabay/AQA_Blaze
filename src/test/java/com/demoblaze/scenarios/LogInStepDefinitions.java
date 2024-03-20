package com.demoblaze.scenarios;

import com.demoblaze.pageobject.HomePage;
import com.demoblaze.pageobject.HomePageLoggedIn;
import com.demoblaze.pageobject.NavBarLoggedUser;
import com.demoblaze.test.SignUpTest;
import com.demoblaze.testdata.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static com.demoblaze.pageobject.LogInModal.LOGIN_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_MODAL_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_PASSWORD_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_TITLE_LOCATOR;
import static com.demoblaze.pageobject.LogInModal.LOGIN_USERNAME_LOCATOR;
import static com.demoblaze.pageobject.NavBarLoggedUser.WELCOME_BUTTON_LOCATOR;
import static com.demoblaze.testdata.TestData.PASSWORD;
import static com.demoblaze.testdata.TestData.USERNAME;

public class LogInStepDefinitions {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://demoblaze.com/";
    SignUpTest signUp = new SignUpTest();

    @Given("the user on the home page")
    public void theUserOnTheHomePage() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        HomePage homePage = new HomePage(driver);                           // Create an instance of Home page;
    }

    @When("the user click login button")
    public void theUserClickLoginButton() {
        HomePage homePage = new HomePage(driver);                           // Create an instance of Home page;
        WebElement navButtonLogIn = homePage.getNavButtonLogIn();           // Get the Login button from the Home page;
        navButtonLogIn.click();                                             // Click on the Login button.
    }

    @And("enter valid username")
    public void enterValidUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000)); // Initialize WebDriverWait object;
        WebElement logInModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL_LOCATOR)));
        WebElement usernameInput = logInModal.findElement(By.xpath(LOGIN_USERNAME_LOCATOR)); // Find the Username input;
        usernameInput.sendKeys(USERNAME);                                       // Send the username to the input.
    }

    @And("enter valid password")
    public void enterValidPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000)); // Initialize WebDriverWait object;
        WebElement logInModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL_LOCATOR)));
        WebElement passwordInput = logInModal.findElement(By.xpath(LOGIN_PASSWORD_LOCATOR)); // Find the Password input;
        passwordInput.sendKeys(TestData.PASSWORD);                                      // Send the password to the input.
    }

    @And("and click login button")
    public void andClickLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000)); // Initialize WebDriverWait object;
        WebElement logInModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL_LOCATOR)));
        WebElement logInButton = logInModal.findElement(By.xpath(LOGIN_BUTTON_LOCATOR)); // Find the LogIn button;
        logInButton.click();                                                             // Click on the button.
        System.out.println("LOGGED IN WITH CREDENTIALS:");
        System.out.println("  Logged with Username: " + USERNAME);
        System.out.println("  Logged with Password: " + PASSWORD);
        System.out.println();
    }

    @Then("the user is on the logged user home page")
    public void theUserIsOnTheLoggedUserHomePage() {
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
        softAssert.assertAll();
    }

    @Then("the correct username is displayed")
    public void theCorrectUsernameIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000)); // Initialize WebDriverWait object;
        WebElement welcomeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(WELCOME_BUTTON_LOCATOR)));
        String expectedText = USERNAME;
        String actualText = welcomeButton.getText();
        Assert.assertEquals(actualText, "Welcome " + expectedText, "Welcome Button: wrong username");
    }

    @Then("the correct title is displayed")
    public void theCorrectTitleIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Initialize WebDriverWait object;
        WebElement logInModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_MODAL_LOCATOR)));
        WebElement titleLogIn = logInModal.findElement(By.xpath(LOGIN_TITLE_LOCATOR));       // Get the modal's title;
        Assert.assertEquals(titleLogIn.getText(), "Log in",
                "Expect title \"Log in\" but get " + titleLogIn.getText());
    }
}