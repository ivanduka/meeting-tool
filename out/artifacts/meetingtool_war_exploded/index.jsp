<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ca.ivanduka.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Meeting Tool</title>
</head>
<body>
<%
    User user1 = new User("Ivan", "Duka", "ivan.duka.pm@gmail.com", String.valueOf(Math.random()));
    out.println(user1.toString());
    System.out.println(user1);
%>

<c:set var="stuff" value="<%= new java.util.Date() %>"/>

<p>Time on the server is ${stuff}</p>
</body>
</html>
