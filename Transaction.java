import java.time.LocalDate;

class Transaction {
    private String date;
    private String description;
    private double amount;
    private String type; // Credit or Debit

    public Transaction(String description, double amount, String type) {
        this.date = String.valueOf(LocalDate.now());
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    public String getDate() { return date; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
    public String getType() { return type; }
}