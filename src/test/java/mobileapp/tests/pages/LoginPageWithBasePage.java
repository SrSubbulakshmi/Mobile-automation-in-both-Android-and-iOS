package mobileapp.tests.pages;

import mobileapp.enums.LocatorNames;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPageWithBasePage extends BasePage{


    private final AppiumDriver driver;
    //private final WebDriverWait driverWait;


    public LoginPageWithBasePage(AppiumDriver driver) {
        super(driver);
        this.driver = driver;
        //driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        //PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
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

    public void clickCountryUnitedKingdom() {
        waitNclick(LocatorNames.accessibility, "United Kingdom", "new UiSelector().text(\"United Kingdom\")");
       /* if(COUNTRY_SELECTOR_UNITED_KINGDOM != null)
            waitIfVisibleAndClick(driver,COUNTRY_SELECTOR_UNITED_KINGDOM, 3)*/;
    }

    public void clickLoginButton() {

        System.out.println("Clicking signing button.....");
        waitNclick(LocatorNames.xpath, "(//XCUIElementTypeOther[@name=\"login-button\"])[2]",
                "//*[@content-desc='login-button']");
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"username\")")
    @iOSXCUITFindBy(iOSNsPredicate = "type == 'XCUIElementTypeTextField' AND value == \"john@courier.com\"")
    private WebElement USER_NAME;

    public void typeUserName(String userName) {
        waitNType(USER_NAME, userName);
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"password\")")
    @iOSXCUITFindBy(className = "XCUIElementTypeSecureTextField")
    private WebElement PASSWORD;

    public void typePassword(String password) {
        waitNType(PASSWORD, password);
    }


    //@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"kc-login\")")
    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"kc-login\")")
    @iOSXCUITFindBy(accessibility = "Login")
    private WebElement KC_LOGIN_BUTTON;

    public void clickKCLoginButton() {
        verifyHumanClick();
        checkSuccessBoxPresent();
        waitNclick(KC_LOGIN_BUTTON);
        //driverWait.until(ExpectedConditions.elementToBeClickable(KC_LOGIN_BUTTON));
        //System.out.println("Login button is now clickable, clicking..... ");
        //KC_LOGIN_BUTTON.click();
    }

    private void verifyHumanClick() {
        waitNclick(LocatorNames.className, "XCUIElementTypeSwitch", "android.widget.CheckBox");
        //waitIfVisibleAndClick(driver, verifyHuman, 5);
    }

    public void checkSuccessBoxPresent() {
        waitNclick(LocatorNames.accessibility, "alert", "new UiSelector().resourceId(\"success\")");
    }


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Invalid Captcha\")")
    private WebElement captchaTextBox;


    public void handleCaptchaIfPresent(String password) {
        if(waitNclick(LocatorNames.uiAutomator, "", "new UiSelector().text(\"Invalid Captcha\")")) {
            typePassword(password);
            checkSuccessBoxPresent();
            clickKCLoginButton();
        }

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
