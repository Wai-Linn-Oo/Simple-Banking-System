import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    BankAccount bankAccount;

    // variable for deposit and withdrawal
    double amount;

    // Constructor of Menu Class
    public Menu(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    // Display Menu function
    public void displayMenu() {

        System.out.println("==================================");
        System.out.println("Please choice the following service ");
        System.out.println();
        System.out.println("1-Account Balance");
        System.out.println("2-Deposit");
        System.out.println("3-Withdrawal");
        System.out.println("4-Previous Transaction");
        System.out.println("5-Account Information");
        System.out.println("6-Exit");
        System.out.println("===================================");
        System.out.print("Your choice: ");
        char choice = scanner.next().charAt(0);

        switch (choice) {
            case '1' -> getBalance();
            case '2' -> getDeposit();
            case '3' -> withdrawal();
            case '4' -> previousTransaction();
            case '5' -> displayAccInfo(bankAccount);
            case '6' -> exit();
            default -> System.out.println("Out of the service!");
        }
        recallMenu();
    }

    // 1. function of Bank Account
    public void getBalance() {
        System.out.println("Your current balance: $" + bankAccount.accountBalance);

    }

    // 2. function of Bank Account
    public void getDeposit() {
        boolean completeDeposit = false;
        double minAmount = 500;
        while (!completeDeposit) {
            System.out.print("Enter the amount of deposit: $");
            amount = scanner.nextDouble();
            if (amount > minAmount) {
                bankAccount.accountBalance += amount;
                bankAccount.transaction = amount;
                completeDeposit = true;
                System.out.println("Your account is successfully deposited");
            } else {
                System.out.println("Deposit must be at least $500 and try again!");
            }
        }
    } // method close tag

    // 3. function of Bank Account
    public void withdrawal() {
        boolean completeWithdrawal = false;
        while (!completeWithdrawal) {
            System.out.print("Enter the amount of withdrawal: $");
            amount = scanner.nextDouble();
            if (amount <= bankAccount.accountBalance) {
                bankAccount.accountBalance -= amount;
                bankAccount.transaction = -amount;
                completeWithdrawal = true;
                System.out.println("Your account is successfully withdrawal");
            } else {
                System.out.println("Insufficient Balance!");
            }
        }
    } // method close tag

    // 4. function of Bank Account
    public void previousTransaction() {
        if (bankAccount.transaction > 0) {
            System.out.println("Deposit: " + bankAccount.transaction + "$");
        } else if (bankAccount.transaction < 0) {
            System.out.println("Withdrawal: " + bankAccount.transaction + "$");
        } else {
            System.out.println("There is no transaction.");
        }
    } // method close tag

    // 5. function of Bank Account
    public void displayAccInfo(BankAccount bankAccount) {
        System.out.println("==============================================");
        System.out.println("Your account name: " + bankAccount.accountName);
        System.out.println("Your account number: " + bankAccount.accountId);
        System.out.println("Your account password: " + bankAccount.accountPassword);
        System.out.println("Your account branch number: " + bankAccount.branchNo);
        System.out.println("Your account branch address: " + bankAccount.branchAddress);
        System.out.println("Your account bank name: " + bankAccount.bankName);
        System.out.println("===============================================");
    } // method close tag

    // 6. function of Bank Account
    public void exit() {
        System.out.println("Thank you for using our banking system");
        System.exit(0);
    } // method close tag

    // recall Menu based on user input after menu function
    public void recallMenu() {
        boolean invalid = false;
        do {
            System.out.print("Go back to MENU? (Y or N): ");
            char recall = scanner.next().toUpperCase().charAt(0);

            if (recall == 'Y') {
                displayMenu();
            } else if (recall == 'N') {
                exit();
            } else {
                System.out.println("Invalid!");
                invalid = true;
            }
        } while (invalid);

    } // method close tag

} // class close tag
