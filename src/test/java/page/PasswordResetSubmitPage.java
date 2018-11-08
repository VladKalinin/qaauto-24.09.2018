package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetSubmitPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//header[@class = 'content__header']")
    private WebElement bannerPasswordResetSubmitPage;

    @FindBy(xpath = "//button[@id = 'reset-password-submit-button']")
    private WebElement goToHomePage;

    public PasswordResetSubmitPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean passwordResetSuccessfully(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/checkpoint/rp/password-reset-submit")
                && bannerPasswordResetSubmitPage.isDisplayed()
                && goToHomePage.isDisplayed();
    }

    public String bannerTextIsDislayed(){
        return bannerPasswordResetSubmitPage.getText();
    }

    public HomePage goToHomePage(){
        goToHomePage.click();
        return PageFactory.initElements(webDriver, HomePage.class);
    }

}
