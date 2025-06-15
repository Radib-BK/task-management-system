package taskmanager.factory;

import taskmanager.models.SimpleTask;
import taskmanager.models.TimedTask;
import taskmanager.models.Task;

public class TaskFactory {

    public static Task createTask(String type, String name, int priority, String dueDate) {
        if (type.equalsIgnoreCase("simple")) {
            return new SimpleTask(name, priority);
        } else if (type.equalsIgnoreCase("timed")) {
            return new TimedTask(name, priority, dueDate);
        }
        throw new IllegalArgumentException("Unknown task type: " + type);
    }
}