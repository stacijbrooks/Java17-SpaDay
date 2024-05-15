package org.launchcode.controllers;


import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    // Handler method to display the add user form
    @GetMapping("/user/add")
    public String displayAddUserForm() {
        return "add"; // Return path to the add.html template.
    }

    // Handler method to process form submission
    @PostMapping("/user/add")
    public String processAddUserForm(@ModelAttribute User user, Model model) {
        // Check if password and verify password match
        if (user.getPassword().equals(user.getVerifyPassword())) {
            // Passwords match, render the user/index.html view template with a welcome message
            model.addAttribute("username", user.getUsername());
            return "user/index"; // Return the path to the user/index.html template
        } else {
            // Passwords don't match, render the form again
            model.addAttribute("error", "Passwords do not match. Please try again.");
            return "add"; // Return the path to the add.html template
        }
    }
}


