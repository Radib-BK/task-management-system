package taskmanager.strategies;

import taskmanager.models.Task;
import taskmanager.models.TimedTask;
import java.util.List;

public class TableDisplayStrategy implements TaskDisplayStrategy {
    
    @Override
    public void displayTasks(List<Task> tasks) {
        System.out.println("\n--- TASKS (TABLE VIEW) ---");
        System.out.println("+------+----------------------+----------+----------------------+");
        System.out.printf("| %-4s | %-20s | %-8s | %-20s |\n", "No.", "Task Name", "Priority", "Due Date");
        System.out.println("+------+----------------------+----------+----------------------+");
        
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskName = task.getName();
            if (taskName.length() > 20) {
                taskName = taskName.substring(0, 17) + "...";
            }
            
            String dueDate = "N/A";
            if (task instanceof TimedTask) {
                dueDate = ((TimedTask) task).getDueDate();
                if (dueDate.length() > 20) {
                    dueDate = dueDate.substring(0, 17) + "...";
                }
            }
            
            System.out.printf("| %-4d | %-20s | %-8d | %-20s |\n", 
                (i + 1),
                taskName, 
                task.getPriority(), 
                dueDate);
        }
        
        System.out.println("+------+----------------------+----------+----------------------+");
        System.out.println("Total tasks: " + tasks.size());
    }
}