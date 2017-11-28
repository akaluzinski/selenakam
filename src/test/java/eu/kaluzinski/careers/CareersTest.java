package eu.kaluzinski.careers;


import eu.kaluzinski.pages.careers.CareersPage;
import eu.kaluzinski.pages.careers.JobPostPage;
import eu.kaluzinski.pages.careers.SearchResultsPage;
import eu.kaluzinski.utils.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Class containing simple tests of Careers page
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class CareersTest extends FunctionalTest {

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    @Test
    public void careersSearchTest() {
        careersSearchFlow("https://akamaijobs.referrals.selectminds.com",
                "test", "Krakow, Poland",
                "Software Development Engineer in Test", 2, 12,
                "Software Development Engineer in Test - LUNA", "Oct 25, 2017");
    }

    /**
     * Verifies following flow:
     * <ol start="1">
     * <li>Opens Careers web site</li>
     * <li>Searches for the jobs with {@code keyword} in {@code city}</li>
     * <li>Validates that there is a total of {@code expectedTotalCount} ​results</li>
     * <li>Validates that number jobs posted in the first results page that have {@code jobTitleInResults} in their title is {@code expectedJobTitleInResultsCount}</li>
     * <li>Validates that job post with title {@code jobPostTitle} was created​ ​on​ {@code expectedJobPostDate}​</li>
     * </ol>
     *
     * @param url                            address of the page to test
     * @param keyword                        keyword to set in 'Find jobs by keyword' field
     * @param city                           city to select from location drop-down
     * @param jobTitleInResults              job title to look for in search results
     * @param expectedJobTitleInResultsCount expected number of {@code jobTitleInResults} occurrences
     * @param expectedTotalCount             expected total count on search results page
     * @param jobPostTitle                   job post to open
     * @param expectedJobPostDate            expected post date of {@code jobPostTitle}
     */
    private void careersSearchFlow(String url, String keyword, String city,
                                   String jobTitleInResults, int expectedJobTitleInResultsCount,
                                   int expectedTotalCount,
                                   String jobPostTitle, String expectedJobPostDate) {
        openPage(url);
        CareersPage careersPage = new CareersPage(driver);
        assertValue("Careers page header", "Welcome to Akamai Careers",
                careersPage.getHeader());

        SearchResultsPage searchResult = careersPage.searchBy(keyword, city);

        assertValue("Number of search results", expectedTotalCount, searchResult.getResultsCount());
        assertValue(String.format("Number of '%s' title occurrences", jobTitleInResults),
                expectedJobTitleInResultsCount, StringUtils.getNumberOfElementsWithSequence(
                        searchResult.getJobTitles(), jobTitleInResults));

//        assertValue(String.format("Number of '%s' job posts", jobPostTitle), 1,
//                searchResult.getJobTitlesCount(jobPostTitle));

        JobPostPage jobPost = searchResult.openJobPostByTitle(jobPostTitle);
        assertValue("Job offer page header", jobPostTitle, jobPost.getHeader());
        assertValue("Job post date", expectedJobPostDate, jobPost.getPostDate());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

