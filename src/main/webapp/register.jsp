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
            <h3 class="signin-text mb-3" style="margin-top: -130px">Sign Up</h3>
            <form method="POST" action="/register" style="margin-top: 20px">
                <div class="form-group">
                    <label for="email">Enter Email Address</label>
                    <input type="email" name="username" id="email" class="form-control" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <label for="firstName">Enter First Name</label>
                    <input type="text" name="firstName" id="firstName" class="form-control" placeholder="First Name" required>
                </div>
                <div class="form-group">
                    <label for="lastName">Enter Last Name</label>
                    <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last Name" required>
                </div>
                <div class="form-group">
                    <label for="city">Select City</label>
                    <select name="city" id="city" class="custom-select" aria-label="Default select example" required>
                        <option value="Colombo">Colombo</option>
                        <option value="Negombo">Negombo</option>
                        <option value="Galle">Galle</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Enter Phone Number</label>
                    <input type="text" name="phoneNumber" id="phoneNumber" class="form-control" placeholder="Phone Number" required>
                </div>
                <div class="form-group">
                    <label for="password">Enter Password</label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
                </div>
                <div class="form-group">
                    <label for="password2">Re-Enter Password</label>
                    <input type="password" name="password2" id="password2" class="form-control" placeholder="Re-Enter Password" required>
                </div>
                <p class="errors" style="margin-top: 10px; color: red">${error}</p>
                <button type="submit" class="btn btn-class">Register</button><br/><br/>
                <a href="/login" style="text-decoration: none; color: #febc17">Login</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>