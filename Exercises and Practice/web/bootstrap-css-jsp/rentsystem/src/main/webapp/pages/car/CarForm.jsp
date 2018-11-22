<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
                <c:if test="${isEdit}">
                    Edit Car
                </c:if>
                <c:if test="${isNew}">
                    Add New Car
                </c:if>
         </title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">

            <div class="title-container">
                <h1>
                    <c:if test="${isEdit}">
                        Edit Car
                    </c:if>
                    <c:if test="${isNew}">
                        Add New Car
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
                <form action="update" method="post" role="form">
            </c:if>
            <c:if test="${isNew}">
                <form action="insert" method="post" role="form">
            </c:if>

            <c:if test="${isEdit}">
                <input type="hidden" name="id" value="<c:out value='${car.id}' />" />
            </c:if>

                <div class="form-group">
                    <label for="plate">
                        Plate: <input class="form-control" type="text" id="plate" name="plate" value="<c:out value='${car.plate}' />" />
                    </label>
                </div>
                <div class="form-group">
                    <label for="color">
                        Color: <input class="form-control" type="text" id="color" name="color" value="<c:out value='${car.color}' />" />
                    </label>
                </div>

                <div class="form-group">
                    <label for="year">
                        Year: <input class="form-control" type="number" id="year" name="year" value="<c:out value='${car.year}' />" />
                    </label>
                </div>

                <div class="form-group">
                    <label for="brand">
                        Brand: <input class="form-control" type="text" id="brand" name="brand" value="<c:out value='${car.brand}' />" />
                    </label>
                </div>

                <div class="form-group">
                    <label for="model">
                        Model: <input class="form-control" type="text" id="model" name="model" value="<c:out value='${car.model}' />" />
                    </label>
                </div>

                <div class="form-group">
                    <label for="model">
                        Type: <select name='type' class="form-control">
                                <option value="${car.type}" selected>${car.type}</option>
                                <c:forEach items="${types}" var="type">
                                    <c:if test="${type != selected}">
                                        <option value="${type}">${type}</option>
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