package org.app.dao;

import org.app.model.Login;
import org.app.model.Register;

public interface UserDao {

    boolean registerUser(Register user);

    boolean loginUser(Login user);

    boolean isUserNameExistedInDatabase(Register user);

    boolean isPasswordCorrect(Login user);
    
}