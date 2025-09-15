import java.util.Scanner;

public class UI {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin123";

    public static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== Welcome to SmartStudent Management System =====");

        int attempts = 3; // Maximum login attempts

        while (attempts > 0) {
            System.out.print("Enter username: ");
            String user = sc.nextLine();
            System.out.print("Enter password: ");
            String pass = sc.nextLine();

            if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
                System.out.println("\nLogin successful! Welcome, Admin.");
                AdminService.showMenu();
                return;
            } else {
                attempts--;
                System.out.println("Invalid credentials! Attempts left: " + attempts);
            }
        }

        System.out.println(" Too many failed attempts. Exiting...");
        System.exit(0);
    }
}


