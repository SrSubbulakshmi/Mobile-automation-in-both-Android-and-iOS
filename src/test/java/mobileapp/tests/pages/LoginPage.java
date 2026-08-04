package mobileapp.tests.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class LoginPage {


    private final AppiumDriver driver;
    private final WebDriverWait driverWait;


    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
    }





    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == \"LOGIN DEV OPTIONS\"`][2]")
    private WebElement LOGIN_DEV_OPTIONS;


    public void click_devOptions() {
        System.out.println("Clicking Dev Options button.....");
        driverWait.until(ExpectedConditions.elementToBeClickable(LOGIN_DEV_OPTIONS));
        LOGIN_DEV_OPTIONS.click();
    }


    @iOSXCUITFindBy(className = "XCUIElementTypeSwitch")
    private WebElement PROXY_TYPE_SWITCH;


    public void enable_proxy() {
        System.out.println("enabling proxy.....");
        driverWait.until(ExpectedConditions.elementToBeClickable(PROXY_TYPE_SWITCH));
        PROXY_TYPE_SWITCH.click();
    }


    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeOther[`name == \"In progress PROCEED\"`][1]")
    private WebElement PROCEED_BUTTON;


    public void click_proceedButton() {
        System.out.println("enabling proxy.....");
        driverWait.until(ExpectedConditions.elementToBeClickable(PROCEED_BUTTON));
        PROCEED_BUTTON.click();
    }




    @AndroidFindBy(xpath = "//*[@content-desc='login-button']")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeOther[@name=\"login-button\"])[2]")
    private WebElement LOGIN_BUTTON;


    @AndroidFindBy(accessibility = "new UiSelector().text(\"United Kingdom\")")
    @iOSXCUITFindBy(accessibility = "United Kingdom")
    private WebElement COUNTRY_SELECTOR_UNITED_KINGDOM;

    public void clickCountryUnitedKingdom() {

        if(COUNTRY_SELECTOR_UNITED_KINGDOM != null)
            waitIfVisibleAndClick(driver,COUNTRY_SELECTOR_UNITED_KINGDOM, 3);
    }


    public void clickLoginButton() {


       /*click_devOptions();
       enable_proxy();
       click_proceedButton();*/


        System.out.println("Clicking signing button.....");
        driverWait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        LOGIN_BUTTON.click();
    }


    //@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"username\")")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"username\")")
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeTextField' AND value == \"john@courier.com\"")
    private WebElement USER_NAME;


    public void typeUserName(String userName) {
        driverWait.until(ExpectedConditions.visibilityOf(USER_NAME));
        USER_NAME.sendKeys(userName);
    }


    //@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"password\")")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"password\")")
    @iOSXCUITFindBy(className = "XCUIElementTypeSecureTextField")
    private WebElement PASSWORD;


    public void typePassword(String password) {
        driverWait.until(ExpectedConditions.visibilityOf(PASSWORD));
        PASSWORD.sendKeys(password);
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"kc-login\")")
    @iOSXCUITFindBy(accessibility = "Login")
    private WebElement KC_LOGIN_BUTTON;


    public void clickKCLoginButton() {
        verifyHumanClick();
        checkSuccessBoxPresent();
        driverWait.until(ExpectedConditions.elementToBeClickable(KC_LOGIN_BUTTON));
        System.out.println("Login button is now clickable, clicking..... ");
        KC_LOGIN_BUTTON.click();
    }




    @AndroidFindBy(className = "android.widget.CheckBox")
    @iOSXCUITFindBy(className = "XCUIElementTypeSwitch")
    private WebElement verifyHuman;


    private void verifyHumanClick() {
        waitIfVisibleAndClick(driver, verifyHuman, 5);
    }


    //@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"success\")")
    @AndroidFindBy(accessibility = "new UiSelector().resourceId(\"success\")")
    @iOSXCUITFindBy(accessibility = "alert")
    private WebElement successCheckBoX;


    public void checkSuccessBoxPresent() {
        if(successCheckBoX != null)
            waitIfVisibleAndClick(driver, successCheckBoX, 10);
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Invalid Captcha\")")
    private WebElement captchaTextBox;


    public boolean handleCaptchaIfPresent(String password) {
        if(captchaTextBox != null)
            if(waitIfVisibleAndClick(driver, captchaTextBox, 10)) {
                typePassword("dtPassword1!");
                checkSuccessBoxPresent();
                clickKCLoginButton();
            }
        return false;
    }


    public boolean waitIfVisibleAndClick(WebDriver driver, WebElement element, int timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));


            System.out.println("Element " + element + " is visible. Clicking...");
            element.click();
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element " + element + " not visible. Skipping...");
            return false;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Element " + element + " not present or stale. Skipping...");
            return false;
        }
    }


}
