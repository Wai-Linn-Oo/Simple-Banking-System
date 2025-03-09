import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    BankAccount bankAccount;

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
        System.out.println("4-Show Transaction");
        System.out.println("5-Print Bank Statement");
        System.out.println("6-Account Information");
        System.out.println("7-Exit");
        System.out.println("===================================");
        System.out.print("Your choice: ");
        char choice = scanner.next().charAt(0);

        switch (choice) {
            case '1' -> getBalance();
            case '2' -> Deposit();
            case '3' -> withdrawal();
            case '4' -> showTransaction();
            case '5' -> printBankStatement();
            case '6' -> displayAccInfo();
            case '7' -> exit();
            default -> System.out.println("Out of the service!");
        }
        recallMenu();
    }

    // 1. function of Bank Account
    private void getBalance() {
        System.out.println("Your current balance: $" + bankAccount.accountBalance);
    }

    // 2. function of Bank Account
    private void Deposit() {
        String description = "Cash In";
        String type = "Credit";
        boolean completeDeposit = false;
        double minAmount = 100;
        double amount;

        while (!completeDeposit) {
            System.out.print("Enter the amount of deposit: $");
            amount = scanner.nextDouble();
            if (amount >= minAmount) {
                bankAccount.accountBalance += amount;
                bankAccount.transactions.add(new Transaction(description, amount, type));
                completeDeposit = true;
                System.out.println("Your account is successfully deposited");
            } else {
                System.out.println("Deposit must be at least $100 and try again!");
            }
        }
    } // method close tag

    // 3. function of Bank Account
    private void withdrawal() {
        String description = "Cash Out";
        String type = "Debit";
        boolean completeWithdrawal = false;
        double amount;

        while (!completeWithdrawal) {
            System.out.print("Enter the amount of withdrawal: $");
            amount = scanner.nextDouble();
            if (amount <= bankAccount.accountBalance) {
                bankAccount.accountBalance -= amount;
                bankAccount.transactions.add(new Transaction(description, amount, type));
                completeWithdrawal = true;
                System.out.println("Your account is successfully withdrawal");
            } else {
                System.out.println("Insufficient Balance!");
            }
        }
    } // method close tag

    // 4. function of Bank Account
    private void showTransaction() {
        if (!bankAccount.transactions.isEmpty()) {
            System.out.printf("%-15s %-25s %-10s %-10s%n", "Date", "Description", "Amount", "Type");
            System.out.println("---------------------------------------------------------------");

            // Print transaction data
            for (Transaction transaction : bankAccount.transactions) {
                System.out.printf("%-15s %-25s $%-9.2f %-10s%n",
                        transaction.getDate(),
                        transaction.getDescription(),
                        transaction.getAmount(),
                        transaction.getType());
            }
        } else {
            System.out.println("There is no transaction");
        }
    } // method close tag

    // 5. function of Bank Account
    private void printBankStatement() {
        if (!bankAccount.transactions.isEmpty()) {
            try {
                // Generate the PDF statement
                BankStatementGenerator.generateStatement(
                        bankAccount.accountName,
                        bankAccount.accountNumber,
                        bankAccount.accountType,
                        bankAccount.accountBalance,
                        bankAccount.transactions);
                System.out.println("Bank statement generated successfully!");

            } catch (Exception e) {
                System.out.println("Something went wrong!");
                recallMenu();
            }

        } else {
            System.out.println("There is no transaction to print bank statement");
        }
    } // method close tag

    // 6. function of Bank Account
    private void displayAccInfo() {
        System.out.println("==============================================");
        System.out.println("Account Type: " + bankAccount.accountType);
        System.out.println("Account name: " + bankAccount.accountName);
        System.out.println("Account number: " + bankAccount.accountNumber);
        System.out.println("Account password: " + bankAccount.accountPassword);
        System.out.println("Bank name: " + bankAccount.bankName);
        System.out.println("Bank branch No: " + bankAccount.branchNo);
        System.out.println("Bank branch address: " + bankAccount.branchAddress);
        System.out.println("===============================================");
    } // method close tag

    // 7. function of Bank Account
    private void exit() {
        System.out.println("Thank you for using our banking system");
        scanner.close();
        System.exit(0);
    } // method close tag

    // recall Menu
    private void recallMenu() {
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
