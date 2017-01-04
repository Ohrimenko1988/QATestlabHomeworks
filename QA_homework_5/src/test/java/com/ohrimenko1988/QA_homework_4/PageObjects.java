package com.ohrimenko1988.QA_homework_4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by Igor on 22.12.2016.
 */
public class PageObjects
{

    PageObjects(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }



    @FindBy(xpath = "//div[@class='hp_sw_logo hpcLogoWhite']")
    WebElement logoBing;

    @FindBy(xpath = "//input[@class='b_searchbox']")
    WebElement searchField;

    @FindBy(xpath = "//input[@class='b_searchboxSubmit']")
    WebElement searchButton;

    //@FindBy(xpath = "//ul/li[@role='option']/div/strong[text()='n']")
    //List<WebElement> searchFieldDropDownList;

    @FindBy(xpath = "//li[@class='b_algo']/div/h2/a[1]")
    WebElement titleRezultSerch;

    public WebElement fullExpression(WebDriver driver , String part)
    {
        return driver.findElement(By.xpath("//ul/li[@role='option']/div/strong[text()='"+part+"']"));
    }




}
