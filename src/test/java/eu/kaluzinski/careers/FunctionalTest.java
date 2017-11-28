package eu.kaluzinski.careers;

import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;


/**
 * Class containing code common functional tests of web applications\
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class FunctionalTest {

    private static Logger log = Logger.getLogger(FunctionalTest.class.getName());

    protected WebDriver driver;

    /**
     * Asserts that actual value is equal to expected.
     *
     * @param messagePrefix Part of message to add in log
     * @param expected      expected value
     * @param actual        actual value
     */
    public <T> void assertValue(String messagePrefix, T expected, T actual) {
        assertEquals(messagePrefix + " is not as expected: ", expected, actual);
        log.info(String.format("\n -------------------------------- \n Value is as expected: '%s' \n--------------------------------\n", actual, expected));
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
