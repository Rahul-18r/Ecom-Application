# Ecom-Application

Basic Spring Boot backend for an e-commerce-style application.
Right now, the project implements the User module with in-memory storage.

## Tech Stack

- Java 17+
- Spring Boot
- Maven Wrapper (`mvnw`, `mvnw.cmd`)
- Lombok

## Current Architecture

The project follows a layered structure:

- `controller` -> REST API endpoints
- `service` -> business logic and in-memory data operations
- `model` -> domain entities (`User`)
- `dto` -> reserved for request/response DTOs (can be expanded)
- `repository` -> reserved for persistence layer (can be expanded)
- `configure` -> reserved for application configuration (can be expanded)

## Implemented Module: User API

Base URL: `http://localhost:8082`

### Endpoints

- `GET /api/users` -> fetch all users
- `GET /api/users/{id}` -> fetch one user by id
- `POST /api/users` -> create new user (ID is generated server-side)
- `PUT /api/users/{id}` -> update existing user

### Sample Request (Create User)

```json
{
  "firstName": "Riya",
  "lastName": "Kapoor",
  "email": "riya@example.com",
  "password": "pass@123"
}
```

Note: Do not send `id` in create payload. It is read-only for request input and assigned by the service.

## Run Locally

Use Windows PowerShell from the project root:

```powershell
.\mvnw.cmd spring-boot:run
```

Application runs on:

- `http://localhost:8082`

## Quick API Checks

```powershell
curl http://localhost:8082/api/users
curl http://localhost:8082/api/users/1
curl -X POST http://localhost:8082/api/users -H "Content-Type: application/json" -d '{"firstName":"Riya","lastName":"Kapoor","email":"riya@example.com","password":"pass@123"}'
curl -X PUT http://localhost:8082/api/users/1 -H "Content-Type: application/json" -d '{"firstName":"Rahul","lastName":"Sharma","email":"rahul.new@example.com","password":"pass@321"}'
```

## Current Limitations

- Data is stored in memory (resets on restart)
- No database integration yet
- No authentication/authorization yet
- Passwords are plain text in responses (demo only)

## Next Planned Enhancements

- Add DTO validation (`@Valid`) and error handling
- Add database persistence (JPA + MySQL/PostgreSQL)
- Add product, cart, and order modules
- Add JWT authentication and role-based access

