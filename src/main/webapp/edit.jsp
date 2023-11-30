<%--
  Created by IntelliJ IDEA.
  User: phgss
  Date: 11/30/2023
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>edit</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            box-sizing: border-box;
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
    </style>
</head>
<body>
<h1>Edit</h1>
<div class="container">
<form method="POST" action="ControlServlet">
    <label >Full Name:</label>
    <input type="text" name="id" required value="${account.id}"><br>
    <label >Full Name:</label>
    <input type="text" name="fullName" required value="${account.fullName}"><br>
    <label >Password:</label>
    <input type="text" name="password" required value="${account.password}"><br>
    <label >Email:</label>
    <input type="text" name="email" required value="${account.email}"><br>
    <label >Phone:</label>
    <input type="text" name="phone" required value="${account.phone}"><br>
    <select name="role" >
        <option value="admin">admin</option>
        <option value="user">user</option>
    </select>
   <br>
    <input type="submit" name="action" value="Save Account">
</form>

</div>
</body>
</html>
