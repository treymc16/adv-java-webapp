<%-- 
    Document   : homepage
    Created on : Apr 21, 2021, 11:05:34 AM
    Author     : Trey
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage</title>
    </head>
    <body>
        <c:if test="${username == null or loggedin == false}" >
            <ul>
                <li><a href="login.html">Login</a></li>
                <li><a href="register.html">Register User</a></li>
            </ul>
        </c:if>
        <c:if test="${loggedin == true}" >
            <p>Welcome <c:out value="${username}"/></p>
            <p><a href="Logout">Logout</a></p>
            <p><a href="changepassword.html">Change Password</a></p>
        </c:if>

    </body>
</html>
