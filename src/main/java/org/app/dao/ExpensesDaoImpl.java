package org.app.dao;

import org.app.config.DatabaseConnection;
import org.app.model.Expenses;
import org.app.model.ExpensesCategory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpensesDaoImpl implements ExpensesDao {
    PreparedStatement preparedStatement;
    Connection connection;
    ResultSet resultSet;

    String sql, remarks;
    int userId, categoryId, amount;

    @Override
    public void addExpanses(Expenses expenses, String userName) {
        amount = expenses.getAmount();
        categoryId = expenses.getCategoryId();
        remarks = expenses.getRemarks();
        userId = getUserId(userName);
        sql = "INSERT INTO expenses(expenses_amount,expenses_category,user_id,remarks,date,time) VALUES (?,?,?,?,curdate(),curtime())";
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, categoryId);
            preparedStatement.setInt(3, userId);
            preparedStatement.setString(4, remarks);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ExpensesCategory> getAllCategory() {
        sql = "SELECT category_id, category_name FROM expenses_category";
        List<ExpensesCategory> expensesCategoryList = new ArrayList<ExpensesCategory>();
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ExpensesCategory expensesCategory = new ExpensesCategory();
                expensesCategory.setCategoryId(resultSet.getInt(1));
                expensesCategory.setCategoryName(resultSet.getString(2));
                expensesCategoryList.add(expensesCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expensesCategoryList;
    }

    @Override
    public int getExpensesAmount(String userName) {
        String sql = "SELECT SUM(expenses.expenses_amount ) FROM expenses INNER JOIN user_details ON expenses.user_id = user_details.user_id WHERE user_details.user_name =?";
        int expenses = 0;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                expenses = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.getSQLState();
        }
        return expenses;
    }

    public int getUserId(String userName) {
        try {
            sql = "SELECT user_id FROM user_details WHERE user_name=? ";
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
