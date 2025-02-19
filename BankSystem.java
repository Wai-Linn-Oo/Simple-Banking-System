import java.util.ArrayList;
import java.util.Arrays;

public class BankSystem extends User {
    BankAccount userAccount;

    // Sample Database of bank account
    ArrayList<BankAccount> bankAccounts = new ArrayList<>(Arrays.asList(
            new BankAccount("AYA",
                    "2",
                    "Insein",
                    "Wai Linn Oo",
                    "1234567890",
                    "123",
                    5000,
                    0),
            new BankAccount("AYA",
                    "3",
                    "Manchester",
                    "Louis",
                    "123456789",
                    "456",
                    10000,
                    0)
    )
    );

    // the whole process from here
    public void startBankingSystem() {

        do {
            System.out.println("==============================");
            System.out.println("WELCOME TO OUR BANKING SYSTEM ");
            System.out.println("==============================");
            System.out.println("1.Login your account");
            System.out.println("2.Create new account");
            System.out.print("Your choice (1 or 2): ");
            char choice = scanner.next().charAt(0);
            scanner.nextLine();
            System.out.println("===============================");

            switch (choice) {
                case '1':
                    getUserInfo();
                    userAccount = login(userName, userPassword);
                    break;
                case '2':
                    getBankInfo(); // get input for bank property
                    getUserInfo();
                    int temp = (int) (Math.random() * 123456890); // get random User ID for new bank account
                    userId = Integer.toString(temp);
                    userAccount = createNew(userName, userId, userPassword, bankName, branchNo, branchAddress);
                    break;
                default:
                    System.out.println("Please choose again!");
            }
        } while (userAccount == null);

        if (userAccount != null) {
            Menu menu = new Menu(userAccount);
            menu.displayMenu();
        }
    } // method close tag

    // if user chooses login account
    public BankAccount login(String userName, String userPassword) {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.validateCredentials(userName, userPassword)) {
                return bankAccount;
            }
        }
        System.out.println("Invalid credentials. Login failed.");

        return null;
    } // method close tag

    // if user choose create new account
    public BankAccount createNew(String userName, String userId, String userPassword, String bankName, String branchNo, String branchAddress) {
        BankAccount bankAccount = new BankAccount(bankName, branchNo, branchAddress, userName, userId, userPassword, userBalance, transaction);
        bankAccounts.add(bankAccount);
        System.out.println("Bank Account was successfully created");

        return bankAccount;
    } // method close tag
} // Class close tag


