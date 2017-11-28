package eu.kaluzinski.careers;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;


/**
 * Class containing code common functional tests of web applications\
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class FunctionalTest {

    protected WebDriver driver;

    /**
     * Asserts that actual value is equal to expected.
     *
     * @param messagePrefix
     * @param expected
     * @param actual
     */
    public <T> void assertValue(String messagePrefix, T expected, T actual) {
        assertEquals(messagePrefix + " is not as expected: ", expected, actual);
        //TODO change to logger
        System.out.println(messagePrefix + " is as expected: " + actual);
    }

    /**
     * Navigates to given url
     *
     * @param url page to be open
     */
    public void openPage(String url) {
        driver.get(url);
    }

}
