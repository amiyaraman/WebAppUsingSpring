<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>List Todos Page</title>
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >

</head>
<body>
<%@include file="comman/navigation.jspf"%>
<div class="container">
    <div>
        <h1>Welcome ${name}</h1>
        <hr>
    </div>
    <div>
        <h1>Your Todos</h1>
        <table class="table">
            <thead>
            <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>Is Done?</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${todos}" var="todo">
                <tr>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td><a href="delete-todo?id=${todo.id}" class=" btn btn-outline-danger">Delete</a></td>
                    <td><a href="update-todo?id=${todo.id}" class="btn btn-outline-primary">Update</a></td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
        <a href="add-todo" class="btn btn-success">Add Todo</a>
    </div>

</div>
<%@include file="comman/footer.jspf"%>