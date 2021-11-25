<%--
  Created by IntelliJ IDEA.
  User: Sachindra Rodrigo
  Date: 11/23/2021
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Express Delivery - Add Agent</title>
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
<%@ include file="utils/error_alert.jsp" %>
<div style="margin-left: 20%; margin-right: 20%">
    <h3 style="margin-top: 30px; margin-bottom: -20px; margin-left: 35px">Add Agent</h3>
    <div class="modal-body">
        <div class="card-body">
            <div class="form-body">
                <form:form method="POST" modelAttribute="user" action="/add-agent"
                           enctype="multipart/form-data">
                    <form:input type="hidden" value="driver" name="userRole" path="userRole"/>
                    <form:input type="hidden" value="password" name="userRole" path="password"/>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="firstName" path="firstName">Agent First Name</form:label>
                            <form:input class="form-control select-filter" name="firstName"
                                        id="firstName" path="firstName"/>
                            <form:errors cssStyle="color: red" path="firstName"/>
                        </div>
                        <div class="col">
                            <form:label for="lastName" path="lastName">Agent Last Name</form:label>
                            <form:input class="form-control select-filter" name="lastName"
                                        id="lastName" path="lastName"/>
                            <form:errors cssStyle="color: red" path="lastName"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="email" path="email">Agent Email</form:label>
                            <form:input class="form-control select-filter" name="email"
                                        id="email" path="email"/>
                            <form:errors cssStyle="color: red" path="email"/>
                            <div style="color: red">${emailError}</div>
                        </div>

                        <div class="col">
                            <form:label for="phoneNumber" path="phoneNumber">Agent Phone Number</form:label>
                            <form:input class="form-control select-filter" name="phone"
                                        id="phone" path="phoneNumber"/>
                            <form:errors cssStyle="color: red" path="phoneNumber"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="location" path="location">City of Agent</form:label>
                            <form:select name="location" id="location" class="custom-select"
                                         aria-label="Default select example" path="location">
                                <option value="Colombo">Colombo</option>
                                <option value="Negombo">Negombo</option>
                                <option value="Negombo">Galle</option>
                            </form:select>
                            <form:errors cssStyle="color: red" path="location"/>
                        </div>
                        <div class="col">
                            <label for="center">Assign Service Center</label>
                            <select name="center" id="center" class="custom-select"
                                    aria-label="Default select example">
                                <c:forEach var="center" items="${centers}">
                                    <option value="${center.getCenter()}">${center.getCenter()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <hr/>
                    <div style="text-align: center"><p>*Note - Agents' default password would be the agents'
                        email.</p>
                    </div>
                    <hr/>
                    <button class="btn btn-primary" type="submit" value="submit">
                        Add Agent
                    </button>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
