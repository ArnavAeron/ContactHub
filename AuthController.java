package com.example.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.scm.entities.User;
import com.example.scm.helpers.Message;
import com.example.scm.helpers.MessageType;
import com.example.scm.repositories.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    // verify email

    @GetMapping("/verify-email")
    public String verifyEmail(
        @RequestParam String token,
        HttpSession session
    ){
        User user = userRepo.findByEmailToken(token).orElse(null);
        if(user != null){
            // if user is found then initiate processing 
            if(user.getEmailToken().equals(token))
            {
                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);
                session.setAttribute("message", Message.builder()
                .type(MessageType.green)
                .content("Your email is verified successfully , you can login now !!!")
                .build());
                return "/user/success_page";
            }
            session.setAttribute("message", Message.builder()
            .type(MessageType.red)
            .content("Email not verified ! Token is not associated with user")
            .build());
            return "/user/error_page";
        }
        session.setAttribute("message", Message.builder()
        .type(MessageType.red)
        .content("Email not verified ! Token is not associated with user")
        .build());
        return "/user/error_page";
    }
}
