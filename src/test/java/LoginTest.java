import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "vlad.kalinin.qa24@gmail.com", "vvkalinin20" },
                { "vlad.kalinin.QA24@gmail.com", "vvkalinin20" },
                { "vlad.kalinin.qa24@gmail.com ", "vvkalinin20" }
        };
    }

    @DataProvider
    public Object[][] emptyPageDataprovider() {
        return new Object[][]{
                { "", "vvkalinin20" },
                { "vlad.kalinin.QA24@gmail.com", "" },
                { "", "" },
                {"as2sd", ""},
                {"", "asc sdb123r"},
                {"a2B@c", ""}
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
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        HomePage homePage = loginPage.login(userEmail,userPasswrod,HomePage.class);
        sleep(3000);
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");
    }

    @Test(dataProvider = "emptyPageDataprovider")
    public void emptyLoginPasswordFieldsTest(String userEmail, String userPasswrod) throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login(userEmail, userPasswrod,LoginPage.class);
        sleep(3000);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    @Test(dataProvider ="wrongGuestHomeDataProvider")
    public void wrongDataTest(String userEmail, String userPasswrod, String errorLoginMessage,String errorPasswordMessage) throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        GuestHomePage guestHomePage = loginPage.login(userEmail, userPasswrod,GuestHomePage.class);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.loginErrorTextDisplayed(), errorLoginMessage, "Email Error message is absent");
        Assert.assertEquals(guestHomePage.passwordErrorTextDisplayed(), errorPasswordMessage, "Password Error message is absent");
    }

    /*
    @Test
    public void passwordFieldIsEmptyTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("vlad.kalinin.qa24@gmail.com", "",LoginPage.class);
        sleep(3000);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    @Test
    public void allFieldsEmptyTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("", "",LoginPage.class);
        sleep(3000);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    @Test
    public void emailIsEmptyTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        loginPage.login("", "test12",LoginPage.class);
        sleep(3000);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
    }

    --------------------------------------

    @Test
    public void emailWrongTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        GuestHomePage guestHomePage = loginPage.login("asdf", "test12",GuestHomePage.class);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.loginErrorTextDisplayed(), "Укажите действительный адрес эл. почты.", "Email Error message is absent");
    }

    @Test
    public void shortEmailTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        GuestHomePage guestHomePage = loginPage.login("as", "test12",GuestHomePage.class);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.loginErrorTextDisplayed(), "Слишком короткий текст (минимальная длина – 3 симв., введено – 2 симв.).", "Email Error message is absent");
    }

    @Test
    public void passwordIsWrongTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        GuestHomePage guestHomePage = loginPage.login("vlad.kalinin.qa24@gmail.com", "test12",GuestHomePage.class);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.passwordErrorTextDisplayed(), "Укажите действительный адрес эл. почты.", "Password Error message is absent");
    }

    @Test
    public void shortPasswordTest() throws InterruptedException {
        webDriver.navigate().to("https://linkedin.com/");
        LoginPage loginPage = PageFactory.initElements(webDriver, LoginPage.class);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        GuestHomePage guestHomePage = loginPage.login("vlad.kalinin.qa24@gmail.com", "test1",GuestHomePage.class);
        sleep(3000);
        Assert.assertTrue(guestHomePage.isGuestHomePageLoaded(), "GuestHome is not loaded");
        Assert.assertEquals(guestHomePage.alertErrorTextDisplayed(), "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.", "Alert Error message is absent");
        Assert.assertEquals(guestHomePage.passwordErrorTextDisplayed(), "Пароль должен содержать не менее 6 символов.", "Password Error message is absent");
    }
    */
}