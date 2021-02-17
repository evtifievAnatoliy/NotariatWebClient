<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Notariat Login Form</title>
<meta charset="UTF-8" />
<link href="<c:url value = "/resources/css/loginForm/reset.css"/>" rel="stylesheet">
<link href="<c:url value = "/resources/css/loginForm/structure.css"/>" rel="stylesheet">
</head>

<body>
<form:form metod="POST" commandName="user" action="check-user" class="box login">
	
	<fieldset class="boxBody">
	
	  <form:label path="name">Name:</form:label>
	  <form:input path="name" />
	  <form:label path="password">Password:</form:label>
	  <form:password path="password" />
	</fieldset>
	
	<footer>
	  <input type="submit" class="btnLogin" value="Login" tabindex="4">
	</footer>
</form:form>
</body>
</html>
