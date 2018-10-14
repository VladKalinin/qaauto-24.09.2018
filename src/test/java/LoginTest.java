import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {

    /**
     *Proconditions:
     * - Open Firefox browser.
     *
     * Scenario:
     * - Navigate to https://www.linkedin.com/ - done
     * - Varify that login page is load - done
     * - Varify that there is Login field
     * - Varify that there is Password field
     * - Varify that there is Sign In button
     *
     * - Enter userEmail to 'user email' field
     * - Enter userPassword to 'user password' field
     * - Click on signIn button.
     * - Varify that Home page is load
     *
     * PostConditions:
     * - Close Firefox
     * 
     */



    @Test
    public void successfulLoginTest () throws InterruptedException {

        String logInEmail = "vlad.kalinin.qa24@gmail.com";
        String passwordData = "vvkalinin20";
        String webIdLogIn = "login-email";
        String webIdPassword = "login-password";
        String signInButtonId = "login-submit";

        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");

        // need if statement
        //Assert.assertEquals("actual", "expected", "error message");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/", "Home page URL is wrong");
        Assert.assertEquals(webIdLogIn, webDriver.findElement(By.id(webIdLogIn)).getAttribute("id"));
        Assert.assertEquals(webIdPassword, webDriver.findElement(By.id(webIdPassword)).getAttribute("id"));
        Assert.assertEquals(signInButtonId, webDriver.findElement(By.id(signInButtonId)).getAttribute("id"));

        webDriver.findElement(By.id(webIdLogIn)).clear();
        webDriver.findElement(By.id(webIdLogIn)).sendKeys(logInEmail);
        webDriver.findElement(By.id(webIdPassword)).clear();
        webDriver.findElement(By.id(webIdPassword)).sendKeys(passwordData);
        webDriver.findElement(By.id(signInButtonId)).click();
        sleep(3000);
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/", "URL is wrong: log in is not successful");

        webDriver.quit();



    }
}
