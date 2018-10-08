import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.Wait;

import java.lang.reflect.Array;
import java.util.List;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class BadCodeExample {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Automation UHUUUUUUUU!!!!!!");
        WebDriver webDriver = new FirefoxDriver();
        webDriver.navigate().to("https://www.google.com.ua");

        WebElement searchField = webDriver.findElement(By.name("q"));

        String searchTerm = "Selenium";

        searchField.sendKeys(searchTerm);
        searchField.submit();
        //searchField.sendKeys(Keys.Enter)

        //*[@class='LC20lb'] - my example
        //div[@class='srg']/div[@class='g'] - class example(this example is more secured (ЖПК))


        sleep(3000);
        List<WebElement> searchResults = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));

        System.out.println("Search results count: " + searchResults.size());

        List<WebElement> listElements = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        for(int i =0;i<listElements.size();i++) {
            String elementText = listElements.get(i).getText();
            System.out.println(elementText);

            if (Pattern.compile(searchTerm, Pattern.CASE_INSENSITIVE + Pattern.LITERAL).matcher(elementText).find()){
                System.out.println(" ");
                System.out.println("-->searchTerm " + searchTerm + " was found");}
            else{
                System.out.println(" ");
                System.out.println("-->searchTerm " + searchTerm + " not found");}

            System.out.println(" ");
            System.out.println(" ");
        }

        /*
        for (WebElement searchResult : searchResults) {
             String searchResultText = searchResults.getText;
             System.out.println(searchResult.getText());
             if(searchResultText.toLowerCase().contains(searchTerm)){
                System.out.println(" ");
                System.out.println("-->searchTerm " + searchTerm + " was found");
             }
             else {
                System.out.println(" ");
                System.out.println("-->searchTerm " + searchTerm + " not found");}
             }

        }
        */

    }

}