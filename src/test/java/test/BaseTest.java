package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LoginPageObj;

public class BaseTest {
    private WebDriver webDriver;
    LoginPageObj loginPage;

    /**
     * BeforeMethod test Method
     */
    @BeforeMethod
    public void beforeMethod(){
        webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://linkedin.com/");
        loginPage = PageFactory.initElements(webDriver, LoginPageObj.class);
    }

    /**
     * AfterMethod test method
     */
    @AfterMethod
    public void afterMethod(){
        webDriver.quit();
    }



}
