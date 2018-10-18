import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {

    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
    }

    @AfterMethod
    public void afterMethod() {
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

    @Test(priority = 3)
    public void successfulLoginTest() throws InterruptedException {
        //WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home Page URL is wrong");

        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Войти или зарегистрироваться", "Login page title is not correct");
        Assert.assertTrue(loginPage.signInButton.isDisplayed(), "SignIn button is not displayed on Login Page");

        loginPage.login("vlad.kalinin.qa24@gmail.com","vvkalinin20");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Home Page URL is wrong");
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn", "Home page title is wrong");
        //li[@id='profile-nav-item']

    }

    @Test(priority = 1)
    public void negativeEmailPasswordTest() throws InterruptedException {
        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home Page URL is wrong");

        loginPage.login("a@b.c","");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Page URL is wrong");
    }

    @Test(priority = 2)
    public void negativePasswordTest() throws InterruptedException {
        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home Page URL is wrong");

        loginPage.login("vlad.kalinin.qa24@gmail.com","Test123!");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME", "Login Submit Page URL is wrong");
    }
}