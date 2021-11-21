<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%@ include file="utils/head_imports.jsp" %>
    <link rel="stylesheet" type="text/css" href="css/index.css"/>
    <title>Express Delivery</title>
</head>
<body class="body-login">
<div class="container">
    <%@ include file="utils/error_alert.jsp" %>
    <%@ include file="utils/success_alert.jsp" %>
    <div class="row content">
        <div class="col-md-6 mb-3">
            <img src="images/logo.png" class="img-fluid" alt="logo"/>
        </div>
        <div class="col-md-6">
            <h3 class="signin-text mb-3">Sign In</h3>
            <form method="POST" action="/login">
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" name="username" id="email" class="form-control" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="Password"
                           required>
                </div>
                <p class="errors" style="margin-top: 10px; color: red">${error}</p>
                <c:if test="${success != null}">
                    <div class="alert alert-success" role="alert">
                            ${success}
                    </div>
                </c:if>
                <button type="submit" class="btn btn-class">Login</button>
                <br/><br/>
                <a href="/register" style="text-decoration: none; color: #febc17">Register Now</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>