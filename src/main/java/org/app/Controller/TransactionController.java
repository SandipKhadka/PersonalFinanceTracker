package org.app.Controller;

import org.app.dao.TransactionDao;
import org.app.model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransactionController {
    TransactionDao transactionDao;
    HttpSession session;

    public TransactionController(TransactionDao transactionDao, HttpSession session) {
        this.transactionDao = transactionDao;
        this.session = session;
    }

    @RequestMapping("/transaction")
    public ModelAndView showIncomeTransaction() {
        ModelAndView modelAndView = new ModelAndView("transaction");
        String userName = (String) session.getAttribute("user");
        List<Transaction> incomeTransaction = transactionDao.getIncomeTransaction(userName);
        modelAndView.addObject("income", incomeTransaction);
        List<Transaction> expensesTransaction = transactionDao.getExpensesTransaction(userName);
        modelAndView.addObject("expense",expensesTransaction);
        return modelAndView;
    }

}
