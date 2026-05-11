package org.example.pages;

import org.example.base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebDriverWait shortWait;
    private final JavascriptExecutor js;

    public SearchResultsPage(WebDriver driver, WebDriverWait wait, WebDriverWait shortWait, JavascriptExecutor js) {
        this.driver    = driver;
        this.wait      = wait;
        this.shortWait = shortWait;
        this.js        = js;
    }

    public void verifyResults() {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'/p/') and normalize-space()!='']")));
        BaseTest.takeScreenshot(driver, "05_search_results");
    }

    public void applyPriceFilter() throws InterruptedException {
        Thread.sleep(500);
        try {
            WebElement selectEl = shortWait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='WoGl7t']//select[@class='hbnjE2']")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", selectEl);
            Thread.sleep(300);
            Select priceSelect = new Select(selectEl);
            boolean selected = false;
            for (WebElement opt : priceSelect.getOptions()) {
                String optText = opt.getText();
                if (optText.contains("10,000") || optText.replaceAll("[^0-9]", "").equals("10000")) {
                    priceSelect.selectByVisibleText(optText);
                    selected = true;
                    break;
                }
            }
            if (!selected) priceSelect.selectByIndex(1);
            Thread.sleep(800);
            BaseTest.takeScreenshot(driver, "06_price_filter");
        } catch (Exception ignored) {}
    }

    public void applyOsFilter() throws InterruptedException {
        try {
            WebElement osSection = shortWait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(text(),'Operating System Version Name')]")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", osSection);
            Thread.sleep(400);
            osSection.click();
            Thread.sleep(500);
            BaseTest.takeScreenshot(driver, "07a_os_section");

            WebElement showMore = shortWait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[normalize-space()='12 MORE']")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", showMore);
            Thread.sleep(200);
            showMore.click();
            Thread.sleep(500);
            BaseTest.takeScreenshot(driver, "07b_os_expanded");

            WebElement pieCheckbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@title='Pie']//div[@class='ybaCDx']")));
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", pieCheckbox);
            Thread.sleep(200);
            pieCheckbox.click();
            Thread.sleep(800);
            BaseTest.takeScreenshot(driver, "07c_pie_selected");
        } catch (Exception ignored) {}
    }

    public void sortByNewest() throws InterruptedException {
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
            "//div[normalize-space(text())='Newest First'] | " +
            "//li[normalize-space(text())='Newest First']"))).click();
        Thread.sleep(1000);
        BaseTest.takeScreenshot(driver, "08_sorted_newest_first");
    }

    public List<String[]> getFirst5Products() {
        List<WebElement> productLinks = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                    By.xpath("//a[contains(@href,'/p/') and normalize-space()!='']"), 0));

        System.out.println("\n First 5 New Mobiles");
        System.out.println("=".repeat(60));

        List<String[]> results = new ArrayList<>();
        int count = 0;

        for (WebElement link : productLinks) {
            if (count >= 5) break;

            String fullText = link.getText().trim();
            if (fullText.isEmpty()) continue;

            String name = Arrays.stream(fullText.split("\n"))
                .map(String::trim)
                .filter(l -> !l.isEmpty()
                          && !l.equalsIgnoreCase("Add to Compare")
                          && !l.equalsIgnoreCase("Coming Soon"))
                .findFirst().orElse(null);
            if (name == null || name.isEmpty()) continue;

            List<WebElement> cardPrices = link.findElements(By.xpath(
                ".//*[starts-with(normalize-space(text()),'₹')" +
                " and not(.//*[starts-with(normalize-space(text()),'₹')])]"));
            if (cardPrices.isEmpty()) continue;

            String priceStr = cardPrices.get(0).getText().replaceAll("[^0-9]", "");
            if (priceStr.isEmpty()) continue;
            int price = Integer.parseInt(priceStr);

            System.out.println((count + 1) + ". " + name + "  ->  Rs." + price);

            if (count == 0) {
                System.out.println("Price Validation: Rs." + price
                    + (price < 30000 ? " < Rs.30,000  PASS" : " >= Rs.30,000  FAIL"));
            }

            results.add(new String[]{name, String.valueOf(price)});
            count++;
        }

        BaseTest.takeScreenshot(driver, "09_final_results");
        return results;
    }
}
