package org.app.dao;

import java.util.List;

import org.app.model.Transaction;

public interface TransactionDao {
    List<Transaction> getIncomeTransaction(String userName);

    List<Transaction> getExpensesTransaction(String userName);
}
