package taskmanager.models;

// Task interface defining the structure for all task types
public interface Task {
    String getName(); // Method to get the name of the task
    int getPriority(); // Method to get the priority level of the task
    String getDetails(); // Method to get the details of the task
}