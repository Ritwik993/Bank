# 🏦 Bank Application – Microservices Backend

This project is a **backend system for a Bank Application** built using **Spring Boot** and **Microservices Architecture**.  
It consists of three independent microservices that handle core banking functionalities.

---

## 📌 Microservices Overview

The application is divided into the following microservices:

### 1️⃣ Accounts Microservice
- Manages customer account details
- Create and fetch bank accounts
- Handles account-related operations

### 2️⃣ Loans Microservice
- Manages loan information for customers
- Create and retrieve loan details
- Handles different types of loans

### 3️⃣ Cards Microservice
- Manages debit/credit card details
- Issue and fetch card information
- Handles card-related operations

Each microservice is **independently deployable** and follows RESTful API principles.

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- REST APIs
- H2 / MySQL (configurable)
- Maven
- Docker (optional)

---

## 🧩 Architecture

- Microservices-based architecture
- Each service has its own database
- Loose coupling between services
- Easily scalable and maintainable

---

## 📂 Project Structure

```text
bank-application-backend/
│
├── accounts/
│   └── Accounts Microservice
│
├── loans/
│   └── Loans Microservice
│
├── cards/
│   └── Cards Microservice
│
└── README.md
