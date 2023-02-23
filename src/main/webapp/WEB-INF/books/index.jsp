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
    <title>Read Share</title>
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
    <!-- ${books} -->
    <div class="container mt-4">
        <div>
            <div class="d-flex justify-content-between">
                <h1 class="text-center mb-2" >Welcome, <c:out value="${userName}"></c:out></h1>
                <a href="/logout">Logout</a>
            </div>
            <div class="d-flex justify-content-between">
                <p class="text-center">Books from everyone's shelves:</p>
                <a href="/books/new">+ Add a Book to my shelf!</a>
            </div>
        </div>
        <div class="row d-flex gap-4">
            <div class="col card border-dark">
                <!-- want to loop through my books and have them come out as a table -->
                <table class="table table-hover">
                    <thead>
                        <th>Tiitle</th>
                        <th>Author</th>
                        <th>Posted By</th>
                    </thead>
                    <tbody>
                        <!-- 
                            -> want a row for each element in my DB 
                            var -> is the varaible of iteration
                            items -> holds the list of books to be iterated over
                        -->
                        <c:forEach var="book" items="${books}">
                            <tr>
                                <!-- want the name of the book to link to the show page -->
                                <td><a href="/books/${book.id}">${book.title}</a></td>
                                <td>${book.author}</td>
                                <!-- ! this is not working -->
                                <!-- ~ got this to work after i added the id manually in DB -->
                                <td>${book.user.userName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <a class="btn btn-success mt-2" href="/books/new">Create a Book</a>
    </div>

</body>
</html>