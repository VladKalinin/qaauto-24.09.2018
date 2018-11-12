package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedIn FromEmailPasswordResetPage Object class
 */
public class FromEmailPasswordResetPageObj {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@id = 'newPassword']")
    private WebElement inputNewPasswordField;

    @FindBy(xpath = "//input[@id = 'confirmPassword']")
    private WebElement inputConfirmNewPasswordField;

    @FindBy(xpath = "//button[@id = 'reset-password-submit-button']")
    private WebElement passwordResetButton;

    /**
     * LinkedIn LoginPage Object constructor
     * @param webDriver - test instance
     */
    public FromEmailPasswordResetPageObj(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Verify isAfterPasswordResetPageLoaded Method
     * @return - boolean statement if the isAfterPasswordResetPageLoaded is loaded or not
     */
    public boolean isAfterPasswordResetPageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/password-reset")
                && inputNewPasswordField.isDisplayed()
                && inputConfirmNewPasswordField.isDisplayed()
                && passwordResetButton.isDisplayed();
    }

    /**
     * LinkedIn passwordResetActionNewPage Method
     * @param newPassword - String with newPassword
     * @param confirmNewPassword - String with confirmNewPassword
     * @return - expected page: PasswordResetSubmitPage
     */
    public PasswordResetSubmitPageObj passwordResetActionNewPage(String newPassword, String confirmNewPassword){
        inputNewPasswordField.sendKeys(newPassword);
        inputConfirmNewPasswordField.sendKeys(confirmNewPassword);
        passwordResetButton.click();
        return PageFactory.initElements(webDriver, PasswordResetSubmitPageObj.class);
    }
}
