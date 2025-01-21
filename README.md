# JDBC Lab: Database Browser Application

## Overview

This project is part of a **JDBC course** and showcases how to connect a Java application to a **MySQL database**. It demonstrates performing **CRUD (Create, Read, Update, Delete)** operations using **JDBC** and displaying the results via a **JavaFX** GUI. The goal is to allow users to interact with a **PersonDetails** database table, manage records, and navigate through them efficiently.
![[ui.png]]

## Key Features

### 1. **Database Connection**

- Connects to a **MySQL database** via **JDBC**.
- **Connection details** (URL, username, password) are stored securely in a `database.properties` file.
- **DataSource** is used to manage database connections and improve performance through connection pooling.

### 2. **CRUD Operations**

- **Create**: Add new records to the database.
- **Read**: Retrieve and display records from the database.
- **Update**: Modify existing records.
- **Delete**: Remove records from the database.

### 3. **GUI Navigation**

- Buttons for navigating records:
  - **First**: Jump to the first record.
  - **Previous**: Go to the previous record.
  - **Next**: View the next record.
  - **Last**: Navigate to the last record.
- **Clear**: Reset all input fields for fresh data entry.

### 4. **JavaFX GUI**

- The interface is designed with **JavaFX** and **Scene Builder**.
- The layout and design are stored in an **FXML file** (`primary.fxml`).
- **PrimaryController** class handles user interactions and updates the GUI dynamically.

### 5. **Maven Build System**

- Built with **Maven** for easy dependency management and project setup.
- **JavaFX** and **MySQL Connector/J** are integrated as Maven dependencies.

## Prerequisites

Before running the application, make sure you have the following:

- **Java 21** or later installed.
- A **MySQL database** with a table named **`PersonDetails`**, which should contain the following columns:
  - `ID` (Primary Key, Auto Increment)
  - `FirstName`
  - `MiddleName`
  - `LastName`
  - `Email`
  - `Phone`
- **Maven** installed to build and manage project dependencies.

## How to Run the Application

### 1. **Clone the Repository**

```bash
git clone <repository-url>
cd tableBrowser
```

### 2. **Configure the Database**

- Open the `database.properties` file and update it with your **MySQL database credentials**.
- Ensure that the **PersonDetails** table exists and contains the required columns in your database.

### 3. **Build the Project with Maven**

```bash
mvn clean install
```

### 4. **Run the Application**

```bash
mvn javafx:run
```

### 5. **Using the Application**

- Upon launch, the application will display the first record from the **`PersonDetails`** table.
- Use the navigation buttons to move between records.
- Perform **CRUD operations** by using the buttons for **New**, **Update**, and **Delete** actions.

## Code Structure

Here's a breakdown of the project structure:

- **`pom.xml`**: Maven build file, contains project dependencies and plugin configurations.
- **`database.properties`**: Contains the database connection details (URL, username, password).
- **`primary.fxml`**: Defines the user interface layout using **FXML** (JavaFX).
- **`module-info.java`**: Contains module information for Java 9 and later versions.
- **`App.java`**: The main application class that launches the **JavaFX** GUI.
- **`DatabaseManager.java`**: Manages all database connections and CRUD operations.
- **`PrimaryController.java`**: Handles user input and updates the GUI accordingly.

## Project Dependencies

The following dependencies are used:

- **JavaFX**: For building the graphical user interface.
- **MySQL Connector/J**: A JDBC driver for connecting to a **MySQL** database.

## Conclusion

This lab provides hands-on experience in building a **JavaFX** application that interacts with a **MySQL database** using **JDBC**. It covers essential topics such as **database connectivity**, **CRUD operations**, and **GUI development**. Through this project, you'll gain a deeper understanding of connecting Java applications to databases and building interactive desktop applications.
