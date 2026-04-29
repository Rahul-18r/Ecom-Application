# Ecom-Application

Basic Spring Boot backend for an e-commerce-style application.
Right now, the project implements basic User and Product modules with DTO-based request/response mapping.

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 (in-memory)
- Maven Wrapper (`mvnw`, `mvnw.cmd`)
- Lombok

## Current Architecture

The project follows a layered structure:

- `controller` -> REST API endpoints
- `service` -> business logic and data operations
- `model` -> domain entities (`User`, `Address`, `Product`)
- `dto` -> request/response DTOs
- `repository` -> Spring Data JPA repositories
- `configure` -> reserved for application configuration (can be expanded)

## Implemented Modules

Base URL: `http://localhost:8082`

### User API

Endpoints:

- `GET /api/users` -> fetch all users
- `GET /api/users/{id}` -> fetch one user by id
- `POST /api/users` -> create new user
- `PUT /api/users/{id}` -> update existing user

Sample Request (Create User):

```json
{
  "username": "rahul18",
  "email": "rahul18@example.com",
  "password": "pass@123",
  "phone": "9876543210",
  "addressDto": {
    "street": "MG Road",
    "city": "Bengaluru",
    "state": "Karnataka",
    "country": "India",
    "zip": "560001"
  }
}
```

### Product API

Endpoints:

- `GET /api/products` -> fetch all products
- `GET /api/products/{id}` -> fetch one product by id
- `POST /api/products` -> create new product
- `PUT /api/products/{id}` -> update existing product
- `DELETE /api/products/{id}` -> delete product

Sample Request (Create Product):

```json
{
  "name": "iPhone 15",
  "description": "Apple smartphone with A17 chip",
  "price": 79999.99,
  "stockQuantity": 50,
  "category": "Mobiles",
  "imageUrl": "https://example.com/images/iphone15.png",
  "active": true
}
```

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
curl -X POST http://localhost:8082/api/users -H "Content-Type: application/json" -d '{"username":"rahul18","email":"rahul18@example.com","password":"pass@123","phone":"9876543210","addressDto":{"street":"MG Road","city":"Bengaluru","state":"Karnataka","country":"India","zip":"560001"}}'
curl -X PUT http://localhost:8082/api/users/1 -H "Content-Type: application/json" -d '{"username":"rahul_updated","email":"rahul.new@example.com","password":"newpass@123","phone":"9999999999","addressDto":{"street":"Park Street","city":"Kolkata","state":"West Bengal","country":"India","zip":"700016"}}'
curl http://localhost:8082/api/products
curl http://localhost:8082/api/products/1
curl -X POST http://localhost:8082/api/products -H "Content-Type: application/json" -d '{"name":"iPhone 15","description":"Apple smartphone with A17 chip","price":79999.99,"stockQuantity":50,"category":"Mobiles","imageUrl":"https://example.com/images/iphone15.png","active":true}'
```

## Current Limitations

- Data is stored in an in-memory H2 database (resets on restart)
- No authentication/authorization yet
- Passwords are plain text in responses (demo only)

## Next Planned Enhancements

- Add DTO validation (`@Valid`) and error handling
- Add persistent database (MySQL/PostgreSQL)
- Add cart and order modules
- Add JWT authentication and role-based access

