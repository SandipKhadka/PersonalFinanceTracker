package org.app.Controller;

import org.app.dao.ExpensesDao;
import org.app.dao.IncomeDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashBoardController {
    IncomeDao incomeDao;
    ExpensesDao expensesDao;
    HttpSession session;

    public DashBoardController(IncomeDao incomeDao, ExpensesDao expensesDao, HttpSession session) {
        this.incomeDao = incomeDao;
        this.expensesDao = expensesDao;
        this.session = session;
    }

    @RequestMapping("/dashboard")
    public ModelAndView showIncome() {
        String userName = (String) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView("user_dashboard");
        int income = incomeDao.getIncomeAmount(userName);
        modelAndView.addObject("income", income);
        int expenses = expensesDao.getExpensesAmount(userName);
        modelAndView.addObject("expenses", expenses);
        int netIncome = income - expenses;
        modelAndView.addObject("netIncome", netIncome);
        return modelAndView;
    }
}
