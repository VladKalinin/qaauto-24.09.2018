import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage{

    private WebDriver webDriver;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;

    @FindBy(xpath = "/html/body/nav/div/form/div/div/div/artdeco-typeahead-deprecated/artdeco-typeahead-deprecated-input/input")
    private WebElement searctTermField;



    public HomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isProfileNavItemDisplayed(){
        return profileNavItem.isDisplayed();
    }

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && isProfileNavItemDisplayed();
    }

    public <T> T serchTermFieldSearchAction(String searchTermText, Class<T> expectedPage){
        searctTermField.sendKeys(searchTermText);
        searctTermField.sendKeys(Keys.ENTER);
        return PageFactory.initElements(webDriver, expectedPage);
    }

}