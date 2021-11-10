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
    <title>Express Delivery - Track Package</title>
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
<div style="margin-left: 20%">
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
            <div style="display: flex;">
                <p style="font-weight: bold">Receiver Mobile : </p>
                <p style="margin-left: 5px"><c:out value="${tracking.mail.receiverPhoneNumber}"/></p>
            </div>
            <p><c:out value="${tracking.mail.receiverAddress}"/></p>
        </div>
        <div class="receiverInfo">
            <div style="display: flex;">
                <p style="font-weight: bold">Sender : </p>
                <p style="margin-left: 5px"><c:out value="${tracking.mail.user.getEmail()}"/></p>
            </div>
            <div style="display: flex;">
                <p style="font-weight: bold">Sender Mobile : </p>
                <p style="margin-left: 5px"><c:out value="${tracking.mail.user.getPhoneNumber()}"/></p>
            </div>
            <p><c:out value="${tracking.mail.pickupAddress}"/></p>
        </div>
        <div class="receiverInfo">
            <div style="display: flex;">
                <p style="font-weight: bold">Parcel Description : </p>
                <p style="margin-left: 5px"><c:out value="${tracking.mail.description}"/></p>
            </div>
            <div style="display: flex;">
                <p style="font-weight: bold">Parcel Weight : </p>
                <p style="margin-left: 5px"><c:out value="${tracking.mail.weight}"/> KG</p>
            </div>
            <div style="display: flex;">
                <p style="font-weight: bold">Parcel Type : </p>
                <p style="margin-left: 5px"><c:out value="${tracking.mail.parcelType}"/></p>
            </div>
            <div style="display: flex;">
                <p style="font-weight: bold">No of Pieces : </p>
                <p style="margin-left: 5px"><c:out value="${tracking.mail.pieces}"/> Piece</p>
            </div>
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
            <div style="display: flex">
                <p style="font-weight: bold; float: left">Driver Mobile : </p>
                <p style="margin-left: 10px">076 251 4255</p>
            </div>
        </div>
    </div>
    <div>
        <div class="center-box">
            <div class="mailStatus">
                <div style="float: left; margin-left: 30px">
                    Package Status :
                </div>
                <div style="float:left; font-weight: bold;">
                    <c:if test="${tracking.mail.status == 'Processing'}">
                            <span style="float: right; font-size: 15px; margin-left: 10px; margin-top: 5px;"
                                  class="badge badge-pill badge-success">${tracking.mail.status}</span><br/><br/>
                    </c:if>
                    <c:if test="${tracking.mail.status == 'Cancelled'}">
                            <span style="float: right; font-size: 15px; margin-left: 10px; margin-top: 5px; background-color: red"
                                  class="badge badge-pill badge-success">${tracking.mail.status}</span><br/><br/>
                    </c:if>
                    <c:if test="${tracking.mail.status == 'Shipped'}">
                            <span style="float: right; font-size: 15px;margin-left: 10px; margin-top: 5px; border-color: orange; color: orange; background-color: white"
                                  class="badge badge-pill badge-success">${tracking.mail.status}</span><br/><br/>
                    </c:if>
                    <c:if test="${tracking.mail.status == 'Delivered'}">
                            <span style="float: right; font-size: 15px; margin-left: 10px; margin-top: 5px; background-color: orange; color: white"
                                  class="badge badge-pill badge-success">${tracking.mail.status}</span><br/><br/>
                    </c:if>
                </div>
            </div>
            <br/><br/>
            <c:if test="${tracking.status11 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status11 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status11Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status11}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status10 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status10 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status10Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status10}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status9 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status9 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status9Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status9}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status8 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status8 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status8Date}"/></div>
                    <div style="float: left; margin-left: 20px"><c:out value="${tracking.status8}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status7 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status7 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status7Date}"/></div>
                    <div style="float: left; margin-left: 30px"><c:out value="${tracking.status7}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status6 == 'NULL'}">
            </c:if>
            <c:if test="${tracking.status6 != 'NULL'}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status6Date}"/></div>
                    <div style="float: left; margin-left: 30px"><c:out value="${tracking.status6}"/></div>
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
                            value="${tracking.status5Date}"/></div>
                    <div style="float: left; margin-left: 30px"><c:out value="${tracking.status5}"/></div>
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
                            value="${tracking.status4Date}"/></div>
                    <div style="float: left; margin-left: 30px"><c:out value="${tracking.status4}"/></div>
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
                    <div style="float: left; margin-left: 30px"><c:out value="${tracking.status3}"/></div>
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
                    <div style="float: left; margin-left: 30px"><c:out value="${tracking.status2}"/></div>
                </div>
                <br/>
            </c:if>
            <div class="status1">
                <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                        type="both"
                        dateStyle="medium"
                        timeStyle="medium"
                        value="${tracking.status1Date}"/></div>
                <div style="float: left; margin-left: 30px"><c:out value="${tracking.status1}"/></div>
            </div>
        </div>
    </div>

</div>
</body>