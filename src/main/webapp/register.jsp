<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <form:form method="POST" action="/register" modelAttribute="user" style="margin-top: 20px">
                <form:input type="hidden" name="userRole" id="userRole" class="form-control"
                            path="userRole" value="customer" required="required"/>
                <div class="form-group">
                    <form:label path="email">Enter Email Address</form:label>
                    <form:input type="text" name="email" id="email" class="form-control" placeholder="Email"
                                path="email"/>
                    <form:errors cssStyle="color: red" path="email"/>
                </div>
                <div style="color: red">${emailError}</div>
                <div class="form-group">
                    <form:label for="firstName" path="firstName">Enter First Name</form:label>
                    <form:input type="text" name="firstName" id="firstName" class="form-control"
                                placeholder="First Name" path="firstName"/>
                    <form:errors cssStyle="color: red" path="firstName"/>
                </div>
                <div class="form-group">
                    <form:label for="lastName" path="lastName">Enter Last Name</form:label>
                    <form:input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last Name"
                                path="lastName"/>
                    <form:errors cssStyle="color: red" path="lastName"/>
                </div>
                <div class="form-group">
                    <form:label for="city" path="location">Select City</form:label>
                    <form:select name="location" id="city" class="custom-select" aria-label="Default select example"
                                 path="location">
                        <option value="Colombo">Colombo</option>
                        <option value="Negombo">Negombo</option>
                        <option value="Galle">Galle</option>
                    </form:select>
                    <form:errors cssStyle="color: red" path="location"/>
                </div>
                <div class="form-group">
                    <form:label for="phoneNumber" path="phoneNumber">Enter Phone Number</form:label>
                    <form:input type="text" name="phoneNumber" id="phoneNumber" class="form-control"
                                placeholder="Phone Number" path="phoneNumber"/>
                    <form:errors cssStyle="color: red" path="phoneNumber"/>
                </div>
                <div class="form-group">
                    <form:label for="password" path="password">Enter Password</form:label>
                    <form:input type="password" name="password" id="password" class="form-control"
                                placeholder="Password" path="password"/>
                    <form:errors cssStyle="color: red" path="password"/>
                </div>
                <div class="form-group">
                    <form:label for="matchingPassword" path="matchingPassword">Re-Enter Password</form:label>
                    <form:input type="password" name="matchingPassword" id="matchingPassword" class="form-control"
                                placeholder="Re-Enter Password" path="matchingPassword"/>
                    <p class="errors" style="margin-top: 10px; color: red">${passwordError}</p>
                </div>

                <button type="submit" class="btn btn-class">Register</button>
                <br/><br/>
                <a href="/login" style="text-decoration: none; color: #febc17">Login</a>
            </form:form>

            <div>
            </div>
        </div>
    </div>
</div>
</body>
</html>