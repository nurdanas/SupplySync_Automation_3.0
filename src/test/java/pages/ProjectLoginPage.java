package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;

public class ProjectLoginPage {
    WebDriver driver = Driver.getDriver();

    public ProjectLoginPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email")
    public WebElement email;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//*[text()='Incorrect login and/or password.']")
    public WebElement invalidCredentialsMessage;

    @FindBy(xpath = "//p[text()='Administrator']")
    public WebElement administrator;

    @FindBy(xpath = "//li[text()='Exit']")
    public WebElement clickExit;

    @FindBy(xpath = "//button[text()='Exit']")
    public WebElement logOut;

    public void login(String username, String password) {
        String loginPageURL = "https://supplysync.us";
        driver.get(loginPageURL);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(this.email));

        this.email.sendKeys(username);
        this.password.sendKeys(password);
        loginButton.click();

    }

    public void logout() {
        administrator.click();
        clickExit.click();
        logOut.click();
    }
}
