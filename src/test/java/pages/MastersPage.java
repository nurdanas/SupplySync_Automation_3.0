package pages;

import utils.BaseUI;
import utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MastersPage extends BaseUI {

    WebDriver driver = Driver.getDriver();

    public MastersPage(){
        PageFactory.initElements(driver, this);}

//    @FindBy(xpath = "//span[contains(., 'Masters')]")
//    public WebElement mastersTitle;

    @FindBy (xpath = "//button[contains(., 'Create Master')]")
    public WebElement createMastersButton;

    @FindBy (css = "input[name='name']")
    public WebElement mastersNameInput;

    @FindBy (css = "input[name='lastName']")
    public WebElement mastersLastNameInput;

    @FindBy (css = "input[name='email']")
    public WebElement mastersEmailInput;

    @FindBy (css = "input[name='password']")
    public WebElement mastersPasswordInput;

    @FindBy (css = "input[name='location']")
    public WebElement mastersPlaceOfResidenceInput;

    @FindBy (css = "input[name='number']")
    public WebElement mastersNumberInput;


    @FindBy (xpath = "//div[@id='mui-component-select-branch_id']")
    public WebElement mastersBranchDropDown;

    public void selectRandomBranchFromDropdown(){
        selectRandomOptionFromDropdown(mastersBranchDropDown,
                By.xpath("//li[@role='option']"));
    }

    @FindBy (xpath = "//button[text()='Create']")
    public WebElement masterSaveButton;

    @FindBy (xpath = "//button[text()='Cancel']")
    public WebElement masterCancelButton;


    @FindBy (xpath = "//div[text()='Email']")
    public WebElement mastersEmailsTable;



    public Boolean verifyTheNewMasterIsCreated(String emailSaved) {

        List<WebElement> emailElements = driver.findElements(
                By.xpath("//div[text()='Email']/following-sibling::div[1]"));

        boolean found = false;

        for (WebElement element : emailElements) {
            String actualEmail = element.getText();
            System.out.println("Checking email: " + actualEmail);
            if (actualEmail.equals(emailSaved)) {
                found = true;
                break;
            }
        }
        return found;
    }
}
