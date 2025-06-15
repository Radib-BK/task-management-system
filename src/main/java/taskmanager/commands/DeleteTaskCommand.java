package taskmanager.commands;

import taskmanager.models.Task;
import java.util.List;
import java.util.Scanner;

public class DeleteTaskCommand extends TaskCommand {
    private List<Task> taskList;

    public DeleteTaskCommand(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    protected void mainLogic() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the task index to delete: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Invalid task index. Please try again.");
        }
    }

    @Override
    protected void postProcessing() {
        System.out.println("Current tasks after deletion:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + ": " + taskList.get(i).getDetails());
        }
    }
}