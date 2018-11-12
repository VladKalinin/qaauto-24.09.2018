package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedIn HomePage Object class
 */

public class HomePage extends BasePage{

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavigationItem;

    @FindBy(xpath = "/html/body/nav/div/form/div/div/div/artdeco-typeahead-deprecated/artdeco-typeahead-deprecated-input/input")
    private WebElement searctTermField;


    /**
     * LinkedIn LoginPage Object constructor
     * @param webDriver - test instance
     */
    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitUntilElementVisible(profileNavigationItem, 10);
    }

    /**
     * Varify isProfileNavItemDisplayed Method
     * @return - boolean statement if the profileNavigationItem is Displayed or not
     */
    public boolean isProfileNavItemDisplayed(){
        return profileNavigationItem.isDisplayed();
    }

    /**
     * Varify isPageLoaded Method
     * @return - boolean statement if the HomePage is loaded or not
     */
    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && isProfileNavItemDisplayed();
    }

    /**
     * LinkedIn serchTermFieldSearchAction Method
     * @param searchTermText - String for search
     * @return - Expected pageObject: SearchPageObj
     */
    public SearchPageObj serchTermFieldSearchAction(String searchTermText){
        searctTermField.sendKeys(searchTermText);
        searctTermField.sendKeys(Keys.ENTER);
        return PageFactory.initElements(webDriver,SearchPageObj.class);
    }

}