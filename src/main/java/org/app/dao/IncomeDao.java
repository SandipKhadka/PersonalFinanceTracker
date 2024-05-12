package org.app.dao;

import org.app.model.Income;
import org.app.model.IncomeCategory;

import java.util.List;

public interface IncomeDao {
    void addIncome(Income income, String userName);
    List<IncomeCategory> getAllCategoriea();
}
