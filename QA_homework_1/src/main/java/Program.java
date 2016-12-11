import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Igor on 11.12.2016.
 */
public class Program
{
    public static void main(String [] args)
    {
        String property = System.getProperty("user.dir")+"/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",property);


        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // 1)
        chromeDriver.get("https://www.bing.com/");

        // 2)
        By locatorSearchFiend = By.xpath("//input[@class='b_searchbox']");
        WebElement searchField = chromeDriver.findElement(locatorSearchFiend);
        searchField.clear();
        searchField.sendKeys("automation");
        searchField.sendKeys(Keys.ENTER);

        // 3)
        System.out.println("The pages title :");
        System.out.println("    "+chromeDriver.getTitle());

        // 4)


        By locatorSearchResult = By.xpath("//div[@class='b_title']/h2/a");
        List<WebElement> listSearchResults = chromeDriver.findElements(locatorSearchResult);

        System.out.println("The searched titles :");

        for(WebElement buferElement : listSearchResults)
        {
            System.out.println("    " +buferElement.getText());
        }









        chromeDriver.quit();
    }
}
