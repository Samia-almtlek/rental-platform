package com.ehb.rental.rentalplatform.controller;


import com.ehb.rental.rentalplatform.model.User;
import com.ehb.rental.rentalplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ✅ Password encoder to securely store passwords
    @Autowired
    private PasswordEncoder passwordEncoder;
    // ----------------------------
    // SHOW REGISTER PAGE
    // ----------------------------
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        // Create an empty User object to bind form fields
        model.addAttribute("user", new User());
        return "register";
    }

    // ----------------------------
    // HANDLE FORM SUBMISSION
    // ----------------------------
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Encrypt the password before saving to DB
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role for new users
        user.setRole("USER");

        // Save user in the database
        userRepository.save(user);

        // Redirect to login page after successful registration
        return "redirect:/login";
    }
}

