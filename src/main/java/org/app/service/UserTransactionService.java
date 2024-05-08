package org.app.service;

import java.util.List;

import org.app.dao.UserTransactionDao;
import org.app.model.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class UserTransactionService {
    @Autowired
    HttpSession session;
    @Autowired
    UserTransactionDao userTransactionDao;

    public UserTransactionService(UserTransactionDao userTransactionDao) {
        this.userTransactionDao = userTransactionDao;
    }

    public List<UserTransaction> getTransactionData() {
        String userName = (String) session.getAttribute("user");
        return userTransactionDao.getUserTransactionData(userName);
    }
}
