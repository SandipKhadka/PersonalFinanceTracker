package org.app.dao;

import org.app.model.Expenses;
import org.app.model.ExpensesCategory;

import java.util.List;

public interface ExpensesDao {
    void addExpanses(Expenses expenses, String userName);

    List<ExpensesCategory> getAllCategory();

    int getExpensesAmount(String userName);
}
