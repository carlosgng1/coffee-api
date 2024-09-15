# Café API ☕️

This is a project for an API to manage orders, payments and products in a coffee shop. The API is built with **Spring Boot** and uses JSON files to store the information.

## Features

- Order management: Stores orders placed by users.
- Payment management: Records payments made by users.
- Product management: Defines available products, with different sizes and prices.

## Installation

1. Clone the repository

If you haven't already done so, clone this repository on your local machine:

````bash
git clone https://github.com/carlosgng1/coffee-api.git

2. Compile the project
The project uses Maven to manage dependencies and compilation. You can compile the project and download the dependencies by running:

mvn clean install

3. Run the application
Once the dependencies are installed, you can run the application in two ways:

You can run the application directly from Maven with the following command:

mvn spring-boot:run

The application will be available at http://localhost:8080.
API endpoints
The API exposes the following endpoints:

GET /pagado: Returns the total paid for each user.
GET /deuda: Returns the debt for each user, subtracting the payments made from the orders.

http://localhost:8080/pagado
http://localhost:8080/deuda

Testing
The project includes unit tests that can be run with Maven. The tests verify the business logic in the order and payment controllers.

To run the tests:

mvn test
This will run the tests in the src/test/java folder and display the results in the console.
