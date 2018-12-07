<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
        <title>Rent Orders</title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "/pages/common/menu.jsp" %>
        <div class="wrap">
            <section>
                <div class="container">
                    <h1>RentOrders Management</h1>

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
                        <caption><h2>List of RentOrders</h2></caption>
                        <tr>
                            <th>Rent Date</th>
                            <th>Car Model</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="rentOrder" items="${listRentOrder}">
                            <tr>
                                <td><c:out value="${dateFormatter.format(rentOrder.rentDate)}" /></td>
                                <td><c:out value="${rentOrder.car.model}" /></td>
                                <td><c:out value="${rentOrder.price}" /></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/rentOrder/edit?id=<c:out value='${rentOrder.id}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="${pageContext.request.contextPath}/rentOrder/delete?id=<c:out value='${rentOrder.id}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="${pageContext.request.contextPath}/rentOrder/new" role="button" class="btn btn-info btn-lg">Add Rent Order</a>
                </div>
            </section>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>