package org.app.dao;

import org.app.config.DatabaseConnection;
import org.app.model.Income;
import org.app.model.IncomeCategory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class IncomeDaoImpl implements IncomeDao {
    int amount, categoryId, userId;
    String remarks, sql;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public void addIncome(Income income, String userName) {
        amount = income.getAmount();
        categoryId = income.getCategoryId();
        remarks = income.getRemarks();
        userId = getUserId(userName);
        try {
            connection = DatabaseConnection.getConnection();
            sql = "INSERT INTO income(income_amount, income_category, user_id, remarks, date, time) VALUES(?,?,?,?,CURDATE(),CURTIME())";
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
    public List<IncomeCategory> getAllCategories() {
        sql = "SELECT * FROM income_category";
        List<IncomeCategory> incomeCategoryList = new ArrayList<IncomeCategory>();
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                IncomeCategory incomeCategory = new IncomeCategory();
                incomeCategory.setCategoryId(resultSet.getInt(1));
                incomeCategory.setCategoryName(resultSet.getString(2));
                incomeCategoryList.add(incomeCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeCategoryList;
    }

    @Override
    public int getIncomeAmount(String userName) {
        String sql = "SELECT SUM(income.income_amount ) FROM income INNER JOIN user_details ON income.user_id = user_details.user_id WHERE user_details.user_name =?";
        int income = 0;
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                income = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return income;
    }

    public int getUserId(String userName) {
        sql = "SELECT user_id FROM user_details WHERE user_name =?";
        try {
            connection = DatabaseConnection.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
