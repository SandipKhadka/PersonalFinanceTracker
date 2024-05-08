package org.app.Controller;

import java.util.List;

import org.app.model.UserTransaction;
import org.app.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserTransactionController {
    @Autowired
    UserTransactionService userTransactionService;

    public UserTransactionController(UserTransactionService userTransactionService) {
        this.userTransactionService = userTransactionService;
    }

    @RequestMapping("/userdashboard")
    public ModelAndView showUserData() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserTransaction> transactions = userTransactionService.getTransactionData();
        modelAndView.setViewName("user_dashboard");
        modelAndView.addObject("user", transactions);
        return modelAndView;
    }
}
