package mobileapp.tests.pages;

import mobileapp.enums.LocatorNames;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private AppiumDriver driver;
    WebDriverWait driverWait;
    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }
    // A reusable method to determine the platform
    protected boolean isAndroidPlatform() {
        return driver.getCapabilities().getCapability("platformName").toString().equalsIgnoreCase("Android");
    }



    public WebElement getElement(LocatorNames locatorName, String iOSLocatorValue, String androidLocatorValue) {
        By locator = setLocatorAndFindElement(locatorName, iOSLocatorValue, androidLocatorValue);
        return driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(locator))) ;
    }

    public WebElement waitForEl(LocatorNames locatorName, String iOSLocatorValue, String androidLocatorValue){
        By locator = setLocatorAndFindElement(locatorName, iOSLocatorValue, androidLocatorValue);
        return driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }


    public void waitNType(LocatorNames locatorName, String iOSLocatorValue, String androidLocatorValue, String typeText){
        WebElement element = waitForEl(locatorName, iOSLocatorValue,androidLocatorValue);
        //getElement(byLocator).clear();
       element.sendKeys(typeText);
    }

    public void waitNType(WebElement element, String typeText) {
        driverWait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(typeText);

    }
    public boolean waitNclick(LocatorNames locatorName, String iOSLocatorValue, String androidLocatorValue) {

        System.out.println("Inside the waitNclick");
        By locator = setLocatorAndFindElement(locatorName, iOSLocatorValue, androidLocatorValue);
        try {
            WebElement element = driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            driverWait.until(ExpectedConditions.elementToBeClickable(element));

            System.out.println("Element " + element + " is visible. Clicking...");
            element.click();
            element.click();
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element " + locator + " is not visible. Skipping...");
            return false;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Element " + locator + " is not present or stale. Skipping...");
            return false;
        }

    }

    public boolean waitNclick(WebElement element) {
        try {
            driverWait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("Element " + element + " is visible. Clicking...");
            element.click();
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element " + element + " is not visible. Skipping...");
            return false;
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Element " + element + " is not present or stale. Skipping...");
            return false;
        }
    }


    private By setLocatorAndFindElement(LocatorNames locatorName, String iOSLocatorValue, String androidLocatorValue) {

        System.out.println(" inside the setLocatorAndFindElement");
        System.out.println("Locator Value " + iOSLocatorValue);
        By locator = null;

       if(isAndroidPlatform()) {

           switch(LocatorNames.valueOf(locatorName.name())) {
               case accessibility:
                   locator = AppiumBy.accessibilityId(androidLocatorValue);
                   break;
               case xpath:
                   locator = AppiumBy.xpath(androidLocatorValue);
                   break;
               case className:
                   locator = AppiumBy.className(androidLocatorValue);
                   break;
               case uiAutomator:
                   locator = AppiumBy.androidUIAutomator(androidLocatorValue);
                   break;
           }

        }
       else {
           switch(LocatorNames.valueOf(locatorName.name())) {
               case accessibility:
                   locator = AppiumBy.accessibilityId(iOSLocatorValue);
                   break;
               case xpath:
                   locator = AppiumBy.xpath(iOSLocatorValue);
                   break;
               case className:
                   locator = AppiumBy.className(iOSLocatorValue);
                   break;
               case uiAutomator:
                   locator = AppiumBy.iOSNsPredicateString(iOSLocatorValue);
                   break;
               case iOSClassChain:
                   locator = AppiumBy.iOSClassChain(iOSLocatorValue);
                   break;
           }

       }
        return locator;
    }
}
