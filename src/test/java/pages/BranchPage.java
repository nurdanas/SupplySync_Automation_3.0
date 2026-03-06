package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUI;
import utils.Driver;

public class BranchPage extends BaseUI {
    WebDriver driver = Driver.getDriver();

    public BranchPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Branches']/..")
    public WebElement branchTab;

    @FindBy(xpath = "//button[text()='Create Branch']")
    public WebElement createBranchBtn;

    @FindBy(xpath = "//input[@name='name']")
    public WebElement nameInput;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailInput;

    @FindBy(xpath = "//input[@name='address']")
    public WebElement addressInput;

    @FindBy(xpath = "//input[@name='phoneNumber']")
    public WebElement phoneInput;

    @FindBy(xpath = "//input[@name='city']")
    public WebElement cityInput;

    @FindBy(xpath = "//div[@id='mui-component-select-regionId']")
    public WebElement selectRegionBtn;

    @FindBy(xpath = "//div[@id='mui-component-select-companyId']")
    public WebElement selectCompanyBtn;

    @FindBy(xpath = "//label[.//input[@value='true']]")
    public WebElement radioShowToClientsTrue;

    @FindBy(xpath = "//button[text()='Create']")
    public WebElement createBtn;

    @FindBy(xpath = "//*[@role='alert']")
    public WebElement successText;

    public void createCompanyBranch(String name, String email, String address, String phoneNumber,
                                                 String city, String regionVisibleText, String companyVisibleText){
        waitAndClick(branchTab);
        waitAndClick(createBranchBtn);
    clearAndSendKeys(nameInput,name);
    clearAndSendKeys(emailInput, email);
    clearAndSendKeys(addressInput, address);
    waitAndClick(phoneInput);
    clearAndSendKeys(phoneInput, phoneNumber);
    clearAndSendKeys(cityInput,city);

    waitAndClick(selectRegionBtn);
    WebElement regionOption = driver.findElement(org.openqa.selenium.By.xpath("//li[text()='"
            + regionVisibleText + "']"));
    waitAndClick(regionOption);

    waitAndClick(selectCompanyBtn);
    WebElement companyOption = driver.findElement(org.openqa.selenium.By.xpath("//li[text()='Toyota']"));
    waitAndClick(companyOption);


    //scrollToElement(radioShowToClientsTrue);
       // waitAndClick(radioShowToClientsTrue);
    jsClick(radioShowToClientsTrue);
    jsClick(createBtn);
//    waitAndClick(radioShowToClientsTrue);
//    waitAndClick(createBtn);

    }

    public String getSuccessMessage(){
        waitUntilVisible(20, successText);
        return successText.getText().trim();
    }

}
