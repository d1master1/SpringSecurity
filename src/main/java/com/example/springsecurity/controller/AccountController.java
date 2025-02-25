package com.example.springsecurity.controller;

import com.example.springsecurity.model.AppUser;
import com.example.springsecurity.dto.RegisterDto;
import com.example.springsecurity.repo.AppUserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepo repo;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(
            Model model,
            @Valid @ModelAttribute RegisterDto registerDto,
            BindingResult result) {

        if(!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(new FieldError("registerDto", "confirmPassword", "Password and confirm do not match"));
        }

        AppUser appUser = repo.findByEmail(registerDto.getEmail());
        if(appUser != null) {
            result.addError(new FieldError("registerDto", "email", "Email is already used"));
        }

        if (result.hasErrors()) {
            return "register";
        }

        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            AppUser newUser = new AppUser();
            newUser.setUsername(registerDto.getUsername());
            newUser.setEmail(registerDto.getEmail());
            newUser.setRole("client");
            newUser.setCreatedAt(new Date());
            newUser.setPassword(bCryptEncoder.encode(registerDto.getPassword()));

            repo.save(newUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);
        }
        catch (Exception ex) {
            result.addError(new FieldError("registerDto", "userName", ex.getMessage()));
        }
        return "register";
    }
}