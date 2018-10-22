import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuestHomePage {

    private WebDriver webDriver;

    private WebElement passwordErrorDisplayed;

    private WebElement signInLink;

    private WebElement loginErrorDisplayed;
    private WebElement alertErrorDisplayed;

    public GuestHomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements(){
        signInLink = webDriver.findElement(By.xpath("//a[@class='nav-link']"));
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
        alertErrorDisplayed = webDriver.findElement(By.xpath("//div[@id='control_gen_1']"));
        return alertErrorDisplayed.getText();
    }

    public String passwordErrorTextDisplayed(){
        passwordErrorDisplayed = webDriver.findElement(By.id("session_password-login-error"));
        return passwordErrorDisplayed.getText();
    }

    public String loginErrorTextDisplayed(){
        loginErrorDisplayed = webDriver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        return loginErrorDisplayed.getText();
    }
}