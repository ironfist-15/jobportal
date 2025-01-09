package com.project.TalentFindr.controller;


import org.springframework.ui.Model;
import com.project.TalentFindr.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobPostActivityController {


    @Autowired
     public UsersService usersService;

    public JobPostActivityController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/dashboard/")
    public String searchJobs(Model model){

        Object currentUserProfile=usersService.getCurrentUserProfile();
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)){    //just checking if the user is not an anonymous one
            String currentUserName=authentication.getName();
            model.addAttribute("username",currentUserName);
        }
        model.addAttribute("user",currentUserProfile);
        return "dashboard";
    }

}
