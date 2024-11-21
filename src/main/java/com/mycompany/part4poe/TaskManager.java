package com.mycompany.part4poe;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    // Add a task to the list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Get tasks filtered by status ("All" returns all tasks)
    public List<Task> getTasksByStatus(String status) {
        if (status.equalsIgnoreCase("All")) {
            return tasks; // Return all tasks if "All" is specified
        }
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTaskStatus().equalsIgnoreCase(status)) {
                result.add(task);
            }
        }
        return result;
    }

    // Get the task with the longest duration
    public Task getLongestTask() {
        if (tasks.isEmpty()) {
            return null; // Return null if no tasks exist
        }
        Task longestTask = tasks.get(0);
        for (Task task : tasks) {
            if (task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }
        return longestTask;
    }

    // Delete a task by its name
    public void deleteTaskByName(String taskName) {
        boolean removed = tasks.removeIf(task -> task.getTaskName().equalsIgnoreCase(taskName));
        if (!removed) {
            System.out.println("Task with name '" + taskName + "' not found.");
        }
    }

    // Display all tasks using the printTaskDetails method from Task
    public void displayAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task task : tasks) {
                System.out.println(task.printTaskDetails());
            }
        }
    }

    // Display all tasks with a specific status
    public void displayTasksByStatus(String status) {
        List<Task> filteredTasks = getTasksByStatus(status);
        if (filteredTasks.isEmpty()) {
            System.out.println("No tasks with status: " + status);
        } else {
            for (Task task : filteredTasks) {
                System.out.println(task.printTaskDetails());
            }
        }
    }

    // Get the total number of tasks
    public int getTotalTasks() {
        return tasks.size();
    }

    // Search for a task by its name
    public Task searchTaskByName(String taskName) {
        return tasks.stream()
                .filter(task -> task.getTaskName().equalsIgnoreCase(taskName))
                .findFirst()
                .orElse(null);
    }

    // Get tasks assigned to a specific developer
    public List<Task> getTasksByDeveloper(String developerName) {
        return tasks.stream()
                .filter(task -> task.getDeveloper().equalsIgnoreCase(developerName))
                .collect(Collectors.toList());
    }
}
