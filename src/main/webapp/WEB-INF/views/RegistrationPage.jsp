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

<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/util.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/main.css">

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

				<br>

				<form:form name='f' method="POST"
					action="/BuyBook/user/registration" modelAttribute="user"
					class="login100-form validate-form">
					
					<div class="wrap-input100 validate-input m-b-20"
						data-validate="Please enter user name">
						<form:input path="userName" class="input100"
							placeholder="User Name" />
						<span class="focus-input100"></span>
					</div>
					<div class="wrap-input100 validate-input m-b-20"
						data-validate="Please enter email">
						<input type="text" name="userEmail" class="input100"
							placeholder="E-mail" /> <span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-20"
						data-validate="Please enter password">
						<form:input path="userPassword" class="input100"
							placeholder="Password" />
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-20"
						data-validate="Please confirm password">
						<form:input path="matchingPassword" class="input100"
							placeholder="Confirm password" />
						<span class="focus-input100"></span>
					</div>
					<div class="container-login100-form-btn">
					<input  class="login100-form-btn" type="submit" name="submit" value="Sign me up" />
                      </div>
				</form:form>

			</div>

			<div class="right">
				<span class="loginwith">Sign in with<br />social network
				</span>

				<button class="social-signin facebook">Log in with facebook</button>
				<button class="social-signin twitter">Log in with Twitter</button>
				<button class="social-signin google">Log in with Google+</button>
			</div>
			<div class="or">OR</div>
		</div>
	</div>



	<script src="${contextPath}/resources/js/main.js"></script>

</body>


</html>