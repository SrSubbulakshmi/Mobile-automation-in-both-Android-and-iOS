package mobileapp.tests;

import mobileapp.tests.BaseTest.BaseCapabilitiesTest;
import mobileapp.tests.BaseTest.MobileCourierKeys;
import mobileapp.tests.pages.LoginPage;
import mobileapp.tests.pages.LoginPageWithBasePage;
import org.testng.annotations.Test;


public class LoginPageTest extends BaseCapabilitiesTest implements MobileCourierKeys {

    @Test
    public void loginTest() {
        System.out.println("Testing");
        //LoginPage loginPage = new LoginPage(driver);
        LoginPageWithBasePage loginPage = new LoginPageWithBasePage(driver);
        //loginPage.clickCountryUnitedKingdom();

        loginPage.clickLoginButton();

        loginPage.typeUserName(getProperty(COURIER_EMAIL));

        loginPage.typePassword(getProperty(COURIER_PASSWORD));

        loginPage.clickKCLoginButton();


        //loginPage.handleCaptchaIfPresent("dtPassword1!");
    }


}
