package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    /**
     * testAccount data:
     * logInEmail = "vlad.kalinin.qa24@gmail.com";
     * passwordData = "Test123@";
     */

    WebDriver webDriver;

    public void waitUntilElementsClicable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
}
