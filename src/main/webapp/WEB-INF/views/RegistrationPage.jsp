<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!Doctype>
<html>
<head>
<title>registration</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<!--===============================================================================================-->
<link rel="icon" type="image/png"
	href="${contextPath}/resources/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/util.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/main.css">
<!--===============================================================================================-->

<meta charset="UTF-8">

</head>
<body>





	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-90 p-b-30">
			
				<form:form name='f' method="POST"
					action="/BuyBook/user/registration" modelAttribute="user"
					class="login100-form validate-form">
					<span class="login100-form-title p-b-40"> Sign Up </span>

					<div>
						<a href="#" class="btn-login-with bg1 m-b-10"> <i
							class="fa fa-facebook-official"></i> Login with Facebook
						</a> <a href="#" class="btn-login-with bg2"> <i
							class="fa fa-twitter"></i> Login with Twitter
						</a>
					</div>
					<br>
					
			<c:if test="${not empty errorsList}">
			<br>
					<c:forEach items="${errorsList}" var="error">
					
						<p style="color: red;">${error}</p>
					</c:forEach>
				</c:if>

					<div class="text-center p-t-55 p-b-30">
						<span class="txt1"> Registration </span>
					</div>

					<div class="wrap-input100 validate-input m-b-16"
						data-validate="Please enter user name">
						<form:input path="userName" class="input100" type="text" name='username'
							placeholder="User name"></form:input> <span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-20"
						data-validate="Please enter password">
						<span class="btn-show-pass"> <i class="fa fa fa-eye"></i>
						</span> <form:input path="userPassword" class="input100" type="password" name='password'
							placeholder="Password"></form:input> <span class="focus-input100"></span>
					</div>
					
					<div class="wrap-input100 validate-input m-b-20"
						data-validate="Please confirm password">
						<span class="btn-show-pass"> <i class="fa fa fa-eye"></i>
						</span> <form:input path="matchingPassword" class="input100" type="password" name='password'
							placeholder="Confirm password"></form:input> <span class="focus-input100"></span>
					</div>

					
					<div class="wrap-input100 validate-input m-b-16"
						data-validate="Please enter your email">
						<form:input path="userEmail" class="input100" type="text" name='email'
							placeholder="Email"></form:input> <span class="focus-input100"></span>
					</div>
					
					
						<div class="container-login100-form-btn">
						<input class="login100-form-btn" name="submit" type="submit"
							value="Sign me up" />
					</div>


				</form:form>
			</div>
		</div>
	</div>


	<!--===============================================================================================-->
	<script
		src="${contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${contextPath}/resources/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="${contextPath}/resources/vendor/bootstrap/js/popper.js"></script>
	<script
		src="${contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="${contextPath}/resources/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${contextPath}/resources/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="${contextPath}/resources/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script
		src="${contextPath}/resources/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="${contextPath}/resources/js/main.js"></script>

</body>
</html>
	
	
	

</body>


</html>