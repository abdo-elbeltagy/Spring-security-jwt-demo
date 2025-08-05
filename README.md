# Spring Boot JWT Authentication App

A simple Spring Boot application demonstrating JWT-based authentication using Spring Security, H2 database, and a clean secret key setup using external YAML configuration.

---

## 🔐 1.Secret Key Setup

To keep your JWT secret key safe and out of version control:

1. **Create a file** `src/main/resources/secrets.yaml`:

```yaml
app:
  jwt:
    secret: <YOUR SECRIT>
```
2. **Import it in your** `application.yml`:
```yaml
spring:
    config:
        import: optional:file:secrets.yaml

```
▶️ How to Run the Application
✅ Prerequisites
Java 17+

Maven 3.8+

IntelliJ IDEA (or other Java IDE)

## 💻 Run with Maven
```bash
./mvnw spring-boot:run
```
Or run DemoApplication.java directly in your IDE.

## 🧪 Testing the API
### 1. 🔑 Authenticate to get JWT Token
Use Postman or curl.

📥 Request:
```http
POST http://localhost:8080/authenticate
Content-Type: application/json
```
```json
{
"username": "admin",
"password": "admin123"
}
```

📥 Response:
```json 
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```
### 2. 🔐 Call Secured Endpoint
Use the JWT token in the Authorization header.
```http 
GET http://localhost:8080/hello-admin
Authorization: Bearer <your-token-here>
```