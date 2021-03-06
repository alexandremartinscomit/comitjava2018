<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
        <title>Test page</title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="../css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <div class="wrap">
            <section>
                <div class="container">
                    <h1>Cars Management</h1>

                    <c:if test="${message != null}">
                        <div class="alert <c:out value='${message.type.reference}' />">
                          <strong><c:out value='${message.text}' /></strong>
                        </div>
                        <center>
                            <div>
                                <span></span>
                            <div>
                        </center>
                    </c:if>
                    <table class="table table-hover">
                        <caption><h2>List of Cars</h2></caption>
                        <tr>
                            <th>Plate</th>
                            <th>Brand</th>
                            <th>Year</th>
                            <th>Color</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="car" items="${listCar}">
                            <tr>
                                <td><c:out value="${car.plate}" /></td>
                                <td><c:out value="${car.brand}" /></td>
                                <td><c:out value="${car.year}" /></td>
                                <td><c:out value="${car.color}" /></td>
                                <td>
                                    <a href="edit?id=<c:out value='${car.id}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="delete?id=<c:out value='${car.id}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="new" role="button" class="btn btn-info btn-lg">Add Car</a>
                </div>
            </section>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>