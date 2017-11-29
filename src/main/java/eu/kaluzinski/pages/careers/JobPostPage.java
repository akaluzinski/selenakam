package eu.kaluzinski.pages.careers;

import eu.kaluzinski.pages.PageObject;
import eu.kaluzinski.utils.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * Represents Job Post page
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class JobPostPage extends PageObject {

    @FindBy(className = "title")
    private WebElement header;

    @FindBy(className = "job_post_date")
    private WebElement jobPostDate;

    public JobPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method returns the header of job post. Waits for the header to appear for 30 seconds.
     *
     * @return the header of job post
     */
    public String getHeader() {
        new Wait(driver, 30, TimeUnit.SECONDS).
                forElementToBeVisible(header);
        return header.getText();
    }

    /**
     * Method returns job post date
     *
     * @return date of the job post
     */
    public String getPostDate() {
        return jobPostDate.getText();
    }
}
