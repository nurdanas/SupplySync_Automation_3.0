package tests;

import org.junit.jupiter.api.*;
import pages.OrdersPage;
import pages.ProjectLoginPage;
import utils.BaseUI;
import utils.ConfigurationReader;
import utils.Driver;
public class OrdersTest extends BaseUI {
    ProjectLoginPage projectLoginPage = new ProjectLoginPage();
    @BeforeEach
    public void setUp() {
        projectLoginPage.login(
                ConfigurationReader.getProperty("username"),
                ConfigurationReader.getProperty("password")
        );
    }

    @Test
    void orders() {
        OrdersPage ordersPage = new OrdersPage(Driver.getDriver());
        ordersPage.goToOrders("elima", "11122023","11102026");
        Assertions.assertEquals(Driver.getDriver().getCurrentUrl(), "https://supplysync.us/dashboard/hostOrders");
    }
}