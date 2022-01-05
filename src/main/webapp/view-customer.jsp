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
    <title>Express Delivery - View Customer</title>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<%@ include file="utils/success_alert.jsp" %>
<%@ include file="utils/error_alert.jsp" %>
<div>
    <c:if test="${customer.isBanned == false}">
        <form method="post" action="/toggle-blacklist">
            <input type="hidden" name="email" value="${customer.email}">
            <div style="float: right; margin-right: 20px">
                <button type="submit" class="btn btn-danger" style="font-size: 20px; ">Blacklist Customer</button>
            </div>
        </form>
    </c:if>
    <c:if test="${customer.isBanned == true}">
        <form method="post" action="/toggle-blacklist">
            <input type="hidden" name="email" value="${customer.email}">
            <div style="float: right; margin-right: 20px">
                <button type="submit" class="btn btn-danger" style="font-size: 20px; ">Remove Blacklist</button>
            </div>
        </form>
    </c:if>
    <div class="center-header">
        <h3 style="float: left">Customer Details for <c:out value="${customer.firstName} ${customer.lastName}"/></h3>
        <div>
            <c:if test="${customer.isBanned == true}">
                <span style="float:left; margin-left: 20px; margin-top: 3px; font-size: 20px; background-color: black"
                      class="badge badge-pill badge-success">Status : Blacklisted</span>
            </c:if>
        </div>
        <br/>
    </div>
    <div class="center-header">
        <h4>Email : ${customer.email}</h4>
    </div>
    <div>
        <div class="left" style="margin-right: -20px">
            <div class="receiverInfo">

                <div style="display: flex;">
                    <p style="font-weight: bold">Email : </p>
                    <p style="margin-left: 5px"><c:out value="${customer.email}"/></p>
                </div>
                <div style="display: flex;">
                    <p style="font-weight: bold">City : </p>
                    <p style="margin-left: 5px"><c:out value="${customer.location}"/></p>
                </div>
            </div>
        </div>
    </div>
    <div class="center-box" style="margin-left: 20px">
        <h4 style="float: left">Packages</h4>
        <button style="float: right" type="button" class="btn btn-primary">View All</button>
        <div>
            <div style="margin-top: 50px">
                <c:forEach var="mail" items="${customer.mailList}">

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
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
