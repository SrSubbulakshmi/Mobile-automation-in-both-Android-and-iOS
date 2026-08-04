package couriermobileapp.tests;

import couriermobileapp.tests.BaseTest.BaseCapabilitiesTest;
import couriermobileapp.tests.BaseTest.MobileCourierKeys;
import couriermobileapp.tests.pages.LoginPage;
import couriermobileapp.tests.pages.LoginPageWithBasePage;
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
