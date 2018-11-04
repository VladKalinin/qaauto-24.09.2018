import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userLoginField;

    @FindBy(xpath = "//button[@id = 'reset-password-submit-button']")
    private WebElement passwordResetButton;


    public PasswordResetPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPasswordResetPageLoaded() {
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/request-password-reset")
                && userLoginField.isDisplayed()
                && passwordResetButton.isDisplayed();
    }

    public void passwordResetAction(String userName){
        userLoginField.sendKeys(userName);
        passwordResetButton.click();
    }
}
