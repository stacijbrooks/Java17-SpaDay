package org.launchcode.controllers;

import ch.qos.logback.core.model.Model;
import org.launchcode.spaday.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    //handler method to display the add user form

    @GetMapping("/user/add")
    public String displayAddUserForm() {
        reutrn "add"; //return path to the add.html template.
    }

    public String processAddUserForm(Model model, @ModelAttribute User user, String verify) {
// add form submission handling code here
    }

    // Handler method to process form submission
    @PostMapping("/user")
    public String addUser(@ModelAttribute("user") User user, Model model) {
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

}
