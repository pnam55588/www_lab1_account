
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Dashboard</title>
</head>
<body>
<h1>Account Dashboard</h1>

<form action="ControlServlet" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <input type="submit" value="Create Account">
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
            <td><c:out value = "${account.getId()}"/></td>
            <td><c:out value = "${account.getId()}"/></td>
            <td><c:out value = "${account.getId()}"/></td>
            <td><c:out value = "${account.getId()}"/></td>
            <td><c:out value = "${account.getId()}"/></td>
            <td><c:out value = "${account.getId()}"/></td>
            <td>
                <a href="ControlServlet?action=edit&id=${account.getId()}">Edit</a>
                <a href="ControlServlet?action=delete&id=${account.getId()}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
