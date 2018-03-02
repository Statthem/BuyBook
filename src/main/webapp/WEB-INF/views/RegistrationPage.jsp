<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!Doctype>
<html>
<head>
<meta charset="UTF-8">
<title>registration</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

 <link rel="stylesheet" href="${contextPath}/resources/css/style.css">

</head>
<body>




<div
		class="form-group ${requestScope['org.springframework.validation.BindingResult.obj'].hasFieldErrors('text1') ? 'hasErrors' : ''}">
		
		
 <div id="login-box">
  <div class="left">
    <h1>Sign up</h1>
    
    
		<c:if test="${not empty errorsList}">
				<c:forEach items="${errorsList}" var="error">
					<p style="color: red;">${error}</p>
				</c:forEach>
			</c:if>
    
    <form:form method="POST" action="/BuyBook/user/registration"
			modelAttribute="user">
    <form:input path="userName" placeholder="User Name"/>    
    <input type="text" name="userEmail" placeholder="E-mail"/>
    <form:input path="userPassword" placeholder="Password"/>
    <form:input path="matchingPassword" placeholder="Confirm password"/>
    
    <input type="submit" name="signup_submit" value="Sign me up" />
  
    </form:form>
    
  </div>
  
  <div class="right">
    <span class="loginwith">Sign in with<br />social network</span>
    
    <button class="social-signin facebook">Log in with facebook</button>
    <button class="social-signin twitter">Log in with Twitter</button>
    <button class="social-signin google">Log in with Google+</button>
  </div>
  <div class="or">OR</div>
</div>
</div>

<!-- 
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
 -->
 
</body>


</html>