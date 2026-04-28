Transaction Service (WIP - Work in Progress)
Este microserviço é um motor de processamento de transações financeiras focado em alta performance e escalabilidade, utilizando o estado da arte do ecossistema Java. O projeto segue os princípios de Clean Architecture e Cloud Native Development
.
🛠 Tecnologias e Decisões Técnicas
Java 21 (Loom): Utilização de Virtual Threads para I/O não bloqueante em larga escala
.
Spring Boot 3.x: Base do framework para produtividade e integração nativa cloud.
Spring Cloud Stream: Abstração de mensageria para comunicação assíncrona via Kafka
.
PostgreSQL: Persistência relacional garantindo consistência transacional (ACID).
Testcontainers: Validação de integração real com instâncias Docker de Postgres e Kafka durante o build
.
Docker Multi-stage: Imagem otimizada para segurança (non-root) e tamanho reduzido.
🚀 Como Rodar o Projeto
1. Subir Infraestrutura
   Certifique-se de que o Docker está rodando. Este comando iniciará os brokers do Kafka e o banco PostgreSQL necessários:
   docker-compose up -d
2. Executar a Aplicação
   Com a infraestrutura ativa, inicie o serviço via Maven Wrapper:
   ./mvnw spring-boot:run
3. Executar Testes de Elite
   Para validar a integração real (via Testcontainers), utilize:
   ./mvnw clean test
   📈 Checklist de Progresso
   Core & Infra
   [x] Configuração inicial Java 21 + Virtual Threads
   .
   [x] Domínio com Java Records e validação de invariantes.
   [x] Persistência PostgreSQL com Spring Data JPA.
   [x] Mensageria Kafka com Spring Cloud Stream
   .
   [x] Dockerfile Multi-stage (Build & Runtime).
   [x] CI Pipeline com GitHub Actions (Build & Test).
   Testes & Resiliência
   [x] Testes de Integração com Testcontainers (Postgres + Kafka)
   .
   [ ] Implementação de Circuit Breaker (Resilience4j).
   [ ] Monitoramento e Observabilidade (Actuator + Prometheus).
   Cloud Native (Próximos Passos)
   [ ] Manifestos de Kubernetes (Deployment, Service, ConfigMaps)
   .
   [ ] Helm Charts para orquestração de ambiente.
   [ ] Estratégia de Secrets Management.