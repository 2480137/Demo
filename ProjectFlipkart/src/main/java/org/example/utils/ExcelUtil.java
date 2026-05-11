package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtil {

    public static String getCellData(String filePath, int rowNum, int colNum) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);
            return sheet.getRow(rowNum).getCell(colNum).toString();
        }
        catch (Exception e) {
            throw new RuntimeException("Error reading Excel");
        }
    }
}
