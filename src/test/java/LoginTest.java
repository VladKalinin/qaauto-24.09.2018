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

    @Test
    public void successfulLoginTest() throws InterruptedException {
        //WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home Page URL is wrong");

        loginPage.login("vlad.kalinin.qa24@gmail.com","vvkalinin20");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "Home Page URL is wrong");

    }

    @Test
    public void negativeLoginTest() throws InterruptedException {
        //WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        WebElement emailField = webDriver.findElement(By.xpath("//input[@class='login-email']"));
        WebElement passwordField = webDriver.findElement(By.xpath("//input[@class='login-password']"));
        WebElement signInButton = webDriver.findElement(By.id("login-submit"));
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home Page URL is wrong");
        //Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up", "Title is wrong"); - lang troubles(EN-RUS)
        Assert.assertEquals(signInButton.isDisplayed(), true, "Sign In button is absent");
        emailField.sendKeys("a@b.c");
        passwordField.sendKeys("");
        signInButton.click();
        sleep(3000);
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home Page URL is wrong");
        //webDriver.quit();
    }
}