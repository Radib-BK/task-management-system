package taskmanager.utils;

import java.util.Scanner;

public class InputParser {

    private Scanner scanner;

    public InputParser() {
        this.scanner = new Scanner(System.in);
    }

    // Method to get a line of input from the user
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    // Method to parse an integer input from the user
    public int getIntInput(String prompt) {
        while (true) {
            try {
                String input = getInput(prompt);
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Method to close the scanner
    public void close() {
        scanner.close();
    }
}