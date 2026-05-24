package org.example.interfaces;

import org.example.models.Transaction;

public interface TransactionRepository {
    void save(Transaction transaction);
}
