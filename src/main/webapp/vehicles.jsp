<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.ServiceCenterDto" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.VehicleDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Express Delivery - Manage Centers</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/admin-homepage.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<%@ include file="utils/success_alert.jsp" %>
<%@ include file="utils/error_alert.jsp" %>
<div style="float: left">
    <h3 style="padding: 20px 30px 20px 30px; color: grey">Manage Vehicles</h3>
</div>
<div class="send-package-button" style="float: right">
    <div>
        <a type="button" data-toggle="modal" data-target="#openAddVehicleModal"
           style="text-decoration: none; margin-bottom: 30px">Add Vehicle</a>
    </div>
</div>
<div style="margin-top: 120px">
    <div>
        <%
            List<VehicleDto> vehicleDtoList = new ArrayList<>();
            try {
                vehicleDtoList = (List<VehicleDto>) request.getAttribute("vehicles");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (vehicleDtoList != null && vehicleDtoList.size() <= 0) {
        %>
        <div style="margin-right: 30px; margin-top: 100px; margin-left: 30px" class="alert alert-secondary"
             role="alert">
            No Service Centers in the system
        </div>
        <%
        } else {
        %>
        <div class="driver-row" style=" margin-left: 90px">
            <c:forEach var="vehicle" items="${vehicles}">
                <div class="card"
                     style="width: 20rem; margin-left: 10px;flex: 0 0 auto; max-width: 100%; margin-right: 10px; border-radius: 10px; height: 15rem">
                    <div class="card-body">
                        <h5 style="float: left"
                            class="card-title">${vehicle.vehicleType}</h5>
                        <c:if test="${vehicle.status == 'available'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: orange"
                                  class="badge badge-pill badge-success">Available</span><br/><br/>
                        </c:if>
                        <c:if test="${vehicle.status == 'taken'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">Occupied</span><br/><br/>
                        </c:if>
                        <c:if test="${vehicle.status == 'Blacklisted'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: black"
                                  class="badge badge-pill badge-success">Blacklisted</span><br/><br/>
                        </c:if>
                        <p style="font-weight: bold" class="card-text">Number : <span
                                style="font-weight: normal">${vehicle.vehicleNumber}</span></p>
                        <c:if test="${vehicle.driverDetail != null}">
                            <p style="font-weight: bold" class="card-text">Driver : <span
                                    style="font-weight: normal">${vehicle.driverDetail.user.firstName} ${vehicle.driverDetail.user.lastName}</span>
                            </p>
                        </c:if>
                        <c:if test="${vehicle.driverDetail == null}">
                            <p style="font-weight: bold" class="card-text">Driver : <span
                                    style="font-weight: normal">No Driver Assigned</span></p>
                        </c:if>
                    </div>
                    <div>
                        <button style="float: right; margin: 10px" type="submit" class="btn btn-primary" data-toggle="modal" data-target="#openViewVehicleModal${vehicle.vehicleId}">View
                            Vehicle
                        </button>
                    </div>
                </div>
                <%@ include file="modals/view-vehicle.jsp" %>
            </c:forEach>
        </div>
        <% } %>
    </div>
</div>
<%@ include file="modals/add-vehicle.jsp" %>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
