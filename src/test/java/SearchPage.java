import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class SearchPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement searchResultItem;

    @FindBy(xpath = "//div[@class='neptune-grid']")
    private WebElement searchBarItem;

    @FindBy(xpath = "//*[@class='search-result__wrapper']")
    //@FindBy(xpath = "//ul[contains(@class, 'search-results__list')]/li[contains(@class, 'occluded')]")
    List <WebElement> listElements;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isSearchPageItemsAreDisplayed() {
        return searchResultItem.isDisplayed();
    }

    public boolean isSearchBarFilterItemIsDisplayed() {
        return searchBarItem.isDisplayed();
    }

    public int searchElementsNumberDisplayed() {
        return listElements.size();
    }



    public boolean isSearchTermPresent(String searchTerm) {
        for(WebElement element: listElements){
            String elementText = element.getText();
            System.out.println(elementText);
            System.out.println();
            if(!elementText.toLowerCase().contains(searchTerm.toLowerCase())){
                System.out.println(searchTerm + " was not found");
                System.out.println();
                return false;
            }
        }
        return true;
    }

    public boolean isSearchPageLoaded() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results/")
                && isSearchPageItemsAreDisplayed()
                && isSearchBarFilterItemIsDisplayed();
    }
}
