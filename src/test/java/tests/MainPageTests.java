package tests;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.MainPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

public class MainPageTests extends BaseUI {
    MainPage mainPage;
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage = new LoginPage();
        mainPage = new MainPage();

        loginPage.login(
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password")
        );
    }


    @Test (groups = {"smoke", "regression"})
    public void administratorSignedOut() {
        explicitWait(20).until(ExpectedConditions.urlContains("dashboard"));
        waitAndClick(mainPage.administratorBtn);
        waitAndClick(mainPage.exitBtn);
        waitAndClick(mainPage.exitBtnInWindow);

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("login"));
    }

    @Test (groups = "regression")
    public void administratorSignOutCancel() {
        waitAndClick(mainPage.administratorBtn);
        waitAndClick(mainPage.exitBtn);
        waitAndClick(mainPage.cancelBtnInWindow);

        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("dashboard"));
    }

    @Test (groups = "regression")
    public void administratorSignOutMessageAppears() {
        waitAndClick(mainPage.administratorBtn);
        waitAndClick(mainPage.exitBtn);

        explicitWait(20).until(ExpectedConditions.visibilityOf(mainPage.exitMessage));
        Assert.assertTrue(mainPage.exitMessage.isDisplayed());
    }

    @Test (groups = "regression")
    public void windowMessageMisspell() {
        waitAndClick(mainPage.administratorBtn);
        waitAndClick(mainPage.exitBtn);

        explicitWait(20).until(ExpectedConditions.visibilityOf(mainPage.exitMessage));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(mainPage.exitMessage.getText(), mainPage.expectedMessage());
        softAssert.assertEquals(mainPage.exitBtnInWindow.getText(), mainPage.expectedExitBtnText());
        softAssert.assertEquals(mainPage.cancelBtnInWindow.getText(), mainPage.expectedCancelBtnText());
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.closeDriver();
    }



}
