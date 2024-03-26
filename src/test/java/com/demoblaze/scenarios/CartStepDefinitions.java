package com.demoblaze.scenarios;

import com.demoblaze.pageobject.CartPage;
import com.demoblaze.pageobject.HomePage;
import com.demoblaze.pageobject.HomePageContent1;
import com.demoblaze.pageobject.HomePageLoggedIn;
import com.demoblaze.pageobject.NavBarLoggedUser;
import com.demoblaze.pageobject.ProductPage;
import com.demoblaze.testdata.ProductData;
import com.demoblaze.testdata.TestData;
import com.demoblaze.utilities.ProductPropertiesReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

import static com.demoblaze.pageobject.LogInModal.LOGIN_BUTTON_LOCATOR;
import static com.demoblaze.pageobject.ProductPage.ADD_TO_CART_BUTTON;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_MODAL_LOCATOR;
import static com.demoblaze.pageobject.SignUpModal.SIGNUP_USERNAME_LOCATOR;
import static com.demoblaze.testdata.TestData.BASE_URL;
import static com.demoblaze.testdata.TestData.CART_URL;
import static com.demoblaze.testdata.TestData.GRID_URL;
import static com.demoblaze.testdata.TestData.PASSWORD;
import static com.demoblaze.testdata.TestData.PRODUCT4_URL;

public class CartStepDefinitions {
    WebDriver driver;
    WebDriverWait wait;

    private ProductPage productPage;
    private Map<String, ProductData> productDataMap;

    // BACKGROUND
    @Given("user on the home page")
    public void userOnTheHomePage() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(GRID_URL), options);   // Driver initialization on SeleniumGrid using GC;
        driver.get(BASE_URL);                                       // Open web page by URL;
    }

    @Then("product properties loaded")
    public void productPropertiesLoaded() throws IOException {
        productDataMap = ProductPropertiesReader.readProductProperties();
    }

    // SCENARIOS
    @When("user navigate to the {string} product page")
    public void userNavigateToTheProductPage(String productId) {
        var homePageContent1 = new HomePageContent1(driver);
        WebElement productLink = null;
        String productUrl = null;
        switch (productId) {
            case "idp_1":
                productLink = homePageContent1.getLinkProductId1();
                productUrl = TestData.PRODUCT1_URL;
                break;
            case "idp_2":
                productLink = homePageContent1.getLinkProductId2();
                productUrl = TestData.PRODUCT2_URL;
                break;
            case "idp_3":
                productLink = homePageContent1.getLinkProductId3();
                productUrl = TestData.PRODUCT3_URL;
                break;
            case "idp_4":
                productLink = homePageContent1.getLinkProductId4();
                productUrl = PRODUCT4_URL;
                break;
            case "idp_5":
                productLink = homePageContent1.getLinkProductId5();
                productUrl = TestData.PRODUCT5_URL;
                break;
            case "idp_6":
                productLink = homePageContent1.getLinkProductId6();
                productUrl = TestData.PRODUCT6_URL;
                break;
            case "idp_7":
                productLink = homePageContent1.getLinkProductId7();
                productUrl = TestData.PRODUCT7_URL;
                break;
            case "idp_8":
                productLink = homePageContent1.getLinkProductId8();
                productUrl = TestData.PRODUCT8_URL;
                break;
            case "idp_9":
                productLink = homePageContent1.getLinkProductId9();
                productUrl = TestData.PRODUCT9_URL;
                break;
            default:
                throw new IllegalArgumentException("Invalid productId: " + productId);
        }
        if (productLink != null) {
            wait = new WebDriverWait(driver, Duration.ofMillis(1000));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(productLink));
            clickableElement.click();
            wait.until(ExpectedConditions.urlContains(productUrl)); // Wait until redirected URL appear;
            System.out.println("CHECKPOINT");
        } else {
            throw new IllegalArgumentException("Invalid productId: " + productId); // Handle invalid productId
        }
    }

    @And("user see the {string} product details")
    public void userSeeTheProductDetails(String productId) {
        ProductData expectedProductData = productDataMap.get(productId);
        productPage = new ProductPage(driver);

        System.out.println("Before retrieving actual product details");

        String actualTitle = productPage.getProductTitle().getText();
        String actualPrice = productPage.getProductPrice().getText();
        String actualDescription = productPage.getProductDescription().getText();

        System.out.println("After retrieving actual product details");

        SoftAssert softAssert = new SoftAssert();
        // Check sections TITLE, PRICE, DESCRIPTION and ADD TO CART button are displaying;
        softAssert.assertTrue(productPage.isProductTitleDisplayed(), "TITLE section is missing");
        softAssert.assertTrue(productPage.isProductPriceDisplayed(), "PRICE section is missing");
        softAssert.assertTrue(productPage.isProductDescriptionDisplayed(), "DESCRIPTION section is missing");
        softAssert.assertTrue(productPage.isAddToCartButtonDisplayed(), "ADD TO CART button is missing");
        // Check TITLE and PRI
        softAssert.assertEquals(actualTitle, expectedProductData.getTitle(), "TITLE is not as expected");
        softAssert.assertEquals(actualPrice, expectedProductData.getPrice(), "PRICE is not as expected");
        softAssert.assertEquals(actualDescription, expectedProductData.getDescription(),
                "DESCRIPTION is not as expected");

        System.out.println("After performing soft assertions");

        softAssert.assertAll();
    }

    @And("user add the product to the cart")
    public void userAddTheProductToTheCart() {
        WebElement addToCartButton = productPage.getAddToCartButton();      // Get the Add to Cart button WebElement;
        addToCartButton.click();                                            // Clicking on the Add to Cart button.
    }

    @Then("alert product added appear")
    public void alertProductAddedAppear() {
        wait.until(ExpectedConditions.alertIsPresent());        // Wait alert appearance;
        try {
            Alert alert = driver.switchTo().alert();            // Move driver focus to the Browser Alert;
            String alertText = alert.getText();                 // Get text from a Browser Alert and put it to the var;
            System.out.println("ALERT TEXT: " + alertText);     // Print out alert's content text;
            Assert.assertTrue(alertText.contains("Product added"),
                    "\"Product added\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
    }

    @And("the {string} product been added to the cart")
    public void theProductBeenAddedToTheCart(String productId) throws InterruptedException {
        driver.get(CART_URL);
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CartPage.ROW_1_TITLE)));
        CartPage cartPage = new CartPage(driver);

        // Verify that the product has been added to the cart
        boolean isProductAdded = cartPage.isProductAddedToCart("Product Title", "$Product Price");
        Assert.assertTrue(isProductAdded, "Product is not added to the cart");

        // Perform further actions as needed, such as getting the total value or placing the order
        String totalValue = cartPage.getTotalValue();
        System.out.println("Total value in cart: " + totalValue);
        cartPage.placeOrder();

        Thread.sleep(1000);
        driver.quit();
    }

    @When("user have {string} in the cart")
    public void userHaveInTheCart(String arg0) {
    }

    @And("user proceed to checkout")
    public void userProceedToCheckout() {
    }

    @Then("user should be redirected to the checkout page")
    public void userShouldBeRedirectedToTheCheckoutPage() {
    }

    @When("user in checkout modal")
    public void userInCheckoutModal() {
    }

    @And("user discard checkout")
    public void userDiscardCheckout() {
    }

    @Then("user should be redirected to the cart page")
    public void userShouldBeRedirectedToTheCartPage() {
    }

    @And("user remove the {string} from the cart")
    public void userRemoveTheFromTheCart(String arg0) {
    }

    @Then("the {string} should be removed from the cart")
    public void theShouldBeRemovedFromTheCart(String arg0) {
    }

    @And("user back to the home page")
    public void userBackToTheHomePage() {
    }

    @Then("the all products should be added to the cart")
    public void theAllProductsShouldBeAddedToTheCart() {
    }

    @When("user have all products in the cart")
    public void userHaveAllProductsInTheCart() {
    }

    @When("user fill in checkout form")
    public void userFillInCheckoutForm() {
    }

    @And("user submit checkout form")
    public void userSubmitCheckoutForm() {
    }

    @Then("checkout should be completed")
    public void checkoutShouldBeCompleted() {
    }



}
