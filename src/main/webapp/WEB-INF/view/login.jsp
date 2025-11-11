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
<script>
    function validateForm() {
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value.trim();
        const userError = document.getElementById('userError');
        const passError = document.getElementById('passError');
        userError.textContent = '';
        passError.textContent = '';

        let isValid = true;

        if (username.length < 3) {
            userError.textContent = 'Username must be at least 3 characters long';
            isValid = false;
        }

        if (password.length < 8) {
            passError.textContent = 'Password must be at least 8 characters long';
            isValid = false;
        }

        return isValid;
    }
</script>
</head>
<body>
	<div class="main">
    <p class="sign" align="center">Log in</p>
    <form class="form1" method="post" action="validate" onsubmit="return validateForm()">
      <input class="username" type="text" placeholder="Username" id="username" name="username">
      <div id="userError" class="error"></div>
      <input class="pass" type="password" placeholder="Password" id="password" name="password">
      <div id="passError" class="error"></div>
      <input type="submit" class="submit"/>
      <div class="error">${error}</div>
      <!-- <p class="forgot" align="center"><a href="#">Forgot Password?</p> -->
      </form>        
    </div>
</body>
</html>