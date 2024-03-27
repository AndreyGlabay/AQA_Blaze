package com.demoblaze.scenarios;

import com.demoblaze.pageobject.CartPage;
import com.demoblaze.pageobject.HomePageContent1;
import com.demoblaze.pageobject.OrderCompleteModal;
import com.demoblaze.pageobject.PlaceOrderModal;
import com.demoblaze.pageobject.ProductPage;
import com.demoblaze.testdata.PlaceOrderFormData;
import com.demoblaze.testdata.ProductData;
import com.demoblaze.testdata.TestData;
import com.demoblaze.utilities.PlaceOrderPropertiesReader;
import com.demoblaze.utilities.ProductPropertiesReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import static com.demoblaze.testdata.TestData.BASE_URL;
import static com.demoblaze.testdata.TestData.CART_URL;
import static com.demoblaze.testdata.TestData.GRID_URL;
import static com.demoblaze.testdata.TestData.PRODUCT4_URL;

public class CartStepDefinitions {
    private static CartStepDefinitions instance;
    WebDriver driver;
    WebDriverWait wait;
    private ProductPage productPage;
    private Map<String, ProductData> productDataMap;

    @Before
    public void setUp() throws IOException {
        productDataMap = ProductPropertiesReader.readProductProperties();
    }

    @After
    public void scenarioFinish() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    // BACKGROUND
    @Given("user on the home page")
    public void userOnTheHomePage() throws IOException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(GRID_URL), options);   // Driver initialization on SeleniumGrid using GC;
        driver.get(BASE_URL);                                       // Open web page by URL;
    }

    // SCENARIOS
    @When("user navigate to the {string} product page")
    public void userNavigateToTheProductPage(String productId) {
        var homePageContent1 = new HomePageContent1(driver);
        WebElement productLink = null;
        String productUrl = null;
        switch (productId) {
            case "PRODUCT_1":
                productLink = homePageContent1.getLinkProductId1();
                productUrl = TestData.PRODUCT1_URL;
                break;
            case "PRODUCT_2":
                productLink = homePageContent1.getLinkProductId2();
                productUrl = TestData.PRODUCT2_URL;
                break;
            case "PRODUCT_3":
                productLink = homePageContent1.getLinkProductId3();
                productUrl = TestData.PRODUCT3_URL;
                break;
            case "PRODUCT_4":
                productLink = homePageContent1.getLinkProductId4();
                productUrl = PRODUCT4_URL;
                break;
            case "PRODUCT_5":
                productLink = homePageContent1.getLinkProductId5();
                productUrl = TestData.PRODUCT5_URL;
                break;
            case "PRODUCT_6":
                productLink = homePageContent1.getLinkProductId6();
                productUrl = TestData.PRODUCT6_URL;
                break;
            case "PRODUCT_7":
                productLink = homePageContent1.getLinkProductId7();
                productUrl = TestData.PRODUCT7_URL;
                break;
            case "PRODUCT_8":
                productLink = homePageContent1.getLinkProductId8();
                productUrl = TestData.PRODUCT8_URL;
                break;
            case "PRODUCT_9":
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
        } else {
            throw new IllegalArgumentException("Invalid productId: " + productId);
        }
    }

    @And("user see the {string} product details")
    public void userSeeTheProductDetails(String productId) {
        ProductData expectedProductData = productDataMap.get(productId);            // Get expected data from the Map;
        productPage = new ProductPage(driver);                                      // New instance of Product page;

        String actualTitle = productPage.getProductTitle().getText();               // Get actual product's TITLE;
        String actualPrice = productPage.getProductPrice().getText();               // Get actual product's PRICE;
        String actualDescription = productPage.getProductDescription().getText();   // Get actual product's DESCRIPTION;

        System.out.println("PRODUCT IN THE CART: ");
        System.out.println("- Title: " + actualTitle);
        System.out.println("- Price: " + actualPrice);
        System.out.println("- Description: " + actualDescription);
        System.out.println();

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
            System.out.println();
            Assert.assertTrue(alertText.contains("Product added"),
                    "\"Product added\" alert is missing");
            alert.accept();                                     // Accept the alert (click [OK] button);
        } catch (NoAlertPresentException e) {                   // There is no alert present;
            System.out.println("The browser alert is missing");
        }
    }

    @And("the {string} product been added to the cart")
    public void theProductBeenAddedToTheCart(String productId) throws InterruptedException {
        driver.get(CART_URL);                                                                       // Get Cart page URL;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CartPage.ROW_1_TITLE)));  // Wait Table loaded;
        CartPage cartPage = new CartPage(driver);                                                   // New Cart page;

        boolean isProductAdded = cartPage.isProductAddedToCart("Product Title", "$Product Price");
        Assert.assertTrue(isProductAdded, "Product is not added to the cart"); // Check the product been added;
    }

    @And("user proceed to checkout")
    public void userProceedToCheckout() {
        driver.get(CART_URL);                                                                       // Get Cart page URL;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CartPage.ROW_1_TITLE)));  // Wait Table loaded;
        CartPage cartPage = new CartPage(driver);                                                   // New Cart page;
        cartPage.placeOrder();                                                               // Method for click button.
    }

    @Then("user should be redirected to the checkout page")
    public void userShouldBeRedirectedToTheCheckoutPage() {
        boolean isModalDisplayed = PlaceOrderModal.isModalDisplayed(driver);            // Method for modal appearance;
        Assert.assertTrue(isModalDisplayed, "Place order modal is missing");    // Modal appearance assertion.
    }

    @And("user discard checkout")
    public void userDiscardCheckout() {
        PlaceOrderModal placeOrderModal = new PlaceOrderModal(driver);
        placeOrderModal.discardPlaceOrderByCrossButton();
    }

    @Then("user should be redirected to the cart page")
    public void userShouldBeRedirectedToTheCartPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, CART_URL, "User is not redirected to the cart page.");

        System.out.println("User is redirected to the cart page successfully.");
        System.out.println();
    }

    @And("user remove the {string} product from the cart")
    public void userRemoveTheProductFromTheCart(String productId) {
        CartPage cartPage = new CartPage(driver);
        boolean isProductAdded = cartPage.isProductAddedToCart("Product Title", "$Product Price");
        Assert.assertTrue(isProductAdded, "Product is still in the cart"); // Check the product still is in cart;
        cartPage.clickDeleteLink();
    }

    @Then("the {string} product should be removed from the cart")
    public void theProductShouldBeRemovedFromTheCart(String productId) {
        driver.get(CART_URL);
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        boolean isProductRemoved = !driver.findElements(By.xpath(CartPage.ROW_1_DELETE)).isEmpty(); // there is no element;
        Assert.assertFalse(isProductRemoved, "Product is still in the cart"); // Check the product been deleted;
    }

    @When("user navigate to the {string} first product page")
    public void userNavigateToTheFirstProductPage(String firstProduct) {
        var homePageContent1 = new HomePageContent1(driver);
        WebElement productLink = null;
        String productUrl = null;
        switch (firstProduct) {
            case "PRODUCT_1":
                productLink = homePageContent1.getLinkProductId1();
                productUrl = TestData.PRODUCT1_URL;
                break;
            case "PRODUCT_2":
                productLink = homePageContent1.getLinkProductId2();
                productUrl = TestData.PRODUCT2_URL;
                break;
            case "PRODUCT_3":
                productLink = homePageContent1.getLinkProductId3();
                productUrl = TestData.PRODUCT3_URL;
                break;
            case "PRODUCT_4":
                productLink = homePageContent1.getLinkProductId4();
                productUrl = PRODUCT4_URL;
                break;
            case "PRODUCT_5":
                productLink = homePageContent1.getLinkProductId5();
                productUrl = TestData.PRODUCT5_URL;
                break;
            case "PRODUCT_6":
                productLink = homePageContent1.getLinkProductId6();
                productUrl = TestData.PRODUCT6_URL;
                break;
            case "PRODUCT_7":
                productLink = homePageContent1.getLinkProductId7();
                productUrl = TestData.PRODUCT7_URL;
                break;
            case "PRODUCT_8":
                productLink = homePageContent1.getLinkProductId8();
                productUrl = TestData.PRODUCT8_URL;
                break;
            case "PRODUCT_9":
                productLink = homePageContent1.getLinkProductId9();
                productUrl = TestData.PRODUCT9_URL;
                break;
            default:
                throw new IllegalArgumentException("Invalid productId: " + firstProduct);
        }
        if (productLink != null) {
            wait = new WebDriverWait(driver, Duration.ofMillis(1000));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(productLink));
            clickableElement.click();
        } else {
            throw new IllegalArgumentException("Invalid productId: " + firstProduct);
        }
    }

    @And("user see the {string} first product in the cart")
    public void userSeeTheFirstProductInTheCart(String firstProduct) {
        ProductData expectedProductData = productDataMap.get(firstProduct);         // Get expected data from the Map;
        productPage = new ProductPage(driver);                                      // New instance of Product page;

        String actualTitle = productPage.getProductTitle().getText();               // Get actual product's TITLE;
        String actualPrice = productPage.getProductPrice().getText();               // Get actual product's PRICE;
        String actualDescription = productPage.getProductDescription().getText();   // Get actual product's DESCRIPTION;

        System.out.println("PRODUCT IN THE CART: ");
        System.out.println("- Title: " + actualTitle);
        System.out.println("- Price: " + actualPrice);
        System.out.println("- Description: " + actualDescription);
        System.out.println();

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
        softAssert.assertAll();
    }

    @And("user back to the home page")
    public void userBackToTheHomePage() {
        ProductPage productPage1 = new ProductPage(driver);
        productPage1.clickHomeButton();
    }

    @And("user navigate to the {string} second product page")
    public void userNavigateToTheSecondProductPage(String secondProduct) {
        var homePageContent1 = new HomePageContent1(driver);
        WebElement productLink = null;
        String productUrl = null;
        switch (secondProduct) {
            case "PRODUCT_1":
                productLink = homePageContent1.getLinkProductId1();
                productUrl = TestData.PRODUCT1_URL;
                break;
            case "PRODUCT_2":
                productLink = homePageContent1.getLinkProductId2();
                productUrl = TestData.PRODUCT2_URL;
                break;
            case "PRODUCT_3":
                productLink = homePageContent1.getLinkProductId3();
                productUrl = TestData.PRODUCT3_URL;
                break;
            case "PRODUCT_4":
                productLink = homePageContent1.getLinkProductId4();
                productUrl = PRODUCT4_URL;
                break;
            case "PRODUCT_5":
                productLink = homePageContent1.getLinkProductId5();
                productUrl = TestData.PRODUCT5_URL;
                break;
            case "PRODUCT_6":
                productLink = homePageContent1.getLinkProductId6();
                productUrl = TestData.PRODUCT6_URL;
                break;
            case "PRODUCT_7":
                productLink = homePageContent1.getLinkProductId7();
                productUrl = TestData.PRODUCT7_URL;
                break;
            case "PRODUCT_8":
                productLink = homePageContent1.getLinkProductId8();
                productUrl = TestData.PRODUCT8_URL;
                break;
            case "PRODUCT_9":
                productLink = homePageContent1.getLinkProductId9();
                productUrl = TestData.PRODUCT9_URL;
                break;
            default:
                throw new IllegalArgumentException("Invalid productId: " + secondProduct);
        }
        if (productLink != null) {
            wait = new WebDriverWait(driver, Duration.ofMillis(1000));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(productLink));
            clickableElement.click();
        } else {
            throw new IllegalArgumentException("Invalid productId: " + secondProduct);
        }
    }

    @And("user see the {string} second product in the cart")
    public void userSeeTheSecondProductInTheCart(String secondProduct) {
        ProductData expectedProductData = productDataMap.get(secondProduct);        // Get expected data from the Map;
        productPage = new ProductPage(driver);                                      // New instance of Product page;

        String actualTitle = productPage.getProductTitle().getText();               // Get actual product's TITLE;
        String actualPrice = productPage.getProductPrice().getText();               // Get actual product's PRICE;
        String actualDescription = productPage.getProductDescription().getText();   // Get actual product's DESCRIPTION;

        System.out.println("PRODUCT IN THE CART: ");
        System.out.println("- Title: " + actualTitle);
        System.out.println("- Price: " + actualPrice);
        System.out.println("- Description: " + actualDescription);
        System.out.println();

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
        softAssert.assertAll();
    }

    @And("user navigate to the {string} third product page")
    public void userNavigateToTheThirdProductPage(String thirdProduct) {
        var homePageContent1 = new HomePageContent1(driver);
        WebElement productLink = null;
        String productUrl = null;
        switch (thirdProduct) {
            case "PRODUCT_1":
                productLink = homePageContent1.getLinkProductId1();
                productUrl = TestData.PRODUCT1_URL;
                break;
            case "PRODUCT_2":
                productLink = homePageContent1.getLinkProductId2();
                productUrl = TestData.PRODUCT2_URL;
                break;
            case "PRODUCT_3":
                productLink = homePageContent1.getLinkProductId3();
                productUrl = TestData.PRODUCT3_URL;
                break;
            case "PRODUCT_4":
                productLink = homePageContent1.getLinkProductId4();
                productUrl = PRODUCT4_URL;
                break;
            case "PRODUCT_5":
                productLink = homePageContent1.getLinkProductId5();
                productUrl = TestData.PRODUCT5_URL;
                break;
            case "PRODUCT_6":
                productLink = homePageContent1.getLinkProductId6();
                productUrl = TestData.PRODUCT6_URL;
                break;
            case "PRODUCT_7":
                productLink = homePageContent1.getLinkProductId7();
                productUrl = TestData.PRODUCT7_URL;
                break;
            case "PRODUCT_8":
                productLink = homePageContent1.getLinkProductId8();
                productUrl = TestData.PRODUCT8_URL;
                break;
            case "PRODUCT_9":
                productLink = homePageContent1.getLinkProductId9();
                productUrl = TestData.PRODUCT9_URL;
                break;
            default:
                throw new IllegalArgumentException("Invalid productId: " + thirdProduct);
        }
        if (productLink != null) {
            wait = new WebDriverWait(driver, Duration.ofMillis(1000));
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(productLink));
            clickableElement.click();
        } else {
            throw new IllegalArgumentException("Invalid productId: " + thirdProduct);
        }
    }

    @And("user see the {string} third product in the cart")
    public void userSeeTheThirdProductInTheCart(String thirdProduct) {
        ProductData expectedProductData = productDataMap.get(thirdProduct);         // Get expected data from the Map;
        productPage = new ProductPage(driver);                                      // New instance of Product page;

        String actualTitle = productPage.getProductTitle().getText();               // Get actual product's TITLE;
        String actualPrice = productPage.getProductPrice().getText();               // Get actual product's PRICE;
        String actualDescription = productPage.getProductDescription().getText();   // Get actual product's DESCRIPTION;

        System.out.println("PRODUCT IN THE CART: ");
        System.out.println("- Title: " + actualTitle);
        System.out.println("- Price: " + actualPrice);
        System.out.println("- Description: " + actualDescription);
        System.out.println();

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
        softAssert.assertAll();
    }

    @And("the all products should be added to the cart")
    public void theAllProductsShouldBeAddedToTheCart() {
        driver.get(CART_URL);                                                                       // Get Cart page URL;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CartPage.ROW_1_TITLE)));  // Wait Table loaded;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CartPage.ROW_2_TITLE)));  // Wait Table loaded;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CartPage.ROW_3_TITLE)));  // Wait Table loaded;
        CartPage cartPage = new CartPage(driver);                                                   // New Cart page;

        boolean is1stProductAdded = cartPage.isFirstProductAdded("Product Title", "$Product Price");
        Assert.assertTrue(is1stProductAdded, "Product is not added to the cart"); // Check the product been added;

        boolean is2ndProductAdded = cartPage.isSecondProductAdded("Product Title", "$Product Price");
        Assert.assertTrue(is2ndProductAdded, "Product is not added to the cart"); // Check the product been added;

        boolean is3rdProductAdded = cartPage.isThirdProductAdded("Product Title", "$Product Price");
        Assert.assertTrue(is3rdProductAdded, "Product is not added to the cart"); // Check the product been added;
    }

    @And("user fill in checkout form")
    public void userFillInCheckoutForm() {
        try {
            Properties properties = PlaceOrderPropertiesReader
                    .readPropertiesFile("src/test/resources/placeorder.properties"); // Read properties from file;
            PlaceOrderFormData formData1 = new PlaceOrderFormData(              // Create Place Order Form Data instance;
                    properties.getProperty("NAME_1"),                           // Get property for the Name;
                    properties.getProperty("COUNTRY_1"),                        // Get property for the Country;
                    properties.getProperty("CITY_1"),                           // Get property for the City;
                    properties.getProperty("CARD_1"),                           // Get property for the Credit Card;
                    properties.getProperty("MONTH_1"),                          // Get property for the Month;
                    properties.getProperty("YEAR_1")                            // Get property for the Year;
            );
            PlaceOrderModal placeOrderModal = new PlaceOrderModal(driver); // New instance of the Place Order modal form;
            placeOrderModal.fillPlaceOrderForm(formData1); // Filling the form using method from Place Order modal;

            System.out.println("PLACE ORDER FORM HAS BEEN FILLED WITH DATA:");
            System.out.println("- Name: " + formData1.getName());
            System.out.println("- Country: " + formData1.getCountry());
            System.out.println("- City: " + formData1.getCity());
            System.out.println("- Card: " + formData1.getCard());
            System.out.println("- Month: " + formData1.getMonth());
            System.out.println("- Year: " + formData1.getYear());
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @And("user submit checkout form")
    public void userSubmitCheckoutForm() {
        PlaceOrderModal placeOrderModal = new PlaceOrderModal(driver);
        placeOrderModal.submitPlaceOrderByPurchaseButton();
    }

    @Then("checkout should be completed")
    public void checkoutShouldBeCompleted() {
        OrderCompleteModal orderCompleteModal = new OrderCompleteModal(driver); // Instance of the Order Complete Modal;

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(orderCompleteModal.isOrderCompleteModalDisplayed(), "Order Complete modal missing");
        softAssert.assertTrue(orderCompleteModal.isOrderCompleteSignDisplayed(), "Order Complete sign missing");
        softAssert.assertEquals(orderCompleteModal.getOrderCompleteThanksText(), "Thank you for your purchase!");
        softAssert.assertAll();

        System.out.println("ORDER COMPLETE MODAL MESSAGE: ");
        System.out.println(orderCompleteModal.getOrderCompleteThanksText());
        System.out.println(orderCompleteModal.getOrderCompleteDataText());
        System.out.println();

        orderCompleteModal.submitOrderCompleteByOkButton();                 // Click the OK button to close the modal;
    }
}
