package com.mycompany.part4poe;

import javax.swing.JOptionPane;

public class Part4poe {
    public static void main(String[] args) {
        UserRegistration userRegistration = new UserRegistration();
        Login user = userRegistration.registerUser(); // Register a new user
        TaskManager manager = new TaskManager();

        // Login
        boolean loggedIn = false;
        while (!loggedIn) {
            String username = JOptionPane.showInputDialog("Enter Username:");
            String password = JOptionPane.showInputDialog("Enter Password:");
            loggedIn = user.loginUser(username, password);
            JOptionPane.showMessageDialog(null, user.returnLoginStatus(loggedIn));
        }

        // Task Management Menu
        while (true) {
            String menu = """
                Task Manager Menu:
                1. Add Task
                2. Display Completed Tasks
                3. Display Longest Task
                4. Search Task by Name
                5. Display Tasks by Developer
                6. Delete Task
                7. Display All Tasks
                8. Exit
            """;
            String choice = JOptionPane.showInputDialog(menu + "\nChoose an option:");
            if (choice == null || choice.equals("8")) break;

            switch (choice) {
                case "1" -> addTask(manager);
                case "2" -> displayCompletedTasks(manager);
                case "3" -> displayLongestTask(manager);
                case "4" -> searchTaskByName(manager);
                case "5" -> displayTasksByDeveloper(manager);
                case "6" -> deleteTask(manager);
                case "7" -> displayAllTasks(manager);
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
            }
        }
    }

    private static void addTask(TaskManager manager) {
        try {
            String taskName = JOptionPane.showInputDialog("Enter Task Name:");
            int taskNumber = manager.getTasksByStatus("All").size();
            String taskDescription = JOptionPane.showInputDialog("Enter Task Description:");
            String developer = JOptionPane.showInputDialog("Enter Developer Name:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration:"));
            String taskStatus = JOptionPane.showInputDialog("Enter Task Status (To Do/Doing/Done):");

            Task task = new Task(taskName, taskNumber, taskDescription, developer, taskDuration, taskStatus);
            manager.addTask(task);
            JOptionPane.showMessageDialog(null, "Task added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid duration. Please enter a number.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private static void displayCompletedTasks(TaskManager manager) {
        var completedTasks = manager.getTasksByStatus("Done");
        if (completedTasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No completed tasks.");
        } else {
            completedTasks.forEach(task -> JOptionPane.showMessageDialog(null, task.printTaskDetails()));
        }
    }

    private static void displayLongestTask(TaskManager manager) {
        Task longestTask = manager.getLongestTask();
        if (longestTask != null) {
            JOptionPane.showMessageDialog(null, longestTask.printTaskDetails());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks available.");
        }
    }

    private static void searchTaskByName(TaskManager manager) {
        String taskName = JOptionPane.showInputDialog("Enter Task Name to Search:");
        Task task = manager.searchTaskByName(taskName);
        if (task != null) {
            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        } else {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    private static void displayTasksByDeveloper(TaskManager manager) {
        String developer = JOptionPane.showInputDialog("Enter Developer Name:");
        var tasksByDeveloper = manager.getTasksByDeveloper(developer);
        if (tasksByDeveloper.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks assigned to this developer.");
        } else {
            tasksByDeveloper.forEach(task -> JOptionPane.showMessageDialog(null, task.printTaskDetails()));
        }
    }

    private static void deleteTask(TaskManager manager) {
        String taskName = JOptionPane.showInputDialog("Enter Task Name to Delete:");
        manager.deleteTaskByName(taskName);
        JOptionPane.showMessageDialog(null, "Task deleted successfully (if it existed).");
    }

    private static void displayAllTasks(TaskManager manager) {
        var allTasks = manager.getTasksByStatus("All");
        if (allTasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
        } else {
            allTasks.forEach(task -> JOptionPane.showMessageDialog(null, task.printTaskDetails()));
        }
    }
}
