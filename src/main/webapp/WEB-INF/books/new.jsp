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
        <div class= "border-dark">
            <div >
                <div class="d-flex justify-content-between">
                    <h2 class="text-center mb-2" >Add a Book to Your Shelf!</h2>
                    <a href="/books">Back to the Shelves</a>
                </div>
            </div>
            <div class="row d-flex gap-4">
                <div class="col card border-dark p-4">
                    <form:form action="/books" method="post" modelAttribute="book">
                        <!-- 
                            -> name of the attribute on the book class 
                            -> should be able to use just userId since it is from session
                        -->
                        <form:hidden path="user" value="${userId}"></form:hidden>
                        <div class="form-control">
                            <form:label path="title">Title:</form:label>
                            <form:errors path="title"></form:errors>
                            <form:input path="title"></form:input>
                        </div>
                        <div class="form-control">
                            <form:label path="author">Author:</form:label>
                            <form:errors path="author"></form:errors>
                            <form:input path="author"></form:input>
                        </div>
                        <div class="form-control">
                            <form:label path="myThoughts">My Thoughts:</form:label>
                            <form:errors path="myThoughts"></form:errors>
                            <form:input path="myThoughts"></form:input>
                        </div>
                        <input class="btn btn-dark btn-sm mt-3 mb-3" type="submit" value="Add Book">
                    </form:form>
                </div>
            </div>
        </div>
        <!-- <a class="btn btn-primary mt-2" href="/books/new">Add a Book</a> -->
    </div>

</body>
</html>