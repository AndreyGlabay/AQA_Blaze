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

import static com.demoblaze.pageobject.SignUpModal.SIGNUP_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_MODAL_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_PASSWORD_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_USERNAME_LOCATOR;

public class SignUpTest {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://demoblaze.com/";
    public String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    public String uniqueUsernameGC = "GC-user-" + timestamp;
    public String uniqueUsernameFF = "FF-user-" + timestamp;
    public String password = "Test-12345";

    // CHECK SUCCESSFUL SIGNUP (CHROME BROWSER)
    @Test (testName = "TC001_Success_SignUp_GC")
    public void successSignUpChrome() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement usernameInput = signUpModal.findElement(By.xpath(SIGNUP_USERNAME_LOCATOR));  // Get Username input;
        usernameInput.sendKeys(uniqueUsernameGC);                                     // Set new Username;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(password);                                                       // Set Password;
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
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
        Thread.sleep(3000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameGC);
        System.out.println("  Password: " + password);
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME ALREADY REGISTERED (CHROME BROWSER)
    @Test (testName = "TC002_User_Already_Exists_SignUp_GC")
    public void userAlreadyExistsSignUpChrome() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement usernameInput = signUpModal.findElement(By.xpath(SIGNUP_USERNAME_LOCATOR));  // Get Username input;
        usernameInput.sendKeys(uniqueUsernameGC);                                                 // Set Username in use ;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(password);                                                       // Set Password;
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
        wait.until(ExpectedConditions.alertIsPresent());                                        // Wait alert appearance;
        try {
            Alert alert = driver.switchTo().alert();            // Move driver focus to the Browser Alert;
            String alertText = alert.getText();                 // Get text from a Browser Alert and put it to the var;
            System.out.println("ALERT TEXT: " + alertText);     // Print out alert's content text;
            Assert.assertTrue(alertText.contains("user already exist"),
                    "\"User already exist\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
        Thread.sleep(3000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameGC);
        System.out.println("  Password: " + password);
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME INPUT LEFT EMPTY (CHROME BROWSER)
    @Test (testName = "TC003_Missing_Username_SignUp_GC")
    public void missingUsernameSignUpChrome() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        // Skip Username entering
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(password + "1");                                     // Set Password;
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
        wait.until(ExpectedConditions.alertIsPresent());                                        // Wait alert appearance;
        try {
            Alert alert = driver.switchTo().alert();            // Move driver focus to the Browser Alert;
            String alertText = alert.getText();                 // Get text from a Browser Alert and put it to the var;
            System.out.println("ALERT TEXT: " + alertText);     // Print out alert's content text;
            Assert.assertTrue(alertText.contains("fill out Username and Password"),
                    "\"Username/Password missing\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
        Thread.sleep(3000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: (no data)");
        System.out.println("  Password: " + password + "1");
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - PASSWORD INPUT LEFT EMPTY (CHROME BROWSER)
    @Test (testName = "TC004_Missing_Password_SignUp_GC")
    public void missingPasswordSignUpChrome() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement usernameInput = signUpModal.findElement(By.xpath(SIGNUP_USERNAME_LOCATOR));  // Get Username input;
        usernameInput.sendKeys(uniqueUsernameGC + "1");                               // Set Username;
        // Skip Password entering
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
        wait.until(ExpectedConditions.alertIsPresent());                                        // Wait alert appearance;
        try {
            Alert alert = driver.switchTo().alert();            // Move driver focus to the Browser Alert;
            String alertText = alert.getText();                 // Get text from a Browser Alert and put it to the var;
            System.out.println("ALERT TEXT: " + alertText);     // Print out alert's content text;
            Assert.assertTrue(alertText.contains("fill out Username and Password"),
                    "\"Username/Password missing\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
        Thread.sleep(3000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameGC + "1");
        System.out.println("  Password: (no data)");
        System.out.println();
    }

    // CHECK SUCCESSFUL SIGNUP (FIREFOX BROWSER)
    @Test (testName = "TC001a_Success_SignUp_FF")
    public void successSignUpFirefox() throws MalformedURLException, InterruptedException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement usernameInput = signUpModal.findElement(By.xpath(SIGNUP_USERNAME_LOCATOR));  // Get Username input;
        usernameInput.sendKeys(uniqueUsernameFF);                                               // Set new Username;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(password);                                                       // Set Password;
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
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
        Thread.sleep(3000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameFF);
        System.out.println("  Password: " + password);
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME ALREADY REGISTERED (FIREFOX BROWSER)
    @Test (testName = "TC002a_User_Already_Exists_SignUp_FF")
    public void userAlreadyExistsSignUpFirefox() throws MalformedURLException, InterruptedException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement usernameInput = signUpModal.findElement(By.xpath(SIGNUP_USERNAME_LOCATOR));  // Get Username input;
        usernameInput.sendKeys(uniqueUsernameFF);                                                 // Set Username in use ;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(password);                                                       // Set Password;
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
        wait.until(ExpectedConditions.alertIsPresent());                                        // Wait alert appearance;
        try {
            Alert alert = driver.switchTo().alert();            // Move driver focus to the Browser Alert;
            String alertText = alert.getText();                 // Get text from a Browser Alert and put it to the var;
            System.out.println("ALERT TEXT: " + alertText);     // Print out alert's content text;
            Assert.assertTrue(alertText.contains("user already exist"),
                    "\"User already exist\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
        Thread.sleep(3000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameFF);
        System.out.println("  Password: " + password);
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME INPUT LEFT EMPTY (FIREFOX BROWSER)
    @Test (testName = "TC003a_Missing_Username_SignUp_FF")
    public void missingUsernameSignUpFirefox() throws MalformedURLException, InterruptedException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        // Skip Username entering
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(password + "1");                                     // Set Password;
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
        wait.until(ExpectedConditions.alertIsPresent());                                        // Wait alert appearance;
        try {
            Alert alert = driver.switchTo().alert();            // Move driver focus to the Browser Alert;
            String alertText = alert.getText();                 // Get text from a Browser Alert and put it to the var;
            System.out.println("ALERT TEXT: " + alertText);     // Print out alert's content text;
            Assert.assertTrue(alertText.contains("fill out Username and Password"),
                    "\"Username/Password missing\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
        Thread.sleep(3000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: (no data)");
        System.out.println("  Password: " + password + "1");
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - PASSWORD INPUT LEFT EMPTY (FIREFOX BROWSER)
    @Test (testName = "TC004a_Missing_Password_SignUp_FF")
    public void missingPasswordSignUpFirefox() throws MalformedURLException, InterruptedException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement usernameInput = signUpModal.findElement(By.xpath(SIGNUP_USERNAME_LOCATOR));  // Get Username input;
        usernameInput.sendKeys(uniqueUsernameFF + "1");                             // Set Username;
        // Skip Password entering
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
        wait.until(ExpectedConditions.alertIsPresent());                                        // Wait alert appearance;
        try {
            Alert alert = driver.switchTo().alert();            // Move driver focus to the Browser Alert;
            String alertText = alert.getText();                 // Get text from a Browser Alert and put it to the var;
            System.out.println("ALERT TEXT: " + alertText);     // Print out alert's content text;
            Assert.assertTrue(alertText.contains("fill out Username and Password"),
                    "\"Username/Password missing\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
        Thread.sleep(3000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameFF + "1");
        System.out.println("  Password: (no data)");
        System.out.println();
    }
}
