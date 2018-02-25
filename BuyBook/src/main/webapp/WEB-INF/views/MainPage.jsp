<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!Doctype>
<html>
<head>
<meta charset="UTF-8">
<title>Main Page</title>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/MainPage.css">

</head>
<body>

	<!-- Set maxResults on page -->
	<c:if test="${empty param.maxResults}">
		<c:set var="maxResults" value="10" />
	</c:if>

	<c:if test="${not empty param.maxResults}">
		<c:set var="maxResults" value="${param.maxResults}" scope="session" />
	</c:if>

	<c:if test="${not empty maxResults}">
		<c:set var="maxResults" value="${maxResults}" scope="session" />
	</c:if>


	<!-- Select for maxResults   -->
	<div id=maxResultsDiv>
		<select id=maxResults onchange="addMaxResultsParam()">
			<option value="${maxResults}">${maxResults}</option>
			<c:if test="${maxResults != 5}">
				<option value="5">5</option>
			</c:if>
			<c:if test="${maxResults != 10}">
				<option value="10">10</option>
			</c:if>
			<c:if test="${maxResults != 20}">
				<option value="20">20</option>
			</c:if>
		</select>
	</div>

	<!-- Select for OrderBy   -->
	<div id=OrderByDiv>
		<select id=OrderBy onchange="OrderBy()">
			<option value="${sessionScope.OrderedBy}">${sessionScope.OrderedBy}</option>
			<c:if test="${sessionScope.OrderedBy != 'OrderByName'}">
				<option value="OrderByName">Order by name</option>
			</c:if>
			<c:if test="${sessionScope.OrderedBy != 'OrderByRelease_date'}">
				<option value="OrderByRelease_date">Order by release date</option>
			</c:if>
			<c:if test="${sessionScope.OrderedBy != 'OrderByRating'}">
				<option value="OrderByRating">Order by rating</option>
			</c:if>
		</select>
	</div>

	<script type="text/javascript">
		function addMaxResultsParam() {
			var x = document.getElementById("maxResults");
			window.location.replace("/BuyBook/MainPage?maxResults=" + x.value);
		}

		function OrderBy() {

			var url = window.location.href;
			var x = document.getElementById("OrderBy");
			var y = document.getElementById("maxResults");

			if (url.indexOf("MainPage/") !== -1) {
				window.location.replace("/BuyBook/MainPage/" + x.value);
			} else {
				window.location.replace("MainPage/" + x.value);
			}

			//	if (url.indexOf("maxResults") !== -1) {

		}
	</script>




	<br> maxResults: ${maxResults}
	<br>

<div id=addBook style="margin-right: 15%;">
<a href="${contextPath}/UploadPage"> add new book </a>
</div>

	<br>


	<!-- Set begin and end for "for each"-->
	<c:if test="${empty param.offset}">
		<c:set var="b" value="${maxResults-maxResults}" />
	</c:if>

	<c:if test="${not empty param.offset}">
		<c:set var="b" value="${param.offset}" />
	</c:if>

	<c:set var="e" value="${b + (maxResults-1)}" />


	<!-- check if sorted -->
	<c:if test="${not empty sessionScope.sortedBooks}">

		<c:set var="allBooks" value="${sessionScope.sortedBooks}" />
	</c:if>


	<!-- Show Books -->
	<c:forEach items="${allBooks}" var="book" begin="${b}" end="${e}">

		<div id=bookField>
			Book: ${book.bookName} <br>


			<!-- Submit hidden form with value of book.id with image onClick(JavaScript)-->
			<form:form method="POST" action="/BuyBook/MainPage/Book"
				id="form-${book.id}">
				<input type="hidden" name="id" value="${book.id}" />
				<img src="${contextPath}/resources/images/${book.imageId}"
					onclick="document.getElementById('form-${book.id}').submit();"
					style="cursor: pointer;">
	</form:form>

			<br> Description: ${book.bookDescription} <br> Release
			date: ${book.releaseDate} <br>
			genre: ${book.bookGenre}
		</div>
	</c:forEach>


	<br> ContextPath = ${contextPath},
	<br>


	<a href="${contextPath}/RegistrationPage">register</a>
	<br>


	<!-- Set current pageNumber (based on param.page)-->
	<c:choose>
		<c:when test="${empty param.page}">
			<c:set var="pageNumber" value="${1}" />
		</c:when>
		<c:otherwise>
			<c:set var="pageNumber" value="${param.page}" />
		</c:otherwise>
	</c:choose>

	<br>

	<!-- Set pagesInTotal (parseNumber for casting result to int)-->
	<c:set var="results" value="${(fn:length(allBooks)/maxResults)+1}" />
	<fmt:parseNumber var="pagesInTotal" integerOnly="true" type="number"
		value="${results}" />
	<br> pagesInTotal: ${pagesInTotal}
	<br>


	<!-- Show pages -->

	<tfoot>
		<tr>
			<th colspan="2" style="text-align: center;"><span
				class="pagination"> <c:forEach begin="1"
						end="${pagesInTotal}" var="val">

						<c:choose>
							<c:when test="${val == pageNumber}">
								<span class="currentStep">${pageNumber}</span>
							</c:when>
							<c:otherwise>

								<!-- Set pages url.params -->
								<c:if test="${empty param.OrderBy}">
									<a
										href="${contextPath}/MainPage?page=${val}&offset=${((0 + maxResults)*val)-maxResults}&maxResults=${maxResults}"
										class="step">${val}</a>

								</c:if>

								<c:if test="${not empty param.OrderBy}">
									<a
										href="${contextPath}/MainPage?page=${val}&offset=${((0 + maxResults)*val)-maxResults}&maxResults=${maxResults}&OrderBy=${param.OrderBy}"
										class="step">${val}</a>

								</c:if>
							</c:otherwise>
						</c:choose>

					</c:forEach>

			</span></th>
		</tr>
	</tfoot>

	<br>
	<br>

<img src="http://localhost:8080/images/witcher.jpg">

	<!--  offset=  "$" + "{((0 + maxResults)*val)-maxResults}" OMG WTF	
	<a href="/?offset=60&amp;max=30" class="step">3</a>
					<a href="/?offset=90&amp;max=30" class="step">4</a><a
					href="/?offset=120&amp;max=30" class="step">5</a> <a
					href="/?offset=150&amp;max=30" class="step">6</a> <a
					href="/?offset=180&amp;max=30" class="step">7</a> <a
					href="/?offset=210&amp;max=30" class="step">8</a> <a
					href="/?offset=240&amp;max=30" class="step">9</a> <a
					href="/?offset=270&amp;max=30" class="step">10</a> <a
					href="/?offset=30&amp;max=30" class="nextLink">→</a> -->


</body>


</html>