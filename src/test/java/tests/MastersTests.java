package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MastersPage;
//import pages.ProjectLoginPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;
import com.github.javafaker.Faker;
import java.time.Duration;


@Test (groups = "regression")
public class MastersTests extends BaseUI {
    MastersPage mastersPage;
    Faker faker;

    @BeforeClass
    public void setUp() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@type='email' or @name='email' or @id='email']")));
        Driver.getDriver()
                .findElement(By.xpath("//input[@type='email' or @name='email' or @id='email']"))
                .sendKeys(ConfigurationReader.getProperty("username"));

        Driver.getDriver()
                .findElement(By.xpath("//input[@type='password' or @name='password' or @id='password']"))
                .sendKeys(ConfigurationReader.getProperty("password"));

        Driver.getDriver()
                .findElement(By.xpath("//button[@type='submit']"))
                .click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Regions')]")));
    }

//    @AfterClass
//    public void tearDown() {
//        Driver.closeDriver();
//    }

    @BeforeMethod
    public void initPages() {
        mastersPage = new MastersPage();
        faker = new Faker();
    }

    @Test
    public void createNewMasterSuccessTest() {
        waitAndClick(mastersPage.mastersTitle);

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

    @Test
    public void createNewMasterFailTest() {
        waitAndClick(mastersPage.mastersTitle);

        waitAndClick(mastersPage.createMastersButton);
        String emailSaved = faker.internet().emailAddress();
        clearAndSendKeys(mastersPage.mastersEmailInput, emailSaved);
        waitAndClick(mastersPage.masterSaveButton);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("Create"));
    }

    @Test
    public void createNewMasterCancelTest() {
        waitAndClick(mastersPage.mastersTitle);
        waitAndClick(mastersPage.createMastersButton);
        waitAndClick(mastersPage.masterCancelButton);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("Create=false"));
    }
}