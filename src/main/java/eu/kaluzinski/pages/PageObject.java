package eu.kaluzinski.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents abstract page
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public abstract class PageObject extends PageFactory {
    protected WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }
}
