<%@ page import="fit.iuh.models.Account" %>
<%@ page import="fit.iuh.models.Role" %>
<%@ page import="fit.iuh.models.Log" %><%--
  Created by IntelliJ IDEA.
  User: phgss
  Date: 9/13/2023
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <STYLE>
        table {
            width: 300px;
            border-collapse: collapse;
            margin: 20px 0;
        }

        td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        form {
            margin-top: 20px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

    </STYLE>
</head>
<body>
<%
    Account account = (Account) session.getAttribute("account");
    Role role = (Role) session.getAttribute("role");
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
    <tr>
        <td>role:</td>
        <td><%= (role != null) ? role.getName() : "No Role" %></td>
    </tr>
    <br>
</table>
    <form action="ControlServlet" method="POST">
        <input type="submit" name="action" value="logout">
    </form>
</body>
</html>
