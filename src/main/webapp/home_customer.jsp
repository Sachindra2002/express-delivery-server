<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.MailDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        function myFunction() {
            let weight = document.getElementById('inputWeight').value;
            let type = document.getElementById('inputTypeofParcel').value;
            let pieces = document.getElementById('inputPieces').value;
            if (weight == 0.0) {
                document.getElementById('cost').value = '0.0 LKR';
            } else if (weight >= 0.1 && weight <= 1.0) {
                document.getElementById('cost').value = '200.0 LKR';
            } else if (weight >= 1.0 && weight <= 3.0) {
                document.getElementById('cost').value = '500.0 LKR';
            } else if (weight >= 3.1 && weight)
                document.getElementById('cost').value = '700 LKR'
        }

    </script>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<%@ include file="utils/success_alert.jsp" %>
<div style="float: left">
    <h3 style="padding: 20px 30px 20px 30px; color: grey">Welcome Back, <span style="color: grey">${name}</span></h3>
</div>
<div>
    <div class="send-package-button" style="float: right">
        <div>
            <a type="button" data-toggle="modal" data-target="#sendPackageModal">Track Package</a>
        </div>
    </div>
    <div class="send-package-button" style="float: right">
        <div>
            <a type="button" data-toggle="modal" data-target="#sendPackageModal">Send a Package</a>
        </div>
    </div>
    <br/><br/><br/>
    <div class="center-header">
        <h3 style="margin-left: 30px; font-size: 25px; margin-top: 30px">Track Recent Upcoming Packages</h3>
        <div class="homepage-row" style=" margin-right: 50px">
            <c:forEach var="mail" items="${upcoming_packages}">
                <div class="card"
                     style="width: 18rem; margin-left: 10px;flex: 0 0 auto; width: auto; max-width: 100%; margin-right: 10px;">
                    <div class="card-body">
                        <h5 style="float: left" class="card-title">From : ${mail.getSenderEmail()}</h5>
                        <span style="float: right; font-size: 15px; margin-left: 30px"
                              class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
                        <p style="font-weight: bold" class="card-text">Description : <span
                                style="font-weight: normal">${mail.getDescription()}</span></p>
                        <p style="font-weight: bold" class="card-text">Parcel Type : <span
                                style="font-weight: normal">${mail.getParcelType()}</span></p>
                        <p style="font-weight: bold" class="card-text">Weight : <span
                                style="font-weight: normal">${mail.getWeight()} KG</span></p>
                    </div>
                    <div>
                        <button style="float: right; margin: 10px" type="button" class="btn btn-danger">Open Dispute
                        </button>
                    </div>
                    <div class="card-header">
                        <p class="card-text" style="float: right; font-weight: bold">#000${mail.getMailId()}</p>
                        <p class="card-text" style="float: right; font-weight: bold; margin-right: 10px">Tracking ID</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <h3 style="margin-left: 30px; font-size: 25px; margin-top: 50px">Track Recent Outgoing Packages</h3>
        <%
            List<MailDto> mail = new ArrayList<>();
            try {
                mail = (List<MailDto>) request.getAttribute("outgoing_packages");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (mail != null && mail.size() <= 0) {
        %>
        <div style="margin-right: 30px; margin-top: 30px" class="alert alert-secondary" role="alert">
            No Outgoing Packages
        </div>
        <%
        } else {
        %>
        <div class="homepage-row" style=" margin-right: 30px">
            <c:forEach var="mail" items="${outgoing_packages}">
                <div class="card"
                     style="width: 18rem; margin-left: 10px;flex: 0 0 auto; width: auto; max-width: 100%; margin-right: 10px;">
                    <div class="card-body">
                        <h5 style="float: left" class="card-title">To : ${mail.getReceiverEmail()}</h5>
                        <span style="float: right; font-size: 15px; margin-left: 30px"
                              class="badge badge-pill badge-success">${mail.getStatus()}</span><br/><br/>
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
                                style="font-weight: normal">${mail.getTotalCost()} LKR</span></p>
                    </div>
                    <c:if test="${mail.getStatus() == 'In Warehouse [Negombo]'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track
                            </button>
                        </div>
                    </c:if>
                    <c:if test="${mail.getStatus() == 'Processing'}">
                        <div>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-danger">Open Dispute
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-warning"><i
                                    style="color: black; margin-right: 10px" class="fa fa-exclamation-circle"
                                    aria-hidden="true"></i> Cancel
                            </button>
                            <button style="float: right; margin: 10px" type="button" class="btn btn-primary">Track
                            </button>
                        </div>
                    </c:if>
                    <div class="card-header">
                        <p class="card-text" style="float: right; font-weight: bold">#000${mail.getMailId()}</p>
                        <p class="card-text" style="float: right; font-weight: bold; margin-right: 10px">Tracking ID</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <% } %>
    </div>
</div>

<%@ include file="modals/send-package.jsp" %>
<%@ include file="utils/script_imports.jsp" %>
</body>
<footer>
    <%@ include file="components/footer.jsp" %>
</footer>
</html>