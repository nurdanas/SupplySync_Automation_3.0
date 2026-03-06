package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.BranchPage;
import pages.LoginPage;
import pages.MainPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

@Test(groups = "regression")
public class BranchTests extends BaseUI {
    LoginPage loginPage = new LoginPage();
    MainPage mainPage;
    BranchPage branchPage;

    @BeforeMethod (alwaysRun = true)
    public void setUp() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage = new LoginPage();
        mainPage = new MainPage();
        branchPage = new BranchPage();
        loginPage.login(
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.closeDriver();
    }

    @Test (groups = {"smoke", "examples"})
    public void validateUserCanCreateBranch(){
        branchPage.createCompanyBranch("QA Solution","codewise@gmail.com",
                "2250 E Devon Ave", "312 666 9988","Des Plaines",
                "Illinois","CodeWise");
        Assert.assertEquals(branchPage.getSuccessMessage(),"Branch successfully created");
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("=false"));
    }




}
