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
    <!-- ${userId}
    ${books} -->
    <div class="container mt-4">
        <div>
            <div class="d-flex justify-content-between mt-2">
                <h1 class="text-center mb-2">${book.title}</h1>
                <a href="/books">Back to the Shelves</a>
            </div>
            <div class="mt-2">
                <h4 class="">${book.user.userName} read ${book.title} by ${book.author}</h4>
                <h5>Here are ${book.user.userName}'s thoughts:</h5>
            </div>
        </div>
        <div class="row gap-4">
            <div class="col card d-flex align-items-center border-dark border-end-0 border-start-0 mt-5 mb-3">
                <!-- want to loop through my books and have them come out as a table -->
                <p>${book.myThoughts}</p>
            </div>
        </div>
        
        <div class="d-flex gap-2">
            <!-- <a class="btn btn-primary mt-2" href="/books/new">Create a Book</a> -->
            <c:if test="${book.user.id == userId}">
                <a class="btn btn-success btn-sm" href="/books/edit/${book.id}">
                    Edit
                </a>
                <!-- this is for the delete 
                    -> we have to include the hidden input which is why the <form> tag is needed
                    -> the work around if we want to use put
                    -> must follow the restful routing 
                    -> need to create a route for delete
                    -> must create a a method for delete
                -->
                <form action="/books/${book.id}" method="post">
                <!-- circumvent the limitation of our browser by adding the hidden input
                    -> userId is what is in session
                    -> need to compare to current book 
                -->
                        <input type="hidden" name="_method" value="delete">
                        <input class="btn btn-danger btn-sm" type="submit" value="Delete">
                </form>
            </c:if>
        </div>
    </div>

</body>
</html>