package views;

import dao.UserDAO;
import model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class greetings {

    public void greetingScreen() {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello!! Welcome to file hider");
        System.out.println("Press 1 to sign up");
        System.out.println("Press 2 to login");
        System.out.println("Press 3 to exit");

        int option = 0; // Initialize the variable

        try {
            option = Integer.parseInt(read.readLine());
        } catch (IOException e) {
            // Handle IOException by displaying an error message
            System.err.println("Error reading input. Please try again.");
            return; // Exit the method
        } catch (NumberFormatException e) {
            // Handle NumberFormatException by displaying an error message
            System.err.println("Invalid input. Please enter a valid numeric option.");
            return; // Exit the method
        }

        switch (option) {
            case 1 -> signUp();
            case 2 -> login();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid option. Please try again.");
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        try {
            if (UserDAO.isExists(email)) {
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email, genOTP);
                System.out.println("Enter the OTP which was sent to your device");
                String otp = sc.nextLine();
                if (otp.equals(genOTP)) {
                    new UserView(email).home();
                } else {
                    System.out.println("The OTP is incorrect. Please try again");
                }
            } else {
                System.out.println("User cannot be found");
            }
        } catch (SQLException e) {
            // Handle SQLException by displaying an error message
            System.err.println("A database error occurred. Please try again later.");
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email, genOTP);
        System.out.println("Enter the OTP which was sent to your device");
        String otp = sc.nextLine();

        if (otp.equals(genOTP)) {
            User user = new User(name, email);
            int response = UserService.saveUser(user);
            switch (response) {
                case 0 -> System.out.println("User is registered");
                case 1 -> System.out.println("User already exists");
            }
        } else {
            System.out.println("The OTP is incorrect. Please try again");
        }
    }
}
