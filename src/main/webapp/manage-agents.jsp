<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.UserDto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Express Delivery - Manage Agents</title>
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
    <h3 style="padding: 20px 30px 20px 30px; color: grey">Manage Agents</h3>
</div>
<div class="send-package-button" style="float: right">
    <div>
        <a type="button" href="/add-agent">Add Agent</a>
    </div>
</div>
<div style="margin-top: 120px">
    <div>
        <%
            List<UserDto> mail = new ArrayList<>();
            try {
                mail = (List<UserDto>) request.getAttribute("agent_list");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (mail != null && mail.size() <= 0) {
        %>
        <div style="margin-right: 30px; margin-top: 100px; margin-left: 30px" class="alert alert-secondary"
             role="alert">
            No Agents in the System
        </div>
        <%
        } else {
        %>
        <div class="driver-row" style=" margin-left: 90px">
            <c:forEach var="agent" items="${agent_list}">
                <div class="card"
                     style="width: 20rem; margin-left: 10px;flex: 0 0 auto; max-width: 100%; margin-right: 10px; border-radius: 10px; height: 15rem">
                    <div class="card-body">
                        <h5 style="float: left"
                            class="card-title">${agent.getFirstName()} ${agent.getLastName()}</h5><br/><br/>
                        <p style="font-weight: bold" class="card-text">Email : <span
                                style="font-weight: normal">${agent.getEmail()}</span></p>
                        <p style="font-weight: bold" class="card-text">Mobile : <span
                                style="font-weight: normal">${agent.getPhoneNumber()}</span></p>
                        <p style="font-weight: bold" class="card-text">Center : <span
                                style="font-weight: normal">${agent.serviceCentre.center}</span></p>
                    </div>
                    <div>
                        <button style="float: right; margin: 10px" type="submit" class="btn btn-primary"
                                data-toggle="modal" data-target="#openViewAgentModal${agent.phoneNumber}">View
                            Agent
                        </button>
                    </div>
                </div>
                <%@ include file="modals/view-agent.jsp" %>
                <div
                        class="modal fade bd-example-modal-lg"
                        id="openUpdateCenterModal${agent.phoneNumber}"
                        tabindex="-1"
                        role="dialog"
                        aria-labelledby="exampleModalCenterTitle"
                        aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Update Center</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="card-body">
                                    <div class="form-body">
                                        <form method="POST" action="/update-center-agent">
                                            <input type="hidden" name="email" value="${agent.email}">
                                            <div class="form-row" style="margin-top: 20px">
                                                <div class="col">
                                                    <label for="centerId">Assign Service Center</label>
                                                    <select name="centerId" id="centerId" class="custom-select"
                                                            aria-label="Default select example">
                                                        <c:forEach var="center" items="${centers}">
                                                            <option value="${center.centreId}">${center.center}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                            <hr/>
                                            <button class="btn btn-primary" type="submit" value="submit">
                                                Update City and Address
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
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
