<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>  
<head>  
<title>Profile Page </title>  
</head>  
<body>  
<c:url value="/logout" var="logoutUrl" />  
 <center>  
<h2>Profile Page | You are now logged in</h2>  
  <h3><a href="${logoutUrl}">Logout</a></h3>  
  <form action="${logoutUrl}" method="post">
  <input type="submit"/> Logout.</a>
  </form>
 </center>  
</body>  
</html>