package View;

import Models.BankAccount;
import Models.Transaction;
import Models.BankStatementGenerator;

import java.awt.*;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    BankAccount bankAccount;

    // Constructor of view.Menu Class
    public Menu(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    // Display view.Menu function
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
        callSound();
        System.out.println("Your current balance: $" + bankAccount.getAccountBalance());
    }

    // 2. function of Bank Account
    private void Deposit() {
        String description;
        String type = "Credit";
        boolean completeDeposit = false;
        double minAmount = 100;
        double amount;

        while (!completeDeposit) {
            System.out.print("Enter the amount of deposit: $");
            amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Please add notes(skip press ENTER): ");
            description = scanner.nextLine();
            description = description.isBlank() ? "Cash In" : description;

            if (amount >= minAmount) {
                bankAccount.setAccountBalance(bankAccount.getAccountBalance() + amount);
                bankAccount.getTranscationList().add(new Transaction(description, amount, type));
                completeDeposit = true;
                System.out.println("Your account is successfully deposited");
                callSound();
            } else {
                System.out.println("Deposit must be at least $100 and try again!");
            }
        }
    } // method close tag

    // 3. function of Bank Account
    private void withdrawal() {
        String description;
        String type = "Debit";
        boolean completeWithdrawal = false;
        double amount;

        while (!completeWithdrawal) {
            System.out.print("Enter the amount of withdrawal: $");
            amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Please add notes(skip press ENTER): ");
            description = scanner.nextLine();
            description = description.isBlank() ? "Cash Out" : description;

            if (amount <= bankAccount.getAccountBalance()) {
                bankAccount.setAccountBalance(bankAccount.getAccountBalance() - amount);
                bankAccount.getTranscationList().add(new Transaction(description, amount, type));
                completeWithdrawal = true;
                System.out.println("Your account is successfully withdrawal");
                callSound();
            } else {
                System.out.println("Insufficient Balance!");
            }
        }
    } // method close tag

    // 4. function of Bank Account
    private void showTransaction() {
        if (!bankAccount.getTranscationList().isEmpty()) {
            System.out.printf("%-15s %-25s %-10s %-10s%n", "Date", "Description", "Amount", "Type");
            System.out.println("---------------------------------------------------------------");

            // Print transaction data
            for (Transaction transaction : bankAccount.getTranscationList()) {
                System.out.printf("%-15s %-25s $%-9.2f %-10s%n",
                        transaction.getDate(),
                        transaction.getDescription(),
                        transaction.getAmount(),
                        transaction.getType());
            }
            callSound();
        } else {
            System.out.println("There is no transaction");
        }
    } // method close tag

    // 5. function of Bank Account
    private void printBankStatement() {
        if (!bankAccount.getTranscationList().isEmpty()) {
            try {
                // Generate the PDF statement
                BankStatementGenerator.generateStatement(
                        bankAccount.getAccountName(),
                        bankAccount.getAccountNumber(),
                        bankAccount.getAccountType(),
                        bankAccount.getAccountBalance(),
                        bankAccount.getTranscationList());
                System.out.println("Bank statement generated successfully!");

                callSound();

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
        System.out.println("Account Type: " + bankAccount.getAccountType());
        System.out.println("Account name: " + bankAccount.getAccountName());
        System.out.println("Account number: " + bankAccount.getAccountNumber());
        System.out.println("Account password: " + bankAccount.getAccountPassword());
        System.out.println("Address: " + bankAccount.getAddress());
        System.out.println("Bank name: " + bankAccount.bankName);
        System.out.println("Bank branch No: " + bankAccount.branchNo);
        System.out.println("Bank branch address: " + bankAccount.branchAddress);
        System.out.println("===============================================");
        callSound();
    } // method close tag

    // 7. function of Bank Account
    private void exit() {
        System.out.println("Thank you for using our banking system");
        scanner.close();
        callSound();
        System.exit(0);
    } // method close tag

    // recall Menu
    private void recallMenu() {
        char recall;
        do {
            System.out.print("Go back to MENU? (Y / N): ");
            recall = scanner.next().toUpperCase().charAt(0);

            if (recall == 'Y') {
                displayMenu();
            } else if (recall == 'N') {
                exit();
            } else {
                System.out.println("Invalid!");
            }
        } while (recall != 'Y' && recall != 'N');

    } // method close tag

    // sound effect when process successfully
    private void callSound() {
        Toolkit.getDefaultToolkit().beep();
    }
} // class close tag
