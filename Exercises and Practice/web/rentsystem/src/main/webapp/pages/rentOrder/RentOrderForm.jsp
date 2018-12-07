<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
                <c:if test="${isEdit}">
                    Edit Rent Order
                </c:if>
                <c:if test="${isNew}">
                    Add New Rent Order
                </c:if>
         </title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file = "/pages/common/menu.jsp" %>
        <div class="container">

            <div class="title-container">
                <h1>
                    <c:if test="${isEdit}">
                        Edit Rent Order
                    </c:if>
                    <c:if test="${isNew}">
                        Add New Rent Order
                    </c:if>
                </h1>
            </div>

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

             <c:if test="${isEdit}">
                <form action="${pageContext.request.contextPath}/rentOrder/update" method="post" role="form">
            </c:if>
            <c:if test="${isNew}">
                <form action="${pageContext.request.contextPath}/rentOrder/insert" method="post" role="form">
            </c:if>

            <c:if test="${isEdit}">
                <input type="hidden" name="id" value="<c:out value='${rentOrder.id}' />" />
            </c:if>

                <div class="form-group">
                    <label for="rentDate">
                        Rent Date: <input class="form-control" type="text" id="rentDate" name="rentDate" value="<c:out value="${dateFormatter.format(rentOrder.rentDate)}" />" /> (MM-dd-yyyy HH:mm)
                    </label>
                </div>
                <div class="form-group">
                    <label for="expectedDeliveryDate">
                        Expected Delivery Date: <input class="form-control" type="text" id="expectedDeliveryDate" name="expectedDeliveryDate" value="<c:out value="${dateFormatter.format(rentOrder.expectedDeliveryDate)}" />" /> (MM-dd-yyyy HH:mm)
                    </label>
                </div>

                <div class="form-group">
                    <label for="price">
                        Price: <input class="form-control" type="number" id="price" name="price" value="<c:out value='${rentOrder.price}' />" />
                    </label>
                </div>

                <div class="form-group">
                    <label for="freeUpgrade">
                        Free Upgrade: <select name='freeUpgrade' class="form-control">
                                <c:if test="${rentOrder.freeUpgrade == false}">
                                    <option value="true">Yes</option>
                                    <option value="${rentOrder.freeUpgrade}" selected>No</option>
                                </c:if>
                                <c:if test="${rentOrder.freeUpgrade == true}">
                                    <option value="${rentOrder.freeUpgrade}" selected>Yes</option>
                                    <option value="false">No</option>
                                </c:if>
                            </select>
                    </label>
                </div>

                <div class="form-group">
                    <label for="observations">
                        Observations: <input class="form-control" type="textarea" id="observations" name="observations" value="<c:out value='${rentOrder.observations}' />" />
                    </label>
                </div>

                <div class="form-group">
                    <label for="car">
                        Car: <select name='car' class="form-control">
                                <option value="${rentOrder.car.id}" selected>${rentOrder.car.model} - ${rentOrder.car.plate}</option>
                                <c:forEach items="${cars}" var="car">
                                    <c:if test="${car.id != selected && car != rentOrder.car.id}">
                                        <option value="${car.id}">${car.model} - ${car.plate}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                    </label>
                </div>

                <div class="form-group">
                    <label for="user">
                        User: <select name='user' class="form-control">
                                <option value="${rentOrder.user.id}" selected>${rentOrder.user.email}</option>
                                <c:forEach items="${users}" var="user">
                                    <c:if test="${user.id != selected && user.id != rentOrder.user.id}">
                                        <option value="${user.id}">${user.email}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                    </label>
                </div>

                <input type="submit" value="Save" class="btn btn-secondary"/>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>