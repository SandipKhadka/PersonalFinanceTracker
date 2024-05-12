package org.app.dao;

import org.app.DBRowMapping.ExpensesCategoryMapping;
import org.app.model.Expenses;
import org.app.model.ExpensesCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpensesDaoImpl implements ExpensesDao {
    JdbcTemplate jdbcTemplate;

    public ExpensesDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    String sql, remarks;
    int userId, categoryId, amount;

    @Override
    public void addExpanses(Expenses expenses, String userName) {
        amount = expenses.getAmount();
        categoryId = expenses.getCategoryId();
        remarks = expenses.getRemarks();
        if (userId == 0) {
            sql = "SELECT user_id FROM user_details WHERE user_name=? ";
            userId = jdbcTemplate.queryForObject(sql, Integer.class, userName);
        }
        sql = "INSERT INTO expenses(expenses_amount,expenses_category,user_id,remarks,date,time) VALUES (?,?,?,?,curdate(),curtime())";
        jdbcTemplate.update(sql, amount, categoryId,userId, remarks);
    }

    @Override
    public List<ExpensesCategory> getAllCategory() {
        sql = "SELECT category_id, category_name FROM expenses_category";
        List<ExpensesCategory> expensesCategories = jdbcTemplate.query(sql, new ExpensesCategoryMapping());
        return expensesCategories;
    }
}
