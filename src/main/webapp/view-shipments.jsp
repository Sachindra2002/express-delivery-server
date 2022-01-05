<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Express Delivery - View Shipments</title>
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
    <h3 style="padding: 20px 30px 20px 30px; color: grey">View Shipments</h3>
</div>
<div style="margin-top: 120px">
    <div>
        <%
            List<MailDto> mail = new ArrayList<>();
            try {
                mail = (List<MailDto>) request.getAttribute("mail_list");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (mail != null && mail.size() <= 0) {
        %>
        <div style="margin-right: 30px; margin-top: 100px; margin-left: 30px" class="alert alert-secondary"
             role="alert">
            No Shipments in the System
        </div>
        <%
        } else {
        %>
        <div class="driver-row" style=" margin-left: 90px">
            <c:forEach var="mail" items="${mail_list}">
                <div class="card"
                     style="width: 20rem; margin-left: 10px;flex: 0 0 auto; max-width: 100%; margin-right: 10px; border-radius: 10px; height: 16rem">
                    <div class="card-body">
                        <h5 style="float: left"
                            class="card-title">Package ID #${mail.mailId}</h5><br/><br/>
                        <p style="font-weight: bold" class="card-text">Customer : <span
                                style="font-weight: normal">${mail.user.firstName} ${mail.user.lastName}</span></p>
                        <p style="font-weight: bold" class="card-text">Center : <span
                                style="font-weight: normal">${mail.serviceCentre.center}</span></p>
                        <p style="font-weight: bold" class="card-text">Package : <span
                                style="font-weight: normal">${mail.description}</span></p>
                    </div>
                    <div>
                        <button style="float: right; margin: 10px" type="submit" class="btn btn-primary" data-toggle="modal"
                                data-target="#openPackageAdminModal${mail.mailId}">View
                            Package
                        </button>
                    </div>
                </div>
                <%@ include file="modals/view-package-admin.jsp" %>
            </c:forEach>
        </div>
        <% } %>
    </div>
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
