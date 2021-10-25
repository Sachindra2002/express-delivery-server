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
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%@ include file="utils/head_imports.jsp" %>
</head>
<body>
    <div><c:out value="${tracking.mail.mailId}"/></div>
    <div><c:out value="${tracking.mail.receiverEmail}"/></div>
    <div><c:out value="${tracking.mail.mailTracking.trackingId}"/></div>
    <div><c:out value="${tracking.status}"/></div>
<%--    <div><c:out value="${tracking.deliveryPartner}"/></div>--%>
    <c:if test="${tracking.driver == 'NULL'}">
        <p>Hello</p>
    </c:if>
</body>