package page;

import org.openqa.selenium.TimeoutException;
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

    public void waitUntilElementVisible(WebElement webElement, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected boolean isUrlContains(String partialUrl, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void verifyElementIsVisible(WebElement webElement, int timeOutInSec, String message) {
        try {
            waitUntilElementVisible(webElement, timeOutInSec);
        } catch (TimeoutException e) {
            throw new AssertionError(message);
        }

    }
}
