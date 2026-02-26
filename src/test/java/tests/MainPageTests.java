package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.MainPage;
import pages.ProjectLoginPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class MainPageTests extends BaseUI {
    //loginPage
    MainPage mainPage = new MainPage();
    ProjectLoginPage projectLoginPage = new ProjectLoginPage();

    @BeforeEach
    public void setUp() {
        projectLoginPage.login(
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password")
        );
//        WebDriver driver = Driver.getDriver();
//        driver.get("https://supplysync.us/login");
//        explicitWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
//        WebElement login = driver.findElement(By.name("email"));
//        login.sendKeys(ConfigurationReader.getProperty("username"));
//        WebElement password = driver.findElement(By.id("password"));
//        password.sendKeys(ConfigurationReader.getProperty("password"));
//        WebElement sigInBtn = driver.findElement(By.tagName("button"));
//        sigInBtn.click();
        //login
    }

    @Test
    public void administratorSignOutMessageAppears() {
        waitAndClick(mainPage.administratorBtn);
        waitAndClick(mainPage.exitBtn);

        explicitWait(20).until(ExpectedConditions.visibilityOf(mainPage.exitMessage));
        Assertions.assertTrue(mainPage.exitMessage.isDisplayed());
    }

    @Test
    public void administratorSignedOut() {
        WebDriver driver = Driver.getDriver();
        waitAndClick(mainPage.administratorBtn);
        waitAndClick(mainPage.exitBtn);
        waitAndClick(mainPage.exitBtnInWindow);

        Assertions.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void administratorSignOutCancel() {
        WebDriver driver = Driver.getDriver();
        waitAndClick(mainPage.administratorBtn);
        waitAndClick(mainPage.exitBtn);
        waitAndClick(mainPage.cancelBtnInWindow);

        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void windowMessageMisspell() {
        waitAndClick(mainPage.administratorBtn);
        waitAndClick(mainPage.exitBtn);

        explicitWait(20).until(ExpectedConditions.visibilityOf(mainPage.exitMessage));
        Assertions.assertEquals(mainPage.exitMessage.getText(), mainPage.expectedMessage());
        Assertions.assertEquals(mainPage.exitBtnInWindow.getText(), mainPage.expectedExitBtnText());
        Assertions.assertEquals(mainPage.cancelBtnInWindow.getText(), mainPage.expectedCancelBtnText());
        //here should be softAssertion, not used yet since Bena said only Junit, and soft is only at testNG
    }

    @AfterEach
    public void tearDown() {
        Driver.closeDriver();
    }

}
