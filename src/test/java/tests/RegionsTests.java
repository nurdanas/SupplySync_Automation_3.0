package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.RegionsPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;

import java.time.Duration;

public class RegionsTests extends BaseUI {
//  Aiperi

    RegionsPage regionsPage;

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

    @BeforeMethod
    public void initPages() {
        regionsPage = new RegionsPage();
    }

    @AfterClass
    public void tearDown() {
        if (Driver.getDriver() != null) {
            Driver.getDriver().quit();
            Driver.driver = null;
        }
    }

    private void waitForBackdropToDisappear() {
        try {
            new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(
                            By.xpath("//div[@aria-hidden='true' and contains(@class,'MuiBackdrop-root')]")));
        } catch (Exception ignored) {
        }
    }

    @Test(description = "Verify that Regions module opens successfully")
    public void shouldOpenRegionsModule() {
        jsClick(regionsPage.regionsMenuItem);
        waitUntilVisible(10, regionsPage.createRegionBtn);

        Assert.assertTrue(
                regionsPage.createRegionBtn.isDisplayed(),
                "Create Region button is not visible after opening Regions module"
        );
    }

    @Test(description = "Verify that a region can be edited")
    public void shouldEditRegionSuccessfully() {
        SoftAssert softAssert = new SoftAssert();
        String updatedName = "Alaska";

        jsClick(regionsPage.regionsMenuItem);
        waitForBackdropToDisappear();
        waitUntilVisible(10, regionsPage.actionsMenuBtn);
        jsClick(regionsPage.actionsMenuBtn);
        waitAndClick(regionsPage.editMenuItem);
        clearAndSendKeys(regionsPage.editRegionNameInput, updatedName);
        jsClick(regionsPage.saveBtn);
        waitForBackdropToDisappear();

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(driver -> regionsPage.allRegionCards.size() > 0);

        softAssert.assertFalse(
                regionsPage.allRegionCards.isEmpty(),
                "Regions list should not be empty after edit"
        );

        boolean regionUpdated = regionsPage.allRegionCards.stream()
                .anyMatch(card -> card.getText().contains(updatedName));

        softAssert.assertTrue(regionUpdated, "Region name was not updated to '" + updatedName + "'");

        softAssert.assertAll();
    }

    @Test(description = "Verify that a region can be deleted")
    public void shouldDeleteRegionSuccessfully() {
        String regionToDelete = "Alaska";

        jsClick(regionsPage.regionsMenuItem);
        waitForBackdropToDisappear();
        waitUntilVisible(10, regionsPage.actionsMenuBtn);
        jsClick(regionsPage.actionsMenuBtn);
        waitAndClick(regionsPage.deleteMenuItem);
        jsClick(regionsPage.confirmDeleteBtn);
        waitForBackdropToDisappear();

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        boolean isDeleted = wait.until(driver ->
                regionsPage.allRegionCards.stream()
                        .noneMatch(card -> card.getText().contains(regionToDelete))
        );

        Assert.assertTrue(isDeleted, "Region '" + regionToDelete + "' was not deleted from the list");
    }
}
