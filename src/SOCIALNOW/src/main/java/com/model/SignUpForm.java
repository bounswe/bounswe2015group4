package com.model;

/**
 * Created by Erdem on 11/17/2015.
 */
public class SignUpForm {


    String email;
    String password;
    String role;
    String name;

    @Override
    public String toString() {
        return "SignUpForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", Role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String surname;

}
