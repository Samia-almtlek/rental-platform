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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String registerUser(@ModelAttribute("user") User user,
                               @RequestParam("confirmPassword") String confirmPassword,
                               Model model

                               ) {
        // 0) Email must be an EHB student email
        if (!user.getEmail().toLowerCase().endsWith("@student.ehb.be")) {
            model.addAttribute("error", "Only EhB student emails are allowed (example@student.ehb.be).");
            return "register";
        }

        // 1) Email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email is already registered.");
            return "register";
        }

        // 2) Passwords must match
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";
        }

        // 3) Strong password validation (8 chars, upper, lower, digit, symbol)
        String password = user.getPassword();

        if (password.length() < 8 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*\\d.*") ||
                !password.matches(".*[!@#$%^&*()].*"))
        {
            model.addAttribute("error",
                    "Password must be at least 8 characters and include: uppercase, lowercase, number, and special symbol.");
            return "register";
        }
        // 4) Encrypt the password before saving to DB
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Default role for new users
        user.setRole("USER");

        // Save user in the database
        userRepository.save(user);

        // Redirect to login page after successful registration
        return "redirect:/login";
    }
}

