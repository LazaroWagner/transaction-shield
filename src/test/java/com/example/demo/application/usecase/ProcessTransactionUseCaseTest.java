package com.example.demo.application.usecase;

import com.example.demo.domain.model.Transaction;
import com.example.demo.infrastructure.persistence.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Testcontainers
public class ProcessTransactionUseCaseTest {

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.cloud.stream.kafka.binder.brokers", kafka::getBootstrapServers);
    }

    @Autowired
    private ProcessTransactionUseCase useCase;

    @Autowired
    private TransactionRepository repository;

    @Test
    public void deveProcessarESalvarTransacaoComSucesso() {
        // Given (Dado que temos um Record de Domínio Java 21
        var transaction = new Transaction(
                UUID.randomUUID(),
                "Pagamento Mentor",
                new BigDecimal("1500.00"),
                LocalDateTime.now()
        );

        // When (Quando executamos o Use Case)
        useCase.execute(transaction);

        // Then (Entao validamos a persistencia no Postgres real)
        var saved = repository.findById(transaction.id());
        assertThat(saved).isPresent();
        assertThat(saved.get().getAmount()).isEqualByComparingTo("1500.00");
    }
}
