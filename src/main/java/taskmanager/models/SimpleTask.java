package taskmanager.models;

public class SimpleTask implements Task {
    private String name;
    private int priority;

    public SimpleTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
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
        return "Task: " + name + " | Priority: " + priority;
    }
}