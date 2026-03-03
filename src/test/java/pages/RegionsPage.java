package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class RegionsPage {

    WebDriver driver = Driver.getDriver();

    public RegionsPage() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//span[contains(text(),'Regions')]")
    public WebElement regionsMenuItem;


    @FindBy(xpath = "//button[contains(., 'Create Region')]")
    public WebElement createRegionBtn;

    @FindBy(xpath = "//input[@id='mui-2']")
    public WebElement regionNameInput;

    @FindBy(xpath = "//button[@type='submit' and contains(.,'Create')]")
    public WebElement confirmCreateBtn;

    @FindBy(xpath = "//button[@type='button' and contains(text(),'Cancel')]")
    public WebElement cancelCreateBtn;


    @FindBy(xpath = "//div[@class='sc-cQMzAB eJBxEJ']//../button")
    public WebElement actionsMenuBtn;

    @FindBy(xpath = "//li[text()='Edit']")
    public WebElement editMenuItem;

    @FindBy(xpath = "//li[text()='Delete']")
    public WebElement deleteMenuItem;


    @FindBy(id = "mui-2")
    public WebElement editRegionNameInput;

    @FindBy(xpath = "//button[text()='Save']")
    public WebElement saveBtn;

    @FindBy(xpath = "//button[text()='Delete']")
    public WebElement confirmDeleteBtn;


    @FindBy(xpath = "//div[@class='sc-jnrVZQ jqxNun']/div")
    public List<WebElement> allRegionCards;


}
