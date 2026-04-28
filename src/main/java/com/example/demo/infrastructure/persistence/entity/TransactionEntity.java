package com.example.demo.infrastructure.persistence.entity;

import com.example.demo.domain.model.Transaction;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter // O JPA exige mutabilidade para o ciclo de vida da entidade
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class TransactionEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // private static Transaction transactionDomian;

    public static TransactionEntity fromDomain(com.example.demo.domain.model.Transaction domain) {
        return TransactionEntity.builder()
                .id(domain.id())
                .description(domain.description())
                .amount(domain.amount())
                .createdAt(domain.timestamp())
                .build();
    }
}
