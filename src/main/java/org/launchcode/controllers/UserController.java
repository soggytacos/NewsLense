package org.launchcode.controllers;

import org.launchcode.models.classes.User;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value="profile/{userId}", method = RequestMethod.GET)
    public String viewProfile(Model model, @PathVariable Long userId) {
        User user = userDao.findById(userId).orElse(null);
        model.addAttribute("title", "Welcome!");
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        //model.addAttribute("dateCreated", user.getDateCreated());
        return "user/profile";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {
        model.addAttribute("title", "Create User");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(Model model, String verify, @ModelAttribute @Valid User user,
                                     Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create User");
            model.addAttribute(user);
            return "user/add";

        } else if (!user.getPassword().equals(verify)) {
            String passwordMessage = "Passwords do not match!";
            user.setPassword("");
            model.addAttribute("passwordMessage", passwordMessage);
            model.addAttribute(user);
            model.addAttribute("title", "Create User");
            return "user/add";

        } else {
            model.addAttribute(user);
            userDao.save(user);
            model.addAttribute("title", "Welcome " + user.getUsername() + "!");
            return "redirect:profile/" + user.getUserId();
        }
    }

}
