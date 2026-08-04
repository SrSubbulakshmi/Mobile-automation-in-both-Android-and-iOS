package mobileapp.tests.BaseTest;


import mobileapp.LoadProperties;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BaseCapabilitiesTest extends LoadProperties {


    public WebDriverWait wait;
    public AppiumDriver driver;


    @BeforeClass
    public void launchApp() throws MalformedURLException {
        System.out.println("Launching mobile app");
        if(getProperty("execution.platform").equalsIgnoreCase("ios")) {
            ios_setupForBrowserstack();
        }
        else
            android_setupForBrowserstack();
    }


    private void ios_setupForBrowserstack() throws MalformedURLException {
        XCUITestOptions options = new XCUITestOptions();


        if(getProperty("execution.environment").equalsIgnoreCase("local")) {
            System.out.println("It is running on local");
//            options.setPlatformName(MobilePlatform.ANDROID);
//            options.setDeviceName(getProperty("device.name.android"));
//            //options.setApp(System.getProperty("user.dir") + getProperty("app.path") + getProperty("app.name"));
//            options.setAutomationName(getProperty("appium.automationName"));
//            options.setPlatformVersion(getProperty("device.version.android"));


            options.setPlatformName(MobilePlatform.IOS);
            options.setDeviceName(getProperty("device.name.ios"));
            options.setApp(System.getProperty("user.dir") + getProperty("app.path") + getProperty("app.name.ios"));
            options.setAutomationName(getProperty("appium.automationName.ios"));
            options.setPlatformVersion(getProperty("device.version.ios"));
            options.setUdid("00008110-001819090A3A201E");
            options.setUpdatedWdaBundleId("com.Raj.webDriverAgentRunner");

            driver = new IOSDriver(new URL(getProperty("appium.server.path")), options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        }
        else {

            System.out.println("It is running on Browserstack");
            if(getProperty("browserstack.legacyOrSDK").equalsIgnoreCase("legacy")) {
                HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                browserstackOptions.put("userName", getProperty("browserstack.userName"));
                browserstackOptions.put("accessKey", getProperty("browserstack.accessKey"));
                browserstackOptions.put("projectName", "Raj iOS Mobile test");
                browserstackOptions.put("buildName", "Mobile Testing with legacy method");
                browserstackOptions.put("appiumVersion", "2.4.1");

                options.setDeviceName(getProperty("iOS.deviceName"))
                        .setPlatformName(getProperty("iOS.platformName"))
                        .setPlatformVersion(getProperty("iOS.platformVersion"))
                        .setApp(getProperty("iOS.app"))
                        .setCapability("bstack:options", browserstackOptions);

            }
            driver = new IOSDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), options);
        }
    }


    private void android_setupForBrowserstack() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();

        if(getProperty("execution.environment").equalsIgnoreCase("local")) {
            System.out.println("It is running on local");
            options.setPlatformName(MobilePlatform.ANDROID);
            options.setDeviceName(getProperty("device.name.android"));
            options.setApp(System.getProperty("user.dir") + getProperty("app.path") + getProperty("app.name.android"));
            options.setAutomationName(getProperty("appium.automationName.android"));
            options.setPlatformVersion(getProperty("device.version.android"));

            driver = new AndroidDriver(new URL(getProperty("appium.server.path")), options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        }
        else {
            System.out.println("It is running on Browserstack");
            if(getProperty("browserstack.legacyOrSDK").equalsIgnoreCase("legacy")) {

                System.out.println("It is running on Browserstack using SDK");

                HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
                browserstackOptions.put("userName", getProperty("browserstack.userName"));
                browserstackOptions.put("accessKey", getProperty("browserstack.accessKey"));
                browserstackOptions.put("projectName", "Raj Android Mobile test");
                browserstackOptions.put("buildName", "Mobile Testing with legacy method");

                browserstackOptions.put("appiumVersion", "2.4.1");

                options.setDeviceName(getProperty("android.deviceName"))
                        .setPlatformName(getProperty("android.platformName"))
                        .setPlatformVersion(getProperty("android.platformVersion"))
                        .setApp(getProperty("android.app"))
                        .setCapability("bstack:options", browserstackOptions);
            }

            driver = new AndroidDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), options);

        }
    }


    //AppiumDriver driver;
    public void setupCapabilities() throws MalformedURLException {
        System.out.println("Environment Testing$$$$" + getProperty("execution.environment"));

        XCUITestOptions options = new XCUITestOptions();

        if(getProperty("execution.environment").equalsIgnoreCase("local")) {
            System.out.println("It is running on local");
            options.setPlatformName(MobilePlatform.ANDROID);
            options.setDeviceName(getProperty("device.name.android"));
            options.setApp(System.getProperty("user.dir") + getProperty("app.path") + getProperty("app.name"));
            options.setAutomationName(getProperty("appium.automationName"));
            options.setPlatformVersion(getProperty("device.version.android"));

            driver = new AndroidDriver(new URL(getProperty("appium.server.path")), options);
            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        }
        else{
            System.out.println("It is running on Browserstack");
            //UiAutomator2Options capabilities = new UiAutomator2Options();


           /*DesiredCapabilities capabilities = new DesiredCapabilities();
           capabilities.setCapability("platformName", MobilePlatform.ANDROID);
           capabilities.setCapability("browserstack.debug", "true");
           capabilities.setCapability("deviceName", "Samsung Galaxy S22 Ultra");
           capabilities.setCapability("os_version", "12.0");
           capabilities.setCapability("app", "bs://41f7cc9636605877e9208b01b120dc6a21j54d50");*//*
           capabilities.setDeviceName(getProperty("Samsung Galaxy S22 Ultra"));
           //capabilities.setApp(System.getProperty("user.dir") + getProperty("app.path") + getProperty("app.name"));
           capabilities.setAutomationName(getProperty("appium.automationName"));
           capabilities.setPlatformVersion(getProperty("12.0"));
           capabilities.setApp("bs://41f7cc9636605877e9208b01b120dc6a21j54d50");
           capabilities.setCapability("bstack:options", bsOptions);
           capabilities.setCapability("browserstack.debug", "true");
           driver = new AndroidDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), capabilities);
           //driver = (AppiumDriver) com.browserstack.sdk.DriverFactory.createDriver();
           //driver = (AppiumDriver) DriverFactory.createDriver();*/


 /*          options.setCapability("platformName", MobilePlatform.ANDROID);
           options.setCapability("app", "bs://41f7cc9636605877e9208b01b120dc6a21j54d50");
           options.setCapability("osVersion", "12.0");
           options.setCapability("deviceName", "Samsung Galaxy S22 Ultra");*/


            //androidDriver = new AndroidDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), options);
            driver = new IOSDriver(new URL("http://hub-cloud.browserstack.com/wd/hub"), options);



        }
    }
}
