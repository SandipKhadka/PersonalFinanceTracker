package org.app.Controller;

import org.app.model.Expenses;
import org.app.model.ExpensesCategory;
import org.app.service.ExpensenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExpansesController {
    ExpensenService expensenService;

    public ExpansesController(ExpensenService expensenService) {
        this.expensenService = expensenService;
    }

    @PostMapping("/expenses")
    public String addExpensesRecord(Expenses expenses) {
        expensenService.addExpanses(expenses);
        return "sucess";
    }

    @RequestMapping("/expenses")
    public ModelAndView showExpensesCategory() {
        ModelAndView mav = new ModelAndView("expenses_form");
        List<ExpensesCategory> expensesCategoryList = expensenService.getExpensesCategory();
        mav.addObject("categoryNames", expensesCategoryList);
        return mav;
    }
}
