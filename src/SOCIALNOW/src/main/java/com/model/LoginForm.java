package com.model;

/**
 * Created by Erdem on 11/19/2015.
 */
public class LoginForm {

    String email;
    String password;
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
