package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchPageObj;

public class SearchTest extends BaseTest {

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
        SearchPageObj searchPageObj = homePage.serchTermFieldSearchAction(searchTerm);
        Assert.assertTrue(searchPageObj.isSearchPageLoaded(), "Search Page is not loaded");
        Assert.assertEquals(searchPageObj.searchElementsNumberDisplayed(), 10, "More or less the 10 results");
        Assert.assertTrue(searchPageObj.isSearchTermPresent(searchTerm), "Search Term does not present");

    }
}
