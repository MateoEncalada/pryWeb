package com.rungroop.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.rungroop.login.dto.RegistrationDto;
import com.rungroop.login.models.UserEntity;
import com.rungroop.login.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            System.out.println("Validation errors: " + result.getAllErrors());
            return "register";
        }

        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if (existingUserEmail != null) {
            result.rejectValue("email", null, "Email already in use");
            return "register";
        }

        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername != null) {
            result.rejectValue("username", null, "Username already in use");
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/clients?success";
    }
    
    

}
