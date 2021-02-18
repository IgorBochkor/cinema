package com.dev.cinema.model.dto;

import com.dev.cinema.annotation.EmailValidation;
import com.dev.cinema.annotation.PasswordValidation;
import javax.validation.constraints.Size;

@PasswordValidation(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords don't match!"
)
public class UserRequestDto {
    @EmailValidation
    private String email;
    @Size(min = 6)
    private String password;
    private String repeatPassword;

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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
