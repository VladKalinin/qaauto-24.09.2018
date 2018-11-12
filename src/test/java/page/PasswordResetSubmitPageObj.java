package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * LinkedIn PasswordResetSubmitPage Object class
 */
public class PasswordResetSubmitPageObj {
    private WebDriver webDriver;

    @FindBy(xpath = "//header[@class = 'content__header']")
    private WebElement bannerPasswordResetSubmitPage;

    @FindBy(xpath = "//button[@id = 'reset-password-submit-button']")
    private WebElement goToHomePage;

    /**
     * LinkedIn PasswordResetCheckpointPage Object constructor
     * @param webDriver - test instance
     */
    public PasswordResetSubmitPageObj(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Verify isPasswordResetPageLoaded Method
     * @return - boolean statement if the passwordResetSuccessfully is loaded or not
     */
    public boolean passwordResetSuccessfully(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/password-reset-submit")
                && bannerPasswordResetSubmitPage.isDisplayed()
                && goToHomePage.isDisplayed();
    }

    /**
     * Verify bannerTextIsDislayed Method
     * @return - String bannerPasswordResetSubmitPage
     */
    public String bannerTextIsDislayed(){
        return bannerPasswordResetSubmitPage.getText();
    }

    /**
     * LinkedIn goToHomePage method
     * @return - expected page: goToHomePage
     */
    public HomePage goToHomePage(){
        goToHomePage.click();
        return PageFactory.initElements(webDriver, HomePage.class);
    }

}
