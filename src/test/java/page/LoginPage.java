package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage{

    //String logInEmail = "vlad.kalinin.qa24@gmail.com";
    //String passwordData = "Test123@";

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement passwordRestButton;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public <T> T login(String userName, String userPassword, Class<T> expectedPage){
        emailField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return PageFactory.initElements(webDriver, expectedPage);
    }

    public PasswordResetPage forgetPassword(){
        passwordRestButton.click();
        return PageFactory.initElements(webDriver, PasswordResetPage.class);
    }



    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && isSignInButtonDisplayed()
                && passwordRestButton.isDisplayed();
    }


    public boolean isSignInButtonDisplayed(){
        return signInButton.isDisplayed();
    }

}