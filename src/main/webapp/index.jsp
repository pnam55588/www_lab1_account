<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1>Account</h1>
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