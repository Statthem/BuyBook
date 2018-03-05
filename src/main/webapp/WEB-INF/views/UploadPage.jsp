<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!Doctype>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Book</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">

	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#idDateField").datepicker();
	});
</script>
<style type="text/css">
body{
background-image: url("${contextPath}/resources/images/paper_texture.jpg");
}

table{
margin-left:35%;


}

 td{ padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: white;
    color: black;
     padding: 8px;
}
</style>
</head>
<body background="${contextPath}/resources/images/paper_texture.jpg">


	<Center><h1>Upload Book</h1></Center>
	<br>
	<br>
	
			<c:if test="${not empty errorsList}">
			<Center>
				<c:forEach items="${errorsList}" var="error">
					<p style="color: black; font-size: 18px; ">${error}</p>
				</c:forEach>
				</Center>
			</c:if>
			
			<c:if test="${not empty genreList}">
			<c:set var="genres" value="${genreList}" scope="session"/>
			</c:if>
	
	<br>

	<form:form method="POST" action="/BuyBook/uploadBook"
		enctype="multipart/form-data" modelAttribute="Book">

		<table>
			<tr>
				<td>Book cover</td>
				<td><input type="file" name="file"></td>
			</tr>

			<tr>
				<td>Book itself*</td>
				<td><input type="file" name="file"></td>
			</tr>

			<tr>
				<td>Name:*</td>
				<td><form:input path="bookName"/></td>
			</tr>

			<tr>
				<td>Description:*</td>
				<td><form:textarea path="bookDescription"></form:textarea></td>

			</tr>

			<tr>
				<td>Author:*</td>
				<td><form:input path="bookAuthor" /></td>
			</tr>

			<tr>
				<td>Release Date*</td>
				<td><input type="text" name="releaseDate" id="idDateField" /></td>
			</tr>

			<tr>
				<td><form:label path="bookGenre">Genre:*</form:label></td>

				<td><form:select path="bookGenre">
						<form:option value="NONE" label="---Select---"></form:option>
						<form:options items="${genres}" />
					</form:select></td>
			</tr>

			<tr>
				<td><input type="submit" value="Upload"> Press here to
					upload your book
				<td>
			</tr>
		</table>
	</form:form>

</body>


</html>