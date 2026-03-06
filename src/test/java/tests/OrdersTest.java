package tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pages.OrdersPage;
import utils.Driver;


public class OrdersTest extends MainPageTests {
//    ProjectLoginPage projectLoginPage = new ProjectLoginPage();
//    @BeforeMethod
//    public void setUp() {
//
//        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
//
//        projectLoginPage.login(
//                ConfigurationReader.getProperty("username"),
//                ConfigurationReader.getProperty("password")
//        );
//    }

    @Test (groups = {"smoke", "regression"})
    void orders() {
        OrdersPage ordersPage = new OrdersPage(Driver.getDriver());
        ordersPage.goToOrders("elima", "11122023","11102026");
        Assert.assertEquals(Driver.getDriver().getCurrentUrl(), "https://supplysync.us/dashboard/hostOrders");
    }
}