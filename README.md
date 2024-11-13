# User Service - Ecommerce BackEnd

## Overview
The **User Service** is a core component of the **Ecommerce BackEnd** application, responsible for managing user-related operations such as registration, login, and CRUD operations for user profiles. It provides the authentication and authorization backbone for the application by issuing JWT tokens on successful login, which are used to secure access to other services through the API Gateway.

## Purpose
The **User Service** facilitates:
- **User Registration**: Allows new users to create an account with basic details.
- **Authentication**: Issues a JWT token on login, enabling secure communication with other services.
- **Role-Based Access**: Manages user roles (e.g., `admin`, `customer`), supporting access control across services.
- **User Profile Management**: Provides endpoints to update and manage user profiles.

## Key Features
- **JWT Token Generation**: Upon successful login, the service generates a JWT token that includes user role and identity.
- **Role Management**: Supports role-based access by storing user roles, allowing the API Gateway and other services to enforce authorization based on role.
- **CRUD Operations**: Allows admins to perform create, read, update, and delete operations on user profiles.

## API Endpoints
Here are the primary endpoints available in the **User Service**:

| Endpoint               | Method | Description                          | Access       |
|------------------------|--------|--------------------------------------|--------------|
| `/user/register`       | POST   | Register a new user                  | Public       |
| `/user/login`          | POST   | User login to obtain JWT token       | Public       |
| `/user/profile`        | GET    | Retrieve current user profile        | Authenticated|
| `/user/profile/update` | PUT    | Update user profile                  | Authenticated|
| `/user/all`            | GET    | Retrieve all user profiles           | Admin Only   |
| `/user/{id}`           | DELETE | Delete a user by ID                  | Admin Only   |

## Dependencies
- **Java 17** (or compatible version)
- **Spring Boot**: Provides the foundational framework for building the service.
- **Spring Security**: Used for securing endpoints and managing user authentication.
- **MySQL**: Database to store user information (username, password, role, etc.).

## Configuration
The configuration for the **User Service** includes settings for connecting to the MySQL database and configuring JWT secret keys for token generation. Key configuration properties are in the `application.yml` file.

## Security
This service is designed to support secure access across the **Ecommerce BackEnd** application:
- **Password Hashing**: User passwords are hashed before storage.
- **JWT Token**: Used for authenticating users across services. The token includes information about the user's role, enabling the API Gateway and other services to implement role-based access control.

This **User Service** is critical in managing user data and access in a secure, scalable way within the **Ecommerce BackEnd**.
