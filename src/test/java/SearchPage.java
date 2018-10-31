import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement searchResultItem;

    @FindBy(xpath = "//*[@class='search-result__wrapper']")
    private WebElement searchResultWrapper;

    public SearchPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isSearchPageItemsAreDisplayed(){
        return searchResultItem.isDisplayed();
    }
    public boolean isSearchPageWrapperAreDisplayed(){
        return searchResultWrapper.isDisplayed();
    }

    public boolean isSearchPageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results/")
                && isSearchPageItemsAreDisplayed()
                && isSearchPageWrapperAreDisplayed();
    }

}
