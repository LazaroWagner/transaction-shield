package com.example.demo.infrastructure.rest.dto;

import com.example.demo.domain.model.Transaction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * O DTO é um Record imutável.
 * o métedo toDomain realiza o mapeamento para a entidade de negócio.
 */

public record TransactionDTO (
    @NotBlank(message = "A descrição é obrigatória")
    String description,

    @NotNull(message = "O valor é obrigatório")
    @Positive(message = "O valor deve ser positivo")
    BigDecimal amount
) {
    // Mapeamento DTO -> Dominio (Clean Architecture)
    public Transaction toDomain() {
        return new Transaction(
            UUID.randomUUID(),
            this.description,
            this.amount,
            LocalDateTime.now()
        );
    }
}
