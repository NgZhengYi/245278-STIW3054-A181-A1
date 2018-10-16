import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Excel {
    private String[] columns = {"No", "Item", "Result"};

    public void writeExcel(final ArrayList<Trivia> triviaArrayList) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Trivia");

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create a Font for styling cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLUE.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Insert data
        int count = 1;
        for (Trivia t : triviaArrayList) {
            Row row = sheet.createRow(count);
            row.createCell(0).setCellValue(count);
            row.createCell(1).setCellValue(t.getC1());
            row.createCell(2).setCellValue(t.getC2());
            count++;
        }

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
