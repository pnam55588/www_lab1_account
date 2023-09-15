
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Dashboard</title>
</head>
<body>
<h1>Account Dashboard</h1>

<form method="POST">
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
            <td><${account.getFullName()}</td>
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
