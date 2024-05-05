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
    public String registerUser(@ModelAttribute("register") Register user, Model model) {
        try {
            if (!userService.registerService(user)) {
                model.addAttribute("loginError", "the user name is already existed");
                return "register";
            }
            userService.registerService(user);
            return "welcome";

        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/login")

    public String loginUser(Login user,Model model,HttpSession session,RedirectAttributes attributes) {
        try {
            if (userService.loginService(user)) {
                String userName = user.getUserName();
                session.setAttribute("user", userName);
                // ModelAndView modelAndView = new ModelAndView();
                // List<DataModel> dataModels = dataService.getDataService(session);
                // modelAndView.setViewName("user");
                // modelAndView.addObject("dataModels",dataModels);
                model.addAttribute("user", userService.getData());
                return "redirect:/welcome";
            } else {
                return "error";
            }
        } catch (Exception e) {
            return "eooror";
        }
    }

}
