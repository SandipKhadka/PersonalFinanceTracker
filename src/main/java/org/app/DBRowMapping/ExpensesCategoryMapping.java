package org.app.DBRowMapping;

import org.app.model.ExpensesCategory;
import org.springframework.jdbc.core.RowMapper;

import io.micrometer.common.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpensesCategoryMapping implements RowMapper<ExpensesCategory> {
    @Override
    @Nullable
    public ExpensesCategory mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
        ExpensesCategory expensesCategory = new ExpensesCategory();
        expensesCategory.setCategoryId(rs.getInt("category_id"));
        expensesCategory.setCategoryName(rs.getString("category_name"));
        return expensesCategory;
    }
}
