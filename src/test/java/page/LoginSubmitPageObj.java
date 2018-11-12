package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedIn LoginSubmitPage Object class
 */

public class LoginSubmitPageObj {

    private WebDriver webDriver;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement loginErrorDisplayed;

    @FindBy(id = "session_password-login-error")
    private WebElement passwordErrorDisplayed;

    @FindBy(xpath = "//a[@class='nav-link']")
    private WebElement signInLink;

    @FindBy(xpath = "//div[@id='control_gen_1']")
    private WebElement alertErrorDisplayed;

    /**
     * LinkedIn LoginSubmitPage Object constructor
     * @param webDriver - test instance
     */
    public LoginSubmitPageObj(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Varify isErrorTextDisplayed Method
     * @return - boolean statement if the isErrorTextDisplayed is Displayed or not
     */
    public boolean isErrorTextDisplayed(){
        return signInLink.isDisplayed();
    }

    /**
     * Varify isGuestHomePageLoaded Method
     * @return - boolean statement if the isGuestHomePageLoaded is loaded or not
     */
    public boolean isGuestHomePageLoaded(){
        return webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME")
                && webDriver.getTitle().contains("Войти в LinkedIn")
                && isErrorTextDisplayed();
    }

    /**
     * Varify isGuestHomePageLoaded Method
     * @return - alertErrorDisplayed string
     */
    public String alertErrorTextDisplayed(){
        return alertErrorDisplayed.getText();
    }

    /**
     * Varify passwordErrorTextDisplayed Method
     * @return - passwordErrorDisplayed string
     */
    public String passwordErrorTextDisplayed(){
        return passwordErrorDisplayed.getText();
    }

    /**
     * Varify passwordErrorTextDisplayed Method
     * @return - loginErrorDisplayed string
     */
    public String loginErrorTextDisplayed(){
        return loginErrorDisplayed.getText();
    }

}