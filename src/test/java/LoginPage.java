import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LoginPage{

    //String logInEmail = "vlad.kalinin.qa24@gmail.com";
    //String passwordData = "vvkalinin20";

    private WebDriver webDriver;

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    public LoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

/*
    public HomePage loginHomePage(String userName, String userPassword){
        emailField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new HomePage(webDriver);
    }

    public LoginPage loginLoginPage(String userName, String userPassword){
        emailField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new LoginPage(webDriver);
    }

    public GuestHomePage loginGuestHomePage(String userName, String userPassword){
        emailField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return new GuestHomePage(webDriver);
    }
*/


    public <T> T login(String userName, String userPassword, Class<T> expectedPage){
        emailField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        signInButton.click();
        return PageFactory.initElements(webDriver, expectedPage);
    }

/*
    public <T> T login(String userName, String userPassword){
        emailField.sendKeys(userName);
        passwordField.sendKeys(userPassword);
        signInButton.click();


        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (webDriver.getCurrentUrl().contains("/feed")){
            return (T) new HomePage(webDriver);
        }
        if (webDriver.getCurrentUrl().contains("=GUEST_HOME")){
            return (T) new GuestHomePage(webDriver);
        }else {
            return (T) new LoginPage(webDriver);
        }
    }
*/

    public boolean isPageLoaded(){
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Войти или зарегистрироваться")
                && isSignInButtonDisplayed();
    }


    public boolean isSignInButtonDisplayed(){
        return signInButton.isDisplayed();
    }

}