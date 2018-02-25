<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
 
<html>
<head>
<title>User Info</title>
</head>
<body>
    
     
    <h2>User Info Page</h2>
 
    <h3>Welcome : ${pageContext.request.userPrincipal.name}</h3>
     ${pageContext.request.userPrincipal}
 
    <b>This is protected page!</b>  
</body>
</html>