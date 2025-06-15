package taskmanager.decorators;

import taskmanager.models.Task;

public class OverdueTaskDecorator extends TaskDecorator {
    
    public OverdueTaskDecorator(Task task) {
        super(task);
    }
    
    @Override
    public String getDetails() {
        return ">>> OVERDUE <<< " + decoratedTask.getDetails();
    }
    
    @Override
    public String getName() {
        return "!!! " + decoratedTask.getName() + " !!!";
    }
    
    @Override
    public int getPriority() {
        return decoratedTask.getPriority();
    }
}