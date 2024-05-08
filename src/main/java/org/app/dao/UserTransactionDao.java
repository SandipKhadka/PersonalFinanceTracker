package org.app.dao;

import java.util.List;

import org.app.model.UserTransaction;

public interface UserTransactionDao {
    List<UserTransaction> getUserTransactionData(String userName);
}
