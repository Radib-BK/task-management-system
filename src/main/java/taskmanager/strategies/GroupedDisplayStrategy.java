package taskmanager.strategies;

import taskmanager.models.Task;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class GroupedDisplayStrategy implements TaskDisplayStrategy {
    
    @Override
    public void displayTasks(List<Task> tasks) {
        System.out.println("\n > Tasks (Grouped by Priority)");
        System.out.println("=" + "=".repeat(50));
        
        // Group tasks by priority
        Map<Integer, List<Task>> groupedTasks = new HashMap<>();
        for (Task task : tasks) {
            int priority = task.getPriority();
            groupedTasks.computeIfAbsent(priority, k -> new ArrayList<>()).add(task);
        }
        
        // Display grouped tasks (priority 1 to 5)
        for (int priority = 1; priority <= 5; priority++) {
            List<Task> tasksWithPriority = groupedTasks.get(priority);
            if (tasksWithPriority != null && !tasksWithPriority.isEmpty()) {
                System.out.println("\n-> Priority " + priority + " (" + getPriorityLabel(priority) + "):");
                System.out.println("-".repeat(30));
                
                for (int i = 0; i < tasksWithPriority.size(); i++) {
                    Task task = tasksWithPriority.get(i);
                    System.out.println("  " + (i + 1) + ". " + task.getDetails());
                }
            }
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Total tasks: " + tasks.size());
    }
    
    private String getPriorityLabel(int priority) {
        switch (priority) {
            case 1: return "Highest";
            case 2: return "High";
            case 3: return "Medium";
            case 4: return "Low";
            case 5: return "Lowest";
            default: return "Unknown";
        }
    }
}