# CaseStudy

# Blood Bank Management System
This project is a simple Blood Bank Management System developed using Core Java and MySQL. The system allows users to manage blood donors, blood inventory, and blood requests through a menu-based console application.

## Classes
**BloodBankManagementSystem.java**: This is the main class that drives the application. It presents a menu to the user for managing donors, inventory, and requests.
**DatabaseConnection.java**: Manages the database connection using JDBC. It establishes the connection to the MySQL database.
**DonorManager.java**: Contains methods for adding, viewing, updating, and deleting donors in the database.
**InventoryManager.java**: Manages the blood inventory. It provides functionalities for adding, viewing, updating, and deleting inventory records.
**RequestManager.java**: Handles blood requests, including registering, viewing, updating, and deleting requests in the system.

## Steps to Run the Project
1. Clone the repository: git clone https://github.com/your-username/your-repository.git
2. Navigate to the project directory: cd CaseStudy/BloodBankManagementSystem/src (path here)
3. Compile the Java files: javac -cp "class path of your mysql jar file" com/bloodbank/management/*.java
4. Run the application: java -cp "class path of your mysql jar file" com.bloodbank.management.BloodBankManagementSystem

## Usage
Once you run the application, you will see a menu with the following options:

**Donor Management**:
Add new donors
View donor details
Update donor information
Delete donors

**Inventory Management**:
Add blood donations to the inventory
View blood inventory details
Update inventory information
Delete blood inventory records

**Request Management**:
Register blood requests
View request details
Update request status
Delete requests

## Conclusion
This project was created as a learning exercise to practice Java and MySQL. The Blood Bank Management System is a basic application that demonstrates how to interact with a database using JDBC in Java.
