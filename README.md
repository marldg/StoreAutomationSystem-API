# Store Automation System

REST API built with Java/Spring Boot to manage products, customers and sales, with automatic stock control and a dashboard endpoint for integration with external systems.

## Technologies
- Java + Spring Boot
- Spring Data JPA + MySQL
- Lombok + Maven

## How to run

### Prerequisites
- Java 17 or higher
- MySQL running locally

### Configuration
Rename `application.properties.example` to `application.properties` and fill in your credentials:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Starting
```bash
git clone https://github.com/marldg/StoreAutomationSystem-API.git
cd StoreAutomationSystem-API
./mvnw spring-boot:run
```
Server starts at `http://localhost:8080`. The database and tables are created automatically.

## Endpoints

### Products
| Method | Route | Description |
|---|---|---|
| POST | `/produtos` | Create product |
| GET | `/produtos` | List all |
| GET | `/produtos/{id}` | Find by id |
| PUT | `/produtos/{id}` | Update product |
| DELETE | `/produtos/{id}` | Delete product |

### Customers
| Method | Route | Description |
|---|---|---|
| POST | `/clientes` | Create customer |
| GET | `/clientes` | List all |
| GET | `/clientes/{id}` | Find by id |
| PUT | `/clientes/{id}` | Update customer |
| DELETE | `/clientes/{id}` | Delete customer |

### Sales
| Method | Route | Description |
|---|---|---|
| POST | `/vendas` | Register sale |
| GET | `/vendas` | List all |
| GET | `/vendas/{id}` | Get sale details |

### Dashboard (external integration)
| Method | Route | Description |
|---|---|---|
| GET | `/api/dados` | Returns total sales, number of orders and status |

The `/api/dados` endpoint requires API Key authentication in the header:
```
X-API-Key: your-key
```

**Sale request example:**
```json
{
  "clienteId": 1,
  "itens": [
    { "produtoId": 1, "quantidade": 2 }
  ]
}
```
When a sale is registered, the stock of the products is automatically decremented.

## Integration
This project integrates with the [Store Analysis](https://github.com/marldg/analisedaloja) — a JavaFX application that consumes the `/api/dados` endpoint and displays the data in a visual dashboard. Also integrates with the [Store Test Site](https://github.com/marldg/Loja-Teste-Completo) — a React + Tailwind CSS web interface.
