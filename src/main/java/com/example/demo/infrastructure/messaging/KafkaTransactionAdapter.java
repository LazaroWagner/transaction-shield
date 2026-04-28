package com.example.demo.infrastructure.messaging;

import com.example.demo.application.port.TransactionOutputPort;
import com.example.demo.domain.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaTransactionAdapter implements TransactionOutputPort {
    private final StreamBridge streamBridge;

    @Override
    public void send(Transaction transaction) {
        // o envio se beneficia das Virtual Theads configuradas no application.yml
        streamBridge.send("transaction-out-0", transaction);
    }

}
