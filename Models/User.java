package Models;

import java.util.Scanner;

public class User {
    // User property
    private String userName;
    private String userPassword;
    private String userAddress;
    private double userBalance;

    // getter of User property
    public String getUserName() {return userName;}
    public String getUserPassword() {return userPassword;}
    public String getUserAddress() {return userAddress;}
    public double getUserBalance() {return userBalance;}

    // Setter for username and password
    public void setUserInfo(Scanner scanner) {

        do {
            System.out.println("Please fill the following credentials");

            System.out.print("Enter the username: ");
            this.userName = scanner.nextLine().toLowerCase();

            System.out.print("Enter the password: ");
            this.userPassword = scanner.nextLine().toLowerCase();

            if (userName.isBlank() && userPassword.isBlank())
                System.out.println("Fields cannot be blank!");

        } while (userName.isBlank() && userPassword.isBlank());
    }//  method close tag

    // setter for user address
    public void setUserAddress(Scanner scanner) {
        do {
            System.out.print("Enter the address: ");
            this.userAddress = scanner.nextLine().toLowerCase();

            if(userAddress.isBlank()) {
                System.out.println("Field cannot be blank!");
            }
        }while (userAddress.isBlank());
    }


}// class close tag









