package org.app.DBRowMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.app.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

public class IncomeTransactionMapping implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setAmount(rs.getInt("income_amount"));
        transaction.setCategory(rs.getString("category_name"));
        transaction.setRemarks(rs.getString("remarks"));
        return transaction;
    }
}
