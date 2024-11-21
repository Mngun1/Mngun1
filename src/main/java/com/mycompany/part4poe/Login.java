/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part4poe;

import java.util.regex.Pattern;

public class Login {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Login(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() >= 5;
    }

    public boolean checkPasswordComplexity() {
        return password.length() >= 8 &&
               Pattern.compile("[A-Z]").matcher(password).find() &&
               Pattern.compile("[0-9]").matcher(password).find() &&
               Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted. Ensure it contains an underscore and is no more than 5 characters.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted. Ensure it contains at least 8 characters, a capital letter, a number, and a special character.";
        }
        return "User registered successfully!";
    }

    public boolean loginUser(String inputUsername, String inputPassword) {
        return inputUsername.equals(this.username) && inputPassword.equals(this.password);
    }

    public String returnLoginStatus(boolean loginSuccess) {
        return loginSuccess
               ? "Welcome " + firstName + " " + lastName + ". It is great to see you again!"
               : "Username or password incorrect. Please try again.";
    }
}

