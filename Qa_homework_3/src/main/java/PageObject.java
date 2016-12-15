import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Created by Igor on 14.12.2016.
 */
public  class PageObject
{

    //--------------- Constructor -----------------//

    public PageObject(WebDriver externalDriver)
    {
        this.driver = externalDriver;
    }

    //--------------- Constants and Variables -------------------//

    private WebDriver driver ;
    final String STARTING_URL = "http://www.bing.com/";
    final String EXPRESSION_TO_SEARCH = "automatio";
    final String FULL_EXPRESSION_TO_SEARCH = "automation";

    //--------------- WebElements -----------------//

    public WebElement imageButton ()
    {
        return driver.findElement(By.id("scpl1"));
    }

    public List<WebElement> imageList ()
    {
        By locatorLoadingImage = By.cssSelector("div>img[alt^=Рекомендуемое]");
        return driver.findElements(locatorLoadingImage);
    }

    public WebElement searchField()
    {
        By locatorSearcField = By.className("b_searchbox");
        return driver.findElement(locatorSearcField);

    }

    public List<WebElement> fullWordsSearch()
    {
        By selectorFullWordSearch = By.cssSelector("ul.sa_drw>li");
        return driver.findElements(selectorFullWordSearch);
    }

    public WebElement innerElementForSearchFullWord (WebElement buferElement)
    {
        return buferElement.findElement(By.cssSelector("li>div"));
    }

    public WebElement inLastMonthFilter()
    {
        By locatorDateFilter = By.xpath("//div[@class='ftrD']/a[@title='В прошлом месяце']");
        return driver.findElement(locatorDateFilter);
    }

    public WebElement date()
    {
        By locatorDate = By.xpath("//ul[@class='ftrUl']/li[6]");
        return driver.findElement(locatorDate);
    }

    public WebElement frameElement()
    {
        By locatorFrame = By.xpath(".//*[@id='OverlayIFrame']");
        return driver.findElement(locatorFrame);
    }


    public WebElement firstImageAfterFilterDate()
    {
        By firstImageLocator = By.xpath("//div[@class='imgres']/div[@class='dg_u']");
        return driver.findElement(firstImageLocator);
    }

    public WebElement loadingMainImageIndicator()
    {
        By locatorLoadingMainImage = By.xpath(".//span[@id='copy']");
        return driver.findElement(locatorLoadingMainImage);
    }

    public WebElement slideShowNextImage()
    {
        By locatorNextImageButton = By.xpath("//div[@class='iol_grp']/a[2]");
        return driver.findElement(locatorNextImageButton);
    }

    public WebElement slideShowPrevImage()
    {
        By locatorPreviousImageButton = By.xpath("//div[@class='iol_grp']/a[1]");
        return driver.findElement(locatorPreviousImageButton);
    }

    public WebElement mainImageOnSlideshow()
    {
        By locatorMainImage = By.xpath(".//*[@id='iol_imw']/div[1]/span/span/img[@title='Просмотр исходного изображения']");
        return driver.findElement(locatorMainImage);
    }


}
