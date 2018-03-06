function ajax_get(url, callback) {
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            console.log('responseText:' + xmlhttp.responseText);
            try {
                var data = JSON.parse(xmlhttp.responseText);
            } catch(err) {
                console.log(err.message + " in " + xmlhttp.responseText);
                return;
            }
            callback(data);
        }
    };
 
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

var url = window.location.href; 
var index = url.indexOf("e/Book/"); 
var bookName = url.substring(index+7,url.length);

var pathToImage="/BuyBook/resources/images/";

ajax_get('/BuyBook/getBook/'+bookName, function(data) {
    document.getElementById("title").innerHTML = data["bookName"];
    document.getElementById("Author").innerHTML = "Author: " + data["bookAuthor"];
    document.getElementById("Genre").innerHTML = "Genre:" + data["bookGenre"];
    document.getElementById("ReleaseDate").innerHTML = "Release Date:" + data["htmlDate"];
    
    
    
    document.getElementById("favourite").href = "/BuyBook/addToFavourite/" + data["id"];
    document.getElementById("download").href = "/BuyBook/downloadBook/" + data["text"];
    
    
    
    document.getElementById("book_cover").src = pathToImage + data["imageId"];
    document.title = data["bookName"];
   
    var html = data["bookDescription"];
    
    document.getElementById("text").innerHTML = html;
});