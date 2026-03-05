package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUI;

public class OrdersPage extends BaseUI {

    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space()='Orders']")
    WebElement ordersLink;

    @FindBy(css = "input[name='search']")
    WebElement searchName;

    @FindBy(xpath = "//label[text()='city']/../..")
    WebElement city;

    @FindBy(xpath = "//li[@role='option'][1]")
    WebElement firstCity;

    @FindBy(xpath = "//span[normalize-space()='From']/following-sibling::div//input")
    WebElement fromInput;

    @FindBy(xpath = "//span[normalize-space()='To']/following-sibling::div//input")
    WebElement toInput;

    @FindBy(xpath = "//input[@name='deliveryType']/..")
    WebElement deliveryType;

    @FindBy(xpath = "//li[@role='option'][1]")
    WebElement firstDeliveryOption;


    public void goToOrders(String name, String fromDate, String toDate) {
        waitAndClick(ordersLink);
        clearAndSendKeys(searchName, name);
        waitAndClick(city);
        waitAndClick(firstCity);
        waitAndSendKeys(fromInput, fromDate);
        waitAndSendKeys(toInput, toDate);
        waitAndClick(deliveryType);
        waitAndClick(firstDeliveryOption);
    }
}

