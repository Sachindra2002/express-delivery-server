<%@ page import="com.sachindrarodrigo.express_delivery_server.dto.InquiryDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <title>Express Delivery - Inquiries</title>
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
    <h3 style="margin-top: 40px; margin-bottom: -20px; margin-left: 35px">Inquiries</h3>
    <div>
        <div>
            <div style="margin-top: 50px; margin-left: 30px; margin-right: 30px">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Customer</th>
                        <th scope="col">Inquiry Type</th>
                        <th scope="col">Description</th>
                        <th scope="col">Date</th>
                        <th scope="col">Status</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<InquiryDto> inquiry = new ArrayList<>();
                        try {
                            inquiry = (List<InquiryDto>) request.getAttribute("inquiry_list");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (inquiry != null && inquiry.size() <= 0) {
                    %>
                    <td style="font-weight: bold; font-size: 20dp">No Inquiries</td>
                    <%
                    } else {
                    %>
                    <c:forEach var="inquiry" items="${inquiry_list}">
                        <tr>
                            <td>${inquiry.inquiryId}</td>
                            <td>${inquiry.user.firstName} ${inquiry.user.lastName}</td>
                            <td>${inquiry.inquiryType}</td>
                            <td>${inquiry.description}</td>
                            <td>${inquiry.createdAt}</td>
                            <td>${inquiry.status}</td>
                            <td><button type="button" class="btn btn-primary">Respond</button></td>
                        </tr>
                    </c:forEach>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ include file="modals/add-inquiry.jsp" %>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
