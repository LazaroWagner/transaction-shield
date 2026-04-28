# 🛡️ Transaction Shield

Este microserviço é um motor de processamento de transações financeiras focado em alta performance e escalabilidade, utilizando o estado da arte do ecossistema Java.

## 🛠️ Tecnologias e Decisões Técnicas

* **Java 21 (Loom):** Utilização de Virtual Threads para I/O não bloqueante em larga escala.
* **Spring Boot 3.x:** Base do framework para produtividade e integração nativa cloud.
* **Spring Cloud Stream:** Abstração de mensageria para comunicação assíncrona via Kafka.
* **PostgreSQL:** Persistência relacional garantindo consistência transacional (ACID).
* **Testcontainers:** Validação de integração real com instâncias Docker durante o build.
* **Docker Multi-stage:** Imagem otimizada para segurança e tamanho reduzido.

---

## 🚀 Como Rodar o Projeto

1.  **Subir Infraestrutura:**
    ```bash
    docker-compose up -d
    ```
2.  **Executar a Aplicação:**
    ```bash
    ./mvnw spring-boot:run
    ```
3.  **Executar Testes de Elite:**
    ```bash
    ./mvnw clean test
    ```

---

## 📈 Checklist de Progresso

### Core & Infra
- [x] Configuração inicial Java 21 + Virtual Threads
- [x] Domínio com Java Records e validação de invariantes
- [x] Persistência PostgreSQL com Spring Data JPA
- [x] Mensageria Kafka com Spring Cloud Stream
- [x] Dockerfile Multi-stage (Build & Runtime)
- [x] CI Pipeline com GitHub Actions

### Testes & Resiliência
- [x] Testes de Integração com Testcontainers
- [ ] Implementação de Circuit Breaker (Resilience4j)
- [ ] Monitoramento e Observabilidade (Actuator + Prometheus)

### Cloud Native (Próximos Passos)
- [ ] Manifestos de Kubernetes (Deployment, Service)
- [ ] Helm Charts para orquestração
- [ ] Estratégia de Secrets Management