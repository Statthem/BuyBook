<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
.image{
    max-width: 300px;
    max-height: 400px;
    }
</style>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
<br>
<br>
	<div style="margin-left: 22%">
	<h2>Welcome - ${currentUser.userName}</h2>
	<br>
		<h3>Favorite books:</h3>
		<br>
			
		<!-- Show Books -->
			<div class="tiles row">

				<c:forEach items="${currentUser.favoriteBooks}" var="book" >

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