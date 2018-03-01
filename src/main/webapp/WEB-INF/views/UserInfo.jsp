<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
 
<html>
<head>
<title>User Info</title>
</head>
<body>
    
     
    <h2>User Info Page</h2>
 
    <h3>Welcome : ${currentUser.userName}</h3>
    <br>
    <br>
    <img src="${contextPath}/resources/images/${book.imageId}"/>
 <br>
 <br>
    <b>This is protected page!</b>  
</body>
</html>