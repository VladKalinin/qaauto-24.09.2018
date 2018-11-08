package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GuestHomePage{

    private WebDriver webDriver;

    @FindBy(id = "session_password-login-error")
    private WebElement passwordErrorDisplayed;

    @FindBy(xpath = "//a[@class='nav-link']")
    private WebElement signInLink;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement loginErrorDisplayed;

    @FindBy(xpath = "//div[@id='control_gen_1']")
    private WebElement alertErrorDisplayed;

    public GuestHomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public boolean isErrorTextDisplayed(){
        return signInLink.isDisplayed();
    }

    public boolean isGuestHomePageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().contains("Войти в LinkedIn")
                && isErrorTextDisplayed();
    }

    public String alertErrorTextDisplayed(){
        return alertErrorDisplayed.getText();
    }

    public String passwordErrorTextDisplayed(){
        return passwordErrorDisplayed.getText();
    }

    public String loginErrorTextDisplayed(){
        return loginErrorDisplayed.getText();
    }

}