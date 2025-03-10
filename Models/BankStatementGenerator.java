package Models;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BankStatementGenerator {

    public static void generateStatement(String customerName, String accountNumber,
                                         String accountType, double currentBalance,
                                         List<Transaction> transactions) throws Exception {

        // Create document with A4 size and write pdf data from document to BankStatement.pdf
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("BankStatement.pdf"));
        document.open();

        // Add bank logo and header (optional)
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
        Paragraph header = new Paragraph("JAVA Bank Statement", headerFont);
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        document.add(Chunk.NEWLINE);

        // Add current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String currentDate = dateFormat.format(new Date());
        Paragraph dateP = new Paragraph("Statement Date: " + currentDate);
        dateP.setAlignment(Element.ALIGN_RIGHT);
        document.add(dateP);
        document.add(Chunk.NEWLINE);

        // Add account information
        Font accountInfoFont = new Font(Font.FontFamily.HELVETICA, 12);
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

        Paragraph accountInfo = new Paragraph();
        accountInfo.add(new Chunk("Customer Name: ", boldFont));
        accountInfo.add(new Chunk(customerName, accountInfoFont));
        document.add(accountInfo);

        Paragraph accountNumberP = new Paragraph();
        accountNumberP.add(new Chunk("Account Number: ", boldFont));
        accountNumberP.add(new Chunk(accountNumber, accountInfoFont));
        document.add(accountNumberP);

        Paragraph accountTypeP = new Paragraph();
        accountTypeP.add(new Chunk("Account Type: ", boldFont));
        accountTypeP.add(new Chunk(accountType, accountInfoFont));
        document.add(accountTypeP);

        Paragraph balanceP = new Paragraph();
        balanceP.add(new Chunk("Current Balance: ", boldFont));
        balanceP.add(new Chunk("$" + String.format("%.2f", currentBalance), accountInfoFont));
        document.add(balanceP);

        document.add(Chunk.NEWLINE);

        // Add transactions table
        PdfPTable table = getPdfPTable(transactions);

        document.add(table);

        // Add footer with summary
        document.add(Chunk.NEWLINE);
        Paragraph footer = new Paragraph("Thank you for banking with JAVA Bank!",
                new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC));
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);

        // Close the document
        document.close();



        // open Bank Statement file automatically if file exits
        File pdfFile = new File("BankStatement.pdf");
        if (pdfFile.exists()) {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            } else {
                System.out.println("PDF created at: " + pdfFile.getAbsolutePath());
            }
        }

    }
    // Format transaction Table
    private static PdfPTable getPdfPTable(List<Transaction> transactions) throws DocumentException {
        PdfPTable table = new PdfPTable(4); // 4 columns
        table.setWidthPercentage(100);

        // Set column widths
        float[] columnWidths = {2f, 5f, 2f, 2f};
        table.setWidths(columnWidths);

        // Add table headers
        Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        BaseColor headerBgColor = new BaseColor(66, 134, 244);

        PdfPCell dateCell = new PdfPCell(new Phrase("Date", tableHeaderFont));
        dateCell.setBackgroundColor(headerBgColor);
        dateCell.setPadding(5);
        table.addCell(dateCell);

        PdfPCell descriptionCell = new PdfPCell(new Phrase("Description", tableHeaderFont));
        descriptionCell.setBackgroundColor(headerBgColor);
        descriptionCell.setPadding(5);
        table.addCell(descriptionCell);

        PdfPCell amountCell = new PdfPCell(new Phrase("Amount", tableHeaderFont));
        amountCell.setBackgroundColor(headerBgColor);
        amountCell.setPadding(5);
        table.addCell(amountCell);

        PdfPCell typeCell = new PdfPCell(new Phrase("Type", tableHeaderFont));
        typeCell.setBackgroundColor(headerBgColor);
        typeCell.setPadding(5);
        table.addCell(typeCell);

        // Add transaction data
        Font dataFont = new Font(Font.FontFamily.HELVETICA, 10);
        BaseColor lightBlue = new BaseColor(242, 247, 255);
        boolean alternateRow = false;

        for (Transaction transaction : transactions) {
            PdfPCell cell1 = new PdfPCell(new Phrase(transaction.getDate(), dataFont));
            PdfPCell cell2 = new PdfPCell(new Phrase(transaction.getDescription(), dataFont));
            PdfPCell cell3 = new PdfPCell(new Phrase("$" + String.format("%.2f", transaction.getAmount()), dataFont));
            PdfPCell cell4 = new PdfPCell(new Phrase(transaction.getType(), dataFont));

            if (alternateRow) {
                cell1.setBackgroundColor(lightBlue);
                cell2.setBackgroundColor(lightBlue);
                cell3.setBackgroundColor(lightBlue);
                cell4.setBackgroundColor(lightBlue);
            }

            cell1.setPadding(5);
            cell2.setPadding(5);
            cell3.setPadding(5);
            cell4.setPadding(5);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);

            alternateRow = !alternateRow;
        }
        return table;
    } // method close tag
} // class close tag
