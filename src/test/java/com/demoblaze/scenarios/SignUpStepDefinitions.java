package com.demoblaze.scenarios;

import com.demoblaze.pageobject.HomePage;
import com.demoblaze.testdata.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.demoblaze.pageobject.SignUpModal.SIGNUP_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_MODAL_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_PASSWORD_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_USERNAME_LOCATOR;
import static com.demoblaze.testdata.TestData.BASE_URL;
import static com.demoblaze.testdata.TestData.GRID_URL;
import static com.demoblaze.testdata.TestData.PASSWORD;

public class SignUpStepDefinitions {
    WebDriver driver;
    WebDriverWait wait;
    WebElement signUpModal;
    public static String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    public static String uniqueUsernameGC = "GC-user-" + timestamp;

    @Given("new user on the home page")
    public void newUserOnTheHomePage() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(GRID_URL), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(BASE_URL);                                        // Open web page by URL;
    }

    @When("new user click nav bar signup button")
    public void newUserClickNavBarSignupButton() {
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
    }

    @Then("sinup modal appeared")
    public void sinupModalAppeared() {
        wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
    }

    @When("new user enter username")
    public void newUserEnterUsername() {
        WebElement usernameInput = signUpModal.findElement(By.xpath(SIGNUP_USERNAME_LOCATOR));  // Get Username input;
        usernameInput.sendKeys(uniqueUsernameGC);                                               // Set new Username;
    }

    @And("new user enter password")
    public void newUserEnterPassword() {
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(PASSWORD);                                                       // Set Password;
    }

    @And("new user click modal signup button")
    public void newUserClickModalSignupButton() {
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
    }

    @Then("alert signup successful appear")
    public void alertSignupSuccessfulAppear() throws InterruptedException {
        TestData.setUniqueUsernameGC(uniqueUsernameGC);
        wait.until(ExpectedConditions.alertIsPresent());                                        // Wait alert appearance;
        try {
            Alert alert = driver.switchTo().alert();            // Move driver focus to the Browser Alert;
            String alertText = alert.getText();                 // Get text from a Browser Alert and put it to the var;
            System.out.println("ALERT TEXT: " + alertText);     // Print out alert's content text;
            Assert.assertTrue(alertText.contains("Sign up successful"),
                    "\"Sign up successful\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameGC);
        System.out.println("  Password: " + PASSWORD);
        System.out.println();
    }
}
