<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/admin-homepage.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/track-parcel.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
    <title>Express Delivery - View Driver</title>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<div>
    <div style="float: right; margin-right: 20px">
        <button type="button" class="btn btn-warning" style="font-size: 20px; ">Edit Driver Details</button>
    </div>
    <div class="center-header">
        <h3 style="float: left">Driver Details for Driver ID #000<c:out value="${driver.getDriverId()}"/></h3>
        <div>
            <c:if test="${driver.getStatus() == 'Available'}">
                <span style="float:left; margin-left: 20px; margin-top: 3px; font-size: 20px"
                      class="badge badge-pill badge-success">Status : ${driver.getStatus()}</span>
            </c:if>
            <c:if test="${driver.getStatus() == 'Unavailable'}">
                <span style="float:left; margin-left: 20px; margin-top: 3px; font-size: 20px; background-color: red"
                      class="badge badge-pill badge-success">Status : ${driver.getStatus()}</span>
            </c:if>
            <c:if test="${driver.getStatus() == 'Busy'}">
                <span style="float:left; margin-left: 20px; margin-top: 3px; font-size: 20px; background-color: orange"
                      class="badge badge-pill badge-success">Status : ${driver.getStatus()}</span>
            </c:if>
        </div>
        <br/>
    </div>

    <div class="center-header">
        <h4>Name : ${driver.user.firstName} ${driver.user.lastName}</h4>
    </div>
    <div>
        <div class="left" style="margin-right: -20px">
            <div class="receiverInfo">

                <div style="display: flex;">
                    <p style="font-weight: bold">Email : </p>
                    <p style="margin-left: 5px"><c:out value="${driver.user.getEmail()}"/></p>
                </div>
                <div style="display: flex;">
                    <p style="font-weight: bold">Driver Mobile : </p>
                    <p style="margin-left: 5px"><c:out value="${driver.user.getPhoneNumber()}"/></p>
                </div>
                <div style="display: flex;">
                    <p style="font-weight: bold">Driver NIC : </p>
                    <p style="margin-left: 5px"><c:out value="${driver.getNIC()}"/></p>
                </div>
                <div style="display: flex;">
                    <p style="font-weight: bold">Driver Date of Birth : </p>
                    <p style="margin-left: 5px"><c:out value="${driver.getDOB()}"/></p>
                </div>
                <div style="display: flex;">
                    <p style="font-weight: bold">Address : </p>
                    <p style="margin-left: 5px"><c:out value="${driver.getAddress()}"/></p>
                </div>
            </div>
            <div class="receiverInfo">
                <c:if test="${driver.user.serviceCentre != null}">
                    <div style="margin-bottom: 10px; font-weight: bold">
                        <div style="float: left; margin-top: 5px"><span style="text-align: center">Assigned Service Center</span>
                        </div>
                        <div style="float:right;">
                            <div>
                                <form method="get" action="/view-service-center">
                                    <input type="hidden" name="serviceCenterId" value="${driver.getDriverId()}">
                                    <button style="float: right;" type="submit" class="btn btn-warning">View Service
                                        Center
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                    <br/><br/>
                    <div style="display: flex;">
                        <p style="font-weight: bold">Service Center ID: </p>
                        <p style="margin-left: 5px"><c:out value="${driver.user.serviceCentre.getCentreId()}"/></p>
                    </div>
                    <div style="display: flex;">
                        <p style="font-weight: bold">Service Center : </p>
                        <p style="margin-left: 5px"><c:out value="${driver.user.serviceCentre.getCentre()}"/></p>
                    </div>

                    <div style="display: flex;">
                        <p style="font-weight: bold">Center City: </p>
                        <p style="margin-left: 5px"><c:out value="${driver.user.serviceCentre.getCity()}"/></p>
                    </div>
                    <div style="display: flex;">
                        <p style="font-weight: bold">Center Address: </p>
                        <p style="margin-left: 5px"><c:out value="${driver.user.serviceCentre.getAddress()}"/></p>
                    </div>

                </c:if>
                <c:if test="${driver.user.serviceCentre == null}">
                    <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-primary"
                         role="alert">
                        No Assigned Service Centre
                    </div>
                    <div style="display: flex">
                        <div style="text-align: center">
                            <form method="get" action="/view-service-center">
                                <input type="hidden" name="serviceCenterId" value="${driver.getDriverId()}">
                                <button style="" type="submit" class="btn btn-warning">Assign Service
                                    Centre
                                </button>
                            </form>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="center-box" style="margin-left: 20px">
            <h4 style="float: left">Driver Assigned Packages</h4>
            <button style="float: right" type="button" class="btn btn-primary">View All</button>
            <div>
                <div style="margin-top: 50px">
                    <c:forEach var="mail" items="${driver.mails}">

                        <div class="card" style="border-radius: 10px; margin-top: 10px">
                            <div class="card-body">
                                <h6 style="float: left" class="card-title">${mail.getDescription()}</h6>
                                <button style="float: right" type="button" class="btn btn-info"><i
                                        class="fas fa-expand-alt"></i></button>
                                <h6 style="float: right; margin-right: 35px; font-weight: normal">${mail.getTotalCost()}</h6>
                                <h6 style="float: right; margin-right: 35px; font-weight: normal">
                                    #000${mail.getMailId()}</h6>
                                <h6 style="float: right; margin-right: 35px; font-weight: normal">${mail.user.getLocation()}</h6>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
        <div class="center-box" style="margin-left: 20px">
            <h4 style="float: left">Driver Documents</h4>
            <button style="float: right" type="button" class="btn btn-primary">View All</button>
            <div>
                <div style="margin-top: 50px">
                    <c:forEach var="document" items="${driver.user.documents}">
                        <div class="card" style="border-radius: 10px; margin-top: 10px">
                            <div class="card-body">
                                <h6 style="float: left" class="card-title">${document.getDescription()}</h6>
                                <form method="GET" action="/download">
                                    <input type="hidden" name="fileName" value="${document.getFileName()}">
                                    <button style="float: right" type="submit" class="btn btn-info"><i
                                            class="fa fa-arrow-down" aria-hidden="true"></i></button>
                                </form>
                                <h6 style="float: right; margin-right: 35px; font-weight: normal">
                                        ${document.getFileSize()} KB</h6>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                
            </div>
        </div>
    </div>
</div>
</body>
</html>