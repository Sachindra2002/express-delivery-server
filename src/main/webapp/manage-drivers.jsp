<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Express Delivery - Manage Drivers</title>
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
    <%@ include file="utils/success_alert.jsp" %>
    <div style="float: left">
        <h3 style="padding: 20px 30px 20px 30px; color: grey">Manage Drivers</h3>
    </div>
    <div class="send-package-button" style="float: right">
        <div>
            <a type="button" href="/add-driver">Add Driver</a>
        </div>
    </div>
    <div style="margin-top: 120px">
        <div>
            <%
                List<MailDto> mail = new ArrayList<>();
                try {
                    mail = (List<MailDto>) request.getAttribute("driver_list");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (mail != null && mail.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 100px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No Drivers in the System
            </div>
            <%
            } else {
            %>
            <div class="driver-row" style=" margin-left: 90px">
                <c:forEach var="driver" items="${driver_list}">
                    <div class="card"
                         style="width: 20rem; margin-left: 10px;flex: 0 0 auto; max-width: 100%; margin-right: 10px; border-radius: 10px;">
                        <div class="card-body">
                            <h5 style="float: left"
                                class="card-title">${driver.getFirstName()} ${driver.getLastName()}</h5>
                            <c:if test="${driver.driverDetail.getStatus() == 'Available'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">${driver.driverDetail.getStatus()}</span><br/><br/>
                            </c:if>
                            <c:if test="${driver.driverDetail.getStatus() == 'Busy'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: orange"
                                  class="badge badge-pill badge-success">${driver.driverDetail.getStatus()}</span><br/><br/>
                            </c:if>
                            <c:if test="${driver.driverDetail.getStatus() == 'Unavailable'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${driver.driverDetail.getStatus()}</span><br/><br/>
                            </c:if>
                            <p style="font-weight: bold" class="card-text">Email : <span
                                    style="font-weight: normal">${driver.getEmail()}</span></p>
                            <p style="font-weight: bold" class="card-text">Mobile : <span
                                    style="font-weight: normal">${driver.getPhoneNumber()}</span></p>
                        </div>
                        <div>
                            <form method="get" action="/view-driver">
                                <input type="hidden" name="driverId" value="${driver.driverDetail.getDriverId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-secondary">View
                                    Driver
                                </button>
                            </form>
                        </div>
                        <div class="card-header" style="border-radius: 10px">
                            <p class="card-text" style="float: right; font-weight: bold; margin-top: 5px">
                                #000${driver.driverDetail.getDriverId()}</p>
                            <p class="card-text"
                               style="float: right; font-weight: bold; margin-right: 10px; margin-top: 5px">Driver
                                ID</p>
                            <br/><br/>
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