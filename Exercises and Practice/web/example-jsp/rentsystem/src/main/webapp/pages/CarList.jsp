<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Car Rent System</title>
</head>
<body>
    <center>
        <h1>Cars Management</h1>
        <h2>
            <a href="new">Add New Car</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Cars</a>

        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Cars</h2></caption>
            <tr>
                <th>ID</th>
                <th>Plate</th>
                <th>Brand</th>
                <th>Year</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="car" items="${listCar}">
                <tr>
                    <td><c:out value="${car.plate}" /></td>
                    <td><c:out value="${car.brand}" /></td>
                    <td><c:out value="${car.year}" /></td>
                    <td><c:out value="${car.color}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${car.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${car.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>