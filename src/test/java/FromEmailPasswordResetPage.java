import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FromEmailPasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@id = 'newPassword']")
    private WebElement inputNewPasswordField;

    @FindBy(xpath = "//input[@id = 'confirmPassword']")
    private WebElement inputConfirmNewPasswordField;

    @FindBy(xpath = "//button[@id = 'reset-password-submit-button']")
    private WebElement passwordResetButton;

    public FromEmailPasswordResetPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isAfterPasswordResetPageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/password-reset")
                && inputNewPasswordField.isDisplayed()
                && inputConfirmNewPasswordField.isDisplayed()
                && passwordResetButton.isDisplayed();
    }



    public <T> T passwordResetActionNewPage(String newPassword,String confirmNewPassword, Class<T> expectedPage){
        inputNewPasswordField.sendKeys(newPassword);
        inputConfirmNewPasswordField.sendKeys(confirmNewPassword);
        passwordResetButton.click();
        return PageFactory.initElements(webDriver, expectedPage);
    }
}
