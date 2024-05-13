package org.app.Controller;

import org.app.service.ExpensenService;
import org.app.service.IncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashBoardController {
    IncomeService incomeService;
    ExpensenService expensenService;

    public DashBoardController(IncomeService incomeService, ExpensenService expensenService) {
        this.incomeService = incomeService;
        this.expensenService = expensenService;
    }


    @RequestMapping("/dashboard")
    public ModelAndView showIncome() {
        ModelAndView modelAndView = new ModelAndView("user_dashboard");
        int income = incomeService.getIncomeAmount();
        modelAndView.addObject("income", income);
        return modelAndView;
    }
}
