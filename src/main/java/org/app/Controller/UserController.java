package org.app.Controller;

import org.app.model.Login;
import org.app.model.Register;
import org.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

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
    public String registerUser(@ModelAttribute("register") Register user, Model model, HttpSession session) {
        String userName = user.getUserName();
        try {
            if (!userService.isUserNameAvailable(userName)) {
                model.addAttribute("userNameError", "The username is already taken please try another");
                return "register";
            } else {
                userService.registerService(user);
                session.setAttribute("user", userName);
                return "redirect:/userdashboard";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/login")

    public String loginUser(Login user, Model model, HttpSession session, RedirectAttributes attributes) {
        String userName = user.getUserName();
        long password = user.hashedPassword();
        try {
            if (userService.isUserNameAvailable(userName)) { // isUserNameAvailable method returns true when username is
                                                             // not present in database otherwise false
                model.addAttribute("userNameError", "The username is not available please check properly");
                return "login";
            }
            if (!userService.isPasswordCorrect(userName, password)) {
                model.addAttribute("passwordError", "Incorrect password");
                return "login";
            }
            userService.loginService(user);
            session.setAttribute("user", userName);
            return "redirect:/userdashboard";
        } catch (Exception e) {
            return "error";
        }
    }

}
