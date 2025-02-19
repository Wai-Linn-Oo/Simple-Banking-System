public class BankAccount extends Bank{
    // bank account property
    protected String accountName;
    protected String accountId;
    protected String accountPassword;
    protected double accountBalance;
    protected double transaction;

    // Constructor of BankAccount
    public BankAccount(String bankName,String branchNo,String branchAddress,String accountName, String accountId, String accountPassword, double accountBalance, double transaction) {
        this.bankName = bankName;
        this.branchNo = branchNo;
        this.branchAddress = branchAddress;
        this.accountName =  accountName;
        this.accountId = accountId;
        this.accountPassword = accountPassword;
        this.accountBalance = accountBalance;
        this.transaction = transaction;
    }

    // Validating User Input to ArrayList
    public boolean validateCredentials(String userName, String userPassword) {
        return accountName.equalsIgnoreCase(userName) && accountPassword.equalsIgnoreCase(userPassword);
    }
} // class close tag
