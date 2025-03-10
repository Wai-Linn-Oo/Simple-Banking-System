package View;

import Models.Bank;
import Models.BankAccount;
import Models.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BankSystem extends Bank {
    BankAccount userAccount;
    User user;
    char choice;

    // Constructor of view.BankSystem
    public BankSystem() {
        this.user = new User();
    }

    // Sample Database of bank account
    ArrayList<BankAccount> bankAccounts = new ArrayList<>(Arrays.asList(
            new BankAccount("1234567890",
                    "Wai Linn Oo",
                    "123",
                    "Insein",
                    1000,
                    "2",
                    "YANGON"),
            new BankAccount("9876543210",
                    "Louis Linn",
                    "456",
                    "Manchester",
                    5000,
                    "5",
                    "England")));

    // Bank System start for here
    public void start() {

        do {
            System.out.println("==============================");
            System.out.println("WELCOME TO OUR BANKING SYSTEM ");
            System.out.println("==============================");
            System.out.println("1.Login your account");
            System.out.println("2.Create new account");
            System.out.print("Your choice (1 or 2): ");
            choice = scanner.next().charAt(0);
            scanner.nextLine();
            System.out.println("===============================");

            userAccount = getUserAccount(choice);

        } while (userAccount == null);

        //  Show menu if user input meets credentials
        Menu menu = new Menu(userAccount);
        menu.displayMenu();
    } // method close tag

    // let user choose login or create new
    BankAccount getUserAccount(char choice) {
        return switch (choice) {
            case '1' -> login();
            case '2' -> createNew();
            default -> {
                System.out.println("Out of service!");
                yield null;
            }
        };
    }// method close tag

    // option 1 : account login
    BankAccount login() {
        user.setUserInfo(scanner);

        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.validateCredentials(user.getUserName(), user.getUserPassword())) {
                System.out.println("Login successfully");
                Toolkit.getDefaultToolkit().beep();
                return bankAccount;
            }
        }
        System.out.println("Invalid credentials. Login failed.");

        return null;
    } // method close tag

    // option 2 : create new account
    BankAccount createNew() {
        user.setUserInfo(scanner);
        user.setUserAddress(scanner);

        getBankInfo(); // Bank would fill the information in REAL WORLD

        int temp = (int) (Math.random() * 123456890); // get random account number for new bank account
        String accountNumber = Integer.toString(temp);

        BankAccount bankAccount = new BankAccount(accountNumber, user.getUserName(), user.getUserPassword(), user.getUserAddress(), user.getUserBalance(), branchNo, branchAddress);
        bankAccounts.add(bankAccount);

        System.out.println("Bank Account was created successfully ");
        Toolkit.getDefaultToolkit().beep();

        return bankAccount;
    } // method close tag
} // Class close tag


