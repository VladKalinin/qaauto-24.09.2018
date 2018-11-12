package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

/**
 * LinkedIn PasswordResetPage Object class
 */
public class PasswordResetPageObj extends BasePage {
    private WebDriver webDriver;

    String passwordChangeLink;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userLoginField;

    @FindBy(xpath = "//button[@id = 'reset-password-submit-button']")
    private WebElement passwordResetButton;

    /**
     * LinkedIn LoginPage Object constructor
     * @param webDriver - test instance
     */
    public PasswordResetPageObj(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitUntilElementVisible(passwordResetButton, 10);
    }

    /**
     * Verify isPasswordResetPageLoaded Method
     * @return - boolean statement if the PasswordResetPageLoaded is loaded or not
     */
    public boolean isPasswordResetPageLoaded() {
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/request-password-reset")
                && userLoginField.isDisplayed()
                && passwordResetButton.isDisplayed();
    }

    /**
     * LinkedIn passwordResetAction Method
     * @param userName - String userEmailPhone (login data)
     * @return - expected page: PasswordResetCheckpointPage
     *
     * FYI: in this method we analyse the if there is passwordResetLink was sent to mail or not
     */
    public PasswordResetCheckpointPageObj passwordResetAction(String userName){

        GMailService gMailService = new GMailService();
        gMailService.connect();
        userLoginField.sendKeys(userName);
        passwordResetButton.click();

        String messageSubject = "vlad, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "vlad.kalinin.qa24@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println(message);

        String passwordChangelink = StringUtils.substringBetween(message, "Чтобы изменить пароль в LinkedIn, нажмите <a href=\"", ">здесь</a>");
        passwordChangelink.replace("amp;","");

        return PageFactory.initElements(webDriver, PasswordResetCheckpointPageObj.class);
    }

    /**
     * LinkedIn FromEmailPasswordResetPage Method
     * @return - expected page: FromEmailPasswordResetPage
     */
    public FromEmailPasswordResetPageObj navigateToLink(){
        webDriver.navigate().to(passwordChangeLink);
        return PageFactory.initElements(webDriver, FromEmailPasswordResetPageObj.class);
    }
}
