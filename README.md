# **Book Club**
<!-- can have readme preview open as well to see how it will appear -->
<!-- - this is a bullet -->
# **Checklist**
<!-- ## this is a sub heading -->
## *Build a Full Stack Spring application that includes Login and Reg* 
- Build an application that requires both user authentication and validations
- Add server-side validations in addition to model-level validations
- Implement authentication logic
- Use 'Optionals' to check if a user exists
- Import and use 'BCrypt' to create hashes and compare hashed strings against the database
- Use and manipulate transient member variables and non-entity classes
- Handle user logout and active session status
- Use session data to pull the current user's information

## *TODO*
1. Add users to an application with Create and Read capabilities
2. Implement a one-to-many relationship between User and another model
3. Identify and implement routes for requests based on a wireframe
4. Manage a user session (login status) by storing and reading their ID in session
5. Apply cumulative skills to build and de-bug a full-stack application

<!-- 
Test: Show how to add a web browser
[website](https://www.google.com) -->


## *Schema*
- [ ] add a schema in MySQL Workbench


## *Applications Properties*
- [ ] update [application.properties](/src/main/resources/application.properties)
<!-- how to put in code blocks us ```-->
```
# Where are jsp files? HERE!
spring.mvc.view.prefix=/WEB-INF/
# Data Persistence
#<!-- after the '/' is <<WHATEVER_YOUR_SCHEMA_NAME>> that you created in MySQL Workbench -->
spring.datasource.url=jdbc:mysql://localhost:3306/book_club
spring.datasource.username=root
spring.datasource.password=rootroot
spring.jpa.hibernate.ddl-auto=update
# For Update and Delete method hidden inputs
spring.mvc.hiddenmethod.filter.enabled=true
```


## *POM File*
- ### After adding this to my pom fie it will ask if you want to update -> always say yes
- [ ] [pom.xml](pom.xml)
```
		<!-- 
		& add the two dependency files here 
		-> after save click yes so Maven can update the dependencies that we can use
		--> 

		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
		<dependency>
                <groupId>javax.servlet</groupId>
                <artifactId>jstl</artifactId>
        </dependency>

		<!-- & Bootstrap -->
	    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>webjars-locator</artifactId>
        <version>0.30</version>
		</dependency>
		
		<!-- BOOTSTRAP DEPENDENCIES -->
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>bootstrap</artifactId>
			<version>5.0.1</version>
		</dependency>
		<dependency>
			<groupId>org.webjars</groupId>
			<artifactId>jquery</artifactId>
			<version>3.6.0</version>
		</dependency>

        <!-- & DEPENDENCIES FOR DISPLAYING JSPS AND USING JSTL TAGS -->
        <dependency>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-jasper</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
        </dependency>

        <!-- & DEPENDENCY FOR USING VALIDATION ANNOTATIONS -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- & Bootswatch -->
		<dependency>
			<groupId>org.webjars.npm</groupId>
			<artifactId>bootswatch</artifactId>
			<version>5.2.3</version>
		</dependency>

        <!-- & DEPENDENCY FOR USING BCRYPT & -->
        <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
```


## *Index File*
- [ ] add Login and Reg. [index.jsp](src/main/webapp/WEB-INF/index.jsp)
<!-- 
when you make this 
    -> can click it and vs code will say the file is not there 
    -> can create file
-> make sure in the right place -->
```
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login and Registration</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
    <!-- Bootstrap CDN -->
    <link 
    rel="stylesheet" 
    href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/lux/bootstrap.min.css" 
    integrity="sha384-9+PGKSqjRdkeAU7Eu4nkJU8RFaH8ace8HGXnkiKMP9I9Te0GJ4/km3L1Z8tXigpG" 
    crossorigin="anonymous"
    >
</head>
<body>
    <!-- ${userId} -->
    <div class="container mt-2">
        <h1 class="text-center mb-2" >Login and Registration</h1>
        <div class="row d-flex gap-4">
            <div class="col card border-dark">
                <h2 class="text-center mt-2" >Register</h2>
                <form:form action="/register" method="post" modelAttribute="newUser">
                    <div>
                        <div class="form-group">
                        <form:label path="userName">Username:</form:label>
                        <form:input  class="form-control border border-dark" path="userName"/>
                        <form:errors class="text-danger" path="userName"/>
                        </div>
                        <div class="form-group">
                        <form:label path="email">Email:</form:label>
                        <form:input  class="form-control border border-dark" path="email"/>
                        <form:errors class="text-danger" path="email"/>
                        </div>
                        <div>
                        <form:label path="password">Password:</form:label>
                        <form:input  class="form-control border border-dark" path="password"/>
                        <form:errors class="text-danger" path="password"/>
                        </div>
                        <div>
                        <form:label path="confirm">Confirm PW:</form:label>
                        <form:input  class="form-control border border-dark" path="confirm"/>
                        <form:errors class="text-danger" path="confirm"/>
                        </div>
                    </div>
                    <input class="mt-2 mb-4 btn btn-dark btn-sm gap-2" type="submit" value="register">
                </form:form>
            </div>
            <div class="col card border-dark">
                <h2 class="text-center mt-2">Login</h2>
                <div class="form-group">
                    <form:form action="/login" method="post" modelAttribute="newLogin">
                        <div class="form-group">
                            <form:label path="email">Email:</form:label>
                            <form:input class="form-control border border-dark" path="email"/>
                            <form:errors class="text-danger" path="email"/>
                        </div>
                        <div>
                            <form:label path="password">Password:</form:label>
                            <form:input class="form-control border border-dark" path="password"/>
                            <form:errors class="text-danger" path="password"/>
                        </div>
                    <input class="mt-4 btn btn-dark btn-sm gap-2" type="submit" value="login">
                    </form:form>
                </div>
            </div>
    
        </div>
    </div>

</body>
</html>
```


## *Controllers*
<!-- 
-> Always generate the MainController by hand 
-> packages will differ for each directory 
-> this will make sure the file is in the right place
-->
- [ ] add controller [MainController.java](src/main/java/com/rochelle/book_club/controllers/MainController.java)

- [ ] add controller [BookController.java](src/main/java/com/rochelle/book_club/controllers/BookController.java)


- Add this to connect service with controller
```
//MainController.java
    @Autowired UserService userService;
```


## *Models*
- [ ] add model [User.java](src/main/java/com/rochelle/book_club/models/User.java)
    
        - [ ] add `@Entity` and `@Table` `@Id` `@GeneratedValue` to the model

- [ ] add a 2nd model [LoginUser.java](src/main/java/com/rochelle/book_club/models/LoginUser.java)

- [ ] add a 3rd model [Book.java](src/main/java/com/rochelle/book_club/models/Book.java)


## *Services*
<!-- need to add a service folder and file with whatever name you want -->
- [ ] add a [UserService.java](src/main/java/com/rochelle/book_club/services/UserService.java)

- [ ] add a [BookService.java](src/main/java/com/rochelle/book_club/services/BookService.java)


- Add this to connect user service with repository
```
//UserService.java
@Autowired UserRepository userRepository;
```

- Add this to connect book service with repository
```
//BookService.java
@Autowired BookRepository bookRepository;
```


## *Repositories*
<!-- need to add a repository folder and file with whatever name you want -->
- [ ] add a [UserRepository.java](src/main/java/com/rochelle/book_club/repositories/UserRepository.java)

- [ ] add a [BookRepository.java](src/main/java/com/rochelle/book_club/repositories/BookRepository.java)

- Extend CrudRepository to obtain all that comes with it
- Overide find method used to list Type
```
 - Example:

 /*
-> CrudRepository takes a generic of what ever we are tracking -> expense and then Long as it's data type for id
-> this is all we need to do to connect to our DB
-> best practice is to have the @Repository but is not needed -> just best practice
->  want code to be as readible as possible at a glance 
 */
@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long>{
    // we need to override our findByEmail to list a User by email
    List<User> findByEmail(String email);
}
```


# BookClub
