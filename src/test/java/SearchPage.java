import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Pattern;

public class SearchPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement searchResultItem;

    @FindBy(xpath = "//*[@class='search-result__wrapper']")
    private WebElement searchResultWrapper;

    @FindBy(xpath = "//*[@class='search-result__wrapper']")
    List<WebElement> listElements;

    public SearchPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isSearchPageItemsAreDisplayed() {
        return searchResultItem.isDisplayed();
    }

    public boolean isSearchPageWrapperAreDisplayed() {
        return searchResultWrapper.isDisplayed();
    }



    public boolean isSearchTermPresent(String searchTerm) {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        for(WebElement element: listElements){
            jse.executeScript("window.scrollBy(0,250)", "");
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
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results/")
                && isSearchPageItemsAreDisplayed()
                && isSearchPageWrapperAreDisplayed();
    }
}
