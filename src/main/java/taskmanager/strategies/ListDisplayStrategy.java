package taskmanager.strategies;

import taskmanager.models.Task;
import java.util.List;

public class ListDisplayStrategy implements TaskDisplayStrategy {
    
    @Override
    public void displayTasks(List<Task> tasks) {
        System.out.println("\n--- TASKS (LIST VIEW) ---");
        System.out.println("=" + "=".repeat(40));
        
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println((i + 1) + ". " + task.getDetails());
        }
        
        System.out.println("=" + "=".repeat(40));
        System.out.println("Total tasks: " + tasks.size());
    }
}