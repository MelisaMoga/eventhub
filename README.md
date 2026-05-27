# EventHub 🎉

A RESTful API for community event management built with **Java Spring Boot**.

## About

EventHub allows users to create and manage local community events — from hiking trips to board game nights. Users can browse events by category, join events, and organizers can approve or reject participation requests.

## Features

- **Event Management** — Create, update, delete and list events
- **Category System** — Organize events by category (Hiking, Board Games, Music, etc.)
- **Participation Workflow** — Users can request to join events with PENDING → APPROVED / REJECTED status
- **User Profiles** — User registration with bio and activity history

## Tech Stack

- **Java 21**
- **Spring Boot 3.5**
- **Spring Data JPA** — Database access and ORM
- **H2 Database** — In-memory database for development
- **Maven** — Dependency management

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

The server will start on `http://localhost:8080`

### Database Console

Access the H2 console at `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:eventhubdb`
- Username: `sa`
- Password: *(leave empty)*

## API Endpoints

### Categories
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/categories` | Get all categories |
| GET | `/api/categories/{id}` | Get category by ID |
| POST | `/api/categories` | Create a new category |
| DELETE | `/api/categories/{id}` | Delete a category |

### Events
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/events` | Get all events |
| GET | `/api/events/{id}` | Get event by ID |
| POST | `/api/events` | Create a new event |
| PUT | `/api/events/{id}` | Update an event |
| DELETE | `/api/events/{id}` | Delete an event |

### Participations
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/participations` | Get all participations |
| POST | `/api/participations/join?userId={id}&eventId={id}` | Join an event |
| PUT | `/api/participations/{id}/approve` | Approve a participation |
| PUT | `/api/participations/{id}/reject` | Reject a participation |

## Example Usage

**Create an event:**
```json
POST /api/events
{
  "title": "Mountain Hike",
  "description": "Easy hike for beginners",
  "location": "Bucegi",
  "eventDate": "2026-06-15T10:00:00",
  "maxParticipants": 10,
  "organizer": { "id": 1 },
  "category": { "id": 1 }
}
```

**Join an event:**
```
POST /api/participations/join?userId=2&eventId=1
```

**Approve a participation:**
```
PUT /api/participations/1/approve
```

## Project Structure

```
src/main/java/com/melisa/eventhub/
├── model/          # Entity classes (User, Event, Category, Participation)
├── repository/     # JPA Repositories
├── controller/     # REST Controllers
└── service/        # Business logic (coming soon)
```

## Roadmap

- [ ] JWT Authentication & Authorization
- [ ] Review and rating system
- [ ] Email notifications
- [ ] Search and filter events by location/date
- [ ] Switch to PostgreSQL for production

## Author

**Melisa Moga** — [github.com/MelisaMoga](https://github.com/MelisaMoga)
