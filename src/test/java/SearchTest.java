import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class SearchTest {

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
     * PreConditions:
     * - Open new Browser
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that Login page is loaded
     * - Login with valid credentials
     * - Verify that Home page is loaded
     * - Enter 'searchTerm' into search field and press RETURN key
     * - Verify that Search page is loaded
     * - Verify 10 searchResults displayed on page
     * - Verify each result item contains 'searchTerm'
     */

    @Test
    public void basicSearchTest() throws InterruptedException {

        String searchTerm = "HR";

        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        HomePage homePage = loginPage.login("vlad.kalinin.qa24@gmail.com","vvkalinin20",HomePage.class);
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");
        SearchPage searchPage = homePage.serchTermFieldSearchAction(searchTerm, SearchPage.class);
        sleep(3000);
        Assert.assertTrue(searchPage.isSearchPageLoaded(), "Search Page is not loaded");
        Assert.assertEquals(searchPage.searchElementsNumberDisplayed(), 10, "More or less the 10 results");
        Assert.assertTrue(searchPage.isSearchTermPresent(searchTerm), "Search Term does not present");

    }
}
