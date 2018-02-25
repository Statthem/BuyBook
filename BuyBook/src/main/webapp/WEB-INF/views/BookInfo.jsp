<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!Doctype>
<html>
<head>
<meta charset="UTF-8">
<title>Book info</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>


<Center>BookInfo:</Center>
<br>
<br>
<br>
Book Name: ${CurrentBook.bookName}



</body>


</html>