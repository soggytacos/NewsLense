package org.launchcode.controllers;

import org.launchcode.models.User;
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

    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Welcome!");
        return "user/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {
        model.addAttribute("title", "Create User");
        model.addAttribute(new User());

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(Model model, String verify, @ModelAttribute @Valid User user, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create User");
            model.addAttribute(user);
            return "user/add";

        } else if (!user.getPassword().equals(verify)) {
            String passwordMessage = "Passwords do not match!";
            user.setPassword("");
            model.addAttribute("paswordMessage", passwordMessage);
            model.addAttribute(user);
            model.addAttribute("title", "Create User");
            return "user/add";
        } else {
            model.addAttribute(user);
            model.addAttribute("title", "Welcome " + user.getUsername() + "!");
            return "user/index";
        }
    }

}
