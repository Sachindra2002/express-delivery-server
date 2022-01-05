<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.DocumentsDto" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.UserDto" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Express Delivery - Admin Home</title>
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
    <%@ include file="utils/error_alert.jsp" %>
    <div>
        <h3 style="padding: 20px 30px 20px 30px">Welcome Back, ${name}</h3>
    </div>
    <div>
        <h4 style="padding: 20px 30px 20px 30px">Admin Dashboard</h4>
    </div>
    <div class="package-stats">
        <div>
            <h4 style="float: left">New Shipments</h4>
            <button style="float: right" type="button" class="btn btn-primary">View All <i style="margin-left: 10px"
                                                                                           class="fas fa-expand"></i>
            </button>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> mail = new ArrayList<>();
                try {
                    mail = (List<MailDto>) request.getAttribute("new_shipments");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (mail != null && mail.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No New Shipments
            </div>
            <%
            } else {
            %>
            <c:forEach var="mail" items="${new_shipments}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <h6 style="float: left" class="card-title">${mail.getDescription()}</h6>
                        <button style="float: right" type="button" class="btn btn-info" data-toggle="modal"
                                data-target="#openPackageAdminModal${mail.mailId}"><i class="fas fa-expand-alt"></i>
                        </button>
                        <h6 style="float: right; margin-right: 35px; font-weight: normal">${mail.getTotalCost()}</h6>
                        <h6 style="float: right; margin-right: 35px; font-weight: normal">#${mail.getMailId()}</h6>
                        <h6 style="float: right; margin-right: 35px; font-weight: normal">${mail.user.getLocation()}</h6>
                    </div>
                </div>
                <%@ include file="modals/view-package-admin.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <div class="package-stats">
        <div>
            <h4 style="float: left">Documents</h4>
            <button style="float: right" type="button" class="btn btn-primary">View All <i style="margin-left: 10px"
                                                                                           class="fas fa-expand"></i>
            </button>
        </div>
        <div style="margin-top: 50px">
            <%
                List<DocumentsDto> documentsDtoList = new ArrayList<>();
                try {
                    documentsDtoList = (List<DocumentsDto>) request.getAttribute("documents");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (documentsDtoList != null && documentsDtoList.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No Documents
            </div>
            <%
            } else {
            %>
            <c:forEach var="document" items="${documents}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <h6 style="float: left" class="card-title">${document.description}</h6>
                        <form method="GET" action="/download">
                            <input type="hidden" name="fileName" value="${document.fileName}">
                            <button style="float: right" type="submit" class="btn btn-info"><i
                                    class="fa fa-arrow-down"></i></button>
                        </form>
                        <h6 style="float: right; margin-right: 35px; font-weight: bold">${document.fileSize} KB</h6>
                    </div>
                </div>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <div class="package-stats-driver">
        <div>
            <h4 style="float: left">Active Drivers</h4>
            <form method="get" action="/drivers">
                <button style="float: right" type="submit" class="btn btn-primary">View All <i style="margin-left: 10px" class="fas fa-expand"></i></button>
            </form>
        </div>
        <div style="margin-top: 50px">
            <%
                List<UserDto> userDtoList = new ArrayList<>();
                try {
                    userDtoList = (List<UserDto>) request.getAttribute("drivers");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (userDtoList != null && userDtoList.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No Active Drivers
            </div>
            <%
            } else {
            %>
            <c:forEach var="driver" items="${drivers}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <h6 style="float: left" class="card-title">${driver.firstName} ${driver.lastName}</h6>
                        <form method="get" action="/view-driver">
                            <input type="hidden" name="driverId" value="${driver.driverDetail.driverId}">
                            <button style="float: right" type="submit" class="btn btn-info"><i
                                    class="fas fa-expand-alt"></i>
                            </button>
                        </form>
                        <h6 style="float: right; margin-right: 35px; font-weight: normal">${driver.phoneNumber}</h6>
                    </div>
                </div>
                <%@ include file="modals/view-package-admin.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>