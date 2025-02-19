package com.example.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.scm.entities.User;
import com.example.scm.forms.UserForm;
import com.example.scm.helpers.Message;
import com.example.scm.helpers.MessageType;
import com.example.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }

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

    // This is showing login page
    // Login page
    @RequestMapping("/login")
    public String LoginPage(){
        System.out.println("Loading Login Page");
        return "login";
    }

    // registration page
    @RequestMapping("/register")
    public String RegistrationPage(Model model){
        System.out.println("Loading Registration Page");
        UserForm userForm = new UserForm();
        // // Setting default value for the field
        // userForm.setName("Arnav Aeron");
        // userForm.setAbout("my name is arnav aero");
        // userForm.setEmail("0211csds062@niet.co.in");
        // userForm.setPassword("1234");
        // userForm.setPhoneNumber("7906365249");
        model.addAttribute("userForm", userForm);
        return "register";
    }

    // processing register
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session){
        System.out.println("Processing Registration");
        System.out.println(userForm);
        // Fetch form data
        // UserForm
        // Validate form data
        if(rBindingResult.hasErrors()){
            return "register";
        }
        // Save to database
        // User Services
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://t3.ftcdn.net/jpg/07/24/59/76/360_F_724597608_pmo5BsVumFcFyHJKlASG2Y2KpkkfiYUU.jpg")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://t3.ftcdn.net/jpg/07/24/59/76/360_F_724597608_pmo5BsVumFcFyHJKlASG2Y2KpkkfiYUU.jpg");

        User savedUser = userService.saveUser(user);
        // message = Registration successfull
        System.out.println("user saved : ");

        // add message here
        Message message = Message.builder().content("Registration Sucessful").type(MessageType.green).build();
        session.setAttribute("message",message);
        // redirect login page        
        return "redirect:/register";
    }
}
