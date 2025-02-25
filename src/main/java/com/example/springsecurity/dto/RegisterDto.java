package com.example.springsecurity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterDto {

    @NotEmpty
    private String username;
    
    @NotEmpty
    @Email
    private String email;

    @Size(min = 6, message = "Нужно ввести не менее 6 символов!")
    private String password;
    
    public String confirmPassword;
    
    public String getUsername() { return username; }
    
    public void setUsername(String username) { this.username = username; }
    
    public String getEmail() { return email; }
    
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    
    public void setPassword(String password) { this.password = password; }
    
    public String getConfirmPassword() { return confirmPassword; }
    
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}