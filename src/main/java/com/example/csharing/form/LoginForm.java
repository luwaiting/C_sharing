package com.example.csharing.form;

import javax.validation.constraints.Email;

public class LoginForm {
    @Email(message = "please input the correct form")
    private String email;
    private String password;

    public String getEmail() {
        return email;
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
