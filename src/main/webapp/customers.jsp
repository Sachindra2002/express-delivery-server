<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.UserDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Express Delivery - Manage Agents</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/admin-homepage.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<%@ include file="utils/success_alert.jsp" %>
<%@ include file="utils/error_alert.jsp" %>
<div style="float: left">
    <h3 style="padding: 20px 30px 20px 30px; color: grey">Manage Customers</h3>
</div>
<div style="margin-top: 120px">
    <div>
        <%
            List<UserDto> mail = new ArrayList<>();
            try {
                mail = (List<UserDto>) request.getAttribute("customers");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (mail != null && mail.size() <= 0) {
        %>
        <div style="margin-right: 30px; margin-top: 100px; margin-left: 30px" class="alert alert-secondary"
             role="alert">
            No Agents in the System
        </div>
        <%
        } else {
        %>
        <div class="driver-row" style=" margin-left: 90px">
            <c:forEach var="customer" items="${customers}">
                <div class="card"
                     style="width: 20rem; margin-left: 10px;flex: 0 0 auto; max-width: 100%; margin-right: 10px; border-radius: 10px; height: 15rem">
                    <div class="card-body">
                        <h5 style="float: left"
                            class="card-title">${customer.firstName} ${customer.lastName}</h5>
                        <c:if test="${customer.isBanned == true}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: black"
                                  class="badge badge-pill badge-success">Blacklisted</span><br/><br/>
                        </c:if>
                        <c:if test="${customer.isBanned == false}">
                            <br/><br/>
                        </c:if>
                        <p style="font-weight: bold" class="card-text">Email : <span
                                style="font-weight: normal">${customer.email}</span></p>
                        <p style="font-weight: bold" class="card-text">Mobile : <span
                                style="font-weight: normal">${customer.phoneNumber}</span></p>
                    </div>
                    <div>
                        <form method="get" action="/view-customer">
                            <input type="hidden" name="email" value="${customer.email}">
                            <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">View
                                Customer
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <% } %>
    </div>
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
