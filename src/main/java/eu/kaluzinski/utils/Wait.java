package eu.kaluzinski.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Utility class that introduces waiting methods.
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class Wait {

    private WebDriverWait wait;
    private WebDriver driver;
    private long timeout;
    private TimeUnit unit;


    public Wait(WebDriver driver, int timeout, TimeUnit unit) {
        this.driver = driver;
        this.unit = unit;
        this.timeout = timeout;
        wait = new WebDriverWait(driver, unit.toMillis(timeout));
    }

    /**
     * Waits for the timeout specified in the constructor
     */
    public void withTimeout() {
        wait.withTimeout(timeout, unit);
    }

    /**
     * Waits for {@code element} to exists. Waits for the time defined in constructor.
     *
     * @param element
     */
    public void forElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
