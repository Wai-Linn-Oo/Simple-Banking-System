package Models;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Bank {
    public Scanner scanner = new Scanner(System.in);
    // bank property
    public String bankName = "JAVA BANK";
    public String branchNo;
    public String branchAddress;

    // Constants for validation patterns and messages
    final String BANK_NAME_PATTERN = "[a-zA-Z ]+";
    final String BRANCH_NO_PATTERN = "[ 1-9 ]";
    final String INVALID_BRANCH_NO_MESSAGE = "Branch No must contain only number.";
    final String EMPTY_FIELD_MESSAGE = "Models.Bank Address must contain only alphabetic characters .";

    // Request bank info from user ( I knew it unlike real world )
    public void getBankInfo() {
        this.branchNo = getOnlyNumInput(scanner, "Enter the branch no: ", BRANCH_NO_PATTERN, INVALID_BRANCH_NO_MESSAGE);
        this.branchAddress = getValidInput(scanner, "Enter the branch address: ", BANK_NAME_PATTERN, EMPTY_FIELD_MESSAGE);

    } // method close tag

    /**
     * Prompts the user for input and ensures it matches a given pattern.
     *
     * @param scanner      The Scanner object for user input.
     * @param prompt       The message to display to the user.
     * @param pattern      The regular expression pattern to match the input against.
     * @param errorMessage The error message to display if the input does not match the pattern.
     * @return Valid user input as a String.
     */
    public String getValidInput(Scanner scanner, String prompt, String pattern, String errorMessage) {
        String input;
        boolean isValid;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().toUpperCase();
            isValid = !input.isBlank() && Pattern.matches(pattern, input);
            if (!isValid) {
                System.out.println(errorMessage);
            }
        } while (!isValid);
        return input;
    } // method close tag

    /**
     * Prompts the user for input and ensures it is not blank.
     *
     * @param scanner      The Scanner object for user input.
     * @param prompt       The message to display to the user.
     * @param errorMessage The error message to display if the input is blank.
     * @return Non-blank user input as a String.
     */
    public String getOnlyNumInput(Scanner scanner, String prompt, String pattern, String errorMessage) {
        String input;
        boolean isValid;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            isValid = !input.isBlank() && Pattern.matches(pattern, input);
            if (!isValid) {
                System.out.println(errorMessage);
            }
        } while (!isValid);
        return input;
    } // method close tag
} // class close tag