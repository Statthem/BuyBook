<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%>

<html>
<head>
<title>My favorite books</title>

<link rel="stylesheet" type="text/css"
	href="/BuyBook/resources/css/MainPage.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style type="text/css">
.image {
	max-width: 300px;
	max-height: 400px;
}
</style>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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

	<br>
	<div style="margin-left: 40%">
	<h2>Welcome - ${currentUser.userName}</h2>
		<br>
		<h3>Favorite books:</h3>
		<br>
	</div>
	<div style="margin-left: 22%">
		

		<!-- Show Books -->
		<div class="tiles row">

			<c:forEach items="${currentUser.favoriteBooks}" var="book">

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
</body>
</html>