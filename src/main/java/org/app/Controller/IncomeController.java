package org.app.Controller;

import jakarta.servlet.http.HttpSession;
import org.app.model.Income;
import org.app.model.IncomeCategory;
import org.app.dao.IncomeDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IncomeController {
    IncomeDao incomeService;
    HttpSession session;

    public IncomeController(IncomeDao incomeDao, HttpSession session) {
        this.incomeService = incomeDao;
        this.session = session;
    }

    @PostMapping("/income")
    public String addIncomeRecord(Income income) {
        String userName = (String) session.getAttribute("user");
        incomeService.addIncome(income, userName);
        return "redirect:/dashboard";
    }

    @RequestMapping("/income")
    public ModelAndView shoIncomeCategory() {
        ModelAndView mav = new ModelAndView("income_form");
        List<IncomeCategory> categories = incomeService.getAllCategories();
        mav.addObject("categoryNames", categories);
        return mav;
    }
}
