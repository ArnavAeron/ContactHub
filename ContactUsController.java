package com.example.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/contact")
public class ContactUsController {

    @PostMapping("/send-message")
    public String sendMessage(@RequestParam("name") String name,
                              @RequestParam("email") String email,
                              @RequestParam("message") String message,
                              Model model) {
        // Process the message (e.g., save to DB, send email, etc.)
        
        // For now, just log it
        System.out.println("Message received from: " + name + " (" + email + ")");
        System.out.println("Message: " + message);
        
        // Send success feedback
        model.addAttribute("successMessage", "Message sent successfully!");
        
        // Redirect back to the contact page with a success message
        return "contact";
    }
}
