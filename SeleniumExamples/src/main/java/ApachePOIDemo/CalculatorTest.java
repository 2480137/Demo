package ApachePOIDemo;

//import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CalculatorTest {

    public static void main(String[] args) throws Exception {

        // File Paths
        //  String excelPath = "C:\\Users\\532252\\IdeaProjects\\sample1\\Resource";
//input & ouput
        String excelPath ="C:\\Users\\2480137\\IdeaProjects\\SeleniumExamples\\Resource\\TestData.xlsx";

        String htmlPath = "file:///C:/Users/2480137/IdeaProjects/SeleniumExamples/Resource/Calc.html"; // CHANGE THIS

        // Setup Selenium
        // WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(htmlPath);

        // Excel Setup
        FileInputStream fis = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        // Styles
        CellStyle passStyle = workbook.createCellStyle();
        passStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle failStyle = workbook.createCellStyle();
        failStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
        failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);
//Read values from Excel sheet
            double input1 = row.getCell(0).getNumericCellValue();
            double input2 = row.getCell(1).getNumericCellValue();
            String operation = row.getCell(2).getStringCellValue().toLowerCase();
            double expected = row.getCell(3).getNumericCellValue();
//pass the read value to the site input fields
            // Enter values in UI
            driver.findElement(By.id("num1")).clear();
            driver.findElement(By.id("num1")).sendKeys(String.valueOf(input1));

            driver.findElement(By.id("num2")).clear();
            driver.findElement(By.id("num2")).sendKeys(String.valueOf(input2));

            // Normalize dropdown value
            Select select = new Select(driver.findElement(By.id("operation")));
            select.selectByValue(operation);

            driver.findElement(By.tagName("button")).click();

            // Get Result
            String resultText = driver.findElement(By.id("result"))
                    .getText()
                    .replace("Result:", "")
                    .trim();

            double actual = Double.parseDouble(resultText);

            // Write Actual
            Cell actualCell = row.createCell(4);
            actualCell.setCellValue(actual);

            // Write Status
            Cell statusCell = row.createCell(5);

// Write the output in the excel sheet
            if (actual == expected) {
                statusCell.setCellValue("PASS");
                statusCell.setCellStyle(passStyle);
            } else {
                statusCell.setCellValue("FAIL");
                statusCell.setCellStyle(failStyle);
            }
        }
        // Save Excel
        fis.close();
        FileOutputStream fos = new FileOutputStream(excelPath);
        workbook.write(fos);
        fos.close();
        workbook.close();
        driver.quit();
        System.out.println("Test execution completed.");
    }
}

