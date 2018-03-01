function addMaxResultsParam() {
	var x = document.getElementById("maxResults");
	window.location.replace("/BuyBook/book_catalogue?maxResults=" + x.value);
}

function OrderBy() {

	var url = window.location.href;
	var x = document.getElementById("OrderBy");
	var y = document.getElementById("maxResults");

	if (url.indexOf("book_catalogue/") !== -1) {
		window.location.replace("/BuyBook/book_catalogue/" + x.value);
	} else {
		window.location.replace("book_catalogue/" + x.value);
	}
}
function checkFilter() {
	//if (document.getElementById("checkBox").checked == true) {
		window.location
				.replace("/BuyBook/book_catalogue/filter/filterByNone");
		//document.getElementById("checkBox").checked = false;
//	}
}