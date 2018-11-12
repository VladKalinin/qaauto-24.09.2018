package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LoginSubmitPageObj;
import page.HomePage;
import page.LoginPageObj;

public class LoginTest extends BaseTest{

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "vlad.kalinin.qa24@gmail.com", "Test123@" },
                { "vlad.kalinin.QA24@gmail.com", "Test123@" },
                { "vlad.kalinin.qa24@gmail.com ", "Test123@" }
        };
    }

    @DataProvider
    public Object[][] emptyPageDataprovider() {
        return new Object[][]{
                { "", "vvkalinin20" },
                { "vlad.kalinin.QA24@gmail.com", "" },
                { "", ""}
        };
    }

    @DataProvider
    public Object[][] wrongGuestHomeDataProvider() {
        return new Object[][]{
                {"asdf","test12","Please enter a valid email address.",""},
                {"as","test12","The text you provided is too short (the minimum length is 3 characters, your text contains 2 characters).",""},
                {"vlad.kalinin.qa24@gmail.com","test12","","Hmm, that's not the right password. Please try again or request a new one."},
                {"vlad.kalinin.qa24@gmail.com","test1","The password you provided must have at least 6 characters.",""}
        };
    }
    /**
     * Proconditions:
     * - Open Firefox browser.
     * <p>
     * Scenario:
     * - Navigate to https://www.linkedin.com/ - done
     * - Varify that login page is load - done
     * - Varify that there is Login field - done
     * - Varify that there is Password field - done
     * - Varify that there is Sign In button - done
     * <p>
     * - Enter userEmail to 'user email' field - done
     * - Enter userPassword to 'user password' field - done
     * - Click on signIn button. - done
     * - Varify that Home page is load - done
     * <p>
     * PostConditions:
     * - Close Firefox - done
     */
    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPasswrod) throws InterruptedException {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        HomePage homePage = loginPage.login(userEmail,userPasswrod,HomePage.class);
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");
    }

    @Test(dataProvider = "emptyPageDataprovider")
    public void emptyLoginPasswordFieldsTest(String userEmail, String userPasswrod) throws InterruptedException {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login(userEmail, userPasswrod, LoginPageObj.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    @Test(dataProvider ="wrongGuestHomeDataProvider")
    public void wrongDataTest(String userEmail, String userPasswrod, String errorLoginMessage,String errorPasswordMessage) throws InterruptedException {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        LoginSubmitPageObj loginSubmitPageObj = loginPage.login(userEmail, userPasswrod, LoginSubmitPageObj.class);
        Assert.assertTrue(loginSubmitPageObj.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(loginSubmitPageObj.alertErrorTextDisplayed(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert Error message is absent");
        Assert.assertEquals(loginSubmitPageObj.loginErrorTextDisplayed(), errorLoginMessage, "Email Error message is absent");
        Assert.assertEquals(loginSubmitPageObj.passwordErrorTextDisplayed(), errorPasswordMessage, "Password Error message is absent");
    }
}