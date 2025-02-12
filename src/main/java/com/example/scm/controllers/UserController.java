package com.example.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {
    
    // User dashboard page
    @RequestMapping(value = "/dashboard", method=RequestMethod.GET)
    public String userDashboard() {
        return "user/dashboard";
    }
    
    // user profile page
    @RequestMapping(value = "/profile", method=RequestMethod.GET)
    public String userProfile() {
        return "user/profile";
    }

    // user add comtact page 

    // user view contact page 

    // user edit contact page 

    // user delete contact page

    // user search contact page

}
