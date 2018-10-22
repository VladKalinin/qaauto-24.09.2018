import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;


public class LoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod(){
        webDriver = new FirefoxDriver();
    }

    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
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
    @Test
    public void successfulLoginTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("vlad.kalinin.qa24@gmail.com", "vvkalinin20");
        HomePage homePage = new HomePage(webDriver);
        sleep(3000);
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");
    }

    @Test
    public void passwordFieldIsEmptyTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("vlad.kalinin.qa24@gmail.com", "");
        sleep(3000);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    @Test
    public void allFieldsEmptyTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("", "");
        sleep(3000);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }


    @Test
    public void emailIsEmptyTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("", "test12");
        sleep(3000);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }



    @Test
    public void emailWrongTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("asdf", "test12");
        GuestHomePage guestHomePage = new GuestHomePage(webDriver);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.loginErrorTextDisplayed(), "Укажите действительный адрес эл. почты.", "Email Error message is absent");
    }

    @Test
    public void shortEmailTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("as", "test12");
        GuestHomePage guestHomePage = new GuestHomePage(webDriver);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.loginErrorTextDisplayed(), "Слишком короткий текст (минимальная длина – 3 симв., введено – 2 симв.).", "Email Error message is absent");
    }

    @Test
    public void passwordIsWrongTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("vlad.kalinin.qa24@gmail.com", "test12");
        GuestHomePage guestHomePage = new GuestHomePage(webDriver);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.passwordErrorTextDisplayed(), "Укажите действительный адрес эл. почты.", "Password Error message is absent");
    }

    @Test
    public void shortPasswordTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("vlad.kalinin.qa24@gmail.com", "test1");
        GuestHomePage guestHomePage = new GuestHomePage(webDriver);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.passwordErrorTextDisplayed(), "Пароль должен содержать не менее 6 символов.", "Password Error message is absent");
    }
}