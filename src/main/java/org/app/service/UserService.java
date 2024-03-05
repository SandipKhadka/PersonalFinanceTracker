package org.app.service;

import org.app.dao.UserDao;
import org.app.model.Login;
import org.app.model.Register;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registerService(Register user) {
        if (userDao.registerUser(user)) {
            return true;
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
}
