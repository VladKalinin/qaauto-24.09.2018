package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * LinkedIn PasswordResetCheckpointPage Object class
 */
public class PasswordResetCheckpointPageObj {
    private WebDriver webDriver;

    @FindBy(xpath = "//header[@class = 'content__header']")
    private WebElement messageWasSend;

    @FindBy(xpath = "//button[@class = 'resend__link']")
    private WebElement resendLinkButton;
    /**
     * LinkedIn PasswordResetCheckpointPage Object constructor
     * @param webDriver - test instance
     */
    public PasswordResetCheckpointPageObj(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Verify isPasswordResetPageLoaded Method
     * @return - string messageWasSend
     */
    public String bannerConfirmedTextIsDislayed(){
        return messageWasSend.getText();
    }

    /**
     * Verify isPasswordResetPageLoaded Method
     * @return - boolean statement if the PasswordResetPageLoaded is loaded or not
     */
    public boolean isPasswordResetLinkWasSend() {
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint")
                && messageWasSend.isDisplayed()
                && resendLinkButton.isDisplayed()
                ;

    }
}
