import java.util.Scanner;

public class User extends Bank {
    Scanner scanner = new Scanner(System.in);

    // User property
    protected String userName;
    protected String userId;
    protected String userPassword;
    protected double userBalance;
    protected double transaction;

    // Input user information
    public void getUserInfo() {
        boolean isBlank = false;

        do {

            System.out.print("Enter the username of account: ");
            userName = scanner.nextLine().toLowerCase();

            System.out.print("Enter the password of account: ");
            userPassword = scanner.nextLine().toLowerCase();

            if (!userName.isBlank() && !userPassword.isBlank()) {
                isBlank = true;
            } else {
                System.out.println("Fields cannot be blank!");
            }
        } while (!isBlank);
    }//  method close tag
}// class close tag







