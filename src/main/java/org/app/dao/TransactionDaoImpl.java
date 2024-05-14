package org.app.dao;

import java.util.List;

import org.app.DBRowMapping.ExpenseTransactionMapping;
import org.app.DBRowMapping.IncomeTransactionMapping;
import org.app.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    String sql;

    JdbcTemplate jdbcTemplate;

    public TransactionDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> getIncomeTransaction(String userName) {
        String sql = "SELECT income_amount,category_name,remarks FROM income INNER JOIN income_category ON income.income_category=income_category.category_id INNER JOIN user_details ON income.user_id = user_details.user_id WHERE user_name =?";
        List<Transaction> incomeTransaction = jdbcTemplate.query(sql, new IncomeTransactionMapping(), userName);
        return incomeTransaction;
    }

    @Override
    public List<Transaction> getExpensesTransaction(String userName) {
        String sql = "SELECT expenses_amount,category_name,remarks FROM expenses INNER JOIN expenses_category ON expenses.expenses_category=expenses_category.category_id INNER JOIN user_details ON expenses.user_id = user_details.user_id WHERE user_name =?";
        List<Transaction> incomeTransaction = jdbcTemplate.query(sql, new ExpenseTransactionMapping(), userName);
        return incomeTransaction;
    }

}
