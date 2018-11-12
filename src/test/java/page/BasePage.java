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

    /**
     * LinkedIn waitUntilElementVisible Method
     * @param webElement - test instance
     * @param timeOutInSec - time for element upload
     */
    public void waitUntilElementVisible(WebElement webElement, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }


    /**
     * LinkedIn isUrlContains Method
     * @param partialUrl - part of url
     * @param timeOutInSec - time for element upload
     * @return - return true/false
     */
    protected boolean isUrlContains(String partialUrl, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSec);
        try {
            return wait.until(ExpectedConditions.urlContains(partialUrl));
        } catch (TimeoutException e) {
            return false;
        }
    }
}
