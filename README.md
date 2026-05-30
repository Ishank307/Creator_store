# CreatorStore

A robust e-commerce backend service built with Spring Boot, designed to handle product inventory and customer orders seamlessly.

## 🚀 Features

- **Product Management:** Full CRUD capabilities for store products.
- **Order Processing:** Create orders with automated stock validation and deduction.
- **Inventory Control:** Prevents order creation if the requested product quantity exceeds available stock.
- **Data Persistence:** Uses Spring Data JPA and PostgreSQL to manage relationships between Orders, OrderItems, and Products.

## 🛠️ Tech Stack

- **Framework:** Spring Boot 3
- **Language:** Java 17
- **Database:** PostgreSQL
- **ORM:** Hibernate / Spring Data JPA
- **Utilities:** Lombok (for boilerplate reduction), `dotenv-java` (for environment variables)
- **API Documentation:** Springdoc OpenAPI (Swagger UI)

## ⚙️ Prerequisites

Before you begin, ensure you have met the following requirements:
- **Java 17** installed on your machine.
- **Maven** installed (or you can use the included `mvnw` wrapper).
- A running instance of **PostgreSQL**.

## 🔧 Environment Variables

This project uses `dotenv-java` to load environment variables from a `.env` file. 

Create a `.env` file in the root directory of the project and add your database configuration:

```env
DATABASE_URL=jdbc:postgresql://localhost:5432/your_database_name
DATABASE_USERNAME=your_postgres_username
DATABASE_PASSWORD=your_postgres_password
```

## 🏃 Running the Application

1. Clone the repository (if you haven't already).
2. Ensure your `.env` file is set up and your PostgreSQL database is running.
3. Build and run the application using Maven:

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## 📚 API Endpoints

Once the application is running, you can access the Swagger UI documentation at:
`http://localhost:8080/swagger-ui.html`

### Product API (`/api/products`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/products` | Retrieve a list of all products. |
| `GET` | `/api/products/{id}` | Retrieve a specific product by its ID. |
| `POST` | `/api/products` | Create a new product. |
| `PUT` | `/api/products/{id}` | Update an existing product. |
| `DELETE` | `/api/products/{id}` | Delete a product by its ID. |

**Example Product Creation Payload:**
```json
{
  "name": "Cool T-Shirt",
  "description": "A very cool t-shirt for creators.",
  "category": "Apparel",
  "price": 25.50,
  "stockQuantity": 100
}
```

### Order API (`/api/orders/`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/orders/` | Retrieve a list of all orders. |
| `GET` | `/api/orders/{id}` | Retrieve a specific order by its ID. |
| `POST` | `/api/orders/` | Create a new order (automatically deducts stock). |
| `DELETE` | `/api/orders/{id}` | Delete an order by its ID. |

**Example Order Creation Payload:**
```json
{
  "customerName": "John Doe",
  "customerEmail": "john@example.com",
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

## 🤝 Contributing

Contributions, issues, and feature requests are welcome! Feel free to check the issues page.
