import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckLinkWasSentPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//header[@class = 'content__header']")
    private WebElement messageWasSend;

    @FindBy(xpath = "//button[@class = 'resend__link']")
    private WebElement resendLinkButton;

    public CheckLinkWasSentPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPasswordResetLinkWasSend() {
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/request-password-reset")
                && messageWasSend.isDisplayed()
                && resendLinkButton.isDisplayed();

    }

    public String bannerConfirmedTextIsDislayed(){
        return messageWasSend.getText();
    }
}
