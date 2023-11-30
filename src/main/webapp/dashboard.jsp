
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Dashboard</title>
    <style>
        form {
            width: 300px;
            margin: 20px auto;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        select {
            margin-bottom: 15px;
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        a {
            display: inline-block;
            padding: 6px 12px;
            text-decoration: none;
            background-color: #4CAF50;
            color: #fff;
            border-radius: 4px;
        }

        a:hover {
            background-color: #45a049;
        }

    </style>
</head>
<body>
<h1>Account Dashboard</h1>

<form method="POST" action="ControlServlet">
    <label >Full Name:</label>
    <input type="text" name="fullName" required><br>
    <label >Password:</label>
    <input type="text" name="password" required><br>
    <label >Email:</label>
    <input type="text" name="email" required><br>
    <label >Phone:</label>
    <input type="text" name="phone" required><br>
    <select name="role" >
        <option value="admin">admin</option>
        <option value="user">user</option>
    </select><br>
    <input type="submit" name="action" value="Create Account">
</form>

<h2>Account List</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Full Name</th>
        <th>Password</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <%--@elvariable id="accounts" type="java.util.List"--%>
    <c:forEach var="account" items="${accounts}">
        <tr>
            <td>${account.getId()}</td>
            <td>${account.getFullName()}</td>
            <td>${account.getPassword()}</td>
            <td>${account.getEmail()}</td>
            <td>${account.getPhone()}</td>
            <td>${account.getStatus()}</td>
            <td>
                <a href="ControlServlet?action=edit&id=${account.getId()}">Edit</a>
                <a href="ControlServlet?action=delete&id=${account.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
