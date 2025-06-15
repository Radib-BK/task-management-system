package taskmanager.decorators;

import taskmanager.models.Task;

public abstract class TaskDecorator implements Task {
    protected Task decoratedTask;

    public TaskDecorator(Task task) {
        this.decoratedTask = task;
    }

    @Override
    public String getName() {
        return decoratedTask.getName();
    }
    
    @Override
    public int getPriority() {
        return decoratedTask.getPriority();
    }
    
    @Override
    public String getDetails() {
        return decoratedTask.getDetails();
    }
}