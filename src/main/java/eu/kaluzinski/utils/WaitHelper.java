package eu.kaluzinski.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Utility method that introduces wait methods.
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class WaitHelper {

    private WebDriverWait wait;
    private WebDriver driver;

    public WaitHelper(WebDriver driver, int timeout, TimeUnit unit){
        this.driver = driver;
        wait = new WebDriverWait(driver, unit.toMillis(timeout));
    }
	//TODO javadoc
    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
