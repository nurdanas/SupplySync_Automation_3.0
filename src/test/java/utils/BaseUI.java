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

    /**
     * This method will wait for element to become visible
     * then it clears existing value and sends new keys
     * @param element - the input field
     * @param keys - the data to be sent
     */
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

    public void jsSendKeys(WebElement element, String value){
        waitUntilVisible(20, element);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].value='';", element);
        js.executeScript("arguments[0].value=arguments[1];", element, value);
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
        safeJSClick(Driver.getDriver().findElements(optionsLocator).get(randomIndex), 3);
    }


    public static boolean safeJSClick(WebElement element, int maxRetries) {

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();

        for (int i = 1; i <= maxRetries; i++) {
            try {
                js.executeScript("arguments[0].click();", element);
                System.out.println("Successfully clicked element on attempt #" + i);
                return true; // Exit loop and method on success
            } catch (Exception e) {
                System.err.println("Attempt #" + i + " failed to click element: " + e.getMessage());
                // Optional: Add a short Thread.sleep(500) here if the page is mid-render
            }
        }

        System.err.println("Failed to click element after " + maxRetries + " attempts.");
        return false;
    }

}