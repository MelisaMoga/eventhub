# EventHub 

![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-brightgreen?logo=springboot)
![JWT](https://img.shields.io/badge/Auth-JWT-blue?logo=jsonwebtokens)
![Swagger](https://img.shields.io/badge/Docs-Swagger-85EA2D?logo=swagger)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

A RESTful API for community event management built with **Java Spring Boot**.

Users can create local events (hiking, board games, photography walks, etc.), join them, and organizers can approve or reject participation requests — all secured with JWT authentication.

> 📖 **Interactive API Docs (Swagger):** Run the app and visit [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

---

## Features

-  **JWT Authentication** — Secure register & login with Bearer token protection
-  **Event Management** — Full CRUD for community events
-  **Category System** — Organize events by category (Hiking, Board Games, Music, etc.)
-  **Participation Workflow** — Join requests with `PENDING → APPROVED / REJECTED` status
-  **Swagger UI** — Interactive API documentation with live testing

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Core language |
| Spring Boot 3.5 | Web framework |
| Spring Data JPA | Database access and ORM |
| Spring Security | Authentication & authorization |
| JWT (jjwt 0.12) | Token-based auth |
| H2 Database | In-memory database for development |
| Swagger / OpenAPI 3 | Interactive API documentation |
| Maven | Dependency management |

---

## Getting Started

### Prerequisites
- Java 21+
- Maven

### Run the application

```bash
git clone https://github.com/MelisaMoga/eventhub.git
cd eventhub
./mvnw spring-boot:run
```

The server starts on `http://localhost:8080`

### Useful URLs

| URL | Description |
|---|---|
| `http://localhost:8080/swagger-ui/index.html` | 📖 Interactive API docs |
| `http://localhost:8080/h2-console` | 🗄️ Database console (dev only) |

**H2 Console connection:**
- JDBC URL: `jdbc:h2:mem:eventhubdb`
- Username: `sa`
- Password: *(leave empty)*

---

## How to use the API

All endpoints except `/api/auth/register` and `/api/auth/login` require a **JWT token**.

### Step 1 — Register a new user

```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "ana",
  "email": "ana@email.com",
  "password": "password123"
}
```

✅ Response:
```
User registered successfully
```

---

### Step 2 — Login and get your token

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "ana@email.com",
  "password": "password123"
}
```

 Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

> The token expires after **24 hours**. Login again to get a new one.

---

### Step 3 — Add the token to your requests

Include this header in every request:
```
Authorization: Bearer <your-token>
```

In **Swagger UI**: click the **Authorize ** button (top right) and paste your token.

In **Postman**: go to **Authorization tab → Bearer Token** and paste your token.

---

### Step 4 — Create an event

```http
POST /api/events
Authorization: Bearer <your-token>
Content-Type: application/json

{
  "title": "Photography Walk",
  "description": "Explore the city and take photos together",
  "location": "Bucharest",
  "eventDate": "2026-06-15T10:00:00",
  "maxParticipants": 8,
  "organizerId": 1,
  "categoryId": 1
}
```

>  Use `organizerId` and `categoryId` — not the full objects. IDs must exist in the database.

 Response: the created event object with full organizer and category details.

---

### Step 5 — Join an event

```http
POST /api/participations/join?userId=2&eventId=1
Authorization: Bearer <your-token>
```

 Response:
```json
{
  "id": 1,
  "user": { "id": 2, "username": "ana", ... },
  "event": { "id": 1, "title": "Photography Walk", ... },
  "status": "PENDING",
  "joinedAt": "2026-05-28T10:00:00"
}
```

---

### Step 6 — Approve or reject a participation (organizer)

```http
PUT /api/participations/1/approve
Authorization: Bearer <your-token>
```

```http
PUT /api/participations/1/reject
Authorization: Bearer <your-token>
```

✅ Response: the participation object with updated status (`APPROVED` or `REJECTED`).

---

## Full API Reference

###  Authentication
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/auth/register` | ❌ | Register a new user |
| POST | `/api/auth/login` | ❌ | Login and receive JWT token |

### 📅 Events
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/events` | ✅ | Get all events |
| GET | `/api/events/{id}` | ✅ | Get event by ID |
| POST | `/api/events` | ✅ | Create a new event |
| PUT | `/api/events/{id}` | ✅ | Update an event |
| DELETE | `/api/events/{id}` | ✅ | Delete an event |

### 🗂️ Categories
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/categories` | ✅ | Get all categories |
| GET | `/api/categories/{id}` | ✅ | Get category by ID |
| POST | `/api/categories` | ✅ | Create a new category |
| DELETE | `/api/categories/{id}` | ✅ | Delete a category |

### 🙋 Participations
| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| GET | `/api/participations` | ✅ | Get all participations |
| POST | `/api/participations/join?userId={id}&eventId={id}` | ✅ | Join an event |
| PUT | `/api/participations/{id}/approve` | ✅ | Approve a participation |
| PUT | `/api/participations/{id}/reject` | ✅ | Reject a participation |

---

## Project Structure

```
src/main/java/com/melisa/eventhub/
├── model/          # Entity classes (User, Event, Category, Participation)
├── dto/            # Request objects (e.g. CreateEventRequest)
├── repository/     # JPA Repositories — database access
├── controller/     # REST Controllers — API endpoints
├── security/       # JWT filter, utility, Swagger and security config
└── service/        # Business logic (coming soon)
```

---

## Roadmap

- [x] REST API with full CRUD
- [x] JWT Authentication & Authorization
- [x] Participation workflow (PENDING → APPROVED/REJECTED)
- [x] Swagger UI with authentication
- [ ] Review and rating system
- [ ] Search and filter events by location/date
- [ ] Email notifications
- [ ] Switch to PostgreSQL for production

---

## Author

**Melisa Nae** — [github.com/MelisaMoga](https://github.com/MelisaMoga)
