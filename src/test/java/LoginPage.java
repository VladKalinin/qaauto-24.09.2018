import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver webDriver;

    //String logInEmail = "vlad.kalinin.qa24@gmail.com";
    //String passwordData = "vvkalinin20";

    WebElement emailField;
    WebElement passwordField;
    WebElement signInButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public void initElements(){
        emailField = webDriver.findElement(By.xpath("//input[@class='login-email']"));
        passwordField = webDriver.findElement(By.xpath("//input[@class='login-password']"));
        signInButton = webDriver.findElement(By.id("login-submit"));
    }

    public void login(String logInEmail, String passwordData){
        emailField.sendKeys(logInEmail);
        passwordField.sendKeys(passwordData);
        signInButton.click();
    }
}
