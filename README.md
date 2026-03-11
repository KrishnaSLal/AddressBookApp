# 📑 AddressBookApp 

---

## 📍 Project Overview

The **Address Book Application** is a Java-based project developed using **Test-Driven Development (TDD)** principles.  
It allows users to manage contact details such as name, address, city, state, zip, phone number, and email.

The project progressively evolves through **25 use cases**, implementing features like contact management, searching, sorting, file handling, database integration using JDBC, and REST API integration using JSON Server.

Each feature is implemented in a **separate Git branch following a "1 Use Case = 1 Branch" strategy**, ensuring structured development and clean version control.

---

## 🛠 Tech Stack

- **Java 8+** – Core programming language used to build the application  
- **Object-Oriented Programming (OOP)** – Used for designing classes and application structure  
- **JUnit 5** – Used for Test-Driven Development (TDD) and unit testing  
- **Maven** – Dependency management and project build tool  
- **JDBC** – Used for database connectivity and CRUD operations  
- **MySQL** – Database used to store Address Book contacts  
- **REST Assured** – Used for testing REST APIs with JSON Server  
- **JSON Server** – Mock REST API used to store and manage contacts  
- **OpenCSV** – Used for reading and writing CSV files  
- **Gson** – Used for JSON serialization and deserialization  
- **Git & GitHub** – Version control and repository management

---

## 🌿 Branching Strategy

This project follows a **feature branch workflow** where each use case is implemented in a separate branch.

- Total Use Cases: **25**
- Total Branches: **25**
- Each branch represents **one use case implementation**

### Structure

- `main` → Stable production-ready code
- `dev` → Integration branch for combining completed use cases
- `feature/UC1-CreateAddressBook`
- `feature/UC2-AddContact`
- `feature/UC3-EditContact`
- `feature/UC4-DeleteContact`
- `feature/UC5-AddMultipleContacts`
- ...
- `feature/UC25-DeleteEntryFromJsonServer`

### Benefits

- Clean commit history  
- Isolated feature development  
- Easy debugging and rollback  
- Professional Git workflow

## 📂 Project Structure

```text
AddressBookApp
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.addressbookapp
│   │   │       │
│   │   │       ├── AddressBookAppApplication.java     # Main Spring Boot Application
│   │   │       │
│   │   │       ├── config
│   │   │       │   └── OpenApiConfig.java             # Swagger/OpenAPI Configuration
│   │   │       │
│   │   │       ├── controller
│   │   │       │   └── AddressBookController.java     # REST API Controller
│   │   │       │
│   │   │       ├── dto
│   │   │       │   └── ContactDTO.java                # Data Transfer Object
│   │   │       │
│   │   │       ├── exception
│   │   │       │   ├── ContactNotFoundException.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │
│   │   │       ├── model
│   │   │       │   └── ContactPerson.java             # Entity Class
│   │   │       │
│   │   │       ├── repository
│   │   │       │   └── ContactPersonRepository.java   # Database Layer
│   │   │       │
│   │   │       └── service
│   │   │           ├── AddressBookService.java        # Service Interface
│   │   │           └── AddressBookIOService.java      # Service Implementation
│   │   │
│   │   └── resources
│   │       └── application.properties                 # Application Configuration
│   │
│   └── test
│       └── java
│           └── com.addressbookapp
│               ├── AddressBookAppApplicationTests.java
│               ├── AddressBookControllerTest.java
│               ├── AddressBookRestService.java
│               ├── AddressBookRestServiceTest.java
│               ├── AddressBookServiceTest.java
│               ├── ContactTest.java
│               └── SwaggerUiTest.java
│
├── pom.xml                                            # Maven Dependencies
└── README.md                                          # Project Documentation

```

# 📚Use Cases

---

## 🔹 UC1 – Create Address Book

Created the basic **Address Book application structure** to store contact details.

### Added:
- `ContactPerson` class
- Fields for contact details:
  - First Name
  - Last Name
  - Address
  - City
  - State
  - Zip
  - Phone Number
  - Email

📌 Focus: Designing the basic domain model for storing contacts.

---

🔗*Code Link* <br>
[UC1 - CreateContacts](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC1-CreateContacts)

---


## 🔹 UC2 – Add New Contact

Implemented functionality to **add a new contact** to the Address Book.

### Added:
- Method to create and store contact details
- Console input for entering contact information
- Storage using collection structure

📌 Focus: Basic contact creation and storage.

---

🔗*Code Link* <br>
[UC2 - AddContacts](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC2-add-contact)

---


## 🔹 UC3 – Edit Existing Contact

Enabled the ability to **edit existing contact details**.

### Implemented:
- Search contact using person's name
- Update existing contact fields

📌 Focus: Updating stored contact information.

---
🔗*Code Link* <br>
[UC3 - EditContact](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC3-edit-contact)

---


## 🔹 UC4 – Delete Contact

Added functionality to **delete a contact** from the Address Book.

### Implemented:
- Search contact by name
- Remove contact from collection

📌 Focus: Contact removal functionality.

---

🔗*Code Link* <br>
[UC4 - DeleteContact](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC4-delete-contact)


---

## 🔹 UC5 – Add Multiple Contacts

Extended the system to support **multiple contacts**.

### Implemented:
- Used collections to store contacts
- Allowed repeated contact creation

📌 Focus: Managing multiple records.

---

🔗*Code Link* <br>
[UC5 - AddMultipleContacts](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC5-add-multiple-contacts)

---

## 🔹 UC6 – Multiple Address Books

Refactored system to support **multiple Address Books**.

### Added:
- Map structure to manage multiple Address Books
- Unique Address Book names

📌 Focus: Managing multiple contact directories.

---

🔗*Code Link* <br>
[UC6 - Multiple AddressBook](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC6-multiple-addressbooks)

---

## 🔹 UC7 – Prevent Duplicate Contacts

Implemented validation to **prevent duplicate contacts**.

### Implemented:
- Duplicate detection based on name
- Validation before insertion

📌 Focus: Data consistency and validation.

---

🔗*Code Link* <br>
[UC7 - PreventDuplicateContact](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC7-prevent-duplicate-contact)

---

## 🔹 UC8 – Search by City or State

Added functionality to **search contacts by city or state**.

### Implemented:
- Filtering contacts using city
- Filtering contacts using state

📌 Focus: Efficient search functionality.

---

🔗*Code Link* <br>
[UC8 - SearchPersonByCityOrState](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC8-search-person-by-city-or-state)


---

## 🔹 UC9 – View Contacts by City or State

Implemented grouping of contacts by **city or state**.

### Added:
- Map for city → contacts
- Map for state → contacts

📌 Focus: Data organization.


---

🔗*Code Link* <br>
[UC9 - ViewPersonByCityOrState](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC9-view-persons-by-city-or-state)

---

## 🔹 UC10 – Count Contacts by City or State

Added functionality to **count contacts by location**.

### Implemented:
- Count contacts in each city
- Count contacts in each state

📌 Focus: Aggregation of data.

---

🔗*Code Link* <br>
[UC10 - CountContacts](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC10-count-contacts-by-city-or-state)

---

## 🔹 UC11 – Sort Contacts by Name

Added sorting functionality.

### Implemented:
- Alphabetical sorting using person name
- Collection sorting mechanisms

📌 Focus: Data ordering.


---

🔗*Code Link* <br>
[UC11 - CountContactsByCityOrState](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC11-sort-contacts-by-name)


---

## 🔹 UC12 – Sort by City, State, or Zip

Enhanced sorting capability.

### Implemented:
- Sort by city
- Sort by state
- Sort by zip code

📌 Focus: Flexible sorting operations.

---

🔗*Code Link* <br>
[UC12 - SortContactsByCityStateZip](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC12-sort-contacts-by-city-state-zip)


---

## 🔹 UC13 – File IO Support

Enabled storing Address Book data in files.

### Implemented:
- File writing
- File reading

📌 Focus: Persistence using File IO.


---

🔗*Code Link* <br>
[UC13 - ReadWriteAddressbook](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC13-read-write-addressbook-file-io)

---

## 🔹 UC14 – CSV File Support

Added support for **CSV file storage**.

### Implemented:
- OpenCSV library
- CSV write and read operations

📌 Focus: Structured file storage.

---

🔗*Code Link* <br>
[UC14 - ReadWriteAddressbookCSV](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC14-read-write-addressbook-csv)

---

## 🔹 UC15 – JSON File Support

Added support for **JSON-based persistence**.

### Implemented:
- Gson library
- JSON serialization and deserialization

📌 Focus: JSON data storage.

---

🔗*Code Link* <br>
[UC15 - ReadWriteAddressbookCSVFile](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC15-read-write-addressbook-json)

---

## 🔹 UC16 – Retrieve Contacts from Database

Connected Address Book to **database using JDBC**.

### Implemented:
- Database connection
- Retrieval of all contact records

📌 Focus: Database integration.

---

🔗*Code Link* <br>
[UC16 - RetrieveContacts](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC16-retrieve-contacts-from-database)

---

## 🔹 UC17 – Update Contact in Database

Implemented database update functionality.

### Implemented:
- Update contact details in DB
- Synchronize in-memory data

📌 Focus: Data synchronization.

---

🔗*Code Link* <br>
[UC17 - Update&Sync](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC17-update-contact-and-sync-with-db)

---



## 🔹 UC18 – Retrieve Contacts by Date Range

Added ability to **retrieve contacts added within a time period**.

### Implemented:
- Date field for contacts
- Query using date range

📌 Focus: Time-based queries.

---

🔗*Code Link* <br>
[UC18 - RetrieveContactsInParticularDate](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC18-retrieve-contacts-by-date-range)

---


## 🔹 UC19 – Count Contacts from Database

Implemented counting contacts directly from database.

### Implemented:
- Count by city
- Count by state

📌 Focus: Database aggregation.


---

🔗*Code Link* <br>
[UC19 - CountContactsState&CityFromDB](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC19-count-contacts-by-city-state-db)


---

## 🔹 UC20 – Add Contact to Database

Added functionality to **insert new contact into database**.

### Implemented:
- JDBC insert operations
- Transaction management

📌 Focus: Reliable database insertion.

---

🔗*Code Link* <br>
[UC20 - AddContactToDB](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC20-add-contact-to-database)

---

## 🔹 UC21 – Add Multiple Contacts Using Threads

Improved performance for bulk insertion.

### Implemented:
- Multithreading
- Parallel database inserts

📌 Focus: Performance optimization.

---

🔗*Code Link* <br>
[UC21 - MultipleContactsUsingMultithreading](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC21-add-multiple-contacts-multithreading)


---

## 🔹 UC22 – Read Contacts from JSON Server

Integrated **JSON Server REST API**.

### Implemented:
- REST Assured
- Fetch contacts from JSON server

📌 Focus: API integration.

---

🔗*Code Link* <br>
[UC22 - ReadEntriesFromJson](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC22-read-entries-from-jsonserver)

---

## 🔹 UC23 – Add Contacts to JSON Server

Added functionality to **insert contacts into JSON Server**.

### Implemented:
- REST API POST operations
- Synchronization with application memory

📌 Focus: REST API insertion.

---

🔗*Code Link* <br>
[UC23 - AddMultipleEntriesJsonServer](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC23-add-multiple-entries-jsonserver)

---

## 🔹 UC24 – Update Contacts in JSON Server

Implemented contact update through API.

### Implemented:
- REST API PUT/PATCH operations
- Update contact records

📌 Focus: API update functionality.

---


🔗*Code Link* <br>
[UC24 - UpdateEntryJsonServer](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC24-update-entry-jsonserver)

---

## 🔹 UC25 – Delete Contacts from JSON Server

Added functionality to delete contacts.

### Implemented:
- REST API DELETE operation
- Sync deletion with application memory

📌 Focus: Complete REST CRUD support.

---

🔗 *Code Link:* 

[UC25 – DeleteEntryJsonServer](https://github.com/KrishnaSLal/AddressBookApp/tree/feature/UC25-delete-entry-jsonserver)

---


# 🎯 Conclusion

The **Address Book Application** evolves from a simple contact management system to a fully functional **enterprise-style application** through 25 incremental use cases.

Throughout the development process, the project demonstrates:

- **Test-Driven Development (TDD)** practices  
- **Clean and modular architecture** using layered design  
- **File-based persistence** (File IO, CSV, JSON)  
- **Database integration** using JDBC  
- **REST API integration** using JSON Server and REST Assured  
- **Scalable and maintainable code structure**

By implementing features step by step through separate Git branches, the project highlights **structured development, proper version control, and progressive architectural improvements**.

This application provides a strong foundation for building **real-world Spring Boot and enterprise backend systems**.

---
