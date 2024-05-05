package org.app.service;

import org.app.dao.UserDao;
import org.app.model.Login;
import org.app.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
    UserDao userDao;
    @Autowired
    HttpSession session;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registerService(Register user) {
        if (!userDao.isUserNameExistedInDatabase(user)) {
            if (userDao.registerUser(user)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean loginService(Login user) {
        if (userDao.loginUser(user)) {
            return true;
        } else {
            return false;
        }
    }

    public String getData() {
        String id = (String) session.getAttribute("user");
        return id;
    }
}
