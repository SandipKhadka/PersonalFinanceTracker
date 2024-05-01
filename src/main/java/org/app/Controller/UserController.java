package org.app.Controller;

import org.app.model.Login;
import org.app.model.Register;
import org.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String show() {
        return "home_page";
    }

    @GetMapping("/register")

    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("register") Register user, Model model) {
        try {
            if(user.getUserName().equals("sandip")) {
                model.addAttribute("loginError", "sandip");
                return "register";
            }
            userService.registerService(user);
            return "welcome";

        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/login")

    public String loginUser(Login user) {
        try {
            if (userService.loginService(user)) {

                return "welcome";
            } else {
                return "error";
            }
        } catch (Exception e) {
            return "eooror";
        }
    }

}
