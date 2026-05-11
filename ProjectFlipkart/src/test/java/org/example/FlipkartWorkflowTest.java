package org.example;

import org.example.base.BaseTest;
import org.example.utils.ExcelUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FlipkartWorkflowTest extends BaseTest {

    private static final String EXCEL_PATH  = "testdata/TestData.xlsx";
    private static final int    PRICE_LIMIT = 30_000;

    private WebDriverWait wait;
    private WebDriverWait shortWait;

    // ── Data Provider ────────────────────────────────────────────────────────

    /**
     * Reads all data rows from Excel.
     * Adjust the row count to match how many rows are in your sheet.
     */
    @DataProvider(name = "browserData")
    public Object[][] getBrowserData() {
        // Read how many rows exist by trying each until failure
        List<Object[]> rows = new ArrayList<>();
        int row = 1;
        while (true) {
            try {
                String browser    = ExcelUtil.getCellData(EXCEL_PATH, row, 0);
                String searchKey  = ExcelUtil.getCellData(EXCEL_PATH, row, 1);
                rows.add(new Object[]{ browser, searchKey });
                row++;
            } catch (Exception e) {
                break;
            }
        }
        return rows.toArray(new Object[0][]);
    }

    // ── Lifecycle ────────────────────────────────────────────────────────────

    @BeforeMethod
    public void setUp(Object[] params) {
        String browser = (String) params[0];
        System.out.println("\n========================================");
        System.out.println("Browser : " + browser);
        System.out.println("========================================");
        driver    = launchBrowser(browser);
        wait      = new WebDriverWait(driver, Duration.ofSeconds(15));
        shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println("Result: " + (result.isSuccess() ? "PASS" : "FAIL"));
        if (driver != null) driver.quit();
    }

    // ── Test ─────────────────────────────────────────────────────────────────

    @Test(dataProvider = "browserData",
          description  = "Search → product page → price validate → cart → checkout")
    public void testFlipkartWorkflow(String browser, String keyword)
            throws InterruptedException {

        // TC-01 – Open & search ───────────────────────────────────────────────
        driver.get("https://www.flipkart.com");
        System.out.println("[1] Opened Flipkart.");

        dismissPopup();

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.sendKeys(keyword);
        Thread.sleep(500);
        searchBox.sendKeys(Keys.ENTER);
        System.out.println("[2] Searched: " + keyword);

        List<WebElement> productLinks = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(
                    "//a[contains(@href,'/p/') and normalize-space(text())!='']")));

        Assert.assertFalse(productLinks.isEmpty(),
            "TC-01 FAIL: No products found for '" + keyword + "'");
        System.out.println("[3] Products found: " + productLinks.size());

        // TC-02 – Open first product & validate price ─────────────────────────
        String firstName = productLinks.get(0).getText().trim();
        productLinks.get(0).click();
        System.out.println("[4] Opened: " + firstName);
        switchToNewTabIfPresent();

        int price = extractPagePrice();
        System.out.printf("[5] Price: Rs.%,d%n", price);

        Assert.assertTrue(price > 0, "TC-02 FAIL: Price could not be read.");
        Assert.assertTrue(price < PRICE_LIMIT,
            "TC-02 FAIL: Price Rs." + price + " >= Rs." + PRICE_LIMIT);
        System.out.println("[5] PASS – price is below Rs." + PRICE_LIMIT);

        // TC-03 – Cart handling ───────────────────────────────────────────────
        boolean cartClicked = clickCartOrBuyNow();

        // TC-04 – Checkout / payment redirection ──────────────────────────────
        if (cartClicked) {
            verifyRedirect();
        } else {
            System.out.println("[7] Cart step skipped (variant selection needed).");
        }
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private void dismissPopup() {
        String[] xpaths = {
            "//button[text()='✕']",
            "//button[contains(text(),'✕')]",
            "//span[text()='✕']/..",
            "(//button)[2]"
        };
        for (String xp : xpaths) {
            try {
                shortWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xp))).click();
                System.out.println("[popup] closed.");
                return;
            } catch (Exception ignored) { }
        }
        System.out.println("[popup] none detected.");
    }

    private void switchToNewTabIfPresent() {
        if (driver.getWindowHandles().size() > 1) {
            String newHandle = driver.getWindowHandles().stream()
                .filter(h -> !h.equals(driver.getWindowHandle()))
                .findFirst().orElseThrow();
            driver.switchTo().window(newHandle);
        }
    }

    private int extractPagePrice() {
        try {
            WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "(//*[starts-with(normalize-space(text()),'₹')" +
                " and not(.//*[starts-with(normalize-space(text()),'₹')])])[1]")));
            return Integer.parseInt(el.getText().replaceAll("[^0-9]", ""));
        } catch (Exception e) {
            System.out.println("  [WARN] Price unreadable: " + e.getMessage());
            return -1;
        }
    }

    private boolean clickCartOrBuyNow() throws InterruptedException {
        String[] labels = { "ADD TO CART", "Add to Cart", "BUY NOW", "Buy Now" };
        for (String label : labels) {
            try {
                WebElement btn = shortWait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space(.)='" + label + "']")));
                ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});", btn);
                Thread.sleep(300);
                btn.click();
                System.out.println("[6] Clicked: " + label);
                return true;
            } catch (Exception ignored) { }
        }
        System.out.println("[6] No cart / buy button found.");
        return false;
    }

    private void verifyRedirect() throws InterruptedException {
        Thread.sleep(3000);
        String url   = driver.getCurrentUrl().toLowerCase();
        String title = driver.getTitle().toLowerCase();

        boolean ok = url.contains("cart") || url.contains("login")
                  || url.contains("checkout") || title.contains("cart")
                  || title.contains("login");

        System.out.println("[7] Redirect URL: " + driver.getCurrentUrl());
        System.out.println("[7] " + (ok ? "PASS – redirection verified." : "INFO – unexpected URL."));

        Assert.assertTrue(ok,
            "TC-04 FAIL: Expected cart/login/checkout redirect. Actual: " + driver.getCurrentUrl());
    }
}
