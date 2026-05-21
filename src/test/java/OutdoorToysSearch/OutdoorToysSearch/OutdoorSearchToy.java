package OutdoorToysSearch.OutdoorToysSearch;

import java.io.*;
import java.time.Duration;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class OutdoorSearchToy {
    WebDriver driver;
    EbaySearchPage searchPage;
    String filePath = "C:\\selenium_pratice\\OutdoorToysSearch\\testdata\\EbayResults.xlsx";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.ebay.com/");
        searchPage = new EbaySearchPage(driver);
    }

    @Test
    public void executeRequirements() throws IOException {
        // 1. Locate and click Advanced Search
        searchPage.clickAdvancedSearch();

        // 2. Enter keywords and Category
        searchPage.fillCriteria("outdoor toys", "Any words, any order", "Toys & Hobbies");

        // 3. Apply specific filters (Title/Desc, New, Returns, Worldwide)
        searchPage.applyFilters();

        // 4. Click Search
        searchPage.submitSearch();

        // 5. Scrape and Write to Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Toys Results");

        // Create Head Titles (Product and Link)
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Product Name");
        header.createCell(1).setCellValue("Product Link");

        List<WebElement> titles = searchPage.getTitles();
        List<WebElement> links = searchPage.getLinks();

        int rowNum = 1;
        for (int i = 0; i < titles.size(); i++) {
            String name = titles.get(i).getText();
            
            // Verify if name matches 'toys'
            if (name.toLowerCase().contains("toys")) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(name);
                
                // Get href values
                String url = links.get(i).getAttribute("href");
                row.createCell(1).setCellValue(url);
            }
        }

        // Save File
        File file = new File(filePath);
        file.getParentFile().mkdirs(); 
        try (FileOutputStream out = new FileOutputStream(file)) {
            workbook.write(out);
        }
        workbook.close();
        System.out.println("Requirement Completed: Data saved to Excel.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}