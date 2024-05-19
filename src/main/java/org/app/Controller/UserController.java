package org.app.Controller;

import org.app.model.Login;
import org.app.model.Register;
import org.app.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("register") Register user, Model model, HttpSession session) {
        String userName = user.getUserName();
        try {
            if (!userDao.isUserNameAvailable(userName)) {
                model.addAttribute("userNameError", "The username is already taken please try another");
                return "register";
            } else {
                userDao.registerUser(user);
                session.setAttribute("user", userName);
                return "expenses_form";
            }
        } catch (Exception e) {
            return "error";
        }
    }

    @PostMapping("/login")

    public String loginUser(Login user, Model model, HttpSession session) {
        String userName = user.getUserName();
        long password = user.hashedPassword();
        try {
            if (userDao.isUserNameAvailable(userName)) { // isUserNameAvailable method returns true when username is
                                                         // not present in database otherwise false
                model.addAttribute("userNameError", "The username is not available please check properly");
                return "login";
            }
            if (!userDao.isPasswordCorrect(userName, password)) {
                model.addAttribute("passwordError", "Incorrect password");
                return "login";
            }
            userDao.loginUser(user);
            session.setAttribute("user", userName);
            return "redirect:/dashboard";
        } catch (Exception e) {
            return "error";
        }
    }
}
