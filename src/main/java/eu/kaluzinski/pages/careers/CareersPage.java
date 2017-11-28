package eu.kaluzinski.pages.careers;

import eu.kaluzinski.pages.PageObject;
import eu.kaluzinski.utils.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Represents home Careers Page
 *
 * @author Adrian Kaluzinski
 * Date: 11-28-2017
 */
public class CareersPage extends PageObject {

    @FindBy(xpath = "//div[@id='portal_promo']/div/h2")
    private WebElement header;

    @FindBy(id = "keyword")
    private WebElement keywordInput;

    @FindBy(id = "jSearchSubmit")
    private WebElement searchButton;

    @FindBy(id = "loc_placeholder")
    private WebElement locationSelector;

    @FindBys(@FindBy(className = "active-result"))
    private List<WebElement> availableCities;

    public CareersPage(WebDriver driver) {
        super(driver);
        initElements(driver, this);
    }

    /**
     * Clean the keyword input field and provides given text. <br/>
     * Method just cleans the field if keyword is null or empty.
     *
     * @param keyword text to type in keyword search field
     */
    public void setKeyword(String keyword) {
        //Clean in case that browser has value cached
        keywordInput.clear();
        if (keyword == null || keyword.isEmpty()) {
            return;
        }
        keywordInput.sendKeys(keyword);
    }

    /**
     * Provide keyword, select city and click on search button.
     *
     * @param keyword text to type in keyword search field. Method just cleans the field if {@code keyword} is null or empty.
     * @param city    name of city to match from drop-down. Does nothing if {@code cityName} is null or empty
     * @return page containing Search Results
     */
    public SearchResultsPage searchBy(final String keyword, final String city) {
        setKeyword(keyword);
        setCity(city);
        search();
        return new SearchResultsPage(driver);
    }

    /**
     * Clicks on Search button
     */
    public void search() {
        searchButton.click();
    }

    /**
     * Select the first matching city from drop-down. <br/>
     * Does nothing if {@code cityName} is null or empty
     *
     * @param cityName name of city to match from drop-down
     * @return name of city that got selected if {@code cityName} non-empty. null otherwise.
     */
    public String setCity(final String cityName) {
        if (cityName == null || cityName.isEmpty()) {
            return null;
        }

        locationSelector.click();
        //Wait for the dropdown to load
        new Wait(driver, 20,TimeUnit.SECONDS ).withTimeout();

        WebElement matchingCity = getCityLinksMatchingName(cityName).get(0);
        matchingCity.click();
        return matchingCity.getText();
    }

    /**
     * Returns text header of the page
     *
     * @return header of Careers page
     */
    public String getHeader() {
        return header.getText();
    }

    /**
     * Method returns links to cities matching given text
     *
     * @param cityName part of the city name
     * @return links to cities containing given text
     */
    private List<WebElement> getCityLinksMatchingName(String cityName) {
        return availableCities.stream().filter(
                option -> option.getText().contains(cityName)).
                collect(Collectors.toList());
    }
}
