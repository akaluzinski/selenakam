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
public class WebTest {

    protected static Logger log = Logger.getLogger(WebTest.class.getName());
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
        log.info(String.format("\n -------------------------------- \n %s is as expected: '%s' \n--------------------------------\n",
                messagePrefix, expected));
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
