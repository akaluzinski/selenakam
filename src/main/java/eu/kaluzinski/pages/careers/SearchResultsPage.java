package eu.kaluzinski.pages.careers;

import eu.kaluzinski.pages.PageObject;
import eu.kaluzinski.utils.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * This class represents page containing search results
 *
 * @autor Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class SearchResultsPage extends PageObject {
    private static final String RESULTS_COUNT_SUFFIX = "results";

    @FindBy(xpath = "//div[@id='job_results_list_hldr']/div/div[@class='number_of_results']")
    private WebElement resultsCountLabel;

    @FindBys(@FindBy(className = "job_link"))
    private List<WebElement> jobLinks;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    /**
     * Open the first job post that matches given {@code jobTitle}
     *
     * @param jobTitle text on the job post link
     * @return Page of the Job Post
     */
    public JobPostPage openJobPostByTitle(final String jobTitle) {
        return openJobPostByTitleAndOrder(jobTitle, 0);
    }

    /**
     * Open n-th job post that matches given {@code jobTitle}
     *
     * @param jobTitle text on the job post link
     * @param n        - 0-based index of job title occurrence on the page
     * @return Page of the Job Post
     */
    public JobPostPage openJobPostByTitleAndOrder(final String jobTitle, final int n) {
        List<WebElement> jobLinks = getJobOfferLinks(jobTitle);
        jobLinks.get(n).click();
        return new JobPostPage(driver);
    }

    /**
     * Method returns text containing number of results. Waits for the text to appear for 30 seconds.
     *
     * @return text of label that shows the number of results
     */
    public String getResultsCountLabel() {
        new Wait(driver, 30, TimeUnit.SECONDS).
                forElementToBeVisible(resultsCountLabel);
        return resultsCountLabel.getText();
    }

    /**
     * Method gets number representing results count
     *
     * @return number representing results count
     */
    public int getResultsCount() {
        return Integer.parseInt(
                getResultsCountLabel().split(RESULTS_COUNT_SUFFIX)[0].trim());
    }

    /**
     * Method returns titles of posted jobs from <b>current</b> page
     *
     * @return list of posted jobs or empty list if none found
     */
    public List<String> getJobTitles() {
        return jobLinks.stream().map(WebElement::getText).
                collect(Collectors.toList());
    }

    /**
     * Method returns number of job links with given link text from <b>current</b> page
     *
     * @param jobOfferTitle text on the offer title
     * @return list of posted jobs or empty list if none found
     */
    public int getJobTitlesCount(final String jobOfferTitle) {
        return getJobOfferLinks(jobOfferTitle).size();
    }

    /**
     * Method gets links to offers with given title
     *
     * @param jobOfferTitle text on the offer title
     * @return links to titles exactly matching title text
     */
    private List<WebElement> getJobOfferLinks(final String jobOfferTitle) {
        return jobLinks.stream().filter(
                link -> link.getText().equals(jobOfferTitle)).
                collect(Collectors.toList());
    }
}
