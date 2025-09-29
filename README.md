# Inventory Management System (Java + JDBC + Swing)

A desktop-based Inventory Management System built in Java using **JDBC** for MySQL database connectivity and **Swing** for the graphical user interface (GUI).

This project allows users to manage products efficiently with CRUD operations (Create, Read, Update, Delete) and search functionality.

---

## Features

- Add, update, and delete products.
- Search products by name or category.
- View products in a dynamic table.
- Store product data persistently in a MySQL database.
- Simple and user-friendly Swing GUI.

---

## Tech Stack

- **Java**  
- **JDBC** (Java Database Connectivity)  
- **Swing** (Graphical User Interface)  
- **MySQL**  

---

## Database Setup

1. Install MySQL.  
2. Create a database named `inventorydb`:
   ```sql
   CREATE DATABASE inventorydb;
Create the products table:

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    category VARCHAR(50)
);
Setup Instructions

Clone the repository:

git clone https://github.com/pastrystrawberry/INVENTORY-MANAGEMENT-SYSTEM.git
cd INVENTORY-MANAGEMENT-SYSTEM


Update your MySQL credentials in DBConnection.java:

String username = "root";
String password = "your_mysql_password";


Compile all Java files:

javac -cp .:mysql-connector-j-9.4.0.jar *.java


Run the application:

java -cp .:mysql-connector-j-9.4.0.jar InventoryGUI
Future Improvements

Input validation for numeric fields.

Export inventory data to CSV.

Better GUI styling and layout.

User authentication for multiple users.
