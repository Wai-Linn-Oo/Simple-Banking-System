import java.util.ArrayList;
import java.util.Arrays;

public class BankSystem extends User {
    BankAccount userAccount;
    char choice;

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

        Menu menu = new Menu(userAccount);
        menu.displayMenu();
    } // method close tag

    // let user choose login or create new
    public BankAccount getUserAccount(char choice) {
        return switch (choice) {
            case '1' -> {
                System.out.println("Please fill the following credentials");
                getUserInfo();
                yield login(userName, userPassword);
            }
            case '2' -> {
                System.out.println("Please fill the following credentials");
                getBankInfo(); // get input for bank property
                getUserInfo();
                int temp = (int) (Math.random() * 123456890); // get random User ID for new bank account
                userId = Integer.toString(temp);
                yield createNew(userName, userId, userPassword, bankName, branchNo, branchAddress);
            }
            default -> {
                System.out.println("Out of service!");
                yield null;
            }
        };
    }// method close tag

    // if user chooses login account
    public BankAccount login(String userName, String userPassword) {
        for (BankAccount bankAccount : bankAccounts) {
            if (bankAccount.validateCredentials(userName, userPassword)) {
                System.out.println("Login successfully");
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
        System.out.println("Bank Account was created successfully ");

        return bankAccount;
    } // method close tag
} // Class close tag


