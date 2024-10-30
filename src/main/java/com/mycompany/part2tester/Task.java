package com.mycompany.part2tester;

public class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;

    public Task(String taskName, int taskNumber, String taskDescription, 
                String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskNumber;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
    }

    public String createTaskID() {
        String firstTwoLetters = taskName.substring(0, 2).toUpperCase();
        String lastThreeLetters = developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return firstTwoLetters + ":" + taskNumber + ":" + lastThreeLetters;
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer: " + developerDetails + "\n" +
               "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task ID: " + taskID + "\n" +
               "Task Duration: " + taskDuration + " hours\n";
    }

    public int getTaskDuration() {
        return taskDuration;
    }
}
