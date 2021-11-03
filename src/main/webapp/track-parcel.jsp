<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Express Delivery - Home</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/track-parcel.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<div>
    <div class="center-header">
        <h2>Delivery Detail for Parcel ID #000<c:out value="${tracking.mail.mailId}"/></h2>
    </div>
    <div class="center-header">
        <h4>Tracking ID #000<c:out value="${tracking.mail.mailTracking.trackingId}"/></h4>
    </div>
    <div class="receiverInfo">
        <div style="display: flex;">
            <p style="font-weight: bold">Receiver : </p>
            <p style="margin-left: 5px"> <c:out value="${tracking.mail.receiverEmail}"/></p>
        </div>
        <p style="font-weight: bold"><c:out value="${tracking.mail.receiverPhoneNumber}"/></p>
        <p><c:out value="${tracking.mail.receiverAddress}"/></p>
    </div>
    <div>

        <div><c:out value="${tracking.status}"/></div>
        <%--Delivery Partner Section--%>
        <c:if test="${tracking.deliveryPartner == 'NULL'}">
            <p></p>
        </c:if>
        <c:if test="${tracking.deliveryPartner != 'NULL'}">
            <div><c:out value="${tracking.driver}"/></div>
        </c:if>
        <%--Driver Section--%>
        <c:if test="${tracking.driver == 'NULL'}">
            <p></p>
        </c:if>
        <c:if test="${tracking.driver != 'NULL'}">
            <div><c:out value="${tracking.driver}"/></div>
        </c:if>
    </div>
</div>
</body>