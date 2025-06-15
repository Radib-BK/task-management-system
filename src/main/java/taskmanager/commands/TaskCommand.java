package taskmanager.commands;

import java.util.Scanner;

public abstract class TaskCommand {
    protected Scanner scanner;
    
    public TaskCommand(Scanner scanner) {
        this.scanner = scanner;
    }
    
    // Template method defining the command execution flow
    public final void execute() {
        parseInput();
        if (validateInput()) {
            runMainLogic();
            postProcessing();
        } else {
            System.out.println("Invalid input. Please try again.");
            System.out.println("Press Enter to continue...");
            scanner.nextLine();
        }
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void parseInput();
    protected abstract boolean validateInput();
    protected abstract void runMainLogic();
    
    // Optional post-processing step
    protected void postProcessing() {
        // Default implementation - can be overridden
    }
}