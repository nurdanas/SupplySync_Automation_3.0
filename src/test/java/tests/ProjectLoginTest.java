package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.ProjectLoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BaseUI;
import utils.Driver;

public class ProjectLoginTest extends BaseUI {
    WebDriver driver;
    ProjectLoginPage projectLoginPage;

    @BeforeEach
    public void setUpTest() {
        driver = Driver.getDriver();
        projectLoginPage = new ProjectLoginPage();
    }

    @AfterEach
    public void closeDriver() {
        Driver.closeDriver();
    }

    @Test
    public void successfulLoginTest() {
        projectLoginPage.login("admin@codewise.com", "codewise123");

        explicitWait(20).until(ExpectedConditions.urlContains("dashboard"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void invalidEmail() {
        projectLoginPage.login("admin@codewi14.com", "codewise123");
        waitUntilVisible(10, projectLoginPage.invalidCredentialsMessage);
        Assertions.assertTrue(projectLoginPage.invalidCredentialsMessage.isDisplayed());


    }

    @Test
    public void invalidPassword() {
        projectLoginPage.login("admin@codewi14.com", "codewise45");
        waitUntilVisible(10, projectLoginPage.invalidCredentialsMessage);
        Assertions.assertTrue(projectLoginPage.invalidCredentialsMessage.isDisplayed());


    }
    @Test
    public void emptyPasswordField() {
        projectLoginPage.login("admin@codewi14.com", "");
        waitUntilVisible(10, projectLoginPage.invalidCredentialsMessage);
        Assertions.assertTrue(projectLoginPage.invalidCredentialsMessage.isDisplayed());


    }
    @Test
    public void emptyEmailField() {
        projectLoginPage.login("", "codewise123");
        waitUntilVisible(10, projectLoginPage.invalidCredentialsMessage);
        Assertions.assertTrue(projectLoginPage.invalidCredentialsMessage.isDisplayed());


    }
    @Test
    public void bothEmptyField() {
        projectLoginPage.login("", "");
        waitUntilVisible(10, projectLoginPage.invalidCredentialsMessage);
        Assertions.assertTrue(projectLoginPage.invalidCredentialsMessage.isDisplayed());
  }
    @Test
    public void adminIsAbleToLogout() {
        projectLoginPage.login("admin@codewise.com", "codewise123");
        explicitWait(20).until(ExpectedConditions.urlContains("dashboard"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        projectLoginPage.logout();
        Assertions.assertTrue(driver.getCurrentUrl().contains("/login"));
    }



}


