package org.app.DBRowMapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.app.model.UserTransaction;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class UserTransactionDataMapper implements RowMapper<UserTransaction> {

    @Override
    @Nullable
    public UserTransaction mapRow(@SuppressWarnings("null") ResultSet arg0, int arg1) throws SQLException {
        UserTransaction transaction = new UserTransaction();
        transaction.setCatagory(arg0.getString("catagory_name"));
        transaction.setExpanses(arg0.getInt("expanses_amount"));
        transaction.setIncome(arg0.getInt("income"));
        return transaction;
    }

}
