package taskmanager.commands;

import taskmanager.models.Task;
import taskmanager.models.TimedTask; // Add this import
import taskmanager.strategies.*;
import taskmanager.decorators.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ViewTasksCommand extends TaskCommand {
    private String displayFormat;
    private boolean applyDecorators;

    public ViewTasksCommand(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void parseInput() {
        clearScreen();
        System.out.println("=== View Tasks ===");
        System.out.println("Choose display format:");
        System.out.println("1. List view");
        System.out.println("2. Table view");
        System.out.println("3. Grouped by priority");
        System.out.print("Enter your choice (1-3): ");
        displayFormat = scanner.nextLine().trim();
        
        // Template Method depends on user input to decide decoration
        System.out.print("Apply smart decorations? (y/n): ");
        String decorateChoice = scanner.nextLine().trim();
        applyDecorators = decorateChoice.equalsIgnoreCase("y");
    }

    @Override
    protected boolean validateInput() {
        return displayFormat.equals("1") || displayFormat.equals("2") || displayFormat.equals("3");
    }

    @Override
    protected void runMainLogic() {
        // 1. TEMPLATE METHOD calls other patterns in sequence
        List<Task> rawTasks = AddTaskCommand.getTaskList();
        
        if (rawTasks.isEmpty()) {
            System.out.println("No tasks found. Add some tasks first!");
            return;
        }

        // 2. DECORATOR PATTERN depends on Template Method's parsing
        List<Task> decoratedTasks = applyDecorators ? 
            applySmartDecorators(rawTasks) : rawTasks;
        
        // 3. STRATEGY PATTERN depends on Template Method's validation
        TaskDisplayStrategy strategy = createDisplayStrategy();
        
        // 4. All patterns work together for final display
        System.out.println("\n" + (applyDecorators ? "--- ENHANCED" : "--- BASIC") + " TASK VIEW ---");
        strategy.displayTasks(decoratedTasks);
        
        // Only offer deletion for list or table views, not for grouped view
        if (!displayFormat.equals("3")) {
            // NEW CODE: Add delete functionality (only for list and table views)
            System.out.println("\nOptions:");
            System.out.println("Enter task number to delete (1-" + rawTasks.size() + ")");
            System.out.println("Or press 0 to return to main menu");
            System.out.print("Your choice: ");
            
            String deleteChoice = scanner.nextLine().trim();
            
            try {
                int taskNum = Integer.parseInt(deleteChoice);
                if (taskNum > 0 && taskNum <= rawTasks.size()) {
                    // Delete the task
                    Task removed = rawTasks.remove(taskNum - 1);
                    clearScreen();
                    System.out.println("Task deleted: " + removed.getDetails());
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    clearScreen();  // Add another clear after user presses Enter
                } else if (taskNum != 0) {
                    clearScreen();  // Clear screen for invalid number
                    System.out.println("Invalid task number.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    clearScreen();  // Clear again after user presses Enter
                } else {
                    // When user enters 0, just clear screen without messages
                    clearScreen();
                }
            } catch (NumberFormatException e) {
                if (!deleteChoice.equals("0")) {
                    clearScreen();  // Clear screen for invalid input
                    System.out.println("Invalid input. Please enter a number.");
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                    clearScreen();  // Clear again after user presses Enter
                } else {
                    // When user enters 0, just clear screen without messages
                    clearScreen();
                }
            }
        } else {
            // For grouped view, just show a message and wait for user to continue
            System.out.println("\nNote: Deletion not available in grouped view.");
            System.out.println("Press Enter to return to main menu...");
            scanner.nextLine();
            clearScreen();
        }
    }
    
    // DECORATOR depends on business logic and user choice
    private List<Task> applySmartDecorators(List<Task> tasks) {
        List<Task> decorated = new ArrayList<>();
        
        for (Task task : tasks) {
            Task decoratedTask = task;
            
            // Chain decorators based on task properties
            // High priority decoration
            if (task.getPriority() <= 2) {
                decoratedTask = new HighPriorityDecorator(decoratedTask);
            }
            
            // Overdue decoration for timed tasks
            if (task instanceof TimedTask) {
                TimedTask timedTask = (TimedTask) task;
                if (isOverdue(timedTask.getDueDate())) {
                    decoratedTask = new OverdueTaskDecorator(decoratedTask);
                }
            }
            
            decorated.add(decoratedTask);
        }
        
        return decorated;
    }
    
    // STRATEGY depends on Template Method's parsing
    private TaskDisplayStrategy createDisplayStrategy() {
        switch (displayFormat) {
            case "1":
                return new ListDisplayStrategy();
            case "2":
                return new TableDisplayStrategy();
            case "3":
                return new GroupedDisplayStrategy();
            default:
                return new ListDisplayStrategy();
        }
    }
    
    private boolean isOverdue(String dueDate) {
        try {
            LocalDate due = LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return due.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    
    @Override
    protected void postProcessing() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    private void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}