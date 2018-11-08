package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordResetPage {
    private WebDriver webDriver;

    String passwordChangeLink;

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

    public CheckLinkWasSentPage passwordResetAction(String userName){

        GMailService gMailService = new GMailService();
        gMailService.connect();
        userLoginField.sendKeys(userName);
        passwordResetButton.click();

        String messageSubject = "vlad, данное сообщение содержит ссылку для изменения пароля";
        String messageTo = "vlad.kalinin.qa24@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";
        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 60);
        System.out.println(message);

        //ToDo: String bar = StringUtils.substringBetween(message, "****", "***"); //Why it does not work
        //String passwordChangelink = StringUtils.substringBetween(message, "****", "***");
        //passwordChangelink.replace("amp;","");

        Pattern stringLocatorElements = Pattern.compile("href=\"(.*?)\"");
        Matcher sourceString = stringLocatorElements.matcher(message);
        String passwordChangeUrl = "";
        for(int i = 0; i < 3; i++) {
            if (sourceString.find()) {
                 passwordChangeUrl= sourceString.group(1).replace("amp;","");
            }
        }

        passwordChangeLink = passwordChangeUrl;
        System.out.println(passwordChangeLink);

        return PageFactory.initElements(webDriver, CheckLinkWasSentPage.class);
    }

    public FromEmailPasswordResetPage navigateToLink(){
        webDriver.navigate().to(passwordChangeLink);
        return PageFactory.initElements(webDriver, FromEmailPasswordResetPage.class);
    }
}
