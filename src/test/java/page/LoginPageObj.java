package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedIn LoginPage Object class
 */

public class LoginPageObj extends BasePage{

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement loginField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement passwordResetButton;

    /**
     * LinkedIn LoginPage Object constructor
     * @param webDriver - test instance
     */
    public LoginPageObj(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * LinkedIn login Method
     * @param userName - Login String;
     * @param userPassword - Password String;
     * @param expectedPage - Expected class;
     * @param <T> - Generic Type to return different pageObject`s;
     * @return - On of expected pageObject`s : LoginPageObj/ HomePageObj/ LoginSubmitPageObj
     */
    public <T> T login(String userName, String userPassword, Class<T> expectedPage){
        loginField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        if (isUrlContains("/feed", 5)) {
            return (T) new HomePage(webDriver);
        }
        if (isUrlContains("/login-submit", 5)) {
            return (T) new LoginSubmitPageObj(webDriver);
        }
        else {
            return (T) this;
        }
    }


    /**
     * LinkedIn forgetPassword Method
     * @return - returns expected pageObject(PasswordResetPageObj)
     */
    public PasswordResetPageObj forgetPassword(){
        passwordResetButton.click();
        return PageFactory.initElements(webDriver, PasswordResetPageObj.class);
    }

    /**
     * Verify isPageLoaded Method
     * @return - boolean statement if the LoginPage is loaded or not
     */
    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && isSignInButtonDisplayed()
                && passwordResetButton.isDisplayed();
    }

    /**
     * Verify isSignInButtonDisplayed Method
     * @return - boolean statement if the signInButton is Displayed or not
     */
    public boolean isSignInButtonDisplayed(){
        return signInButton.isDisplayed();
    }

}