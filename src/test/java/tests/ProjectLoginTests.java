package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginDataProvider;
import pages.ProjectLoginPages;
import utils.BaseUI;
import utils.Driver;

public class ProjectLoginTests  extends BaseUI {
    ProjectLoginPages loginPage;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new ProjectLoginPages();

    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }


    @Test(dataProvider = "invalidLoginData", dataProviderClass = LoginDataProvider.class)
    public void invalidLoginTest(String email, String password) {
        loginPage.login(email, password);

        waitUntilVisible(10, loginPage.invalidCredentialsMessage);
        Assert.assertTrue(loginPage.invalidCredentialsMessage.isDisplayed());
    }


    @Test(dataProvider = "validLoginData", dataProviderClass = LoginDataProvider.class)
    public void successfulLoginTest(String email, String password) {
        loginPage.login(email, password);

        explicitWait(20).until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("dashboard"));
    }


    @Test
    public void adminIsAbleToLogout() {
        loginPage.login("admin@codewise.com", "codewise123");

        explicitWait(20).until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("dashboard"));

        loginPage.logout();
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("/login"));
    }


}