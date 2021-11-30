<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Express Delivery - Admin Home</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/admin-homepage.css">
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
        <h3 style="padding: 20px 30px 20px 30px">Welcome Back, ${name}</h3>
    </div>
    <div>
        <h4 style="padding: 20px 30px 20px 30px">Admin Dashboard</h4>
    </div>
    <div class="package-stats">
        <div>
            <h4 style="float: left">New Shipments</h4>
            <button style="float: right" type="button" class="btn btn-primary">View All <i style="margin-left: 10px" class="fas fa-expand"></i></button>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> mail = new ArrayList<>();
                try {
                    mail = (List<MailDto>) request.getAttribute("new_shipments");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (mail != null && mail.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No New Shipments
            </div>
            <%
            } else {
            %>
            <c:forEach var="mail" items="${new_shipments}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <h6 style="float: left" class="card-title">${mail.getDescription()}</h6>
                        <button style="float: right" type="button" class="btn btn-info"><i class="fas fa-expand-alt"></i></button>
                        <h6 style="float: right; margin-right: 35px; font-weight: normal">${mail.getTotalCost()}</h6>
                        <h6 style="float: right; margin-right: 35px; font-weight: normal">#${mail.getMailId()}</h6>
                        <h6 style="float: right; margin-right: 35px; font-weight: normal">${mail.user.getLocation()}</h6>
                    </div>
                </div>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <div class="package-stats">
        <div>
            <h4 style="float: left">Driver Details</h4>
            <button style="float: right" type="button" class="btn btn-primary">View All</button>
        </div>
    </div>
</body>
</html>