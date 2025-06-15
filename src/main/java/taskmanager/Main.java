package taskmanager;

import taskmanager.commands.AddTaskCommand;
import taskmanager.commands.ViewTasksCommand;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            clearScreen();
            System.out.println("\n=== Task Management System ===");
            System.out.println("Please choose an option:");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    AddTaskCommand addTaskCommand = new AddTaskCommand(scanner);
                    addTaskCommand.execute();
                    break;
                case "2":
                    ViewTasksCommand viewTasksCommand = new ViewTasksCommand(scanner);
                    viewTasksCommand.execute();
                    break;
                case "3":
                    running = false;
                    clearScreen();
                    System.out.println("Exiting the Task Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
            }
        }

        scanner.close();
    }
    
    // Method to clear terminal screen
    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            // If clearing fails, just add some blank lines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}