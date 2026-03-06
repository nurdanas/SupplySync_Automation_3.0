package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class BaseUI {

    public void waitAndClick(WebElement element){
        waitUntilClickable(20, element);
        element.click();
    }

    public void clearAndSendKeys(WebElement element, String keys){
        waitUntilVisible(20, element);
        element.clear();
        element.sendKeys(keys);
    }

    public void jsClick(WebElement element){
        waitUntilClickable(20, element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public WebDriverWait explicitWait(int seconds){
        return new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds));
    }

    public void waitUntilClickable(int seconds, WebElement element){
        new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitUntilVisible(int seconds, WebElement element){
        new  WebDriverWait(Driver.getDriver(), Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public  void selectRandomOptionFromDropdown(WebElement dropdown, By optionsLocator){
        waitAndClick(dropdown);
        explicitWait(20).until(ExpectedConditions.numberOfElementsToBeMoreThan(optionsLocator, 1));
        int randomIndex = new Random().nextInt(0, Driver.getDriver().findElements(optionsLocator).size());
        waitAndClick(Driver.getDriver().findElements(optionsLocator).get(randomIndex));
    }

}



