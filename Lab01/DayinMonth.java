//Example 6.4
import java.util.Scanner;

public class DayinMonth {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String monthInput;
        int month = -1, year;

        while (month == -1) {
            System.out.print("Enter month (number or name): ");
            monthInput = scanner.nextLine().trim();
            month = getMonthNumber(monthInput);
            if (month == -1) {
                System.out.println("Invalid month! Try again.");
            }
        }
        while (true) {
            System.out.print("Enter year: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year >= 0) break;
            }
            System.out.println("Invalid year! Please enter a valid non-negative year.");
            scanner.next();
        }

        int days = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> (LeapYear(year) ? 29 : 28);
            default -> 0;
        };

        System.out.println("Number of days in month: " + days);
        scanner.close();
    }

    private static int getMonthNumber(String input) {
        return switch (input.toLowerCase()) {
            case "january", "jan.", "jan", "1" -> 1;
            case "february", "feb.", "feb", "2" -> 2;
            case "march", "mar.", "mar", "3" -> 3;
            case "april", "apr.", "apr", "4" -> 4;
            case "may", "5" -> 5;
            case "june", "jun.", "jun", "6" -> 6;
            case "july", "jul.", "jul", "7" -> 7;
            case "august", "aug.", "aug", "8" -> 8;
            case "september", "sep.", "sep", "9" -> 9;
            case "october", "oct.", "oct", "10" -> 10;
            case "november", "nov.", "nov", "11" -> 11;
            case "december", "dec.", "dec", "12" -> 12;
            default -> -1;
        };
    }

    private static boolean LeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}