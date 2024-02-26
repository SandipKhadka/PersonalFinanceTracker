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
    public void registerService(Register user) {
        userDao.registerUser(user);
    }
    public void loginService(Login user) {
        userDao.loginUser(user);
    }
}
