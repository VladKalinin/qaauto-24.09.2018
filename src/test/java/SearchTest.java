import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
     *
     *
     * - Enter 'searchTerm' into search field and press RETURN key
     * - Verify that Search page is loaded
     * - Verify 10 searchResults displayed on page
     * - Verify each result item contains 'searchTerm'
     */

    @Test
    public void basicSearchTest() throws InterruptedException {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login Page is not loaded");
        HomePage homePage = loginPage.login("vlad.kalinin.qa24@gmail.com","vvkalinin20",HomePage.class);
        sleep(3000);
        Assert.assertTrue(homePage.isPageLoaded(), "Home Page is not loaded");

        SearchPage searchPage = homePage.serchTermFieldSearchAction("HR", SearchPage.class);
        sleep(3000);
        Assert.assertTrue(searchPage.isSearchPageLoaded(), "Search Page is not loaded");

        String searchText = "HR";
        List<WebElement> listElements = webDriver.findElements(By.xpath("//*[@class='search-result__wrapper']"));
        for (int i = 0; i < listElements.size(); i++) {
            String elementText = listElements.get(i).getText();
            System.out.println(elementText);

            if (Pattern.compile(searchText, Pattern.CASE_INSENSITIVE + Pattern.LITERAL).matcher(elementText).find()) {
                System.out.println(" ");
                System.out.println("-->searchTerm " + searchText + " was found");
            } else {
                System.out.println(" ");
                System.out.println("-->searchTerm " + searchText + " not found");
            }

            System.out.println(" ");
            System.out.println(" ");
        }

    }
}
