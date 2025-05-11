# Spring Boot - Student Vaccination Portal

A backend application built with Spring Boot for student vaccination portal

---

## Prerequisites

Ensure you have the following installed on your system:

- **Java JDK** (17 or above recommended)
- **MySQL Server**
- **VSCode** (or any preferred IDE)
- **Maven** (if not bundled with your IDE)

---

## Clone the Repository

git clone https://github.com/Ashwath1999/vaccination-portal-backend

## Setup instructions

1.	Create MySQL Database and run this following command<br>
    **CREATE DATABASE vaccination_portal**<br>
2.	Configure MySQL connection<br>
**Edit src/main/resources/application.properties**<br>
*spring.datasource.url=jdbc:mysql://localhost:3306/vaccination_portal<br>
spring.datasource.username=root<br>
spring.datasource.password=yourpassword<br>*
3.	Run the Spring Boot server using the below command<br>
**./mvnw spring-boot:run**<br>
4.	Springboot will be running  at:<br>
**http://localhost:8081**

