package org.app.service;

import jakarta.servlet.http.HttpSession;
import org.app.dao.ExpensesDao;
import org.app.model.Expenses;
import org.app.model.ExpensesCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensenService {
    HttpSession session;
    ExpensesDao expensesDao;

    public ExpensenService(HttpSession session, ExpensesDao expensesDao) {
        this.session = session;
        this.expensesDao = expensesDao;
    }

    public void addExpanses(Expenses expenses) {
        String userName = (String) session.getAttribute("user");
        expensesDao.addExpanses(expenses,  userName);
    }

    public List<ExpensesCategory> getExpensesCategory() {
        return expensesDao.getAllCategory();
    }
}
