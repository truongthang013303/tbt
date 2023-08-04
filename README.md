# News Website
## IDE
		Intellij Idea

# Technologies:
-	Java 8
-	Spring Boot
-	Spring Data JPA
-	Spring Security
-	Thymeleaf
-	Bootstrap
-	Javascript
-	jQuery
-	MySQL
# Main Dependencies
- Spring Boot
- Spring Security
- Spring Data JPA
- Commonlang3
- Thymeleaf
- Thymeleaf Page Layout
- Thymeleaf Extra Security
- Spring Boot Validation
# How to use
### MySQL Configuration in 'src/main/resources/application.properties'
- spring.datasource.url=jdbc:mysql://localhost:3306/springboot_clone

- spring.datasource.username=root

- spring.datasource.password=123456
### Database name
- Database name: springboot_clone
### Create Database & Data of Tables
- Run this file in mysql: springboot_clone.sql
### The endpoints are located in 'http://localhost:8083/' and config its port on src/main/resources/application.properties
### Make sure to create a database called springboot
# Authority
- /quantri/** - [ADMIN] required
- Access to the admin page: /quantri
- Username: admin
- Password: 123456
# Links
### Admin Page Links
- /quantri - admin dashboard
- /quantri/theloai/danhsach - category management page
- /quantri/baiviet/danhsach – news management page
- /quantri/nguoidung/danhsach – user management page
- /quantri/role/danhsach – role management page
- /quantri/privilege – privilege management page
- /quantri/comment – comment management page
### User Home Page Links
- / - home page
### Other Links
- /login - login
- /logout - logout
- /register - register



