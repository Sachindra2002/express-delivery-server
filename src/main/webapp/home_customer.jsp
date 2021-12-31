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
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
    <script>

        function submitHiddenForm(mailId) {
            document.getElementById('mailId').value = mailId;
        }

        function submitHiddenForm2(mailId) {
            document.getElementById('mailId2').value = mailId;
        }

        function submitHiddenForm3(mailId) {
            document.getElementById('mailId3').value = mailId;
        }

    </script>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<%@ include file="utils/success_alert.jsp" %>
<%@ include file="utils/error_alert.jsp" %>
<div style="float: left">
    <h3 style="padding: 20px 30px 20px 30px; color: grey">Welcome Back, <span style="color: grey">${name}</span></h3>
</div>
<div>
    <div class="send-package-button" style="float: right">
        <div>
            <a type="button" data-toggle="modal" data-target="#openDisputeModal">Track Package</a>
        </div>
    </div>
    <div class="send-package-button" style="float: right">
        <div>
            <a type="button" style="text-decoration: none" href="/send-package">Send a Package</a>
        </div>
    </div>
    <br/><br/><br/>
    <div class="center-header">
        <h3 style="margin-left: 30px; font-size: 25px; margin-top: 30px">Track Recent Upcoming Packages</h3>
        <%
            List<MailDto> mail = new ArrayList<>();
            try {
                mail = (List<MailDto>) request.getAttribute("upcoming_packages");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (mail != null && mail.size() <= 0) {
        %>
        <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary" role="alert">
            No Incoming Packages
        </div>
        <%
        } else {
        %>
        <div class="homepage-row" style=" margin-right: 50px">
            <c:forEach var="mail" items="${upcoming_packages}">
                <div class="card"
                     style="width: 18rem; margin-left: 10px;flex: 0 0 auto; width: auto; max-width: 100%; margin-right: 10px; border-radius: 10px">
                    <div class="card-body">
                        <h5 style="float: left" class="card-title">From : ${mail.user.getFirstName()} ${mail.user.getLastName()}</h5>
                        <c:if test="${mail.getStatus() == 'Processing'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Accepted'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Assigned'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">Accepted</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Driver Accepted'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">Accepted</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Cancelled'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Rejected'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Shipped'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; border-color: orange; color: orange; background-color: white"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Delivered'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: orange; color: white"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Delivery Started'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Package picked up'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <p style="font-weight: bold" class="card-text">Description : <span
                                style="font-weight: normal">${mail.getDescription()}</span></p>
                        <p style="font-weight: bold" class="card-text">Parcel Type : <span
                                style="font-weight: normal">${mail.getParcelType()}</span></p>
                        <p style="font-weight: bold" class="card-text">Weight : <span
                                style="font-weight: normal">${mail.getWeight()} KG</span></p>
                    </div>
                    <c:if test="${mail.getStatus() == 'Delivered'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-warning"
                                    data-toggle="modal" data-target="#openReturnModal"
                                    onclick="submitHiddenForm2(${mail.getMailId()})">Initiate Return
                            </button>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>

                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Processing'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Shipped'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Package picked up'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Shipped'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Delivery Started'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Driver Accepted'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Assigned'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Rejected'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Cancelled'}">
                        <div>
                            <form method="get" action="/track-parcel">
                                <input type="hidden" name="mailId" value="${mail.getMailId()}">
                                <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">Track Parcel
                                </button>
                            </form>
                        </div>
                    </c:if>
                    <div class="card-header" style="border-radius: 10px">
                        <p class="card-text" style="float: right; font-weight: bold; margin-top: 5px">
                            #${mail.getMailId()}</p>
                        <p class="card-text"
                           style="float: right; font-weight: bold; margin-right: 10px; margin-top: 5px">Package ID</p>
                        <br/><br/>
                        <p class="card-text" style="float: right; font-weight: bold; margin-top: -15px">
                            #${mail.mailTracking.getTrackingId()}</p>
                        <p class="card-text"
                           style="float: right; font-weight: bold; margin-right: 10px; margin-top: -15px">Tracking ID</p>
                        <p class="card-text"
                           style="float: left; font-weight: bold; margin-left: 10px; margin-top: -45px">Date</p>
                        <p class="card-text"
                           style="float: left; font-weight: bold; margin-top: -45px; margin-left: 50px"><fmt:formatDate pattern = "yyyy-MM-dd" value = "${mail.getCreatedAt()}" /></p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <% } %>
        <h3 style="margin-left: 30px; font-size: 25px; margin-top: 50px">Track Recent Outgoing Packages</h3>
        <%
            List<MailDto> mails = new ArrayList<>();
            try {
                mails = (List<MailDto>) request.getAttribute("outgoing_packages");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (mails != null && mails.size() <= 0) {
        %>
        <div style="margin-right: 30px; margin-top: 30px; margin-left: 30px" class="alert alert-secondary" role="alert">
            No Outgoing Packages
        </div>
        <%
        } else {
        %>
        <div class="homepage-row" style=" margin-right: 30px">
            <c:forEach var="mail" items="${outgoing_packages}">
                <div class="card"
                     style="width: 18rem; margin-left: 10px;flex: 0 0 auto; width: auto; max-width: 100%; margin-right: 10px ; border-radius: 10px">
                    <div class="card-body">
                        <h5 style="float: left" class="card-title">To : ${mail.getReceiverFirstName()} ${mail.getReceiverLastName()}</h5>
                        <c:if test="${mail.getStatus() == 'Processing'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Accepted'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>

                        <c:if test="${mail.getStatus() == 'Driver Accepted'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">Accepted</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Assigned'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px"
                                  class="badge badge-pill badge-success">Accepted</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Cancelled'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Delivery Started'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Package picked up'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Rejected'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: red"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Shipped'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; border-color: orange; color: orange; background-color: white"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <c:if test="${mail.getStatus() == 'Delivered'}">
                            <span style="float: right; font-size: 15px; margin-left: 30px; background-color: orange; color: white"
                                  class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        </c:if>
                        <p style="font-weight: bold" class="card-text">Address : <span
                                style="font-weight: normal">${mail.getReceiverAddress()} [${mail.getReceiverCity()}]</span>
                        </p>
                        <p style="font-weight: bold" class="card-text">Description : <span
                                style="font-weight: normal">${mail.getDescription()}</span></p>
                        <p style="font-weight: bold" class="card-text">Parcel Type : <span
                                style="font-weight: normal">${mail.getParcelType()}</span></p>
                        <p style="font-weight: bold" class="card-text">Weight : <span
                                style="font-weight: normal">${mail.getWeight()} KG</span></p>
                        <p style="font-weight: bold" class="card-text">Delivery Cost : <span
                                style="font-weight: normal">${mail.getTotalCost()}</span></p>
                    </div>
                    <c:if test="${mail.getStatus() == 'In Transit'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Package picked up'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Driver Accepted'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Delivery Started'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Rejected'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Accepted'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Assigned'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Shipped'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Cancelled'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger"
                                    data-toggle="modal" data-target="#openDisputeModal${mail.mailId}">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Processing'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-warning"
                                    data-toggle="modal" data-target="#openCancelModal"
                                    onclick="submitHiddenForm3(${mail.getMailId()})"><i
                                    style="color: black; margin-right: 10px" class="fa fa-exclamation-circle"
                                    aria-hidden="true"></i> Cancel
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track Parcel
                            </button>
                        </div>
                    </c:if>
                    <div class="card-header" style="border-radius: 10px">
                        <p class="card-text" style="float: right; font-weight: bold">#000${mail.mailTracking.getTrackingId()}</p>
                        <p class="card-text" style="float: right; font-weight: bold; margin-right: 10px">Tracking ID</p>
                    </div>
                </div>
                <%@ include file="modals/open-dispute.jsp" %>
            </c:forEach>
        </div>
        <% } %>
    </div>
</div>

<%@ include file="modals/initiate-return.jsp" %>
<%@ include file="modals/cancel-parcel.jsp" %>
<%@ include file="utils/script_imports.jsp" %>
</body>
<footer>
    <%--    <%@ include file="components/footer.jsp" %>--%>
</footer>
</html>