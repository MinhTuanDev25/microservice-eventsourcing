# Event Sourcing Microservices

Personal project for learning **Event Sourcing**, **CQRS**, and **microservices** with Spring Boot.

Each bounded context is a separate service: commands and events on the write side (Axon), read models via projections and queries. Services register with **Eureka**; **Spring Cloud Gateway** is the single entry point; shared code lives in **commonservice**.

> **Work in progress** — more services and infrastructure will be added over time.

---

## What's here (so far)

| Module | Description |
|--------|-------------|
| `discoverserver` | Eureka Server (`:8761`) |
| `apigateway` | API Gateway — routes to downstream services (`:8080`) |
| `bookservice` | Book domain (CQRS, `:9001`) |
| `employeeservice` | Employee domain (CQRS, `:9002`) |
| `commonservice` | Shared library (e.g. exception handling) |

**Stack:** Java 17 · Spring Boot 3 · Spring Cloud (Gateway, Eureka) · Axon · JPA / H2 · SpringDoc (Swagger UI)

---

## Quick start

```bash
# 1. Install shared lib
cd commonservice && mvn clean install -DskipTests

# 2. Run Eureka, microservices, then gateway (separate terminals)
cd discoverserver && mvn spring-boot:run
cd bookservice && mvn spring-boot:run
cd employeeservice && mvn spring-boot:run
cd apigateway && mvn spring-boot:run
```

**API entry point (via gateway):** `http://localhost:8080`  
Examples: `/api/v1/books/**`, `/api/v1/employees/**`

Swagger UI still on each service directly (e.g. `:9001`, `:9002` `/swagger-ui/index.html`).

---

## Author

**Minh Tuan**
