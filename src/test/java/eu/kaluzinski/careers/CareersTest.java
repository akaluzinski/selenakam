package eu.kaluzinski.careers;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class CareersTest {
    public static final String RESULTS_COUNT_SUFFIX = "results";
    private WebDriver driver;


    @Before
    public void setUp() throws Exception {
        //TODO use value from PATH if already known.
        //TODO use setting from configuration file
        // TODO handle Linux and Mac OS paths as well
        System.setProperty("webdriver.gecko.driver", "I:\\Selenium\\geckodriver.exe");

        //TODO add console logger and screenshots
        //TODO introduce Page Object Pattern

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCareersSearch() {
        openPage("https://akamaijobs.referrals.selectminds.com");
        assertEquals("Careers page header is incorrect", "Welcome to Akamai Careers",
                driver.findElement(By.xpath("//div[@id='portal_promo']/div/h2")).getText());

        String keyword = "test";
        setKeyword(keyword);
        setCity("Krakow, Poland");
        search();

        waitForElementToBeVisible(By.className("jSearchTitle"));

//    TODO  Is it enough to verify number from text or is it better to count results?
        verifyNumberOfResults(12);
        verifyNumberOfTitlesHavingSubstring(2, "Software Development Engineer in Test");

        String jobPostTitle = "Software Development Engineer in Test - LUNA";
        openJobByTitle(jobPostTitle);
        By jobOfferTitleLocator = By.className("title");
        waitForElementToBeVisible(jobOfferTitleLocator);
        assertEquals("Job offer page is not correct ", jobPostTitle,
                driver.findElement(jobOfferTitleLocator).getText());

        verifyJobPostDate("Oct 25, 2017");

    }




    public void openPage(String url) {
        driver.get(url);
    }

    public void openJobByTitle(String jobTitle) {
        List<WebElement> jobLinks = getElementsByLocatorAndNodeText(By.className("job_link"), jobTitle);

        //TODO change exception type and resolve maven crash
        if (jobLinks.size() != 1)
            throw new RuntimeException("List of job links is incorrect: There are " + jobLinks.size() + " but 1 was expected");

        jobLinks.get(0).click();
    }

    /**
     * Method returns titles of posted jobs from <b>current</b> page
     *
     * @return list of posted jobs or empty list if none found
     */
    public List<String> getJobTitles() {
        return driver.findElements(By.className("job_link"))
                .stream().map(link -> link.getText()).
                        collect(Collectors.toList());
    }

    /**
     * Counts number of list's elements containing given sequence.
     *
     * @param list
     * @param sequence
     * @return
     */
    public int getNumberOfElementsWithSequence(List<String> list, String sequence) {
        //TODO add junits

        if (list == null) {
            throw new NullPointerException("List is null");
        }
        int i = 0;
        for (String listElement : list) {
            if (listElement == null)
                continue;
            else if (listElement.contains(sequence))
                i++;
        }
        return i;
    }

    public int getResultsCount() {
        By numberOfResultsTextLocator = By.xpath("//div[@id='job_results_list_hldr']/div/div[@class='number_of_results']");
        waitForElementToBeVisible(numberOfResultsTextLocator);
        String actualResultsText = driver.findElement(numberOfResultsTextLocator).getText();
        return Integer.parseInt(actualResultsText.split(RESULTS_COUNT_SUFFIX)[0].trim());
    }

    public List<WebElement> getElementsByLocatorAndNodeText(By locator, String nodeText) {
        return driver.findElements(locator).stream().filter(
                option -> option.getText().trim().equals(nodeText)).collect(Collectors.toList());
    }


    public void waitForElementToBeVisible(By locator) {
        waitForElementToBeVisible(locator, 20);
    }

    public void waitForElementToBeVisible(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
    }


    public void setKeyword(String keyword) {
        WebElement inputField = driver.findElement(By.id("keyword"));
        //Clean in case that browser has some value cached
        inputField.clear();
        inputField.sendKeys(keyword);
    }

    public void search() {
        driver.findElement(By.id("jSearchSubmit")).click();
    }

    public void setCity(String cityName) {
        driver.findElement(By.id("loc_placeholder")).click();

        //FIXME change constant time wait to dynamic till list elements are available
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.withTimeout(2, TimeUnit.SECONDS);

        List<WebElement> listOfOptions = getElementsByLocatorAndNodeText(By.className("active-result"), cityName);
        if (listOfOptions.size() != 1)
            throw new RuntimeException("List of cities is incorrect: There are " + listOfOptions.size() + " but 1 was expected");

        listOfOptions.get(0).click();
    }


    public void verifyNumberOfResults(int expectedNumberOfResults) {
        assertEquals("Number of results is not as expected", getResultsCount(), expectedNumberOfResults);
        //TODO change assertEquals() to info+fail block
        System.out.println("Number of results is correct: " + expectedNumberOfResults);
    }

    private void verifyNumberOfTitlesHavingSubstring(int expectedNumberOfTitleSubstrings, String titleSubstring) {
        Assert.assertEquals(
                String.format("Number of '%s' occurrences in title is incorrect", titleSubstring),
                expectedNumberOfTitleSubstrings,
                getNumberOfElementsWithSequence(getJobTitles(), titleSubstring)
        );
    }

    public void verifyJobPostDate(String expectedDate) {
        //TODO date as a date type
        assertEquals("Job post date is not correct",
                driver.findElement(By.className("job_post_date")).getText(), expectedDate);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}

