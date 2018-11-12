package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

/**
 * LinkedIn SearchPage Object class
 */
public class SearchPageObj {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@class='neptune-grid']")
    private WebElement searchFilterBarItem;

    @FindBy(xpath = "//*[@class='search-result__wrapper']")
    List <WebElement> listElements;

    /**
     * LinkedIn SearchPage Object constructor
     * @param webDriver - test instance
     */
    public SearchPageObj(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Varify isSearchBarFilterItemIsDisplayed Method
     * @return - boolean statement if the searchFilterBarItem is Displayed or not
     */
    public boolean isSearchBarFilterItemIsDisplayed() {
        return searchFilterBarItem.isDisplayed();
    }

    /**
     * Varify searchElementsNumberDisplayed Method
     * @return - length of the listElements
     */
    public int searchElementsNumberDisplayed() {
        return listElements.size();
    }


    /**
     *  Varify isSearchTermPresent Method
     * @param searchTerm - String for search
     * @return - boolean statement if the searchTerm was found or not
     */
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

    /**
     * Varify isSearchPageLoaded Method
     * @return - boolean statement if the isSearchPageLoaded is loaded or not
     */
    public boolean isSearchPageLoaded() {
        JavascriptExecutor jse = (JavascriptExecutor)webDriver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results/")
                && isSearchBarFilterItemIsDisplayed();
    }
}
