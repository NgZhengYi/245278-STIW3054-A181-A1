import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Excel {
    private String[] columns = {"No", "Item", "Result"}; // Header Item

    /**
     *  Printing Array Record into Excel File
     */
    public void writeExcel(final ArrayList<Trivia> triviaArrayList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Trivia");

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create a Font for styling cells
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());
        headerFont.setFontName("Arial");

        // Create a CellStyle with the font
        XSSFCellStyle headerXSSFCellStyle = workbook.createCellStyle();
        headerXSSFCellStyle.setFont(headerFont);
        headerXSSFCellStyle.setAlignment(HorizontalAlignment.CENTER);
        /*
        XSSFFont contentFont = workbook.createFont();
        contentFont.setFontHeightInPoints((short) 14);
        contentFont.setFontName("Arial");

        XSSFCellStyle contentCellStyle = workbook.createCellStyle();
        contentCellStyle.setFont(contentFont);
        */
        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerXSSFCellStyle);
        }

        // Insert table data
        int count = 1;
        for (Trivia t : triviaArrayList) {
            Row row = sheet.createRow(count);
            row.createCell(0).setCellValue(count);
            row.createCell(1).setCellValue(t.getKey());
            row.createCell(2).setCellValue(t.getValue());
            count++;
        }
        /*
        int count = 1;
        for (Trivia trivia : triviaArrayList) {
            Row row = sheet.createRow(count);
            Cell cellColumn1 = row.createCell(0);
            cellColumn1.setCellValue(count);
            cellColumn1.setCellStyle(contentCellStyle);
            Cell cellColumn2 = row.createCell(1);
            cellColumn2.setCellValue(trivia.getKey());
            cellColumn2.setCellStyle(contentCellStyle);
            Cell cellColumn3 = row.createCell(2);
            cellColumn3.setCellValue(trivia.getValue());
            cellColumn3.setCellStyle(contentCellStyle);
            count++;
        }
        */
        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("Trivia.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();

        System.out.println("Successfully Printed in Excel File");
    }
}
