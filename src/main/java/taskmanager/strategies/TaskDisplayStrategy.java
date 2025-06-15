package taskmanager.strategies;

import taskmanager.models.Task;
import java.util.List;

public interface TaskDisplayStrategy {
    void displayTasks(List<Task> tasks);
}