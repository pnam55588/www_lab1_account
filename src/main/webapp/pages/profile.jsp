<%@ page import="fit.iuh.models.Account" %><%--
  Created by IntelliJ IDEA.
  User: phgss
  Date: 9/13/2023
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Account account = (Account) session.getAttribute("account");
%>
<h1>Profile</h1>
<table>
    <tr>
        <td>full name:</td>
        <td><%=account.getFullName()%></td>
    </tr>
    <tr>
        <td>email:</td>
        <td><%=account.getEmail()%></td>
    </tr>
    <tr>
        <td>phone:</td>
        <td><%=account.getPhone()%></td>
    </tr>
</table>
</body>
</html>
