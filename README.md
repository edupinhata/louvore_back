# Louvore Back — Church Worship Music Manager API

A production-ready Spring Boot backend for managing church worship music teams, musicians, vocalists, songs, playlists, services, and scheduling.

## Tech Stack

- Java 21
- Spring Boot 3.4.1
- Maven
- PostgreSQL 15
- Spring Data JPA + Flyway migrations
- Spring Security with JWT authentication
- Lombok
- springdoc-openapi / Swagger UI
- Testcontainers for integration tests
- Docker & docker-compose

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose (for local development)

## Running Locally

### Option 1: Docker Compose (Recommended)

```bash
# Start PostgreSQL + App
docker-compose up --build

# The API will be available at http://localhost:8080
# Swagger UI at http://localhost:8080/swagger-ui.html
```

### Option 2: Run with local PostgreSQL

1. Start PostgreSQL (or use the provided docker-compose for DB only):
   ```bash
   docker-compose up postgres
   ```

2. Run the application:
   ```bash
   export DB_URL=jdbc:postgresql://localhost:5432/louvore
   export DB_USERNAME=louvore
   export DB_PASSWORD=louvore
   export JWT_SECRET=your-secret-key-at-least-256-bits-long
   mvn spring-boot:run
   ```

## Default Admin Credentials

A default admin user is seeded on startup:
- **Username:** `admin`
- **Password:** `admin123`

> Change these via environment variables `ADMIN_USERNAME` and `ADMIN_PASSWORD` in production.

## Authentication

1. Get a JWT token:
   ```bash
   curl -X POST http://localhost:8080/api/v1/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username":"admin","password":"admin123"}'
   ```

2. Use the token in subsequent requests:
   ```bash
   curl http://localhost:8080/api/v1/churches \
     -H "Authorization: Bearer <your-token>"
   ```

## API Documentation

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/api-docs
- **Health Check:** http://localhost:8080/actuator/health

## API Base Path

All endpoints are under `/api/v1/`

### Key Endpoints

| Method | Path | Description |
|--------|------|-------------|
| POST | `/api/v1/auth/login` | Login and receive JWT |
| GET/POST | `/api/v1/churches` | List/Create churches |
| GET/PUT/DELETE | `/api/v1/churches/{id}` | Read/Update/Delete church |
| GET/POST | `/api/v1/members?churchId=...` | List/Create members |
| GET/POST | `/api/v1/instruments` | List/Create instruments |
| GET/POST | `/api/v1/songs` | List/Search/Create songs |
| GET/POST | `/api/v1/service-events?churchId=...` | List/Create service events |
| POST | `/api/v1/service-events/{id}/generate-schedule` | Generate schedule (placeholder) |
| GET/POST | `/api/v1/availabilities?serviceEventId=...` | List/Create availabilities |
| GET/POST | `/api/v1/schedule-assignments?serviceEventId=...` | List/Create assignments |

### Example: Create a Church

```bash
curl -X POST http://localhost:8080/api/v1/churches \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Igreja Batista Central",
    "city": "São Paulo",
    "state": "SP",
    "active": true
  }'
```

Response:
```json
{
  "id": "550e8400-e29b-41d4-a716-446655440000",
  "name": "Igreja Batista Central",
  "city": "São Paulo",
  "state": "SP",
  "active": true,
  "createdAt": "2024-01-01T00:00:00Z",
  "updatedAt": "2024-01-01T00:00:00Z"
}
```

### Error Response Format

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Church not found with id: 550e8400-...",
  "timestamp": "2024-01-01T00:00:00Z"
}
```

## Roles and Authorization

| Role | Permissions |
|------|-------------|
| `ADMIN` | Full access to all endpoints |
| `LEADER` | Create/update members, songs, events, assignments |
| `MEMBER` | Read access + manage own availability |

## Running Tests

```bash
# Integration tests use Testcontainers (Docker required)
mvn test
```

## Project Structure

```
src/main/java/com/louvore/louvore_back/
├── config/          # Spring configuration (JPA, Security, OpenAPI, Seeder)
├── controller/      # REST controllers
├── domain/
│   ├── entity/      # JPA entities
│   └── enums/       # Domain enumerations
├── dto/
│   ├── request/     # Input DTOs with validation
│   └── response/    # Output DTOs
├── exception/       # Global exception handling
├── mapper/          # Entity ↔ DTO mappers
├── repository/      # Spring Data JPA repositories
├── security/        # JWT filter, service, properties
└── service/
    ├── (interfaces) # Service contracts
    └── impl/        # Service implementations
```

## Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| `DB_URL` | `jdbc:postgresql://localhost:5432/louvore` | Database JDBC URL |
| `DB_USERNAME` | `louvore` | Database username |
| `DB_PASSWORD` | `louvore` | Database password |
| `JWT_SECRET` | (dev default) | JWT signing secret (min 256 bits) |
| `JWT_EXPIRATION_MS` | `86400000` | JWT expiry in ms (default: 24h) |
| `ADMIN_USERNAME` | `admin` | Default admin username |
| `ADMIN_PASSWORD` | `admin123` | Default admin password |

## Roadmap

### Next Backend Steps

1. **Ministry CRUD** — Add controllers for ministry management
2. **MemberSkill & VocalProfile** — Endpoints for skill/voice management
3. **Playlist & PlaylistSong** — Setlist management endpoints
4. **SongKeyOption** — Song key variants management
5. **SchedulingConstraint** — Constraint management for scheduling rules
6. **Scheduling Engine** — Implement heuristic-based auto-scheduling in `SchedulingServiceImpl`
7. **User Management** — Admin endpoints for user/role management
8. **Notifications** — Email/push notifications for schedule assignments
9. **Frontend Integration** — CORS configuration for React/Next.js frontend
10. **Observability** — Metrics (Micrometer/Prometheus), structured logging
11. **Caching** — Redis-based caching for frequent reads
12. **CI/CD** — GitHub Actions pipeline for build, test, and deployment
