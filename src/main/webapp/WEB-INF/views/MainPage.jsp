<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!Doctype>
<html>
<head>
<meta charset="UTF-8">
<title>BuyBook</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
	href="${contextPath}/resources/css/MainPage.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="${contextPath}/resources/js/book_catalogue.js"></script>
</head>
<body>
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar">
				<i class="fa fa-bars fa-lg"></i>
			</button>


			<div id="navbar" class="navbar-collapse collapse">
				<form:form action="${contextPath}/book_catalogue/search"
					method="POST" class="navbar-form navbar-right">
					<div class="fast-search input-group">
						<select id="q" placeholder="book title"
							class="form-control head-suggestion selectized" maxlength="50"
							tabindex="-1" style="display: none;"><option value=""
								selected="selected"></option></select>
						<div
							class="selectize-control form-control head-suggestion single plugin-preserve_on_blur">
							<div class="selectize-input items not-full">
								<input type="text" name="bookName" autocomplete="off"
									tabindex="" placeholder="book title" name="q" type="text">
							</div>
						</div>
						<span class="input-group-btn">

							<button type="submit" title="Search" class="btn btn-default">
								<span class="fa fa-search"></span>
							</button>
						</span>
					</div>
				</form:form>



				<ul id="accountMenu" class="nav navbar-nav navbar-right">






					<sec:authorize access="!hasRole('ROLE_USER')">
						<li><a class="strong" href="/BuyBook/registration">Register</a></li>
						<li><a class="strong" href="/BuyBook/login">Login</a></li>
					</sec:authorize>



					<li><a class="strong" href="/BuyBook/userInfo">My favorite</a></li>
					<sec:authorize access="isAuthenticated()">
						<li><a class="strong" href="/BuyBook/logout">Log Out</a></li>
					</sec:authorize>

				</ul>


			</div>

		</div>
		<!--/.nav-collapse -->
	</div>



	<!-- check for pop-ups -->

	<c:if test="${not empty newBook}">
		<script type="text/javascript">
			function codeAddress() {
				alert('new book uploaded successfuly');
			}
			window.onload = codeAddress;
		</script>
		<c:remove var="newBook" />
	</c:if>

	<c:if test="${not empty bookNotFound}">
		<script type="text/javascript">
			function codeAddress() {
				alert('Sorry, book:${bookNotFound} not found');
			}
			window.onload = codeAddress;
			<c:remove var="searchedBook"/>
			<c:remove var="bookNotFound"/>
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

	<br>
	<br>


	<!-- check if sorted -->
	<c:if test="${not empty sessionScope.sortedBooks}">
		<c:set var="allBooks" value="${sessionScope.sortedBooks}" />
	</c:if>
		
	<c:if test="${sessionScope.OrderedBy == 'OrderByName'}">
				<c:set var="SortedBy" value=" A-Z"></c:set>
			</c:if>
			<c:if test="${sessionScope.OrderedBy == 'OrderByRelease_date'}">
				<c:set var="SortedBy" value=" release date"></c:set>
			</c:if>
			<c:if test="${sessionScope.OrderedBy == 'OrderByRating'}">
				<c:set var="SortedBy" value=" rating"></c:set>
			</c:if>
	
	<!-- check if filtered -->
	<c:if test="${not empty filteredBooks}">
		<c:set var="allBooks" value="${filteredBooks}" />
	</c:if>

	<!-- check if searched -->
	<c:if test="${not empty sessionScope.searchedBook}">
		<c:set var="allBooks" value="${searchedBook}" />
		<c:remove var="searchedBook" />
	</c:if>



	<!-- Set begin and end for "for each"-->
	<c:if test="${empty param.offset}">
		<c:set var="b" value="${maxResults-maxResults}" />
	</c:if>

	<c:if test="${not empty param.offset}">
		<c:set var="b" value="${param.offset}" />
	</c:if>

	<c:set var="e" value="${b + (maxResults-1)}" />

	<div class="pageBlock container" id="mangaBox">


		<div class="leftContent">
			<h1>BuyBook catalog</h1>


			<c:if test="${not empty filteredBy}">
				<h2>${filteredBy}</h2>
			</c:if>

			<c:if test="${not empty OrderedBy}">
				<h2>Sorted by ${SortedBy}</h2>
			</c:if>

			<!-- Show Books -->
			<div class="tiles row">

				<c:forEach items="${allBooks}" var="book" begin="${b}" end="${e}">

					<div class="tile col-sm-6 ">
						<div class="img">
							<a href="${contextPath}/book_catalogue/Book/${book.bookName}"
								class="non-hover"><img class="lazy"
								src="${contextPath}/resources/images/${book.imageId}"
								id="book_cover" style="display: inline;" title="cover"
								alt="cover" /> </a>
						</div>

						<div class="tags"></div>

						<div class="desc">
							<div class="star-rate"></div>

							<h3>
								<a href="${contextPath}/book_catalogue/Book/${book.bookName}"
									title="${book.bookName}">${book.bookName}</a>
							</h3>


							<div class="tile-info">
								<div hidepic="true" class="person-link">Author:
									${book.bookAuthor}</div>
								<br> ${book.releaseDate} <br>
								<div class="element-link">${book.bookGenre}</div>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>

				</c:forEach>
			</div>

			<br> <br>


		</div>

		<div class="rightContent">

			<div id=addBook style="margin-right: 15%;">
				<a href="${contextPath}/upload"> add new book </a>
			</div>

			<!-- Select for maxResults   -->
			<div id=maxResultsDiv>
				Max books on page: <select id=maxResults
					onchange="addMaxResultsParam()">
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
			<br>

			<c:if test="${sessionScope.OrderedBy == 'OrderByName'}">
				<c:set var="SortedBy" value=" A-Z"></c:set>
			</c:if>
			<c:if test="${sessionScope.OrderedBy == 'OrderByRelease_date'}">
				<c:set var="SortedBy" value=" release date"></c:set>
			</c:if>
			<c:if test="${sessionScope.OrderedBy == 'OrderByRating'}">
				<c:set var="SortedBy" value=" rating"></c:set>
			</c:if>

			<c:if test="${empty sessionScope.OrderedBy}">
				<c:set var="SortedBy" value="None"></c:set>
			</c:if>

			<!-- Select for OrderBy   -->
			<div id=OrderByDiv>
				Sort by: <select id=OrderBy onchange="OrderBy()">
					<option value="${sessionScope.OrderedBy}">${SortedBy}</option>
					<c:if test="${sessionScope.OrderedBy != 'OrderByName'}">
						<option value="OrderByName">Sort by A-Z</option>
					</c:if>
					<c:if test="${sessionScope.OrderedBy != 'OrderByRelease_date'}">
						<option value="OrderByRelease_date">Sort by release date</option>
					</c:if>
					<c:if test="${sessionScope.OrderedBy != 'OrderByRating'}">
						<option value="OrderByRating">Sort by rating</option>
					</c:if>
				</select>
			</div>

			<br> <br>

			<div class="genres">
				<p>
					FilterBy:
					<button id="checkBox" type="button" onclick="checkFilter()"
						style="margin-left: 45%;">clear filter</button>
				</p>
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

	<!-- Show pages -->
	<center>
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
	</center>
	<br>

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