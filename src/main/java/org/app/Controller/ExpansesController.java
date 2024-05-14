package org.app.Controller;

import jakarta.servlet.http.HttpSession;
import org.app.model.Expenses;
import org.app.model.ExpensesCategory;
import org.app.dao.ExpensesDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExpansesController {
    ExpensesDao expensesDao;
    HttpSession session;

    public ExpansesController(ExpensesDao expensesDao, HttpSession session) {
        this.expensesDao = expensesDao;
        this.session = session;
    }

    @PostMapping("/expenses")
    public String addExpensesRecord(Expenses expenses) {
        String userName = (String) session.getAttribute("user");
        expensesDao.addExpanses(expenses, userName);
        return "redirect:/dashboard";
    }

    @RequestMapping("/expenses")
    public ModelAndView showExpensesCategory() {
        ModelAndView mav = new ModelAndView("expenses_form");
        List<ExpensesCategory> expensesCategoryList = expensesDao.getAllCategory();
        mav.addObject("categoryNames", expensesCategoryList);
        return mav;
    }
}
