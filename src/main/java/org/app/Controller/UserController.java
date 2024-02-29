package org.app.Controller;

import org.app.model.Login;
import org.app.model.Register;
import org.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String registerUser(@ModelAttribute Register user, ModelMap model) {
        try {
            userService.registerService(user);
            return "redirect:/welcome.html";

        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

    @PostMapping("/login")

    public String loginUser(Login user) {
        try {
            userService.loginService(user);
            return "redirect:welcome.html";
        } catch (Exception e) {
            return "redirect:/error.html";
        }
    }

}
