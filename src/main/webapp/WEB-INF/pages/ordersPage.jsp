<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 17.01.2021
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Просмотр заказов</title>
</head>
<body>
<h2>Orders</h2>
<table>
    <tr>
        <th>id</th>
        <th>Date</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.dateOrder}</td>

            <td>${order.client.name}</td>
            <td>${order.client.surname}</td>
            <td>${order.client.phone}</td>

            <td>${order.product.name}</td>
            <td>${order.product.price}</td>
            <td>
                <a href="/editOrder/${order.id}">edit</a>
                <a href="/delete/${order.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add</h2>
<c:url value="/addOrder" var="add"/>
<a href="${add}">Add new order</a>

<a href="/editOrder">Редактировать заказы</a>
</body>
</html>
