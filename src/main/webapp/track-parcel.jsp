<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<c:set var="now" value="<% = new java.util.Date()%>"/>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<div>
    <div class="center-header">
        <h2>Delivery Details for Parcel ID #000<c:out value="${tracking.mail.mailId}"/></h2>
    </div>
    <div class="center-header">
        <h4>Order Placed on <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                            value="${tracking.mail.createdAt}"/></h4>
    </div>
    <div class="center-header">
        <h4>Tracking ID #000<c:out value="${tracking.mail.mailTracking.trackingId}"/></h4>
    </div>
    <div class="left">
        <div class="receiverInfo">
            <div style="display: flex;">
                <p style="font-weight: bold">Receiver : </p>
                <p style="margin-left: 5px"><c:out value="${tracking.mail.receiverEmail}"/></p>
            </div>
            <p style="font-weight: bold"><c:out value="${tracking.mail.receiverPhoneNumber}"/></p>
            <p><c:out value="${tracking.mail.receiverAddress}"/></p>
        </div>
        <div class="driverInfo">
            <%--Delivery Partner Section--%>
            <c:if test="${tracking.deliveryPartner == 'NULL'}">
            </c:if>
            <c:if test="${tracking.deliveryPartner != 'NULL'}">
                <div style="display: flex">
                    <p style="font-weight: bold; float: left">Delivery Partner : </p>
                    <p style="float:left; margin-left: 10px"><c:out value="${tracking.deliveryPartner}"/></p>
                </div>
            </c:if>
            <c:if test="${tracking.driver == 'NULL'}">
            </c:if>
            <c:if test="${tracking.driver != 'NULL'}">
                <div style="display: flex">
                    <p style="font-weight: bold; float: left">Driver : </p>
                    <p style="margin-left: 10px"><c:out value="${tracking.driver}"/></p>
                </div>

            </c:if>
        </div>
    </div>
    <div>
        <div class="center-box">
            <div class="mailStatus">
                <div style="float: left; margin-left: 30px">
                    Package Status :
                </div>
                <div style="float:left; font-weight: bold; margin-left: 10px">
                    <c:out value="${tracking.mail.status}"/>
                </div>
            </div>
            <br/><br/>
            <c:if test="${tracking.status6 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status6 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status2Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status6}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status5 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status5 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status2Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status5}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status4 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status4 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status2Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status4}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status3 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status3 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status3Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status3}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status2 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status2 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status2Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status2}"/></div>
                </div>
                <br/>
            </c:if>
            <div class="status1">
                <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate type="both"
                                                                                                             dateStyle="medium"
                                                                                                             timeStyle="medium"
                                                                                                             value="${tracking.status1Date}"/></div>
                <div style="float: left; margin-left: 20px"><c:out value="${tracking.status1}"/></div>
            </div>
        </div>
    </div>

</div>
</body>