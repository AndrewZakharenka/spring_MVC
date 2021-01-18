<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 17.01.2021
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:if test="${empty order.dateOrder}">
        <title>Add</title>
    </c:if>
    <c:if test="${!empty order.dateOrder}">
        <title>Edit</title>
    </c:if>
</head>
<body>
    <c:if test="${empty order.dateOrder}">
        <c:url value="/addOrder" var="var"/>
    </c:if>
    <c:if test="${!empty order.dateOrder}">
        <c:url value="/editOrder" var="var"/>
    </c:if>
    <form action="${var}" method="POST">
        <input type="hidden" name="id" value="${order.id}">
        <label for="dateOrder">Date</label>
        <input type="text" name="dateOrder" id="dateOrder" value="${order.dateOrder}">

        <label for="clientName">Client name</label>
        <input type="hidden" name="client.id" value="${order.client.id}">
        <input type="text" name="client.name" id="clientName" value="${order.client.name}">
        <label for="clientSurname">Client surname</label>
        <input type="text" name="client.surname" id="clientSurname" value="${order.client.surname}">
        <label for="clientPhone">Client phone</label>
        <input type="text" name="client.phone" id="clientPhone" value="${order.client.phone}">

        <input type="hidden" name="product.id" value="${order.product.id}">
        <label for="productName">Product name</label>
        <input type="text" name="product.name" id="productName" value="${order.product.name}">
        <label for="productPrice">Product price</label>
        <input type="text" name="product.price" id="productPrice" value="${order.product.price}">
        <c:if test="${empty order.dateOrder}">
            <input type="submit" value="Add new order">
        </c:if>
        <c:if test="${!empty order.dateOrder}">
            <input type="submit" value="Edit order">
        </c:if>
    </form>

</body>
</html>
