<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Express Delivery - Add Driver</title>
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
<div style="margin-left: 20%; margin-right: 20%">
    <h3 style="margin-top: 30px; margin-bottom: -20px; margin-left: 35px">Add Driver</h3>
    <div class="modal-body">
        <div class="card-body">
            <div class="form-body">
                <form:form method="POST" modelAttribute="user" action="/add-driver"
                           enctype="multipart/form-data">
                    <form:input type="hidden" value="driver" name="userRole" path="userRole"/>
                    <form:input type="hidden" value="password" name="userRole" path="password"/>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="firstName" path="firstName">Driver First Name</form:label>
                            <form:input class="form-control select-filter" name="firstName"
                                        id="firstName" path="firstName"/>
                            <form:errors cssStyle="color: red" path="firstName"/>
                        </div>
                        <div class="col">
                            <form:label for="lastName" path="lastName">Driver Last Name</form:label>
                            <form:input class="form-control select-filter" name="lastName"
                                        id="lastName" path="lastName"/>
                            <form:errors cssStyle="color: red" path="lastName"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="email" path="email">Driver Email</form:label>
                            <form:input class="form-control select-filter" name="email"
                                        id="email" path="email"/>
                            <form:errors cssStyle="color: red" path="email"/>
                        </div>
                        <div class="col">
                            <form:label for="phoneNumber" path="phoneNumber">Driver Phone Number</form:label>
                            <form:input class="form-control select-filter" name="phone"
                                        id="phone" path="phoneNumber"/>
                            <form:errors cssStyle="color: red" path="phoneNumber"/>
                        </div>
                    </div>
                    <div class="form-row">
                    <div class="col">
                        <form:label for="location" path="location">City of Driver</form:label>
                        <form:select name="location" id="location" class="custom-select"
                                     aria-label="Default select example" path="location">
                            <option value="Colombo">Colombo</option>
                            <option value="Negombo">Negombo</option>
                            <option value="Negombo">Galle</option>
                        </form:select>
                        <form:errors cssStyle="color: red" path="location"/>
                    </div>
                    <form:form method="POST" modelAttribute="driverDetail" action="/add-driver"
                               enctype="multipart/form-data">
                        <form:input type="hidden" value="Unavailable" name="status" path="status"/>
                        <div class="col">
                            <form:label for="center" path="user.serviceCentre.centre">Assign Service Center</form:label>
                            <form:select name="center" id="center" class="custom-select"
                                         aria-label="Default select example" path="user.serviceCentre.centre">
                                <c:forEach var="center" items="${centers}">
                                    <option value="${center.getCenter()}">${center.getCenter()}</option>
                                </c:forEach>
                            </form:select>
                            <form:errors cssStyle="color: red" path="user.serviceCentre.centre"/>
                        </div>
                        </div>
                        <div class="form-row">
                            <div class="col">
                                <form:label for="dob" path="DOB">Driver Date of Birth</form:label>
                                <form:input type="date" class="form-control select-filter" name="DOB"
                                            id="dob" path="DOB"/>
                                <form:errors cssStyle="color: red" path="DOB"/>
                            </div>
                            <div class="col">
                                <form:label for="nic" path="NIC">Driver National Identity Number</form:label>
                                <form:input class="form-control select-filter" name="nic"
                                            id="nic" maxlength="12" path="NIC"/>
                                <form:errors cssStyle="color: red" path="NIC"/>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col">
                                <form:label for="address" path="address">Driver Address</form:label>
                                <form:textarea class="form-control select-filter" name="address"
                                               id="address" path="address"/>
                                <form:errors cssStyle="color: red" path="address"/>
                            </div>
                        </div>
                        <div class="form-row" style="margin-top: 20px">
                            <div class="col" style="margin-right: 10px">
                                <input type="file" class="custom-file-input" name="nicImage" id="nicImage" required>
                                <label class="custom-file-label" for="nicImage">Upload NIC</label>
                            </div>
                            <div class="col" style="margin-right: 10px">
                                <input type="file" class="custom-file-input" name="licence" id="licence" required>
                                <label class="custom-file-label" for="licence">Upload Licence</label>
                            </div>
                            <div class="col">
                                <input type="file" class="custom-file-input" name="insurance" id="insurance" required>
                                <label class="custom-file-label" for="insurance">Upload Insurance</label>
                            </div>
                        </div>
                        <hr/>
                        <div style="text-align: center"><p>*Note - Drivers' default password would be the drivers'
                            email.</p>
                        </div>
                        <hr/>
                        <button class="btn btn-primary" type="submit" value="submit">
                            Add Driver
                        </button>
                    </form:form>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
