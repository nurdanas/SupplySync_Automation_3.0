package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class LoginPage extends BaseUI {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
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

    public void login(String username, String actualPassword) {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

        waitUntilVisible(20, email);
        email.sendKeys(username);
        password.sendKeys(actualPassword);
        loginButton.click();
    }

    public void logout() {
        administrator.click();
        clickExit.click();
        logOut.click();
    }

}
