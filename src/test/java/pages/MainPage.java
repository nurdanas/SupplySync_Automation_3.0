package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

public class MainPage {
    WebDriver driver = Driver.getDriver();

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy (linkText = "Companies")
    public WebElement companiesPage;

    @FindBy (linkText = "Branches")
    public WebElement branchesPage;

    @FindBy (linkText = "Masters")
    public WebElement mastersPage;

    @FindBy (linkText = "Tariffs")
    public WebElement tariffsPage;

    @FindBy (linkText = "Orders")
    public WebElement ordersPage;

    @FindBy (linkText = "Regions")
    public WebElement regionsPage;

    @FindBy (xpath = "//button[@aria-label='Account settings']")
    public WebElement administratorBtn;

    @FindBy (xpath = "//li[text()='Exit']")
    public WebElement exitBtn;

    @FindBy (xpath = "//p[contains(text(), 'exit?')]")
    public WebElement exitMessage;

    @FindBy (xpath = "//button[text()='Exit']")
    public WebElement exitBtnInWindow;

    @FindBy (xpath = "//button[text()='Cancer']")
    public WebElement cancelBtnInWindow;

    public String expectedMessage() {
        return "Are you sure you want to exit?";
    }

    public String expectedCancelBtnText() {
        return "Cancel";
    }

    public String expectedExitBtnText() {
        return "Exit";
    }
}
