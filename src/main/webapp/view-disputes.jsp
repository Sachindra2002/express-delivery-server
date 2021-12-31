<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.InquiryDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.DisputeDto" %><%--
  Created by IntelliJ IDEA.
  User: Sachindra Rodrigo
  Date: 12/31/2021
  Time: 1:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Express Delivery - Disputes</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/track-parcel.css">
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
<div>
    <h3 style="margin-top: 40px; margin-bottom: -20px; margin-left: 35px">Disputes</h3>
    <div>
        <div>
            <div style="margin-top: 50px; margin-left: 30px; margin-right: 30px">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Dispute Type</th>
                        <th scope="col">Description</th>
                        <th scope="col">Date</th>
                        <th scope="col">Status</th>
                        <th scope="col">Mail ID</th>
                        <th scope="col">Customer</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<DisputeDto> disputeDtoList = new ArrayList<>();
                        try {
                            disputeDtoList = (List<DisputeDto>) request.getAttribute("dispute_list");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (disputeDtoList != null && disputeDtoList.size() <= 0) {
                    %>
                    <td style="font-weight: bold; font-size: 20dp">No Inquiries</td>
                    <%
                    } else {
                    %>
                    <c:forEach var="dispute" items="${dispute_list}">
                        <tr>
                            <td>${dispute.disputeId}</td>
                            <td>${dispute.disputeType}</td>
                            <td>${dispute.description}</td>
                            <td>${dispute.createdAt}</td>
                            <td>${dispute.status}</td>
                            <td>${dispute.mail.mailId}</td>
                            <td>${dispute.mail.user.firstName} ${dispute.mail.user.lastName}</td>
                            <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#openResponseModal${dispute.disputeId}">Respond</button></td>
                        </tr>
                        <%@ include file="modals/respond-disputes.jsp" %>
                    </c:forEach>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
