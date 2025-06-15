package taskmanager.decorators;

import taskmanager.models.Task;

public class HighPriorityDecorator extends TaskDecorator {
    
    public HighPriorityDecorator(Task task) {
        super(task);
    }
    
    @Override
    public String getDetails() {
        return "[HIGH PRIORITY] " + decoratedTask.getDetails();
    }
    
    @Override
    public String getName() {
        return "*** " + decoratedTask.getName() + " ***";
    }
    
    @Override
    public int getPriority() {
        return decoratedTask.getPriority();
    }
}