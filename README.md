# 🎬 Movie Ticket Booking System

A **Spring Boot** based Movie Ticket Booking System that demonstrates real-world backend architecture using **Object-Oriented Design**, **Design Patterns**, **Spring Boot**, **Spring Data JPA**, and **PostgresSQL**.

The application simulates an online movie ticket booking platform where users can browse movies, view shows, select seats, calculate ticket prices dynamically, process payments, and confirm bookings.

---

# ✨ Features

- 👤 User Management
- 🎥 Movie Management
- 🏢 Theatre Management
- 🖥️ Screen Management
- 💺 Seat Management
- 🎭 Show Scheduling
- 🔒 Seat Locking Mechanism
- 🎟️ Movie Ticket Booking
- 💰 Dynamic Pricing Strategies
- 💳 Payment Processing
- ✅ Booking Confirmation
- 📢 Observer-based Movie Notifications
- 🌐 RESTful APIs
- ⚠️ Global Exception Handling
- 📝 SLF4J Logging
- 🧪 Unit Testing with JUnit & Mockito

---

# 🛠 Tech Stack

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.x |
| Spring Data JPA | ✓ |
| Hibernate | ✓ |
| MySQL | ✓ |
| Maven | ✓ |
| JUnit 5 | ✓ |
| Mockito | ✓ |
| SLF4J | ✓ |
| Postman | API Testing |

---

# 📂 Project Structure

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── enums
├── exception
├── strategy
│   ├── Payment
│   └── Pricing
├── pubsub
├── config
└── util
```

---

# 🏛 Design Patterns Used

## 1. Strategy Pattern

Used to dynamically choose pricing and payment algorithms.

### Pricing Strategies

- Weekday Pricing
- Weekend Pricing
- Festival Pricing

### Payment Strategies

- Credit Card
- Debit Card
- UPI

Example:

```java
PricingStrategy pricingStrategy =
        pricingStrategyFactory.getPricingStrategy(show.getPricingType());

double totalAmount = pricingStrategy.calculatePrice(seats);
```

---

## 2. Factory Pattern

`PricingStrategyFactory` is responsible for returning the correct pricing strategy based on the pricing type.

Example:

```java
PricingStrategy pricingStrategy =
        pricingStrategyFactory.getPricingStrategy(show.getPricingType());
```

---

## 3. Builder Pattern

Used to construct immutable `Booking` objects.

Example:

```java
Booking booking = new Booking.BookingBuilder()
        .setUser(user)
        .setShow(show)
        .setSeats(seats)
        .setTotalAmount(totalAmount)
        .setPayment(payment)
        .build();
```

---

## 4. Observer Pattern

Implemented using

- MoviePublisher
- MovieSubscriber
- UserSubscriber

Whenever a new movie is published, all subscribed users are notified.

---

# 🔄 Booking Flow

```text
User
 │
 ▼
Select Movie
 │
 ▼
Select Show
 │
 ▼
Select Seats
 │
 ▼
Seat Lock Manager
 │
 ▼
Pricing Strategy
 │
 ▼
Payment Strategy
 │
 ▼
Payment Transaction
 │
 ▼
Booking Confirmed
```

---

# 🗄 Database Entities

- User
- Movie
- City
- Theatre
- Screen
- Seat
- Show
- Booking
- PaymentTransaction

### Entity Relationship Overview

```text
City
 └── Theatre

Theatre
 └── Screen

Screen
 ├── Seat
 └── Show

Movie
 └── Show

User
 └── Booking

Booking
 ├── Seats
 ├── PaymentTransaction
 └── Show
```

---

# 🌐 REST APIs

## User APIs

| Method | Endpoint |
|---------|----------|
| POST | `/users` |
| GET | `/users` |
| GET | `/users/{id}` |

---

## Movie APIs

| Method | Endpoint |
|---------|----------|
| POST | `/movies` |
| GET | `/movies` |
| GET | `/movies/{id}` |

---

## Theatre APIs

| Method | Endpoint |
|---------|----------|
| POST | `/theatres` |
| GET | `/theatres` |
| GET | `/theatres/{id}` |

---

## Show APIs

| Method | Endpoint |
|---------|----------|
| POST | `/shows` |
| GET | `/shows` |
| GET | `/shows/{id}` |

---

## Booking APIs

| Method | Endpoint |
|---------|----------|
| POST | `/bookings` |
| GET | `/bookings/{id}` |

---

# 💰 Pricing Flow

```text
Show
 │
 ▼
PricingType
 │
 ▼
PricingStrategyFactory
 │
 ├── WeekdayPricingStrategy
 ├── WeekendPricingStrategy
 └── FestivalPricingStrategy
```

---

# 🔒 Seat Locking Flow

```text
User selects seats
        │
        ▼
SeatLockManager
        │
        ▼
Lock Seats
        │
        ▼
Payment Success?
      /      \
    Yes       No
     │         │
     ▼         ▼
 Book Seats  Unlock Seats
```

---

# 💳 Payment Flow

```text
Booking Request
       │
       ▼
Calculate Price
       │
       ▼
Payment Strategy
       │
       ▼
Payment Transaction
       │
       ▼
Booking Confirmation
```

---

# 🧪 Unit Testing

The project uses **JUnit 5** and **Mockito** for unit testing.

Current test coverage includes:

- Booking Service
- Pricing Strategy
- Payment Strategy
- Observer Pattern (Publisher & Subscriber)

---

# ⚠️ Exception Handling

Custom exceptions implemented:

- ResourceNotFoundException
- SeatLockedException
- SeatAlreadyBookedException
- PaymentFailedException

A global exception handler returns consistent API responses.

---

# 📝 Logging

SLF4J logging has been added for:

- Booking creation
- Seat locking
- Payment processing
- Booking confirmation
- Error handling

---

# 🚀 Future Enhancements

- JWT Authentication
- Role-based Authorization
- Email Notifications
- SMS Notifications
- Redis-based Seat Locking
- Payment Gateway Integration (Stripe/Razorpay)
- Docker Support
- CI/CD Pipeline
- Swagger/OpenAPI Documentation
- Kafka-based Notifications

---

# 👩‍💻 Author

**Afrah Shabbeer**

If you found this project helpful, feel free to ⭐ the repository.
