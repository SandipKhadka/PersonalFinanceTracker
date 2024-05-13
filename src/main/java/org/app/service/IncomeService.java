package org.app.service;

import jakarta.servlet.http.HttpSession;
import org.app.dao.IncomeDao;
import org.app.model.Income;
import org.app.model.IncomeCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {
    IncomeDao incomeDao;
    HttpSession session;
    public  IncomeService(IncomeDao incomeDao, HttpSession session) {
        this.incomeDao = incomeDao;
        this.session = session;
    }

    public void addIncome(Income income) {
        String username = (String) session.getAttribute("user");
        incomeDao.addIncome(income,username);
    }
    public List<IncomeCategory> getAllCategories() {
        return incomeDao.getAllCategories();
    }
    public int getIncomeAmount() {
        String userName = (String) session.getAttribute("user");
        return incomeDao.getIncomeAmount(userName);
    }
}
