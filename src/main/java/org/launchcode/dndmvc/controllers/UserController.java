package org.launchcode.dndmvc.controllers;

import org.launchcode.dndmvc.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")

public class UserController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
        public String add(Model model){
        model.addAttribute("title", "Add User");
        model.addAttribute("user", new User());
        return "user/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
        public String add(Model model, @ModelAttribute @Valid User user, Errors errors, String verify_password){
        model.addAttribute("user", user);

        if(errors.hasErrors()) {
            return "user/add";
        }

        if (user.getPassword().equals(verify_password)) {
            return "user/index";
        }

        model.addAttribute("error_message", "I'm sorry, but password doesn't match verify password. Please try again.");
        return "user/add";
    }
}
