package org.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.app.config.DatabaseConnection;
import org.app.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    String sql;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public List<Transaction> getIncomeTransaction(String userName) {
        String sql = "SELECT income_amount,category_name,remarks FROM income INNER JOIN income_category ON income.income_category=income_category.category_id INNER JOIN user_details ON income.user_id = user_details.user_id WHERE user_name =?";
        List<Transaction> incomeTransactions = new ArrayList<Transaction>();
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setAmount(resultSet.getInt(1));
                transaction.setCategory(resultSet.getString(2));
                transaction.setRemarks(resultSet.getString(3));
                incomeTransactions.add(transaction);
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.getSQLState();
        }
        return incomeTransactions;
    }

    @Override
    public List<Transaction> getExpensesTransaction(String userName) {
        String sql = "SELECT expenses_amount,category_name,remarks FROM expenses INNER JOIN expenses_category ON expenses.expenses_category=expenses_category.category_id INNER JOIN user_details ON expenses.user_id = user_details.user_id WHERE user_name =?";
        List<Transaction> expensesTransactions = new ArrayList<Transaction>();
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setAmount(resultSet.getInt(1));
                transaction.setCategory(resultSet.getString(2));
                transaction.setRemarks(resultSet.getString(3));
                expensesTransactions.add(transaction);
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.getSQLState();
        }
        return expensesTransactions;
    }

}
