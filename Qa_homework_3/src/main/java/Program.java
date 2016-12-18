
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;


/**
 * Created by Igor on 07.12.2016.
 */
public class Program
{

    public static void main (String [] args)
    {


        //---------------------- Get Path To The Driver ----------------//

        String property = System.getProperty("user.dir")+"/driver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver" , property);


        //---------------------- Global Options ------------------------//

        WebDriver chromeDriver = new ChromeDriver(); //create webdriver
        PageObject pObj = new PageObject(chromeDriver); //create class with property
        Actions actions = new Actions(chromeDriver); //create actions
        JavascriptExecutor jse = ((JavascriptExecutor)chromeDriver);//create Java Executor
        WebDriverWait explicitWait = new WebDriverWait(chromeDriver , 10); //create WebDriwerWait object
        chromeDriver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); //global wait
        chromeDriver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS); //wait Java Script execute


        //---------------------------  1  ----------------------------------//

        chromeDriver.get(pObj.STARTING_URL); //download starting page
        Waits.waitLoadPage(chromeDriver); //wait the pages download


        //---------------------------  2  ----------------------------------//
        WebElement imageButton = pObj.imageButton();
        imageButton.click();                  //click the "Изображения" button

        //wait until the pages title will be that in case
        explicitWait.until(ExpectedConditions.titleIs("Лента изображений Bing"));

        //---------------------------  3  -----------------------------------//

        //array with loading images
        List<WebElement> prevScrollList = pObj.imageList();

        //scroll down page 4 times
        for(int i = 0 ; i <4; i++)
        {

            int prevScrollSize = prevScrollList.size() ;
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            explicitWait.until(visibilityOf(prevScrollList.get(prevScrollSize-1)));

            //wait for the last element who was downloaded after scroll
            explicitWait.until(presenceOfElementLocated(pObj.locatorImageListElement(prevScrollSize)));

            List<WebElement> afterScrollList = pObj.imageList();
            int afterScrollSize =  afterScrollList.size();
            if(afterScrollSize > prevScrollSize)
            {
                System.out.println("New images was downloaded");
            }

        }

       //----------------------------  4  ------------------------------//

        //scroll to the page top
        jse.executeScript("window.scrollTo(0,0)");

        //find search field
        WebElement searchField = pObj.searchField();

        //click on search field (activate)
        actions.click(searchField).build().perform();

        //to input the search word
        searchField.sendKeys(pObj.EXPRESSION_TO_SEARCH);

        //take the result array with word combinations
        List<WebElement> fullWordsList = pObj.fullWordsSearch();

        //find full expression
        for(WebElement buferElement : fullWordsList)
        {
            WebElement innerElement = pObj.innerElementForSearchFullWord(buferElement);
            if(innerElement.getText().equals(pObj.FULL_EXPRESSION_TO_SEARCH))
            {
                // click on detected full expression
                actions.click(buferElement).build().perform();

                // wait page
                Waits.waitLoadPage(chromeDriver);
                break;
            }
        }

        //--------------------------  5  ---------------------------------//

        //find drop-down list "Дата"
        WebElement date = pObj.date();

        //find necessary element in list
        WebElement inLastMonthFilter =pObj.inLastMonthFilter();

        //click the "Дата" button until the "В прошлом месяце" paragraph
        //will be visible
        while(!inLastMonthFilter.isDisplayed())
        {
            actions.click(date).build().perform();
        }

        //click the "В прошлом месяце" paragraph
        actions.click(inLastMonthFilter).build().perform();


        //--------------------------  6  ----------------------------------//

        //find necessary frame
        WebElement frameElement = pObj.frameElement();

        //find first image on the page
        WebElement firstImageAfterFilterDate = pObj.firstImageAfterFilterDate();

        //click on it
        actions.click(firstImageAfterFilterDate).build().perform();

        //--------------------------  7  ----------------------------------//

        //switch frame
        chromeDriver.switchTo().frame(frameElement);

        //wait page
        Waits.waitLoadPage(chromeDriver);

        //find button "i" (copyright) it visible only after main image was downloaded
        WebElement loadingMainImageIndicator = pObj.loadingMainImageIndicator();

        //wait until it will be visible
        explicitWait.until(ExpectedConditions.visibilityOf(loadingMainImageIndicator));



        // find next image of slide show
        WebElement slideShowNextImage = pObj.slideShowNextImage();

        // take its x coordinate
        int coord = slideShowNextImage.getLocation().x;

        // click on element
        actions.click(slideShowNextImage).build().perform();

        // wait until the slide show will scrolled
        Waits.waitForSlideShowNext(chromeDriver,slideShowNextImage,coord);

        //find previously image of slide show
        WebElement previousImage = pObj.slideShowPrevImage();

        //click on it
        actions.click(previousImage).build().perform();

        //wait download  the main image
        explicitWait.until(ExpectedConditions.visibilityOf(loadingMainImageIndicator));

        //----------------------------  8 -------------------------------//

        //find the main image
        WebElement mainImage = pObj.mainImageOnSlideshow();

        //click on it
        actions.click(mainImage).build().perform();

        //check that image was downloaded in the new window
        Set<String> handles = chromeDriver.getWindowHandles();
        if(handles.size()>1)
        {
            System.out.println("The image was downloaded in the new window");
        }




        //close webdriver
        chromeDriver.quit();
    }
}
