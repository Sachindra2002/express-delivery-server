<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Express Delivery - Admin Home</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
</head>
<body>
<div>
    <!--Navigation Bar-->
    <jsp:include page="utils/navbar.jsp">
        <jsp:param name="page" value="home"/>
    </jsp:include>
    <div>
        <h3 style="padding: 20px 30px 20px 30px">Welcome Back</h3>
    </div>
</div>
</body>
</html>