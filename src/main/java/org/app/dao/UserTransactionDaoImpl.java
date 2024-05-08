package org.app.dao;

import java.util.List;

import org.app.DBRowMapping.UserTransactionDataMapper;
import org.app.model.UserTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserTransactionDaoImpl implements UserTransactionDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserTransactionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserTransaction> getUserTransactionData(String userName) {
        String sql = "SELECT catagory_name, expanses_amount, income  FROM UserTransaction INNER JOIN Catagory ON UserTransaction.catagory = Catagory.Catagory_id WHERE UserTransaction.user_name = '"
                + userName + "'";
        List<UserTransaction> transactions = jdbcTemplate.query(sql, new UserTransactionDataMapper());
        return transactions;
    }

}
