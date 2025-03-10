package Models;

import java.time.LocalDate;
import java.util.Scanner;

public class Transaction {
    // Transaction property
    private String date;
    private String description;
    private double amount;
    private String type; // Credit or Debit

    // Constructor or setter of Transaction
    public Transaction(String description, double amount, String type) {
        this.date = String.valueOf(LocalDate.now());
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    // Getter for Transaction property
    public String getDate() { return date; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getType() { return type; }

}