# Shopping Application Backend

A robust, microservices-based backend for an e-commerce shopping application built with Spring Boot. This project demonstrates modern software architecture principles, including service decomposition, event-driven communication, distributed tracing, and API gateway pattern.

## 🏗️ Architecture

The application follows a microservices architecture with the following components:

- **API Gateway**: Centralized entry point for all client requests, handling routing, authentication, and load balancing using Spring Cloud Gateway.
- **User Service**: Manages user registration, authentication, and authorization with JWT tokens.
- **Item Service**: Handles product catalog management, including CRUD operations for items.
- **Order Service**: Processes shopping orders, integrates with Redis for caching, and publishes events to Kafka.
- **Audit Service**: Consumes audit events from Kafka and persists them in MongoDB for compliance and monitoring.

### Architecture Diagram
```
[Client] → [API Gateway] → [User Service]
                    ↓
              [Item Service] ← [Order Service] → [Kafka] → [Audit Service]
                    ↓
              [MySQL]        [Redis]              [MongoDB]
```

## 🚀 Features

- **Microservices Design**: Modular, independently deployable services
- **API Gateway**: Centralized routing and cross-cutting concerns
- **Authentication & Authorization**: JWT-based security
- **Event-Driven Architecture**: Asynchronous communication via Apache Kafka
- **Distributed Caching**: Redis for performance optimization
- **Observability**: OpenTelemetry tracing and metrics collection
- **API Documentation**: Swagger/OpenAPI integration
- **Database Variety**: MySQL, MongoDB, H2, and Redis for different use cases

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.x
- **Language**: Java 21
- **API Gateway**: Spring Cloud Gateway
- **Messaging**: Apache Kafka
- **Databases**: MySQL, MongoDB, H2
- **Caching**: Redis
- **Security**: Spring Security, JWT
- **Observability**: OpenTelemetry, Micrometer
- **Documentation**: SpringDoc OpenAPI
- **Build Tool**: Maven
- **Containerization**: Docker (partial)

## 📋 Prerequisites

Before running the application, ensure you have the following installed:

- Java 21
- Maven 3.6+
- MySQL 8.0+
- MongoDB 4.0+
- Redis 6.0+
- Apache Kafka 2.8+
- Docker (optional, for containerized services)

## 🔧 Installation & Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/shopping-app-backend.git
   cd shopping-app-backend
   ```

2. **Start Infrastructure Services**:
   - Start MySQL, MongoDB, Redis, and Kafka (using Docker or local installations)
   - Configure connection details in respective `application.properties` files

3. **Build all services**:
   ```bash
   # Build each service
   cd apigateway && mvn clean install
   cd ../user-service && mvn clean install
   cd ../item-service && mvn clean install
   cd ../order-service && mvn clean install
   cd ../audit-service && mvn clean install
   ```

## ▶️ Running the Application

Start services in the following order:

1. **Start Infrastructure** (Kafka, Redis, Databases)

2. **Start Audit Service** (listens for events):
   ```bash
   cd audit-service
   mvn spring-boot:run
   ```

3. **Start User Service**:
   ```bash
   cd user-service
   mvn spring-boot:run
   ```

4. **Start Item Service**:
   ```bash
   cd item-service
   mvn spring-boot:run
   ```

5. **Start Order Service**:
   ```bash
   cd order-service
   mvn spring-boot:run
   ```

6. **Start API Gateway**:
   ```bash
   cd apigateway
   mvn spring-boot:run
   ```

The API Gateway will be available at `http://localhost:4001`

## 📚 API Documentation

- **User Service**: http://localhost:4002/user-swagger-ui
- **Item Service**: http://localhost:4003/user-swagger-ui

## 🔍 Service Ports

- API Gateway: 4001
- User Service: 4002
- Item Service: 4003
- Order Service: 4004
- Audit Service: Default Spring Boot port (8080)


*This project showcases expertise in microservices architecture, Spring ecosystem, event-driven systems, and cloud-native development practices.*
