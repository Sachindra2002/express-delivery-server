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
    <link rel="stylesheet" href="css/custom-track.css">
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
        <h2>Delivery Details for Parcel ID #<c:out value="${tracking.mail.mailId}"/></h2>
    </div>
    <div class="center-header">
        <h4>Order Placed on <fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"
                                            value="${tracking.mail.createdAt}"/></h4>
    </div>
    <div class="center-header">
        <h4>Tracking ID #<c:out value="${tracking.mail.mailTracking.trackingId}"/></h4>
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

    </div>
    <div>
        <div class="center-box-track">

            <div class="custom-status">
                <div style="border-radius: 10px" class="card card-timeline px-2 border-none">
                    <ul class="bs4-order-tracking">
                        <c:if test="${tracking.mail.status == 'Processing'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step ">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'Accepted'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step ">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'Driver Accepted'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step ">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'Assigned'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step ">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'pickup'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step ">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'Package picked up'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step ">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'transit'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step ">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'out for delivery'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step ">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'Delivered'}">
                            <li class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-check-double"></i></div>
                                Order Accepted
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-box"></i></div>
                                Pick up package
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-warehouse"></i></div>
                                In Transit
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-truck"></i></div>
                                Out for delivery
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-house-user"></i></div>
                                Delivered
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'Rejected'}">
                            <li style="margin-left: 25%" class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-times-circle"></i></div>
                            </li>
                            <li style="color: red" class="step active">
                                <div style="color: white; background-color: red"><i class="fas fa-times-circle"></i>
                                </div>
                                Order Rejected
                            </li>
                        </c:if>
                        <c:if test="${tracking.mail.status == 'Cancelled'}">
                            <li style="margin-left: 25%" class="step active">
                                <div><i class="fas fa-check-circle"></i></div>
                                Order Placed
                            </li>
                            <li class="step active">
                                <div><i class="fas fa-times-circle"></i></div>
                            </li>
                            <li style="color: red" class="step active">
                                <div style="color: white; background-color: red"><i class="fas fa-times-circle"></i>
                                </div>
                                Order Cancelled
                            </li>
                        </c:if>
                    </ul>
                </div>

            </div>
            <br/>
            <c:if test="${tracking.status11 == null}">
            </c:if>
            <c:if test="${tracking.status11 != null}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 30px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status11Date}"/></div>
                    <div style="float: left; margin-left: 50px"><c:out value="${tracking.status11}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status10 == null}">
            </c:if>
            <c:if test="${tracking.status10 != null}">
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
            <c:if test="${tracking.status9 == null}">
            </c:if>
            <c:if test="${tracking.status9 != null}">
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
            <c:if test="${tracking.status8 == null}">
            </c:if>
            <c:if test="${tracking.status8 != null}">
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
            <c:if test="${tracking.status7 == null}">
            </c:if>
            <c:if test="${tracking.status7 != null}">
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
            <c:if test="${tracking.status6 == null}">
            </c:if>
            <c:if test="${tracking.status6 != null}">
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
            <c:if test="${tracking.status5 == null}">
            </c:if>
            <c:if test="${tracking.status5 != null}">
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
            <c:if test="${tracking.status4 == null}">
            </c:if>
            <c:if test="${tracking.status4 != null}">
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
            <c:if test="${tracking.status3 == null}">
            </c:if>
            <c:if test="${tracking.status3 != null}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 350px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status3Date}"/></div>
                    <div style="float: left; margin-left: 80px"><c:out value="${tracking.status3}"/></div>
                </div>
                <br/>
            </c:if>
            <c:if test="${tracking.status2 == null}">
            </c:if>
            <c:if test="${tracking.status2 != null}">
                <div class="status1">
                    <div style="float: left; font-weight: bold; color: black; margin-left: 350px"><fmt:formatDate
                            type="both"
                            dateStyle="medium"
                            timeStyle="medium"
                            value="${tracking.status2Date}"/></div>
                    <div style="float: left; margin-left: 80px"><c:out value="${tracking.status2}"/></div>
                </div>
                <br/>
            </c:if>
            <div class="status1">
                <div style="float: left; font-weight: bold; color: black; margin-left: 350px"><fmt:formatDate
                        type="both"
                        dateStyle="medium"
                        timeStyle="medium"
                        value="${tracking.status1Date}"/></div>
                <div style="float: left; margin-left: 80px"><c:out value="${tracking.status1}"/></div>
            </div>

        </div>
        <c:if test="${tracking.mail.driverDetail !=  null}">
            <div class="driver-track-details">

                <div class="driver-info">
                    <p>Driver Name
                        : ${tracking.mail.driverDetail.user.firstName} ${tracking.mail.driverDetail.user.lastName}</p>
                    <p>Driver Mobile : ${tracking.mail.driverDetail.user.phoneNumber}</p>
                    <p>Driver Vehicle
                        : ${tracking.mail.driverDetail.vehicle.vehicleNumber} ${tracking.mail.driverDetail.vehicle.vehicleType}</p>
                </div>
                <div class="driver-info-2">
                    <p style="font-weight: bold; font-size: 20px">Deliver Partner : ${tracking.deliveryPartner}</p>
                    <p style="font-weight: bold; font-size: 20px">Estimated Date : ${tracking.mail.dropOffDate}</p>
                </div>
            </div>
        </c:if>
        <c:if test="${tracking.mail.driverDetail ==  null}">
        </c:if>
    </div>

</div>
<%@ include file="utils/script_imports.jsp" %>

</body>