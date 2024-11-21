/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part4poe;



import javax.swing.JOptionPane;

public class UserRegistration {
    private Login user;

    // Method to register a new user
    public Login registerUser() {
        String username;
        String password;
        String firstName;
        String lastName;

        while (true) {
            // Collect user details
            firstName = JOptionPane.showInputDialog("Enter your First Name:");
            if (firstName == null || firstName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "First Name cannot be empty!");
                continue;
            }

            lastName = JOptionPane.showInputDialog("Enter your Last Name:");
            if (lastName == null || lastName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Last Name cannot be empty!");
                continue;
            }

            username = JOptionPane.showInputDialog("Enter a Username (must contain an underscore and be no more than 5 characters):");
            if (username == null || username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty!");
                continue;
            }

            password = JOptionPane.showInputDialog("Enter a Password (at least 8 characters, 1 uppercase letter, 1 number, 1 special character):");
            if (password == null || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty!");
                continue;
            }

            // Validate user input using Login class
            user = new Login(username, password, firstName, lastName);
            String registrationResult = user.registerUser();

            // Display result of registration
            JOptionPane.showMessageDialog(null, registrationResult);

            if (registrationResult.equals("User registered successfully!")) {
                break; // Exit loop if registration is successful
            }
        }

        return user;
    }
}

