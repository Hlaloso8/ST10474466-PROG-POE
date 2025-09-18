package loginapp_chat;

import java.util.Scanner;
import java.util.regex.Pattern;

public class LoginApp_chat {
    public String username;
    public String password;
    private String cellNumber;
    public String firstName;
    public String lastName;
    
    //Constructor
    public LoginApp_chat(String username, String password, String cellNumber, String firstName, String lastName){
        this.username = "";
        this.password = "";
        this.cellNumber = "";
        this.firstName = "";
        this.lastName = "";
        
    }

    // Main method to run the program
    public static void main(String[] args) {
        LoginApp_chat loginSystem = new LoginApp_chat();
        Scanner scanner = new Scanner(System.in);
        

        System.out.println("=== Registration ===");
        String registrationResult = loginSystem.registerUser(scanner);
        System.out.println(registrationResult);

        if (registrationResult.toLowerCase().contains("successful")) {
            System.out.println("\n=== Login ===");
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            boolean loginSuccess = loginSystem.loginUser(enteredUsername, enteredPassword);
            System.out.println(loginSystem.returnLoginStatus(loginSuccess));
        }

        scanner.close();
    }

    public LoginApp_chat(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Check if username meets requirements
    public boolean checkUsername(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    // Check if password meets complexity requirements
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;
        if (!Pattern.compile("[A-Z]").matcher(password).find()) return false;
        if (!Pattern.compile("[0-9]").matcher(password).find()) return false;
        return Pattern.compile("[^A-Za-z0-9]").matcher(password).find();
    }

    // Check if cell phone number is correctly formatted
    public boolean checkCellPhoneNumber(String cellNumber) {
      
        String pattern = "^\\+\\d{1,3}\\d{7,10}$";
        return Pattern.matches(pattern, cellNumber);
    }

    // Handle user registration process
    public String registerUser(Scanner scanner) {
        
        //User first name
        System.out.print("Enter your first name: ");
        this.firstName = scanner.nextLine();
        
        //user last name
        System.out.print("Enter your last name: ");
        this.lastName = scanner.nextLine();

        // Username validation
        System.out.print("Enter username (must contain _ and be ≤5 characters): ");
        String username = scanner.nextLine();
        if (!checkUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        this.username = username;

        // Password validation
        System.out.print("Enter password (≥8 chars, with capital, number, special char): ");
        String password = scanner.nextLine();
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        this.password = password;

        // Cell number validation
        System.out.print("Enter cell phone number (with international code, e.g., +27831234567): ");
        String cellNumber = scanner.nextLine();
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        this.cellNumber = cellNumber;

        return "Registration successful!";
    }

    // Verify login credentials
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(this.username) && enteredPassword.equals(this.password);
    }

    // Return appropriate login status message
    public String returnLoginStatus(boolean isSuccessful) {
        if (isSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}