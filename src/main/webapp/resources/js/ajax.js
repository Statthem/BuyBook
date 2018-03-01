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

var url = new URL(window.location.href); 
var id = url.searchParams.get("id");

ajax_get('/BuyBook/getBook/'+id, function(data) {
    document.getElementById("title").innerHTML = data["bookName"];
    document.getElementById("Author").innerHTML = data["bookAuthor"];
 
    var html = "<h2>" + data["bookDescription"] + "</h2>";
    
   // html += "<ul>";
    //   for (var i=0; i < data["articles"].length; i++) {
    //       html += '<li><a href="' + data["articles"][i]["url"] + '">' + data["articles"][i]["title"] + "</a></li>";
    //   }
    //html += "</ul>";
    
    document.getElementById("text").innerHTML = html;
});