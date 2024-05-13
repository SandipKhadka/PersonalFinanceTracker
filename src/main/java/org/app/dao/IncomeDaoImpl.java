package org.app.dao;

import org.app.DBRowMapping.IncomeCategoryMapping;
import org.app.model.Income;
import org.app.model.IncomeCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncomeDaoImpl implements IncomeDao {
    JdbcTemplate jdbcTemplate;
    int amount, categoryId, userId;
    String remarks, sql;

    public IncomeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addIncome(Income income, String userName) {
        amount = income.getAmount();
        categoryId = income.getCategoryId();
        remarks = income.getRemarks();

        if (userId == 0) {
            sql = "SELECT user_id FROM user_details WHERE user_name = ?";
            userId = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        }
        sql = "INSERT INTO income(income_amount, income_category, user_id, remarks, date, time) VALUES(?,?,?,?,CURDATE(),CURTIME())";
        jdbcTemplate.update(sql, amount, categoryId, userId, remarks);
    }

    @Override
    public List<IncomeCategory> getAllCategories() {
        sql = "SELECT * FROM income_category";
        List<IncomeCategory> categories = jdbcTemplate.query(sql, new IncomeCategoryMapping());
        return categories;
    }

    @Override
    public int getIncomeAmount(String userName) {
        String sql = "SELECT SUM(income.income_amount ) FROM income INNER JOIN user_details ON income.user_id = user_details.user_id WHERE user_details.user_name =?";
        int income = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        return income;
    }

}
