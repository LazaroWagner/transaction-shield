package com.example.demo.infrastructure.persistence.repository;

import com.example.demo.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// Abstração do Spring Data JPA para operações ACID no Postgres.
public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}
