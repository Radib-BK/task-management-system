package taskmanager.commands;

import taskmanager.factory.TaskFactory;
import taskmanager.models.Task;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AddTaskCommand extends TaskCommand {
    private static List<Task> taskList = new ArrayList<>();
    private String taskType;
    private String taskName;
    private int priority;
    private String dueDate;

    public AddTaskCommand(Scanner scanner) {
        super(scanner);
    }

    @Override
    protected void parseInput() {
        clearScreen();
        System.out.println("=== Add New Task ===");
        System.out.print("Enter task type (simple/timed): ");
        taskType = scanner.nextLine().trim();
        
        System.out.print("Enter task name: ");
        taskName = scanner.nextLine().trim();
        
        System.out.print("Enter priority (1-5, where 1 is highest): ");
        try {
            priority = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            priority = -1;
        }
        
        if (taskType.equalsIgnoreCase("timed")) {
            System.out.print("Enter due date (yyyy-mm-dd): ");
            dueDate = scanner.nextLine().trim();
        }
        // NO MORE decoration questions here!
    }

    @Override
    protected boolean validateInput() {
        if (taskName == null || taskName.isEmpty()) {
            System.out.println("Error: Task name cannot be empty.");
            return false;
        }
        
        if (priority < 1 || priority > 5) {
            System.out.println("Error: Priority must be between 1-5.");
            return false;
        }
        
        if (!taskType.equalsIgnoreCase("simple") && !taskType.equalsIgnoreCase("timed")) {
            System.out.println("Error: Task type must be 'simple' or 'timed'.");
            return false;
        }
        
        return true;
    }

    @Override
    protected void runMainLogic() {
        try {
            // FACTORY creates the base task (no decorations)
            Task newTask = TaskFactory.createTask(taskType, taskName, priority, dueDate);
            taskList.add(newTask);
            
            System.out.println("✅ Task added successfully!");
            System.out.println("Task details: " + newTask.getDetails());
            
        } catch (Exception e) {
            System.out.println("Error creating task: " + e.getMessage());
        }
    }
    
    @Override
    protected void postProcessing() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
    
    public static List<Task> getTaskList() {
        return taskList;
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