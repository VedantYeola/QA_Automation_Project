package OutdoorToysSearch.OutdoorToysSearch;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class EbaySearchPage {
    WebDriver driver;
    JavascriptExecutor js;

    // --- Locators ---
    @FindBy(xpath = "//a[contains(@class,'gh-search-button')]")
    WebElement advancedSearchLink;

    @FindBy(xpath = "//input[@id='_nkw']")
    WebElement keywordInput;

    @FindBy(xpath = "//select[@name='_in_kw']")
    WebElement matchTypeDropdown;

    @FindBy(xpath = "//select[contains(@id,'_sacat')]")
    WebElement categoryDropdown;

    @FindBy(xpath = "//input[contains(@id,'TitleDesc')]")
    WebElement titleDescCheckbox;

    @FindBy(xpath = "//input[contains(@value,'_ItemCondition')]") // "New" condition
    WebElement conditionNew;

    @FindBy(xpath = "//input[contains(@name,'_FR')]") // Free Returns
    WebElement freeReturns;

    @FindBy(xpath = "//input[contains(@name,'LH_RPA')]") // Returns Accepted
    WebElement returnsAccepted;

    @FindBy(xpath = "//input[contains(@id,'LH_PrefLoc')]") // Worldwide Location
    WebElement worldwideLoc;

    @FindBy(xpath = "//div[@class='adv-form__actions']//button[@type='submit']")
    WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'s-card__title')]//span[contains(@class,'su-styled-text')]")
    List<WebElement> itemTitles;

    @FindBy(xpath = "//a[@class='s-card__link']")
    List<WebElement> itemLinks;

    public EbaySearchPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // --- Actions ---
    public void clickAdvancedSearch() {
        advancedSearchLink.click();
    }

    public void fillCriteria(String keyword, String match, String category) {
        keywordInput.sendKeys(keyword);
        new Select(matchTypeDropdown).selectByVisibleText(match);
        new Select(categoryDropdown).selectByVisibleText(category);
    }

    public void applyFilters() {
        // Scroll and Click Title & Description
        js.executeScript("arguments[0].scrollIntoView(true)", titleDescCheckbox);
        if (!titleDescCheckbox.isSelected()) titleDescCheckbox.click();

        // Condition: New
        conditionNew.click();

        // Show Results: Free Returns & Returns Accepted
        js.executeScript("arguments[0].scrollIntoView(true)", freeReturns);
        freeReturns.click();
        returnsAccepted.click();

        // Location: Worldwide
        js.executeScript("arguments[0].scrollIntoView(true)", worldwideLoc);
        worldwideLoc.click();
    }

    public void submitSearch() {
        searchButton.click();
    }

    public List<WebElement> getTitles() { return itemTitles; }
    public List<WebElement> getLinks() { return itemLinks; }
}