/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part2tester;

import javax.lang.model.SourceVersion;

/**
 *
 * @author RC_Student_lab
 */
public class TEST2 {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public TEST2(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }
}



