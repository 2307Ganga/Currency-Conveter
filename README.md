# Currency Converter Application

This is a Spring Boot application that provides a simple currency conversion service. The application fetches exchange rates from an external API and allows users to convert amounts between different currencies through a web interface or API.

## Features

- Fetches real-time exchange rates using the [ExchangeRate-API](https://www.exchangerate-api.com).
- Allows currency conversion via REST API.
- User-friendly web interface for entering conversion details.
- Global exception handling for better error messages.
- Lightweight and easy to deploy.

---

## Technologies Used

- **Backend**: Spring Boot (Java)
- **Frontend**: HTML, CSS, JavaScript (Fetch API)
- **Dependencies**: 
  - `Spring Web`
  - `Spring Boot Starter`
  - `RestTemplate` for making external API calls
- **Build Tool**: Maven
- **External API**: ExchangeRate-API

---

## Installation and Setup

### Prerequisites

1. **Java**: Ensure Java JDK 11+ is installed on your machine.
2. **Maven**: Make sure Maven is installed and configured.
3. **Node.js** (Optional): For additional development with frontend tools.

### Steps to Run the Application

1. **Clone the Repository**
   ```bash
   git clone https://github.com/2307Ganga/CurrencyConverter.git
   cd CurrencyConverter

2. **To Run this Code**
   ```bash
   mvn clean install
   mvn spring-boot:run

