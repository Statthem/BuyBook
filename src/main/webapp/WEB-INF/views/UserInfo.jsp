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

</head>
<body>
<br>
<br>
	<div style="margin-left: 22%">
	<h2>Welcome - ${currentUser.userName}</h2>
	<br>
		<h3>Favorite books:</h3>
		<br>

		<c:forEach items="${currentUser.favoriteBooks}" var="book">

			<div class="col-sm-6 ">
				<a href="/BuyBook/book_catalogue/Book/${book.bookName}"
					title="${book.bookName}"><h1> ${book.bookName}</h1></a>
					
				<div class="img">
					<a href="/BuyBook/book_catalogue/Book/${book.bookName}"
						class="non-hover"><img class="lazy"
						src="/BuyBook/resources/images/${book.imageId}" id="book_cover"
						style="display: inline;" title="cover" alt="cover" align="top" />
					</a> <br> <br>  <br>
          <br>
				</div>

			</div>
			<div class="clearfix"></div>


		</c:forEach>
	</div>


	<br>
	<br>

</body>
</html>