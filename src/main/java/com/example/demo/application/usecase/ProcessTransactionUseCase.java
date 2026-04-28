package com.example.demo.application.usecase;

import com.example.demo.application.port.TransactionOutputPort;
import com.example.demo.domain.model.Transaction;
import com.example.demo.infrastructure.persistence.entity.TransactionEntity;
import com.example.demo.infrastructure.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessTransactionUseCase {

    private final TransactionRepository repository; // Injeção direta ou via Port de persistencia
    private final TransactionOutputPort transactionOutputPort; // Porta de Saída (kafka)

    /**
     * O Use Case recebe 'Transaction' (Domain),
     * garantindo que a lógica de negócio seja independente da interface de entrada.
     */
    public void execute(Transaction transaction) {

        // 1. Persistencia (Garante a Consistencia ACID no Postgres)
        var entity = TransactionEntity.fromDomain(transaction);
        repository.save(entity);

        // Aqui entrariam validações de negócio complexas (ex: saldo, fraude)
        transactionOutputPort.send(transaction);
    }
}
