import java.util.ArrayList;

public class BankAccount extends Bank{
    // bank account property
    protected String accountName;
    protected String accountNumber;
    protected String accountPassword;
    protected String address;
    protected String accountType;
    protected double accountBalance;
    ArrayList<Transaction> transactions;

    // Constructor of BankAccount
     BankAccount( String accountNumber, String accountName, String accountPassword, String address, double accountBalance,String branchNo,String branchAddress) {
        this.accountType = "Saving";
        this.accountNumber = accountNumber;
        this.accountName =  accountName;
        this.accountPassword = accountPassword;
        this.address = address;
        this.accountBalance = accountBalance;
        bankName = "JAVA BANK";
        this.branchNo = branchNo;
        this.branchAddress = branchAddress;
        this.transactions = new ArrayList<>();
    }

    // Validating UserInfo to BankAccounts ArrayLIst
    boolean validateCredentials(String userName, String userPassword) {
        return accountName.equalsIgnoreCase(userName) && accountPassword.equalsIgnoreCase(userPassword);
    }
} // class close tag




