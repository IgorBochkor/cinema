#cinema
In this project used N-tier architecture with **DB layer**, **DAO layer**, **Service layer**, **Controllers layer** and **View layer**. 
Project was developed according to SOLID principles with authorization and authentication.
**UML diagram that describes the relationship between the entities.**
____
One user can have multiple roles. 

_No role_: 

- Registration
- Authorization

_User_: 

- View a list of available movies
- View the list of cinema halls
- View order list
- Find session by date
- Add sessions to shopping cart
- Make an order

_Admin:_

 - View / add movie
 - View / add cinema hall
 - Add movie session
 - Find session by date
 - Find user by email
 ____
 
 **Technologies Used**
 
  - Java 14
  - Maven 4.0.0
  - Maven Checkstyle Plugin
  - Hibernate
  - Spring 
    + Core
    + MVC 
    + Security
  - MySQL RDBMS 
  - Apache Tomcat
  ____
  **Running the Project**
  
   1. Download and install the JDK.
   2. Download and install servlet container Apache Tomcat.
   3. Download and install MySQL.
   4. Find file _db.properties_ in resources and change the parameters to yours:
      + url: jdbc:mysql://localhost:3306/"your_db_name"?serverTimezone=UTC
      + username: "your MySQL username"
      + password: "your password"
   5. Run the project. 
   6. Default admin role:
    * login: "admin@gmail.com"
    * password: "123456"
  