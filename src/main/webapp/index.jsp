
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <style>
    form {
      width: 300px;
      margin: 20px auto;
    }

    label {
      display: block;
      margin-bottom: 5px;
    }

    input {
      width: 100%;
      padding: 8px;
      margin-bottom: 10px;
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
<h1>Login</h1>
<br/>
  <form action="ControlServlet" method="GET">
    <label>email:</label> <br>
    <input type="text" name="email"> <br>
    <label>password: </label><br>
    <input type="text" name="password"> <br>
    <input type="submit" name="action" value="login"><br>
  </form>

</body>
</html>