import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class PasswordResetTest {

    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://linkedin.com/");
        loginPage = PageFactory.initElements(webDriver, LoginPage.class);
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }

    /**
     * Precondition:
     * - Open new Browser
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify loginPage Elements
     * - Press button 'Forget Password'
     * - Verify that password reset page is loaded
     * - Write the email address and press button
     * - Manually change the current link to link from mail
     * - Verify that correct page is loaded
     * - Put on new password and confirm new password
     * - Go to home page
     *
     * PastCondition:
     * - Close browser
     */


    @Test
    public void basicSearchTest() throws InterruptedException {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        PasswordResetPage passwordResetPage = loginPage.forgetPassword(PasswordResetPage.class);
        Assert.assertTrue(passwordResetPage.isPasswordResetPageLoaded(), "Password Reset Page is not loaded");
        sleep(2000);
        CheckLinkWasSentPage checkLinkWasSentPage=passwordResetPage.passwordResetAction("vlad.kalinin.qa24@gmail.com", CheckLinkWasSentPage.class);
        Assert.assertTrue(checkLinkWasSentPage.isPasswordResetLinkWasSend(), "Password reset link was not send");
        Assert.assertEquals(checkLinkWasSentPage.bannerConfirmedTextIsDislayed(), "Мы отправили вам ссылку по эл. почте", "Banner message is absent");
        sleep(60000);
        webDriver.getCurrentUrl();
        FromEmailPasswordResetPage fromEmailPasswordResetPage = new FromEmailPasswordResetPage(webDriver);
        Assert.assertTrue(fromEmailPasswordResetPage.isAfterPasswordResetPageLoaded(), "Password Reset page from Email is loaded");
        PasswordResetSubmitPage passwordResetSubmitPage = fromEmailPasswordResetPage.passwordResetActionNewPage("Test123@","Test123@", PasswordResetSubmitPage.class);
        sleep(2000);
        Assert.assertTrue(passwordResetSubmitPage.passwordResetSuccessfully(), "Password was not reset");
        Assert.assertEquals(passwordResetSubmitPage.bannerTextIsDislayed(), "Ваш пароль успешно изменён", "Banner message is absent");
        HomePage homePage = passwordResetSubmitPage.goToHomePage(HomePage.class);
        sleep(2000);
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");
    }
}
