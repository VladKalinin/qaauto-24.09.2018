import org.testng.annotations.Test;

public class LoginTest {

    /**
     *Proconditions:
     * - Open Firefox browser.
     *
     * Scenario:
     * - Navigate to https://www.linkedin.com/
     * - Varify that login page is load
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
    public void successfulLoginTest (){
        WebDriver webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        //Assert.assertEquals("actual", "expected", "error message");
        Assert.assertEquals(webDriver.getCurrentUrl, "https://www.linkedin.com/", "Home page URL is wrong");


    }
}
