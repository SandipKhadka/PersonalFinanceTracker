package org.app.DBRowMapping;

import org.app.model.IncomeCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeCategoryMapping implements RowMapper<IncomeCategory> {

    @Override
    public IncomeCategory mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setCategoryId(rs.getInt("category_id"));
        incomeCategory.setCategoryName(rs.getString("category_name"));
        return incomeCategory;
    }
}
