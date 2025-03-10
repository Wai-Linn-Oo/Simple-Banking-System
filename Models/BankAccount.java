package Models;

import java.util.ArrayList;

public class BankAccount extends Bank {
    // bank account property
    private String accountName;
    private String accountNumber;
    private String accountPassword;
    private String address;
    private String accountType;
    private double accountBalance;
    private ArrayList<Transaction> transactions;

    // Constructor of Models.BankAccount
    public BankAccount(String accountNumber, String accountName, String accountPassword, String address, double accountBalance, String branchNo, String branchAddress) {
        this.accountType = "Saving";
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.address = address;
        this.accountBalance = accountBalance;
        this.branchNo = branchNo;
        this.branchAddress = branchAddress;
        this.transactions = new ArrayList<>();
    }

    // getter methods of BankAccount property
    public String getAccountName() { return accountName;}
    public String getAccountNumber() { return accountNumber;}
    public String getAccountPassword() { return accountPassword;}
    public String getAddress() { return address;}
    public String getAccountType() { return accountType;}
    public double getAccountBalance() { return accountBalance;}
    public ArrayList<Transaction> getTranscationList() { return transactions;}

    // setter method of Bank Balance
    public void setAccountBalance(double amount) {
        this.accountBalance = amount;
    }

    // Validating UserInfo to BankAccounts ArrayLIst
    public boolean validateCredentials(String userName, String userPassword) {
        return accountName.equalsIgnoreCase(userName) && accountPassword.equalsIgnoreCase(userPassword);
    }
} // class close tag




