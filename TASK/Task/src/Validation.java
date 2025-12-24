import java.util.Calendar;
import java.util.Scanner;

public class Validation {

    public static int readInt(Scanner sc, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                value = Integer.parseInt(input);
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public static int positiveInt(Scanner sc, String prompt) {
        int value;
        while (true) {
            value = readInt(sc, prompt);
            if (value > 0) return value;
            System.out.println("Please enter a positive number.");
        }
    }

    public static int correctYear(Scanner sc, String prompt) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int year;
        while (true) {
            year = readInt(sc, prompt);
            if (year > 0 && year <= currentYear) return year;
            System.out.println("Invalid year. Enter a year between 1 and " + currentYear);
        }
    }

    public static String emptyString(Scanner sc, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty. Please enter a valid value.");
        }
    }

    public static int allowZero(Scanner sc, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value >= 0) return value;
                else System.out.println("Enter 0 or a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
