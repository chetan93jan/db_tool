<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css" />
</head>
<body>
	<div class="main">
    <p class="sign" align="center">Log in</p>
    <form class="form1" method="post">
      <input class="username" type="text" placeholder="Username" id="username" name="username">
      <input class="pass" type="password" placeholder="Password" id="password" name="password">
      <input type="submit" class="submit"/>
      <!-- <p class="forgot" align="center"><a href="#">Forgot Password?</p> -->
      </form>        
    </div>
</body>
</html>