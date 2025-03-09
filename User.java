import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);
    String userName;
    String userPassword;
    String userAddress;
    double userBalance;

    // Input user information
    public void getUserInfo() {

        boolean isBlank = false;

        do {
            System.out.println("Please fill the following credentials");

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







