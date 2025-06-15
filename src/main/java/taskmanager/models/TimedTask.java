package taskmanager.models;

public class TimedTask implements Task {
    private String name;
    private int priority;
    private String dueDate;

    public TimedTask(String name, int priority, String dueDate) {
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String getDetails() {
        return "Task: " + name + " | Priority: " + priority + " | Due: " + dueDate;
    }

    // Additional method for TimedTask
    public String getDueDate() {
        return dueDate;
    }
}