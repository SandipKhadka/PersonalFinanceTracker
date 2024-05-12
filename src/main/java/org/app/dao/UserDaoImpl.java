package org.app.dao;

import org.app.model.Login;
import org.app.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    String firstName, lastName, userName, sql;
    long hashedPassword;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean registerUser(Register user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        userName = user.getUserName();
        hashedPassword = user.hashedPassword();
        sql = "INSERT INTO user_details(first_name,last_name,user_name,password) VALUES(?,?,?,?)";
        int result = jdbcTemplate.update(sql, firstName, lastName, userName, hashedPassword);
        return result == 1;
    }

    @Override
    public boolean loginUser(Login user) {
        userName = user.getUserName();
        hashedPassword = user.hashedPassword();
        return isPasswordCorrect(userName, hashedPassword);

    }

    @Override
    public boolean isUserNameAvailable(String userName) {
        sql = "SELECT COUNT(user_id) FROM user_details WHERE user_name =?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        return count != 1;
    }

    @Override
    public boolean isPasswordCorrect(String userName,long password) {
        sql = "SELECT COUNT(user_id) FROM user_details WHERE user_name =? AND password=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userName,password);
        return count == 1;
    }

}
