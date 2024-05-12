package org.app.Controller;

import org.app.model.Income;
import org.app.model.IncomeCategory;
import org.app.service.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IncomeController {
    IncomeService incomeService;

    public IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @PostMapping("/income")
    public String addIncomeRecord(Income income) {
        incomeService.addIncome(income);
        return "sucess";
    }

    @RequestMapping("/income")
    public ModelAndView shoIncomeCategory() {
        ModelAndView mav = new ModelAndView("income_form");
        List<IncomeCategory> categories = incomeService.getAllCategories();
        mav.addObject("categoryNames", categories);
        return mav;
    }
}
