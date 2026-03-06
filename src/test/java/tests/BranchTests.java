package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BranchPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

import java.time.Duration;

public class BranchTests extends BaseUI {
    BranchPage branchPage;

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
                By.xpath("//span[text()='Branches']/..")));
    }

    @BeforeMethod
    public void initPages() {
        branchPage = new BranchPage();
    }

    @AfterClass
    public void tearDown() {
        Driver.closeDriver();
    }


    @Test
    public void validateUserCanCreateBranch(){
        branchPage.createCompanyBranch("QA Solution","codewise@gmail.com",
                "2250 E Devon Ave", "312 666 9988","Des Plaines",
                "Illinois","CodeWise");
        Assert.assertEquals(branchPage.getSuccessMessage(),"Branch successfully created");
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("=false"));
    }




}
