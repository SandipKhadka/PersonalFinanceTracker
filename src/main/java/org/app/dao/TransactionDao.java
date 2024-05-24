package org.app.dao;

import java.util.List;

import org.app.model.Transaction;
import org.app.model.TransactionFilter;

public interface TransactionDao {

    List<Transaction> getIncomeTransaction(String userName, TransactionFilter transactionFilter);

    List<Transaction> getExpensesTransaction(String userName);
}
