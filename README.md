# Fitness Tracker Backend

Monorepo backend for **Fitness Tracker**, built with **Java 17**, **Spring Boot**, **Spring Security (JWT)**, **Feign**, and **Docker**.
The project is designed as a **microservice architecture** with clear boundaries, secure service-to-service communication, and production-ready defaults.

---

## 🧩 Architecture Overview

This backend consists of multiple Spring Boot services communicating over HTTP using **Feign** and secured with **JWT (RS256)**.

```
Client (Mobile / Frontend)
   │
   │  Authorization: Bearer <JWT>
   ▼
User Service
   │  (Feign + JWT forwarding)
   ▼
Auth Service (Internal API)
```

### Key Principles

* **Auth Service** issues JWT and owns authentication data
* **User Service** consumes Auth Service via Feign
* **No shared database** between services
* **Internal endpoints** protected via JWT scopes
* **Docker-first** setup

---

## 📁 Repository Structure

```
fitness-tracker-be/
│
├── libs/
│   └── common-core/          # Shared DTOs & common utilities
│
├── services/
│   ├── auth-service/         # Authentication & JWT issuer
│   └── user-service/         # User-facing APIs
│
├── docker-compose.yml        # Local & containerized runtime
└── pom.xml                   # Root Maven (multi-module)
```

---

## 🔐 Security Model

### JWT

* **Algorithm**: RS256 (asymmetric)
* **Issued by**: `auth-service`
* **Verified by**: `auth-service` and `user-service`
* **Key distribution**: public key via mounted file

### JWT Claims

Example payload:

```json
{
  "sub": "user-id",
  "scope": "user internal",
  "iat": 1700000000,
  "exp": 1700003600
}
```

### Scope Mapping

| Scope Value | Spring Authority |
| ----------- | ---------------- |
| `user`      | `SCOPE_user`     |
| `internal`  | `SCOPE_internal` |

Implemented using `JwtGrantedAuthoritiesConverter`.

---

## 🔒 Endpoint Classification

### Public Endpoints

Accessible by authenticated users.

| Service      | Endpoint              | Description          |
| ------------ | --------------------- | -------------------- |
| user-service | `GET /api/users/me`   | Current user profile |
| auth-service | `POST /auth/login`    | Login & JWT issuance |
| auth-service | `POST /auth/register` | User registration    |

### Internal Endpoints

Accessible **only** by other services with `SCOPE_internal`.

| Service      | Endpoint                   | Description             |
| ------------ | -------------------------- | ----------------------- |
| auth-service | `GET /internal/users/{id}` | User summary (internal) |

---

## 🔄 Service-to-Service Communication

### Feign Client

`user-service` uses **Spring Cloud OpenFeign** to call `auth-service`.

JWT is automatically forwarded using a custom **Feign RequestInterceptor**.

Key points:

* Token is extracted from `JwtAuthenticationToken`
* Authorization header is forwarded as-is

---

## 🐳 Docker & Runtime

### Docker Compose

Services are designed to run together using Docker Compose.

* Each service runs in its own container
* PostgreSQL runs as a shared infrastructure container
* JWT keys are mounted as read-only files

Example:

```yaml
user-service:
  ports:
    - "8082:8080"
  environment:
    JWT_PUBLIC_KEY_PATH: file:/app/config/jwt-public.pem
```

---

## 🗄️ Database Strategy

* Each service owns **its own database schema**
* No cross-service database access
* Migration handled via **Flyway** (auth-service)

---

## 🧪 Testing Strategy

* Manual testing via Postman
* JWT-based flows tested end-to-end
* Internal APIs validated through Feign

Recommended test flow:

1. Login via `auth-service`
2. Obtain JWT
3. Call `user-service /api/users/me`
4. Verify Feign → auth-service internal call

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Security (OAuth2 Resource Server)
* Spring Cloud OpenFeign
* PostgreSQL
* Flyway
* Docker & Docker Compose
* Maven (multi-module)

---

## 🚀 Getting Started

### Prerequisites

* Java 17
* Maven 3.9+
* Docker & Docker Compose

### Run Locally

```bash
mvn clean install
```

```bash
docker compose up --build
```
