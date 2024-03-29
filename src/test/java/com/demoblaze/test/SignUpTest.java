package com.demoblaze.test;

import com.demoblaze.pageobject.HomePage;
import com.demoblaze.pageobject.SignUpModal;
import com.demoblaze.testdata.TestData;
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
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_TITLE_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_X_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_CLOSE_LOCATOR;

import static com.demoblaze.testdata.TestData.PASSWORD;

public class SignUpTest {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://demoblaze.com/";
    public static String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    public static String uniqueUsernameGC = "GC-user-" + timestamp;
    public String uniqueUsernameFF = "FF-user-" + timestamp;

    public static String getUniqueUsernameGC() {
        return uniqueUsernameGC;
    }

    // CHECK SUCCESSFUL SIGNUP (CHROME BROWSER)
    @Test (testName = "TC001_Success_SignUp_GC", groups = {"groupGC"}, priority = 1)
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
        usernameInput.sendKeys(uniqueUsernameGC);                                               // Set new Username;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(PASSWORD);                                                       // Set Password;
        WebElement signUpButton = signUpModal.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));     // Get SignUp button;
        signUpButton.click();                                                                   // Click on the button;
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

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME ALREADY REGISTERED (CHROME BROWSER)
    @Test (testName = "TC002_User_Already_Exists_SignUp_GC", groups = {"groupGC"}, priority = 2)
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
        usernameInput.sendKeys(uniqueUsernameGC);                                               // Set Username in use ;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(PASSWORD);                                                       // Set Password;
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameGC);
        System.out.println("  Password: " + PASSWORD);
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME INPUT LEFT EMPTY (CHROME BROWSER)
    @Test (testName = "TC003_Missing_Username_SignUp_GC", groups = {"groupGC"}, priority = 3)
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
        passwordInput.sendKeys(PASSWORD + "1");                                     // Set Password;
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: (no data)");
        System.out.println("  Password: " + PASSWORD + "1");
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - PASSWORD INPUT LEFT EMPTY (CHROME BROWSER)
    @Test (testName = "TC004_Missing_Password_SignUp_GC", groups = {"groupGC"}, priority = 3)
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
        usernameInput.sendKeys(uniqueUsernameGC + "1");                             // Set Username;
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameGC + "1");
        System.out.println("  Password: (no data)");
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME AND PASSWORD INPUTS LEFT EMPTY (CHROME BROWSER)
    @Test (testName = "TC005_Missing_Password_SignUp_GC", groups = {"groupGC"}, priority = 3)
    public void missingUsernameAndPasswordSignUpChrome() throws MalformedURLException, InterruptedException {
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameGC + "1");
        System.out.println("  Password: (no data)");
        System.out.println();
    }

    // CHECK SIGNUP DISCARD AFTER INPUTS FILLING - BY "CLOSE" BUTTON (CHROME BROWSER)
    @Test (testName = "TC006_Discard_by_Close_SignUp_GC", groups = {"groupGC"}, priority = 4)
    public void discardByCloseButtonSignUpChrome() throws MalformedURLException, InterruptedException {
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
        usernameInput.sendKeys(uniqueUsernameGC);                                   // Set Username;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(PASSWORD);                                           // Set Password;
        WebElement closeButton = signUpModal.findElement(By.xpath(SIGNUP_CLOSE_LOCATOR));       // Get Close button;
        closeButton.click();                                                                    // Click on the button;
        Thread.sleep(500);
        Assert.assertFalse(signUpModal.isDisplayed(), "Sign Up modal still displayed after \"Close\" click");
        Thread.sleep(1000);
        driver.quit();
    }

    // CHECK SIGNUP DISCARD BEFORE INPUTS FILLING - BY "X" BUTTON (CHROME BROWSER)
    @Test (testName = "TC007_Discard_by_X_SignUp_GC", groups = {"groupGC"}, priority = 4)
    public void discardByXButtonSignUpChrome() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement xButton = signUpModal.findElement(By.xpath(SIGNUP_X_LOCATOR));           // Get X button;
        xButton.click();                                                                    // Click on the button;
        Thread.sleep(500);
        Assert.assertFalse(signUpModal.isDisplayed(), "Sign Up modal still displayed after \"X\" click");
        Thread.sleep(1000);
        driver.quit();
    }

    // CHECK SIGNUP TITLE (CHROME BROWSER)
    @Test (testName = "TC008_Title_SignUp_GC", groups = {"groupGC"}, priority = 5)
    public void titleSignUpChrome() throws MalformedURLException, InterruptedException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement titleSignUp = signUpModal.findElement(By.xpath(SIGNUP_TITLE_LOCATOR));       // Get the modal's title;
        Assert.assertEquals(titleSignUp.getText(), "Sign up",
                "Expect title \"Sign up\" but get " + titleSignUp.getText());
        Thread.sleep(1000);
        driver.quit();
    }

    // CHECK SUCCESSFUL SIGNUP (FIREFOX BROWSER)
    @Test (testName = "TC001a_Success_SignUp_FF", groups = {"groupFF"}, priority = 1)
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
        passwordInput.sendKeys(PASSWORD);                                                       // Set Password;
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameFF);
        System.out.println("  Password: " + PASSWORD);
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME ALREADY REGISTERED (FIREFOX BROWSER)
    @Test (testName = "TC002a_User_Already_Exists_SignUp_FF", groups = {"groupFF"}, priority = 2)
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
        usernameInput.sendKeys(uniqueUsernameFF);                                               // Set Username in use ;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(PASSWORD);                                                       // Set Password;
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameFF);
        System.out.println("  Password: " + PASSWORD);
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME INPUT LEFT EMPTY (FIREFOX BROWSER)
    @Test (testName = "TC003a_Missing_Username_SignUp_FF", groups = {"groupFF"}, priority = 3)
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
        passwordInput.sendKeys(PASSWORD + "1");                                     // Set Password;
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: (no data)");
        System.out.println("  Password: " + PASSWORD + "1");
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - PASSWORD INPUT LEFT EMPTY (FIREFOX BROWSER)
    @Test (testName = "TC004a_Missing_Password_SignUp_FF", groups = {"groupFF"}, priority = 3)
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameFF + "1");
        System.out.println("  Password: (no data)");
        System.out.println();
    }

    // CHECK UNSUCCESSFUL SIGNUP - USERNAME AND PASSWORD INPUTS LEFT EMPTY (CHROME BROWSER)
    @Test (testName = "TC005a_Missing_Password_SignUp_GC", groups = {"groupFF"}, priority = 3)
    public void missingUsernameAndPasswordSignUpFirefox() throws MalformedURLException, InterruptedException {
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
        Thread.sleep(1000);
        driver.quit();
        System.out.println("TEST USER DATA:");
        System.out.println("  Username: " + uniqueUsernameGC + "1");
        System.out.println("  Password: (no data)");
        System.out.println();
    }


    // CHECK SIGNUP DISCARD AFTER INPUTS FILLING - BY "CLOSE" BUTTON (FIREFOX BROWSER)
    @Test (testName = "TC006a_Discard_by_Close_SignUp_FF", groups = {"groupFF"}, priority = 4)
    public void discardByCloseButtonSignUpFirefox() throws MalformedURLException, InterruptedException {
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
        usernameInput.sendKeys(uniqueUsernameGC);                                               // Set Username;
        WebElement passwordInput = signUpModal.findElement(By.xpath(SIGNUP_PASSWORD_LOCATOR));  // Get Password input;
        passwordInput.sendKeys(PASSWORD);                                                       // Set Password;
        WebElement closeButton = signUpModal.findElement(By.xpath(SIGNUP_CLOSE_LOCATOR));       // Get Close button;
        closeButton.click();                                                                    // Click on the button;
        Thread.sleep(500);
        Assert.assertFalse(signUpModal.isDisplayed(), "Sign Up modal still displayed after \"Close\" click");
        Thread.sleep(1000);
        driver.quit();
    }

    // CHECK SIGNUP DISCARD BEFORE INPUTS FILLING - BY "X" BUTTON (FIREFOX BROWSER)
    @Test (testName = "TC007a_Discard_by_X_SignUp_FF", groups = {"groupFF"}, priority = 4)
    public void discardByXButtonSignUpFirefox() throws MalformedURLException, InterruptedException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement xButton = signUpModal.findElement(By.xpath(SIGNUP_X_LOCATOR));           // Get X button;
        xButton.click();                                                                    // Click on the button;
        Thread.sleep(500);
        Assert.assertFalse(signUpModal.isDisplayed(), "Sign Up modal still displayed after \"X\" click");
        Thread.sleep(1000);
        driver.quit();
    }

    // CHECK SIGNUP TITLE (FIREFOX BROWSER)
    @Test (testName = "TC008a_Title_SignUp_FF", groups = {"groupFF"}, priority = 5)
    public void titleSignUpFirefox() throws MalformedURLException, InterruptedException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var homePage = new HomePage(driver);                        // Create instance of the Home Page;
        WebElement navButtonSignUp = homePage.getNavButtonSignUp(); // Get element Sign Up button;
        Actions actions = new Actions(driver);                      // Create instance of the Actions class;
        actions.moveToElement(navButtonSignUp).click().perform();   // Perform mouse click on the SignUp button;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000)); // Apply 1s delay;
        WebElement signUpModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SIGNUP_MODAL_LOCATOR)));
        WebElement titleSignUp = signUpModal.findElement(By.xpath(SIGNUP_TITLE_LOCATOR));       // Get the modal's title;
        Assert.assertEquals(titleSignUp.getText(), "Sign up",
                "Expect title \"Sign up\" but get " + titleSignUp.getText());
        Thread.sleep(1000);
        driver.quit();
    }
}
