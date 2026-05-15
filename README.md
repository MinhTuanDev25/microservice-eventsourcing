# Event Sourcing Microservices

Personal project for learning **Event Sourcing**, **CQRS**, and **microservices** with Spring Boot.

Each bounded context is a separate service: commands and events on the write side (Axon), read models via projections and queries. Services register with **Eureka**; shared code lives in **commonservice**.

> **Work in progress** — more services and infrastructure will be added over time.

---

## What's here (so far)

| Module | Description |
|--------|-------------|
| `discoverserver` | Eureka Server |
| `bookservice` | Book domain (CQRS) |
| `employeeservice` | Employee domain (CQRS) |
| `commonservice` | Shared library (e.g. exception handling) |

**Stack:** Java 17 · Spring Boot 3 · Spring Cloud · Axon · JPA / H2 · SpringDoc (Swagger UI)

---

## Quick start

```bash
# 1. Install shared lib
cd commonservice && mvn clean install -DskipTests

# 2. Run Eureka, then each service in its own terminal
cd discoverserver && mvn spring-boot:run
cd bookservice && mvn spring-boot:run      # :9001
cd employeeservice && mvn spring-boot:run  # :9002
```

API docs: Swagger UI on each service (`/swagger-ui/index.html`).

---

## Author

**Minh Tuan**
