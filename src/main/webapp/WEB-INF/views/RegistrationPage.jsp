<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!Doctype>
<html>
<head>
<meta charset="UTF-8">
<title>registration</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style rel="stylesheet" type="text/css">
body {
	font-size: 16pt;
}

fieldset {
	font-size: 12px;
	font-weight: bold;
	padding: 10px;
	width: 500px;
}

td {
	font-size: 14px;
}

td.label {
	text-align: right;
	width: 175px;
}

td.form {
	width: 350px;
}

div.submit {
	width: 450px;
	text-align: right;
	padding-top: 15px;
}

span.small {
	font-size: 10px;
}

span.required {
	font-weight: bold;
	font-size: 20px;
	color: #ff0000;
}

input {
	border-style: solid;
	border-color: #000000;
	border-width: 1px;
	background-color: #f2f2f2;
}

.steps {
	width: 500px;
}

td.stepOn, td.stepOff {
	width: 100px;;
	border-style: solid;
	border-width: 1px;
	border-color: #000000;
	padding: 5px;
	font-size: 14px;
}

td.stepOff {
	background-color: #efefef;
}

.proceed {
	text-align: right;
}
</style>

</head>
<body>


	<Center>Registration</Center>
	<br>
	<br>
	<br>
	<div
		class="form-group ${requestScope['org.springframework.validation.BindingResult.obj'].hasFieldErrors('text1') ? 'hasErrors' : ''}">

		<form:form method="POST" action="/BuyBook/user/registration"
			modelAttribute="user">


			<c:if test="${not empty errorsList}">
				<c:forEach items="${errorsList}" var="error">
					<p style="color: red;">${error}</p>
				</c:forEach>
			</c:if>


			<table id="userRegistrationTable" cellpadding="10">



				<tr>
					<td><form:label path="userName">Name:</form:label></td>
					<td><form:input path="userName" /></td>
				</tr>
				<tr>
					<td><form:label path="userPassword">password:</form:label></td>
					<td><form:input path="userPassword" /></td>
				</tr>
				<tr>
					<td><form:label path="matchingPassword">Confirm password</form:label></td>
					<td><form:input path="matchingPassword" /></td>
				</tr>
				<tr>
					<td>email</td>
					<td><input type="email" name="userEmail" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Submit"></td>
				</tr>
			</table>
		</form:form>


	</div>

</body>


</html>