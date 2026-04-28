package com.example.demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction (
        UUID id,
        String description,
        BigDecimal amount,
        LocalDateTime timestamp
) {
    public Transaction {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor deve ser positivo.");
        }
    }
}
