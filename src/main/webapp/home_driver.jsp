<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.domain.Mail" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Express Delivery - Driver Home</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/admin-homepage.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
</head>
<body>
<!--Navigation Bar-->
<div>
    <jsp:include page="utils/navbar.jsp">
        <jsp:param name="page" value="home"/>
    </jsp:include>
    <%@ include file="utils/success_alert.jsp" %>
    <%@ include file="utils/error_alert.jsp" %>
    <div>
        <h3 style="padding: 20px 30px 20px 30px">Welcome Back, ${name}</h3>
    </div>
    <div>
        <h4 style="padding: 20px 30px 20px 30px">Driver Dashboard</h4>
    </div>
    <div class="package-stats-agent" style="text-align: center">
        <div>
            <h4 style="float: left">New Shipments to be Accepted</h4>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> mail = new ArrayList<>();
                try {
                    mail = (List<MailDto>) request.getAttribute("assigned_packages");
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
            <c:forEach var="mail" items="${assigned_packages}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center" style="font-weight: bold">${mail.user.firstName} ${mail.user.lastName}</p>
                        </div>
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center">${mail.description}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">From : ${mail.pickupAddress}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">To : ${mail.receiverAddress}</p>
                        </div>
                        <div style="width: 200px; height: 50px; float:left;">
                            <p class="center">${mail.parcelType}</p>
                        </div>
                        <div style="width: 100px;height: 50px; float:left; margin-left: 10px">
                            <p class="center"><fmt:formatDate
                                    type="both"
                                    dateStyle="medium"
                                    timeStyle="medium"
                                    value="${mail.createdAt}"/></p>
                        </div>
                        <div style="width: 80px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button"
                                    class="btn btn-outline-success"
                                    data-toggle="modal" data-target="#openAcceptPackageDriverModal${mail.mailId}"
                            >
                                Accept
                            </button>
                        </div>
                        <div style="width: 80px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button" class="btn btn-outline-danger"
                                    data-toggle="modal" data-target="#openRejectPackageDriverModal${mail.mailId}"
                            >
                                Reject
                            </button>
                        </div>

                        <div style="width: 100px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button" class="btn btn-info"
                                    data-toggle="modal" data-target="#openPackageAgentModal${mail.mailId}"
                            >
                                View
                            </button>
                        </div>
                    </div>
                </div>
                <%@ include file="modals/driver-accept-package.jsp" %>
                <%@ include file="modals/driver-reject-package.jsp" %>
                <%@ include file="modals/view-package-agent.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <div class="package-stats-agent" style="text-align: center; margin-top: 30px">
        <div>
            <h4 style="float: left">Accepted Shipments</h4>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> acceptedmail = new ArrayList<>();
                try {
                    acceptedmail = (List<MailDto>) request.getAttribute("accepted_packages");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (acceptedmail != null && acceptedmail.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No Shipments to be started
            </div>
            <%
            } else {
            %>
            <c:forEach var="mail" items="${accepted_packages}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center" style="font-weight: bold">${mail.user.firstName} ${mail.user.lastName}</p>
                        </div>
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center">${mail.description}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">From : ${mail.pickupAddress}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">To : ${mail.receiverAddress}</p>
                        </div>
                        <div style="width: 200px; height: 50px; float:left;">
                            <p class="center">${mail.parcelType}</p>
                        </div>
                        <div style="width: 100px;height: 50px; float:left; margin-left: 10px">
                            <p class="center"><fmt:formatDate
                                    type="both"
                                    dateStyle="medium"
                                    timeStyle="medium"
                                    value="${mail.createdAt}"/></p>
                        </div>
                        <div style="width: 100px; height: 50px; float:right;">
                            <form method="post" action="/start-shipment">
                                <input type="hidden" name="mailId" value="${mail.mailId}">
                                <button style="float: right" type="submit" class="btn btn-outline-success">
                                    Start
                                </button>
                            </form>
                        </div>
                        <div style="width: 100px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button" class="btn btn-info"
                                    data-toggle="modal" data-target="#openPackageAgentModal${mail.mailId}"
                            >
                                View
                            </button>
                        </div>
                    </div>
                </div>
                <%@ include file="modals/view-package-agent.jsp" %>
                <%@ include file="modals/assign-driver.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <div class="package-stats-agent" style="text-align: center; margin-top: 30px">
        <div>
            <h4 style="float: left">Started Shipments</h4>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> transitMails = new ArrayList<>();
                try {
                    transitMails = (List<MailDto>) request.getAttribute("started_packages");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (transitMails != null && transitMails.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No started packages
            </div>
            <%
            } else {
            %>
            <c:forEach var="mail" items="${started_packages}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center" style="font-weight: bold">${mail.user.firstName} ${mail.user.lastName}</p>
                        </div>
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center">${mail.description}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">From : ${mail.pickupAddress}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">To : ${mail.receiverAddress}</p>
                        </div>
                        <div style="width: 200px; height: 50px; float:left;">
                            <p class="center">${mail.parcelType}</p>
                        </div>
                        <div style="width: 100px;height: 50px; float:left; margin-left: 10px">
                            <p class="center"><fmt:formatDate
                                    type="both"
                                    dateStyle="medium"
                                    timeStyle="medium"
                                    value="${mail.createdAt}"/></p>
                        </div>
                        <div style="width: 150px; height: 50px; float:right;">
                            <form method="post" action="/confirm-pickup">
                                <input type="hidden" name="mailId" value="${mail.mailId}">
                                <button style="float: right" type="submit" class="btn btn-outline-success">
                                    Confirm Pickup
                                </button>
                            </form>
                        </div>
                        <div style="width: 100px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button" class="btn btn-info"
                                    data-toggle="modal" data-target="#openPackageAgentModal${mail.mailId}"
                            >
                                View
                            </button>
                        </div>
                    </div>
                </div>
                <%@ include file="modals/view-package-agent.jsp" %>
                <%@ include file="modals/chnage-driver-modal.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <div class="package-stats-agent" style="text-align: center; margin-top: 30px">
        <div>
            <h4 style="float: left">Picked Up packages</h4>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> mailDtoList = new ArrayList<>();
                try {
                    mailDtoList = (List<MailDto>) request.getAttribute("picked_up_packages");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (mailDtoList != null && mailDtoList.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No Picked up Packages
            </div>
            <%
            } else {
            %>
            <c:forEach var="mail" items="${picked_up_packages}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center" style="font-weight: bold">${mail.user.firstName} ${mail.user.lastName}</p>
                        </div>
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center">${mail.description}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">From : ${mail.pickupAddress}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">To : ${mail.receiverAddress}</p>
                        </div>
                        <div style="width: 200px; height: 50px; float:left;">
                            <p class="center">${mail.parcelType}</p>
                        </div>
                        <div style="width: 100px;height: 50px; float:left; margin-left: 10px">
                            <p class="center"><fmt:formatDate
                                    type="both"
                                    dateStyle="medium"
                                    timeStyle="medium"
                                    value="${mail.createdAt}"/></p>
                        </div>
                        <div style="width: 150px; height: 50px; float:right;">
                            <form style="float: right" method="post" action="/out-for-delivery-package">
                                <input type="hidden" name="mailId" value="${mail.mailId}">
                                <button style="float: right" type="submit" class="btn btn-outline-success">
                                    Start Delivery
                                </button>
                            </form>
                        </div>
                        <div style="width: 150px; height: 50px; float:right;">
                            <button style="float: right" type="submit" class="btn btn-outline-success"  data-toggle="modal" data-target="#openTransitpackageModal${mail.mailId}">
                                Transit Package
                            </button>
                        </div>
                    </div>
                </div>
                <%@ include file="modals/view-package-agent.jsp" %>
                <%@ include file="modals/transit-package.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <div class="package-stats-agent" style="text-align: center; margin-top: 30px">
        <div>
            <h4 style="float: left">In Transit Shipments</h4>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> mailDtoList1 = new ArrayList<>();
                try {
                    mailDtoList1 = (List<MailDto>) request.getAttribute("in_transit_packages");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (mailDtoList1 != null && mailDtoList1.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No Transit packages
            </div>
            <%
            } else {
            %>
            <c:forEach var="mail" items="${in_transit_packages}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center" style="font-weight: bold">${mail.user.firstName} ${mail.user.lastName}</p>
                        </div>
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center">${mail.description}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">From : ${mail.pickupAddress}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">To : ${mail.receiverAddress}</p>
                        </div>
                        <div style="width: 200px; height: 50px; float:left;">
                            <p class="center">${mail.parcelType}</p>
                        </div>
                        <div style="width: 100px;height: 50px; float:left; margin-left: 10px">
                            <p class="center"><fmt:formatDate
                                    type="both"
                                    dateStyle="medium"
                                    timeStyle="medium"
                                    value="${mail.createdAt}"/></p>
                        </div>
                        <div style="width: 150px; height: 50px; float:right;">
                            <form style="float: right" method="post" action="/out-for-delivery-package">
                                <input type="hidden" name="mailId" value="${mail.mailId}">
                                <button style="float: right" type="submit" class="btn btn-outline-success">
                                    Start Delivery
                                </button>
                            </form>
                        </div>
                        <div style="width: 100px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button" class="btn btn-info"
                                    data-toggle="modal" data-target="#openPackageAgentModal${mail.mailId}"
                            >
                                View
                            </button>
                        </div>
                    </div>
                </div>
                <%@ include file="modals/view-package-agent.jsp" %>
                <%@ include file="modals/chnage-driver-modal.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
    <div class="package-stats-agent" style="text-align: center; margin-top: 30px">
        <div>
            <h4 style="float: left">Out for Delivery Shipments</h4>
        </div>
        <div style="margin-top: 50px">
            <%
                List<MailDto> mailDtoList2 = new ArrayList<>();
                try {
                    mailDtoList2 = (List<MailDto>) request.getAttribute("out_for_delivery_packages");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (mailDtoList2 != null && mailDtoList2.size() <= 0) {
            %>
            <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary"
                 role="alert">
                No Out for delivery packages
            </div>
            <%
            } else {
            %>
            <c:forEach var="mail" items="${out_for_delivery_packages}">
                <div class="card" style="border-radius: 10px; margin-top: 10px">
                    <div class="card-body">
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center" style="font-weight: bold">${mail.user.firstName} ${mail.user.lastName}</p>
                        </div>
                        <div style="width: 200px;height: 50px; float:left;">
                            <p class="center">${mail.description}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">From : ${mail.pickupAddress}</p>
                        </div>
                        <div style="width: 300px; height: 50px; float:left;">
                            <p class="center">To : ${mail.receiverAddress}</p>
                        </div>
                        <div style="width: 200px; height: 50px; float:left;">
                            <p class="center">${mail.parcelType}</p>
                        </div>
                        <div style="width: 100px;height: 50px; float:left; margin-left: 10px">
                            <p class="center"><fmt:formatDate
                                    type="both"
                                    dateStyle="medium"
                                    timeStyle="medium"
                                    value="${mail.createdAt}"/></p>
                        </div>
                        <div style="width: 150px; height: 50px; float:right;">
                            <form style="float: right" method="post" action="/confirm-delivery">
                                <input type="hidden" name="mailId" value="${mail.mailId}">
                                <button style="float: right" type="submit" class="btn btn-outline-success">
                                    Confirm Delivery
                                </button>
                            </form>
                        </div>
                        <div style="width: 100px; height: 50px; float:right;">
                            <button style="float: right; margin-left: 10px" type="button" class="btn btn-info"
                                    data-toggle="modal" data-target="#openPackageAgentModal${mail.mailId}"
                            >
                                View
                            </button>
                        </div>
                    </div>
                </div>
                <%@ include file="modals/view-package-agent.jsp" %>
                <%@ include file="modals/chnage-driver-modal.jsp" %>
            </c:forEach>
            <% } %>
        </div>
    </div>
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>