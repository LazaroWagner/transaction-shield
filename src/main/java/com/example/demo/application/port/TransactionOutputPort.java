package com.example.demo.application.port;

import com.example.demo.domain.model.Transaction;

public interface TransactionOutputPort {
    void send(Transaction transaction);
}
