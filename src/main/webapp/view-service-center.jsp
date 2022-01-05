<%--
  Created by IntelliJ IDEA.
  User: Sachindra Rodrigo
  Date: 1/2/2022
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<html>
<head>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/admin-homepage.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/track-parcel.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
    <title>Express Delivery - View Center</title>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<%@ include file="utils/success_alert.jsp" %>
<%@ include file="utils/error_alert.jsp" %>
<div>
    <div style="float: right; margin-right: 20px">
        <form method="post" action="/remove-service-center">
            <input type="hidden" name="centerId" value="${center.centreId}">
            <button type="submit" class="btn btn-warning" style="font-size: 20px;" >Remove Center</button>
        </form>

    </div>
    <div class="center-header">
        <h3 style="float: left">Center Details for Center ID #<c:out value="${center.centreId}"/></h3>
        <br/>
    </div>
    <div class="center-header">
        <h4>Center : ${center.center}</h4>
    </div>
    <div>
        <div class="left" style="margin-right: -20px">
            <div class="receiverInfo">

                <div style="display: flex;">
                    <p style="font-weight: bold">Address : </p>
                    <p style="margin-left: 5px"><c:out value="${center.address}"/></p>
                </div>
                <div style="display: flex;">
                    <p style="font-weight: bold">City : </p>
                    <p style="margin-left: 5px"><c:out value="${center.city}"/></p>
                </div>
            </div>
        </div>
    </div>
    <div class="center-box" style="margin-left: 20px">
        <h4 style="float: left">Packages Managed</h4>
        <button style="float: right" type="button" class="btn btn-primary">View All</button>
        <div>
            <div style="margin-top: 50px">
                <c:forEach var="mail" items="${center.mailList}">

                    <div class="card" style="border-radius: 10px; margin-top: 10px">
                        <div class="card-body">
                            <h6 style="float: left" class="card-title">${mail.description}</h6>
                            <button style="float: right" type="button" class="btn btn-info"><i
                                    class="fas fa-expand-alt" data-toggle="modal"
                                    data-target="#openPackageAdminModal${mail.mailId}"></i></button>
                            <h6 style="float: right; margin-right: 35px; font-weight: normal">${mail.totalCost}</h6>
                            <h6 style="float: right; margin-right: 35px; font-weight: normal">
                                #000${mail.mailId}</h6>
                            <h6 style="float: right; margin-right: 35px; font-weight: normal">${mail.user.location}</h6>
                        </div>
                    </div>
                    <%@ include file="modals/view-package-admin.jsp" %>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="center-box" style="margin-left: 20px">
        <h4 style="float: left">Users</h4>
        <button style="float: right" type="button" class="btn btn-primary">View All</button>
        <div>
            <div style="margin-top: 50px">
                <c:forEach var="center" items="${center.users}">
                    <div class="card" style="border-radius: 10px; margin-top: 10px">
                        <div class="card-body">
                            <h6 style="float: left" class="card-title">${center.firstName} ${center.lastName}</h6>
                            <button style="float: right" type="button" class="btn btn-info"><i
                                    class="fas fa-expand-alt"></i></button>
                            <h6 style="float: right; margin-right: 35px; font-weight: normal">${center.userRole}</h6>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
