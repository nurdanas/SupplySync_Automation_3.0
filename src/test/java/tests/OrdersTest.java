package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.OrdersPage;
import utils.ConfigurationReader;
import utils.Driver;


public class OrdersTest {
    LoginPage loginPage = new LoginPage();
    MainPage mainPage;


    @BeforeMethod (alwaysRun = true)
    public void setUp() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        loginPage = new LoginPage();
        mainPage = new MainPage();
        loginPage.login(
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password"));
    }

    @Test (groups = {"smoke", "regression"})
    void orders() {
        OrdersPage ordersPage = new OrdersPage(Driver.getDriver());
        ordersPage.goToOrders("elima", "11122023","11102026");
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://supplysync.us/dashboard/hostOrders");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Driver.closeDriver();
    }
}