## 🧾 POS Microservices System

A Point of Sale (POS) system built using Spring Boot 3.5.7, Java 21, MariaDB, Docker, and Eureka Discovery Server, following a Microservices Architecture.

This system supports JWT-based authentication and role-based authorization for Admin and Customer users.

## Features
 Authentication Service.

Register and login users (JWT authentication).

Roles: Admin and Customer.

Admins manage customers, products, and orders.

Customers can make orders.

 Product Service

Manage products (CRUD).

Only accessible to Admins.

 Order Service

Customers can place orders.

Admins can view and manage all orders.

 API Gateway

Acts as a single entry point for all requests.

Routes requests to the correct microservice.

Secures routes using JWT.

 Discovery Server (Eureka)

Enables service discovery and registration.

## Microservices Architecture

pos-microservices/

│
├── discovery-server/          # Eureka Discovery Server

├── config-server/             # Centralized Configurations (optional)

├── api-gateway/               # API Gateway for routing

├── auth-service/              # Handles user registration, login, JWT

├── customer-service/          # Customer profile management

├── product-service/           # Product CRUD operations

├── order-service/             # Order management

└── docker-compose.yml         # Container orchestration

## Technologies Used

Java 21

Spring Boot 3.5.7

Spring Cloud 2023.x

Spring Security & JWT

Spring Data JPA

Lombok

MariaDB

Eureka Discovery

Docker & Docker Compose

## Setup & Run Instructions
 Prerequisites

Java 21+

Maven 3.9+

Docker Desktop

Git

🪜 Steps
1. Clone the Repository
git clone https://github.com/<your-username>/pos-microservices.git
cd pos-microservices

2. Build All Services
mvn clean package -DskipTests

3. Start with Docker
docker-compose up --build

4. Access Services
Service	URL
Eureka Server	http://localhost:8761

API Gateway	http://localhost:8080

Auth Service	http://localhost:8081

Product Service	http://localhost:8082

Order Service	http://localhost:8083
 Authentication Overview

JWT Token generated during login.

Include the token in the Authorization header:

Authorization: Bearer <token>


Admin can:

Manage customers

Create, update, delete products

View all orders

Customer can:

Register & login

Browse products

Place orders

## Recommended Tools

Postman
 – Test APIs

Docker Desktop
 – Run containers

IntelliJ IDEA / VS Code
 – Development

GitHub Desktop
 – Easy version control

 Author

Saleh Mussa
Software Developer
📧 salehe.mussa.ngamilla@gmail.com




