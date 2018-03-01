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

 <script src="${contextPath}/resources/js/book_catalogue.js"></script> 
</head>
<body>

<c:if test="${not empty newBook}">
		<script type="text/javascript">
	        function codeAddress() {
	            alert('new book uploaded successfuly');
	        } window.onload = codeAddress;
	       
		</script>
	</c:if>

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

	<br> maxResults: ${maxResults}
	<br>

	<div id=addBook style="margin-right: 15%;">
		<a href="${contextPath}/upload"> add new book </a>
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
	<div class="bookBox">
		<div class="leftContent">

			<!-- check if filtered -->
			<c:if test="${not empty filteredBooks}">
				<c:set var="allBooks" value="${filteredBooks}" />
			</c:if>

			<!-- Show Books -->
			<c:forEach items="${allBooks}" var="book" begin="${b}" end="${e}">

				<div id=bookField>
					Book: ${book.bookName} <br> <a
						href="${contextPath}/book_catalogue/Book/${book.id}"><img
						src="${contextPath}/resources/images/${book.imageId}"
						id="book_cover" /></a> <br> Description: ${book.bookDescription}
					<br> Release date: ${book.releaseDate} <br> genre:
					${book.bookGenre}
					<a href="${contextPath}/resources/html/bookInfo.html">test ajax</a>
				</div>
			</c:forEach>

		</div>

		<br> <br>

		<div class="rightContent">
			<div class="genres">
			<p>	FilterBy:             <button id="checkBox" type="button" onclick="checkFilter()">clear filter</button> </p>
				<c:forEach items="${genreList}" var="genre">
					<div id=genre>
						<a href="${contextPath}/book_catalogue/filter/filterBy${genre}">${genre}</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

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
										href="${contextPath}/book_catalogue?page=${val}&offset=${((0 + maxResults)*val)-maxResults}&maxResults=${maxResults}"
										class="step">${val}</a>

								</c:if>

								<c:if test="${not empty param.OrderBy}">
									<a
										href="${contextPath}/book_catalogue?page=${val}&offset=${((0 + maxResults)*val)-maxResults}&maxResults=${maxResults}&OrderBy=${param.OrderBy}"
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


	<!-- Submit hidden form with value of book.id with image onClick(JavaScript)-->
	<!--	<form:form method="POST" action="/BuyBook/MainPage/Book"
				id="form-${book.id}">
				<input type="hidden" name="id" value="${book.id}" />
				<img src="${contextPath}/resources/images/${book.imageId}" id="book_cover"
					onclick="document.getElementById('form-${book.id}').submit();"
					style="cursor: pointer;">
	</form:form> -->

</body>


</html>