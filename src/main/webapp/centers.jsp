<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.ServiceCenterDto" %>
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
    <h3 style="padding: 20px 30px 20px 30px; color: grey">Manage Centers</h3>
</div>
<div class="send-package-button" style="float: right">
    <div>
        <a type="button" href="/add-agent">Add Center</a>
    </div>
</div>
<div style="margin-top: 120px">
    <div>
        <%
            List<ServiceCenterDto> serviceCenterDtoList = new ArrayList<>();
            try {
                serviceCenterDtoList = (List<ServiceCenterDto>) request.getAttribute("centers");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (serviceCenterDtoList != null && serviceCenterDtoList.size() <= 0) {
        %>
        <div style="margin-right: 30px; margin-top: 100px; margin-left: 30px" class="alert alert-secondary"
             role="alert">
            No Service Centers in the system
        </div>
        <%
        } else {
        %>
        <div class="driver-row" style=" margin-left: 90px">
            <c:forEach var="center" items="${centers}">
                <div class="card"
                     style="width: 20rem; margin-left: 10px;flex: 0 0 auto; max-width: 100%; margin-right: 10px; border-radius: 10px; height: 15rem">
                    <div class="card-body">
                        <h5 style="float: left"
                            class="card-title">${center.center}</h5><br/><br/>
                        <p style="font-weight: bold" class="card-text">City : <span
                                style="font-weight: normal">${center.city}</span></p>
                        <p style="font-weight: bold" class="card-text">Address : <span
                                style="font-weight: normal">${center.address}</span></p>
                    </div>
                    <div>
                        <form method="get" action="/">
                            <input type="hidden" name="agentId" value="${center.centreId}">
                            <button style="float: right; margin: 10px" type="submit" class="btn btn-primary">View
                                Center
                            </button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
        <% } %>
    </div>
</div>

</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
