# Inventory Management System (Java + JDBC + Swing)

A desktop inventory management application built with Java Swing and MySQL (via JDBC).  
It allows you to add, view, update, delete, and search products stored in a database.

---

## 📋 Features

- Add new products with name, quantity, price, category  
- View all products in a table  
- Update existing products  
- Delete products  
- Search by name or category  

---

## 🛠️ Tech Stack

- Java  
- JDBC (Java Database Connectivity)  
- Swing (Graphical User Interface)  
- MySQL  

---

## 🔧 Setup & Usage

### 1. Configure MySQL

Log into MySQL:
```sql
CREATE DATABASE inventorydb;
USE inventorydb;

CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  quantity INT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  category VARCHAR(50)
);

CREATE USER 'inventory_user'@'localhost' IDENTIFIED BY 'YourPasswordHere';
GRANT ALL PRIVILEGES ON inventorydb.* TO 'inventory_user'@'localhost';
FLUSH PRIVILEGES;

Update DBConnection.java

##Set correct credentials:

String url = "jdbc:mysql://localhost:3306/inventorydb?useSSL=false&serverTimezone=UTC";
String username = "inventory_user";   // or "root"
String password = "YourPasswordHere";
Compile the code

Open a terminal in your project directory:

javac -cp .:mysql-connector-j-9.4.0.jar *.java
4. Run the application

On Linux/macOS:

java -cp .:mysql-connector-j-9.4.0.jar InventoryGUI


On Windows (PowerShell / CMD):

java -cp ".;mysql-connector-j-9.4.0.jar" InventoryGUI
Project Architecture
User → Swing GUI → ProductDAO (SQL layer) → DBConnection → MySQL DB (products table)

