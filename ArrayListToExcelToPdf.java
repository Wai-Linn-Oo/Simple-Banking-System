import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayListToExcelToPdf {

    public static void main(String[] args) {
        try {
            // Sample ArrayList data (replace with your actual data)
            List<Person> peopleList = new ArrayList<>();
            peopleList.add(new Person("Louis", 30, "Software Engineer"));
            peopleList.add(new Person("Jane Smith", 28, "Data Scientist"));
            peopleList.add(new Person("Robert Johnson", 35, "Project Manager"));
            peopleList.add(new Person("Emily Davis", 26, "UX Designer"));

            // Path to the existing Excel file
            String excelFilePath = "existing_file.xlsx";

            // Update the Excel file with ArrayList data
            updateExcelWithArrayList(excelFilePath, peopleList);

            // Convert the updated Excel to PDF
            convertExcelToPdf(excelFilePath, "output.pdf");

            System.out.println("Process completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing Excel file with data from an ArrayList
     */
    private static void updateExcelWithArrayList(String excelFilePath, List<Person> peopleList) throws IOException {
        // Load the existing Excel file
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(inputStream);

        // Get the first sheet or create it if it doesn't exist
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            sheet = workbook.createSheet("Data");
        }

        // Create header row if the sheet is empty
        if (sheet.getPhysicalNumberOfRows() == 0) {
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Age");
            headerRow.createCell(2).setCellValue("Occupation");
        }

        // Find the last row to start appending data
        int lastRowNum = sheet.getLastRowNum();
        if (sheet.getPhysicalNumberOfRows() > 0) {
            lastRowNum++; // Start from the next row
        }

        // Add data from ArrayList to Excel
        for (int i = 0; i < peopleList.size(); i++) {
            Person person = peopleList.get(i);
            Row row = sheet.createRow(lastRowNum + i);

            Cell nameCell = row.createCell(0);
            nameCell.setCellValue(person.getName());

            Cell ageCell = row.createCell(1);
            ageCell.setCellValue(person.getAge());

            Cell occupationCell = row.createCell(2);
            occupationCell.setCellValue(person.getOccupation());
        }

        // Auto-size columns for better readability
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
        }

        // Save the updated Excel file
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    /**
     * Converts an Excel file to PDF format
     */
    private static void convertExcelToPdf(String excelFilePath, String pdfFilePath) throws Exception {
        // Load the Excel file
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Create a PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
        document.open();

        // Create a table with the appropriate number of columns
        int numColumns = getMaxColumns(sheet);
        PdfPTable table = new PdfPTable(numColumns);
        table.setWidthPercentage(100);

        // Add data from Excel to PDF
        for (Row row : sheet) {
            for (int i = 0; i < numColumns; i++) {
                Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                String cellValue = getCellValueAsString(cell);

                PdfPCell pdfCell = new PdfPCell();
                pdfCell.setPadding(5);
                pdfCell.setPhrase(new com.itextpdf.text.Phrase(cellValue));
                table.addCell(pdfCell);
            }
        }

        // Add the table to the document
        document.add(table);

        // Close resources
        document.close();
        workbook.close();
        inputStream.close();
    }

    /**
     * Helper method to get the maximum number of columns in a sheet
     */
    private static int getMaxColumns(Sheet sheet) {
        int maxColumns = 0;
        for (Row row : sheet) {
            maxColumns = Math.max(maxColumns, row.getLastCellNum());
        }
        return maxColumns;
    }

    /**
     * Helper method to convert cell values to string regardless of type
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    /**
     * Sample Person class for demonstration
     */
    static class Person {
        private String name;
        private int age;
        private String occupation;

        public Person(String name, int age, String occupation) {
            this.name = name;
            this.age = age;
            this.occupation = occupation;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public String getOccupation() {
            return occupation;
        }
    }
}
