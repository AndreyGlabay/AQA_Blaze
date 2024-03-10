package com.demoblaze.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Create the test class and implement Page Object for the Home Page
public class HomePage {
    private WebDriver driver;
    public static final String BRAND_LOCATOR = "//*[@id=\"nava\"]";
    public static final String LOGO_LOCATOR = "//*[@id=\"nava\"]/img";
    public static final String NAVBAR_LOCATOR = "//*[@id=\"navbarExample\"]";
    public static final String HOME_BUTTON_LOCATOR = "//*[@id=\"navbarExample\"]/ul/li[1]/a";
    public static final String CONTACT_BUTTON_LOCATOR = "//*[@id=\"navbarExample\"]/ul/li[2]/a";
    public static final String ABOUT_BUTTON_LOCATOR = "//*[@id=\"navbarExample\"]/ul/li[3]/a";
    public static final String CART_BUTTON_LOCATOR = "//*[@id=\"cartur\"]";
    public static final String LOGIN_BUTTON_LOCATOR = "//*[@id=\"login2\"]";
    public static final String SIGNUP_BUTTON_LOCATOR = "//*[@id=\"signin2\"]";
    public static final String CAROUSEL_LOCATOR = "//*[@id=\"contcar\"]";
    public static final String MENU_AREA_LOCATOR = "//*[@id=\"contcont\"]/div/div[1]";
    public static final String MENU_CATEGORIES_LOCATOR = "//*[@id=\"cat\"]";
    public static final String MENU_PHONES_LOCATOR = "//*[@id=\"itemc\"]";
    public static final String MENU_LAPTOPS_LOCATOR = "//*[@id=\"itemc\"]";
    public static final String MENU_MONITORS_LOCATOR = "//*[@id=\"itemc\"]";
    public static final String CONTENT_AREA_LOCATOR = "//*[@id=\"contcont\"]/div/div[2]";
    public static final String PREVIOUS_LOCATOR = "//*[@id=\"prev2\"]";
    public static final String NEXT_LOCATOR = "//*[@id=\"next2\"]";
    public static final String FOOTER_ABOUT_LOCATOR = "//*[@id=\"fotcont\"]/div[1]";
    public static final String FOOTER_CONTACTS_LOCATOR = "//*[@id=\"fotcont\"]/div[2]";
    public static final String FOOTER_LOGO_LOCATOR = "//*[@id=\"fotcont\"]/div[3]";
    public static final String FOOTER_COPYRIGHT_LOCATOR = "/html/body/footer";

    private WebElement headerBrand;
    private WebElement headerLogo;
    private WebElement navBar;
    private WebElement navButtonHome;
    private WebElement navButtonContact;
    private WebElement navButtonAboutUs;
    private WebElement navButtonCart;
    private WebElement navButtonLogIn;

    public WebElement getNavButtonHome() {
        return navButtonHome;
    }

    public WebElement getNavButtonContact() {
        return navButtonContact;
    }

    public WebElement getNavButtonAboutUs() {
        return navButtonAboutUs;
    }

    public WebElement getNavButtonCart() {
        return navButtonCart;
    }

    public WebElement getNavButtonLogIn() {
        return navButtonLogIn;
    }

    public WebElement getNavButtonSignUp() {
        return navButtonSignUp;
    }

    public WebElement getCategoryMenuPhones() {
        return categoryMenuPhones;
    }

    public WebElement getCategoryMenuLaptops() {
        return categoryMenuLaptops;
    }

    public WebElement getCategoryMenuMonitors() {
        return categoryMenuMonitors;
    }

    public WebElement getPreviousButton() {
        return previousButton;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    private WebElement navButtonSignUp;
    private WebElement carousel;
    private WebElement categoryMenuArea;
    private WebElement categoryMenuTitle;
    private WebElement categoryMenuPhones;
    private WebElement categoryMenuLaptops;
    private WebElement categoryMenuMonitors;
    private WebElement contentArea;
    private WebElement previousButton;
    private WebElement nextButton;
    private WebElement footerAbout;
    private WebElement footerContacts;
    private WebElement footerLogo;
    private WebElement footerCopyright;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        headerBrand = driver.findElement(By.xpath(BRAND_LOCATOR));
        headerLogo = driver.findElement(By.xpath(LOGO_LOCATOR));
        navBar = driver.findElement(By.xpath(NAVBAR_LOCATOR));
        navButtonHome = driver.findElement(By.xpath(HOME_BUTTON_LOCATOR));
        navButtonContact = driver.findElement(By.xpath(CONTACT_BUTTON_LOCATOR));
        navButtonAboutUs = driver.findElement(By.xpath(ABOUT_BUTTON_LOCATOR));
        navButtonCart = driver.findElement(By.xpath(CART_BUTTON_LOCATOR));
        navButtonLogIn = driver.findElement(By.xpath(LOGIN_BUTTON_LOCATOR));
        navButtonSignUp = driver.findElement(By.xpath(SIGNUP_BUTTON_LOCATOR));
        carousel = driver.findElement(By.xpath(CAROUSEL_LOCATOR));
        categoryMenuArea = driver.findElement(By.xpath(MENU_AREA_LOCATOR));
        categoryMenuTitle = driver.findElement(By.xpath(MENU_CATEGORIES_LOCATOR));
        categoryMenuPhones = driver.findElement(By.xpath(MENU_PHONES_LOCATOR));
        categoryMenuLaptops = driver.findElement(By.xpath(MENU_LAPTOPS_LOCATOR));
        categoryMenuMonitors = driver.findElement(By.xpath(MENU_MONITORS_LOCATOR));
        contentArea = driver.findElement(By.xpath(CONTENT_AREA_LOCATOR));
        previousButton = driver.findElement(By.xpath(PREVIOUS_LOCATOR));
        nextButton = driver.findElement(By.xpath(NEXT_LOCATOR));
        footerAbout = driver.findElement(By.xpath(FOOTER_ABOUT_LOCATOR));
        footerContacts = driver.findElement(By.xpath(FOOTER_CONTACTS_LOCATOR));
        footerLogo = driver.findElement(By.xpath(FOOTER_LOGO_LOCATOR));
        footerCopyright = driver.findElement(By.xpath(FOOTER_COPYRIGHT_LOCATOR));
    }

    // Methods for check are web elements present on the page
    public boolean isHeaderBrandDisplayed() {
        return headerBrand.isDisplayed();
    }

    public boolean isHeaderLogoDisplayed() {
        return headerLogo.isDisplayed();
    }

    public boolean isNavBarDisplayed() {
        return navBar.isDisplayed();
    }

    public boolean isNavButtonHomeDisplayed() {
        return navButtonHome.isDisplayed();
    }

    public boolean isNavButtonContactDisplayed() {
        return navButtonContact.isDisplayed();
    }

    public boolean isNavButtonAboutUsDisplayed() {
        return navButtonAboutUs.isDisplayed();
    }

    public boolean isNavButtonCartDisplayed() {
        return navButtonCart.isDisplayed();
    }

    public boolean isNavButtonLogInDisplayed() {
        return navButtonLogIn.isDisplayed();
    }

    public boolean isNavButtonSignUpDisplayed() {
        return navButtonSignUp.isDisplayed();
    }

    public boolean isCarouselDisplayed() {
        return carousel.isDisplayed();
    }

    public boolean isCategoryMenuAreaDisplayed() {
        return categoryMenuArea.isDisplayed();
    }

    public boolean isCategoryMenuTitleDisplayed() {
        return categoryMenuTitle.isDisplayed();
    }

    public boolean isCategoryMenuPhonesDisplayed() {
        return categoryMenuPhones.isDisplayed();
    }

    public boolean isCategoryMenuLaptopsDisplayed() {
        return categoryMenuLaptops.isDisplayed();
    }

    public boolean isCategoryMenuMonitorsDisplayed() {
        return categoryMenuMonitors.isDisplayed();
    }

    public boolean isContentAreaDisplayed() {
        return contentArea.isDisplayed();
    }

    public boolean isPreviousButtonDisplayed() {
        return previousButton.isDisplayed();
    }

    public boolean isNextButtonDisplayed() {
        return nextButton.isDisplayed();
    }

    public boolean isFooterAboutDisplayed() {
        return footerAbout.isDisplayed();
    }

    public boolean isFooterContactsDisplayed() {
        return footerContacts.isDisplayed();
    }

    public boolean isFooterLogoDisplayed() {
        return footerLogo.isDisplayed();
    }

    public boolean isFooterCopyrightDisplayed() {
        return footerCopyright.isDisplayed();
    }






//    // Login flow.
//    public void login(String username, String password) {
//        inputUsername.sendKeys(username);
//        inputPassword.sendKeys(password);
//        buttonLogin.click();
//    }
//
//    // Get and check Error Message.
//    public boolean checkMessage(String str) {
//        messageError = driver.findElement(By.xpath(MESSAGE_LOCATOR));
//        return (messageError.getText().contains(str));
//    }




}