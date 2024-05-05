package org.app.dao;

import org.app.model.Login;
import org.app.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    String firstName, lastName, userName, password, sql;
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
        password = user.getPassword();
        String sql = "INSERT INTO UserDetails (FirstName,LastName,UserName,Password) values(?,?,?,?)";
        int result = jdbcTemplate.update(sql, firstName, lastName, userName, password);
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean loginUser(Login user) {
        userName = user.getUserName();
        password = user.getPassword();
        sql = "SELECT COUNT(*) FROM UserDetails WHERE UserName=? AND Password=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userName, password);
        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isUserNameExistedInDatabase(Register user) {
        userName = user.getUserName();
        sql = "SELECT COUNT(userName) FROM UserDetails WHERE UserName =?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isPasswordCorrect(Login user) {
        throw new UnsupportedOperationException("Unimplemented method 'isPasswordCorrect'");
    }

}
