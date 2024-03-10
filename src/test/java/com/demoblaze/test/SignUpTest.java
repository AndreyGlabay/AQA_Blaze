package com.demoblaze.test;

import com.demoblaze.pageobject.HomePage;
import com.demoblaze.pageobject.SignUpModal;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.demoblaze.pageobject.SignUpModal.SIGNUP_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_MODAL_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_PASSWORD_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_USERNAME_LOCATOR;

public class SignUpTest {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://demoblaze.com/";
    public String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    public String uniqueUsername = "user-" + timestamp;
    public String username = "John";
    public String password = "Test-12345";

    // CHECK SUCCESS LOGIN
    @Test (testName = "TC001_Success_SignUp_GC")
    public void successSignUpChrome() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500)); // Apply 0.5s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement usernameInput = signUpModal.findElement(By.xpath(SIGNUP_USERNAME_LOCATOR));  // Get Username input;
        usernameInput.sendKeys(uniqueUsername);                                     // Set Username;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(password);                                                       // Set Password;
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;

        Alert alert = null;                             // Create var for Browser Alert and assign it to null value;
        try {
            alert = driver.switchTo().alert();          // Move driver focus to the Browser Alert;
            String alertText = alert.getText();         // Get text from a Browser Alert and put it to the var;
            if (alertText.contains("Sign up successful.")) {
                System.out.println("Sign up successful.");
            } else if (alertText.contains("This user already exist.")) {
                System.out.println("This user already exist.");
            } else {
                System.out.println("Unexpected data");
            }
            alert.accept();                             // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {           // There is no alert present;
        }

        Thread.sleep(5000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("Username: " + uniqueUsername);
        System.out.println("Password: " + password);
    }


}
