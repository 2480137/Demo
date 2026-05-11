package org.example.tests;

import com.aventstack.extentreports.Status;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.base.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.SearchResultsPage;
import org.example.utils.ExcelUtil;
import org.example.utils.ExtentReportManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

public class FlipkartTest extends BaseTest {

    private JavascriptExecutor js;
    private WebDriverWait wait;
    private WebDriverWait shortWait;

    // ── Report lifecycle ──────────────────────────────────────────────────────

    @BeforeSuite
    public void setUpReport() {
        ExtentReportManager.initReports();
    }

    @AfterSuite
    public void tearDownReport() {
        ExtentReportManager.flushReports();
    }

    // ── Test lifecycle ────────────────────────────────────────────────────────

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        String browserToUse = System.getProperty("browser", browser);
        ExtentReportManager.createTest("Flipkart Mobile Search [" + browserToUse + "]");
        launchBrowser(browserToUse);
        js        = (JavascriptExecutor) driver;
        wait      = new WebDriverWait(driver, Duration.ofSeconds(15));
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String path = takeScreenshot(driver, "FAILED_" + result.getName());
            if (path != null) {
                try {
                    ExtentReportManager.getTest()
                        .addScreenCaptureFromPath(path, "Failure Screenshot");
                } catch (Exception ignored) {}
            }
            ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());
        }
        if (driver != null) driver.quit();
    }

    // ── Test ─────────────────────────────────────────────────────────────────

    @Test
    public void flipkartMobileSearch() throws InterruptedException {

        String excelPath = "testdata/TestData.xlsx";
        String searchKey = ExcelUtil.getCellData(excelPath, 1, 1);

        HomePage homePage = new HomePage(driver, wait, shortWait);

        homePage.openSite();
        ExtentReportManager.getTest().pass("Opened Flipkart homepage");

        homePage.closeLoginPopup();
        ExtentReportManager.getTest().pass("Login popup handled");

        homePage.search(searchKey);
        ExtentReportManager.getTest().pass("Searched: mobiles under 15000");

        SearchResultsPage resultsPage = new SearchResultsPage(driver, wait, shortWait, js);

        resultsPage.verifyResults();
        ExtentReportManager.getTest().pass("Search results verified");

        resultsPage.applyPriceFilter();
        ExtentReportManager.getTest().pass("Price filter applied: max Rs.10,000");

        resultsPage.applyOsFilter();
        ExtentReportManager.getTest().pass("OS filter applied: Pie");

        resultsPage.sortByNewest();
        ExtentReportManager.getTest().pass("Sorted by: Newest First");

        List<String[]> results = resultsPage.getFirst5Products();

        if (results != null && !results.isEmpty()) {
            int firstPrice = Integer.parseInt(results.get(0)[1]);
            if (firstPrice < 30000) {
                ExtentReportManager.getTest().pass("Price Validation: Rs." + firstPrice + " < Rs.30,000");
            } else {
                ExtentReportManager.getTest().fail("Price Validation: Rs." + firstPrice + " >= Rs.30,000");
            }
        }

        writeToExcel(excelPath, results);
        ExtentReportManager.getTest().pass("Results saved to Excel");
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private void writeToExcel(String excelPath, List<String[]> results) {
        try {
            FileInputStream fis = new FileInputStream(excelPath);
            Workbook wb = WorkbookFactory.create(fis);
            fis.close();

            int idx = wb.getSheetIndex("Results");
            if (idx != -1) wb.removeSheetAt(idx);

            Sheet sheet = wb.createSheet("Results");

            // ── Header style (bold) ───────────────────────────────────────────
            CellStyle headerStyle = wb.createCellStyle();
            Font headerFont = wb.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row header = sheet.createRow(0);
            Cell h0 = header.createCell(0); h0.setCellValue("Mobile Name");  h0.setCellStyle(headerStyle);
            Cell h1 = header.createCell(1); h1.setCellValue("Price (Rs.)");  h1.setCellStyle(headerStyle);
            Cell h2 = header.createCell(2); h2.setCellValue("Status");       h2.setCellStyle(headerStyle);

            // ── PASS style (green fill, bold, white font) ─────────────────────
            CellStyle passStyle = wb.createCellStyle();
            passStyle.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
            passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font passFont = wb.createFont();
            passFont.setBold(true);
            passStyle.setFont(passFont);

            // ── FAIL style (red fill, bold) ───────────────────────────────────
            CellStyle failStyle = wb.createCellStyle();
            failStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            Font failFont = wb.createFont();
            failFont.setBold(true);
            failStyle.setFont(failFont);

            // ── Dash style (grey, centered) ───────────────────────────────────
            CellStyle dashStyle = wb.createCellStyle();
            dashStyle.setAlignment(HorizontalAlignment.CENTER);
            Font dashFont = wb.createFont();
            dashFont.setColor(IndexedColors.GREY_50_PERCENT.getIndex());
            dashStyle.setFont(dashFont);

            // ── Data rows ─────────────────────────────────────────────────────
            for (int i = 0; i < results.size(); i++) {
                Row row = sheet.createRow(i + 1);
                int price = Integer.parseInt(results.get(i)[1]);

                row.createCell(0).setCellValue(results.get(i)[0]);
                row.createCell(1).setCellValue(price);

                Cell statusCell = row.createCell(2);
                if (i == 0) {
                    // First mobile — validate price
                    if (price < 30000) {
                        statusCell.setCellValue("PASS");
                        statusCell.setCellStyle(passStyle);
                    } else {
                        statusCell.setCellValue("FAIL");
                        statusCell.setCellStyle(failStyle);
                    }
                } else {
                    // Other mobiles — dash
                    statusCell.setCellValue("-");
                    statusCell.setCellStyle(dashStyle);
                }
            }

            // ── Auto-size columns ─────────────────────────────────────────────
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);

            FileOutputStream fos = new FileOutputStream(excelPath);
            wb.write(fos);
            fos.close();
            wb.close();
        } catch (Exception ignored) {}
    }
}
