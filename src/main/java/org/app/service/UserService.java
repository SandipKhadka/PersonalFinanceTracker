package org.app.service;

import org.app.dao.UserDao;
import org.app.model.Login;
import org.app.model.Register;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
    UserDao userDao;
    HttpSession session;

    public UserService(UserDao userDao, HttpSession session) {
        this.userDao = userDao;
        this.session = session;
    }

    public boolean registerService(Register user) {
        return userDao.registerUser(user);
    }

    public boolean loginService(Login user) {
        return userDao.loginUser(user);
    }

    public boolean isUserNameAvailable(String usserName) {
        return userDao.isUserNameAvailable(usserName);
    }

    public boolean isPasswordCorrect(String userName, long password) {
        return userDao.isPasswordCorrect(userName, password);
    }

}
