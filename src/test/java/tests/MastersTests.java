package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.MainPage;
import pages.MastersPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;
import com.github.javafaker.Faker;

@Test (groups = "regression")
public class MastersTests extends BaseUI {
    Faker faker;
    LoginPage loginPage;
    MainPage mainPage;
    MastersPage mastersPage;

    @BeforeMethod (alwaysRun = true)
    public void setUp() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        faker = new Faker();
        loginPage = new LoginPage();
        mainPage = new MainPage();
        mastersPage = new MastersPage();
        loginPage.login(
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Driver.closeDriver();
    }


    @Test (groups = "examples")
    public void messagePrint (){
        System.out.println("Masters page message");
    }
    @Test (groups = "smoke", priority = 1)
    public void createNewMasterSuccessTest() {
        waitAndClick(mainPage.mastersPage);

        waitAndClick(mastersPage.createMastersButton);
        clearAndSendKeys(mastersPage.mastersNameInput, faker.name().firstName());
        clearAndSendKeys(mastersPage.mastersLastNameInput, faker.name().lastName());
        String emailSaved = faker.internet().emailAddress();
        clearAndSendKeys(mastersPage.mastersEmailInput, emailSaved);
        clearAndSendKeys(mastersPage.mastersPasswordInput, faker.internet().password());
        clearAndSendKeys(mastersPage.mastersPlaceOfResidenceInput, "CHI");
        waitAndClick(mastersPage.mastersNumberInput);
        mastersPage.mastersNumberInput.sendKeys(faker.number().digits(10));
        mastersPage.selectRandomBranchFromDropdown();

        waitAndClick(mastersPage.masterSaveButton);
        waitUntilVisible(10, mastersPage.mastersEmailsTable);
        Assert.assertTrue(mastersPage.verifyTheNewMasterIsCreated(emailSaved));
    }

    @Test (priority = 2, dependsOnMethods = "createNewMasterSuccessTest")
    public void createNewMasterFailTest() {
        waitAndClick(mainPage.mastersPage);

        waitAndClick(mastersPage.createMastersButton);
        String emailSaved = faker.internet().emailAddress();
        clearAndSendKeys(mastersPage.mastersEmailInput, emailSaved);
        waitAndClick(mastersPage.masterSaveButton);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("Create"));
    }

    @Test(priority = 3, dependsOnMethods = "createNewMasterSuccessTest")
    public void createNewMasterCancelTest() {
        waitAndClick(mainPage.mastersPage);
        waitAndClick(mastersPage.createMastersButton);
        waitAndClick(mastersPage.masterCancelButton);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("Create=false"));
    }
}