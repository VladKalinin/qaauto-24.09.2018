package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;
import static java.lang.Thread.sleep;


public class PasswordResetTest extends BaseTest {

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

//ToDo - HomeWork

    @Test
    public void basicSearchTest() throws InterruptedException {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        PasswordResetPageObj passwordResetPageObj = loginPage.forgetPassword();
        Assert.assertTrue(passwordResetPageObj.isPasswordResetPageLoaded(), "Password Reset Page is not loaded");
        sleep(5000);
        PasswordResetCheckpointPageObj passwordResetCheckpointPageObj = passwordResetPageObj.passwordResetAction("vlad.kalinin.qa24@gmail.com");
        Assert.assertTrue(passwordResetCheckpointPageObj.isPasswordResetLinkWasSend(), "Password reset link was not send");
        Assert.assertEquals(passwordResetCheckpointPageObj.bannerConfirmedTextIsDislayed(), "Мы отправили вам ссылку по эл. почте", "Banner message is absent");
        sleep(60000);
        FromEmailPasswordResetPageObj fromEmailPasswordResetPageObj = passwordResetPageObj.navigateToLink();
        Assert.assertTrue(fromEmailPasswordResetPageObj.isAfterPasswordResetPageLoaded(), "Password Reset page from Email is loaded");
        PasswordResetSubmitPageObj passwordResetSubmitPageObj = fromEmailPasswordResetPageObj.passwordResetActionNewPage("Test123@","Test123@");
        sleep(5000);
        Assert.assertTrue(passwordResetSubmitPageObj.passwordResetSuccessfully(), "Password was not reset");
        Assert.assertEquals(passwordResetSubmitPageObj.bannerTextIsDislayed(), "Ваш пароль успешно изменён", "Banner message is absent");
        HomePage homePage = passwordResetSubmitPageObj.goToHomePage();
        sleep(5000);
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");

    }
}
