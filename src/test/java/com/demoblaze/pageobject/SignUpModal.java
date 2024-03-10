package com.demoblaze.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUpModal {
    private WebDriver driver;
    public static final String SIGNUP_MODAL_LOCATOR = "//*[@id=\"signInModal\"]/div";
    public static final String SIGNUP_TITLE_LOCATOR = "//*[@id=\"signInModalLabel\"]";
    public static final String SIGNUP_X_LOCATOR = "//*[@id=\"signInModal\"]/div/div/div[1]/button/span";
    public static final String SIGNUP_USERNAME_LOCATOR = "//*[@id=\"sign-username\"]";
    public static final String SIGNUP_PASSWORD_LOCATOR = "//*[@id=\"sign-password\"]";
    public static final String SIGNUP_BUTTON_LOCATOR = "//*[@id=\"signInModal\"]/div/div/div[3]/button[2]";
    public static final String SIGNUP_CLOSE_LOCATOR = "//*[@id=\"signInModal\"]/div/div/div[3]/button[1]";


    private WebElement signUpForm;
    private WebElement signUpTitle;
    private WebElement signUpX;
    private WebElement signUpInputUsername;
    private WebElement signUpInputPassword;
    private WebElement signUpButtonSignup;
    private WebElement signUpButtonClose;


}
