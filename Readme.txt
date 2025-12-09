OPD Management System – Backend (Spring Boot + Hibernate)

This is the backend service for the OPD Management System, built using Spring Boot, Hibernate/JPA, and MySQL.
It provides REST APIs for managing patients, doctors, appointments, OPD visits, prescriptions, and authentication.
The backend is designed to run in Eclipse IDE as a Maven Spring Boot project.

✨ Features

Patient registration and management

Doctor and department management

Appointment creation and scheduling

OPD visit handling (diagnosis, notes, symptoms)

Prescription generation

JWT-based authentication and role management

Database integration using Hibernate/JPA

RESTful API structure

🛠️ Technologies Used

Java 17+

Spring Boot

Spring Web

Spring Security (JWT Authentication)

Hibernate / JPA

MySQL Database

Maven

Eclipse IDE

📁 Project Setup (Eclipse)
1. Import Project

Open Eclipse

Go to File → Import → Maven → Existing Maven Project

Select the backend folder:

opd-management-system/backend

2. Configure Database

Open src/main/resources/application.properties and update:

spring.datasource.url=jdbc:mysql://localhost:3306/opd_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🚀 Running the Backend

In Eclipse:

Right-click the project

Select Run As → Spring Boot App

Backend will start on:
👉 http://localhost:8080
