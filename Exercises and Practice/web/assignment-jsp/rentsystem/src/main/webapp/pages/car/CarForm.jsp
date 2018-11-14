<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cars Store Application</title>
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
    <c:if test="${message != null}">
        <center>
            <div>
                <span><c:out value='${message}' /></span>
            <div>
        </center>
    </c:if>
    <div align="center">
        <c:if test="${isEdit}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${isNew}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${isEdit}">
                        Edit Car
                    </c:if>
                    <c:if test="${isNew}">
                        Add New Car
                    </c:if>
                </h2>
            </caption>
                <c:if test="${isEdit}">
                    <input type="hidden" name="id" value="<c:out value='${car.id}' />" />
                </c:if>
            <tr>
                <th>Plate: </th>
                <td>
                    <input type="text" name="plate" size="45"
                            value="<c:out value='${car.plate}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Color: </th>
                <td>
                    <input type="text" name="color" size="45"
                            value="<c:out value='${car.color}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Year: </th>
                <td>
                    <input type="text" name="year" size="4"
                            value="<c:out value='${car.year}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Brand: </th>
                <td>
                    <input type="text" name="brand" size="45"
                            value="<c:out value='${car.brand}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Model: </th>
                <td>
                    <input type="text" name="model" size="45"
                            value="<c:out value='${car.model}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Type: </th>
                <td>
                    <select name='type'>
                        <option value="${car.type}" selected>${car.type}</option>
                        <c:forEach items="${types}" var="type">
                            <c:if test="${type != selected}">
                                <option value="${type}">${type}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>
</body>
</html>