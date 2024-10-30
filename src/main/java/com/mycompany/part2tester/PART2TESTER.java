package com.mycompany.part2tester;

import javax.swing.*;
import java.util.ArrayList;

public class PART2TESTER {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int totalHours = 0;
    private static login currentUser;

    public static void main(String[] args) {
       

        registerUser();
        if (login()) {
            JOptionPane.showMessageDialog(null, "Welcome to EasyKanban, " + currentUser.getFirstName());

            int option;
            do {
                option = showMenu();
                switch (option) {
                    case 1 -> addTasks();
                    
                    
                    case 2 -> JOptionPane.showMessageDialog(null, "Coming Soon");
                    
                    
                    case 3 -> JOptionPane.showMessageDialog(null, "THANKS FOR USING OUR SYSTEM ;)");
                    default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
                }
            } while (option != 3);
        } else {
            JOptionPane.showMessageDialog(null, "Login failed.ending process...");
        }
    }

    private static void registerUser() {
        String username;
        do {
            username = JOptionPane.showInputDialog("Create a Username (must contain '_' and max 5 characters):");
        } while (!checkUserName(username));

        String password;
        do {
            password = JOptionPane.showInputDialog("Create a Password (at least 8 chars, 1 uppercase, 1 number, 1 special char):");
        } while (!checkPasswordComplexity(password));

        String firstName = JOptionPane.showInputDialog("Enter First Name:");
        String lastName = JOptionPane.showInputDialog("Enter Last Name:");

        currentUser = new login(username, password, firstName, lastName);
        JOptionPane.showMessageDialog(null, "Registration successful!");
    }

    private static boolean checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure it contains '_' and is no more than 5 characters long.");
        return false;
    }

    private static boolean checkPasswordComplexity(String password) {
        if (password.length() >= 8 && password.chars().anyMatch(Character::isUpperCase) &&
            password.chars().anyMatch(Character::isDigit) && password.chars().anyMatch(ch -> "!@#$%^&*()".indexOf(ch) >= 0)) {
            return true;
        }
        JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure it contains at least 8 characters, a capital letter, a number and a special character.");
        return false;
    }

    private static int showMenu() {
        String menu = """
                Please select an option:
                1) Add Tasks
                2) Show Report
                3) Quit
                """;
        return Integer.parseInt(JOptionPane.showInputDialog(menu));
    }

    private static boolean login() {
        String username = JOptionPane.showInputDialog("Enter Username:");
        String password = JOptionPane.showInputDialog("Enter Password:");
        
        // Basic login check
        return username.equals(currentUser.getUsername()) && password.equals(currentUser.getPassword());
    }

    private static void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks to add:"));

        for (int i = 0; i < numTasks; i++) {
            String taskName = JOptionPane.showInputDialog(" Task Name:");
            String taskDescription = JOptionPane.showInputDialog(" Task Description (max 50 chars):");

            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                i--;  
                continue;
            }

            String developerDetails = JOptionPane.showInputDialog(" Developer's First and Last Name:");
            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter Task Duration (in hours):"));

            String[] statuses = {"To Do", "Doing", "Done"};
            String taskStatus = (String) JOptionPane.showInputDialog(null, 
                    "Select Task Status", "Task Status", 
                    JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);

            Task task = new Task(taskName, i, taskDescription, developerDetails, taskDuration, taskStatus);
            tasks.add(task);
            totalHours += task.getTaskDuration();

            JOptionPane.showMessageDialog(null, task.printTaskDetails());
        }

        JOptionPane.showMessageDialog(null, "Total Duration: " + totalHours + " hours");
    }
}
