package com.example.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    // home 
    @RequestMapping("/home")
    public String home(Model model) 
    {
        System.out.println("Home page handller !!!");

        // sending data to view 
        model.addAttribute("name", "Arnav Aeron");
        model.addAttribute("age", "21");
        model.addAttribute("GithubRepository", "https://github.com/ArnavAeron");
        return "home";
    }

    // about
    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin" , false);
        System.out.println("About page loading");
        return "about";
    }
    

    // services
    @RequestMapping("/services")
    public String servicesPage(){
        System.out.println("Services page loading");
        return "services";
    }

    // contact
    @RequestMapping("/contact")
    public String contactPage(){
        System.out.println("Contact Page Loading");
        return "contact";
    }

    // Login page
    @RequestMapping("/login")
    public String LoginPage(){
        System.out.println("Loading Login Page");
        return "login";
    }

    // Login page
    @RequestMapping("/register")
    public String RegistrationPage(){
        System.out.println("Loading Registration Page");
        return "register";
    }
}
