package org.app.dao;

import org.app.model.Login;
import org.app.model.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean registerUser(Register user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String userName = user.getUserName();
        String password = user.getPassword();
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
        String userName = user.getUserName();
        String password = user.getPassword();
        String sql = "SELECT COUNT(*) FROM UserDetails WHERE userNAme=? AND password=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userName, password);
        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }
}
