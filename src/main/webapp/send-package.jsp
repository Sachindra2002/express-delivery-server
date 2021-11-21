<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Express Delivery - Send Package</title>
    <link rel="icon" href="images/logo.png"/>
    <link rel="stylesheet" href="css/track-parcel.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <%@ include file="utils/head_imports.jsp" %>
    <script>
        function myFunction() {
            let cost = 0.0;
            let typeCost = 0.0;
            let weight = document.getElementById('inputWeight').value;
            let type = document.getElementById('inputTypeofParcel').value;
            let pieces = document.getElementById('inputPieces').value;

            if (type === "Small 20CM X 20CM") {
                typeCost = 200.0;
            } else if (type === "Medium 45CM X 45CM") {
                typeCost = 300.0;
            } else if (type === "Large 80CM X 80CM") {
                typeCost = 500.0;
            } else if (type === "Card Envelop") {
                typeCost = 100.0;
            } else if (type === "Express Delivery Flyer") {
                typeCost = 50.0;
            } else {
                typeCost = 0.0;
            }

            if (weight === 0.0) {
                cost = 0.0;
            } else if (weight >= 0.1 && weight <= 1.0) {
                cost = 200.0;
                document.getElementById('cost').value = (cost + typeCost) * pieces + " LKR";
            } else if (weight >= 1.0 && weight <= 3.0) {
                cost = 500.0;
                document.getElementById('cost').value = (cost + typeCost) * pieces + " LKR";
            } else if (weight >= 3.1 && weight <= 5.0) {
                cost = 700.0;
                document.getElementById('cost').value = (cost + typeCost) * pieces + " LKR";
            } else if (weight >= 5.1 && weight <= 8.0) {
                cost = 850.0;
                document.getElementById('cost').value = (cost + typeCost) * pieces + " LKR";
            } else {
                cost = 1000.0;
                document.getElementById('cost').value = (cost + typeCost) * pieces + " LKR";
            }
        }
    </script>
</head>
<body>
<!--Navigation Bar-->
<jsp:include page="utils/navbar.jsp">
    <jsp:param name="page" value="home"/>
</jsp:include>
<div style="margin-left: 20%; margin-right: 20%">
    <h3 style="margin-top: 30px; margin-bottom: -20px; margin-left: 35px">Send Package</h3>
    <div class="modal-body">
        <div class="card-body">
            <div class="form-body">
                <form:form method="POST" modelAttribute="mail" action="/send-package">
                    <form:input type="hidden" value="proccesssing" name="status" path="status"/>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="pickupAddress" path="pickupAddress">Pickup Address</form:label>
                            <form:textarea class="form-control select-filter" name="pickupAddress"
                                      id="pickupAddress"  path="pickupAddress"/>
                            <form:errors cssStyle="color: red" path="pickupAddress"/>
                        </div>

                    </div>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="inputReceiverAddress" path="receiverAddress">Receivers' Address</form:label>
                            <form:textarea class="form-control select-filter" name="receiverAddress"
                                      id="receiverAddress"  path="receiverAddress"/>
                            <form:errors cssStyle="color: red" path="receiverAddress"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="inputDesc" path="description">Package Description</form:label>
                            <form:textarea class="form-control select-filter" name="description"
                                      id="inputDesc"  path="description"/>
                            <form:errors cssStyle="color: red" path="description"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="receiverPhoneNumber" path="receiverPhoneNumber">Receivers' Phone Number</form:label>
                            <form:input type="text" class="form-control select-filter" name="receiverPhoneNumber"
                                   id="receiverPhoneNumber"  path="receiverPhoneNumber"/>
                            <form:errors cssStyle="color: red" path="receiverPhoneNumber"/>
                        </div>
                        <div class="col">
                            <form:label for="receiverEmail" path="receiverEmail">Receivers' Email</form:label>
                            <form:input type="text" class="form-control select-filter" name="receiverEmail"
                                   id="receiverEmail"  path="receiverEmail"/>
                            <form:errors cssStyle="color: red" path="receiverEmail"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="receiverFirstName" path="receiverFirstName">Receivers' First Name</form:label>
                            <form:input type="text" class="form-control select-filter" name="receiverFirstName"
                                   id="receiverFirstName"  path="receiverFirstName"/>
                            <form:errors cssStyle="color: red" path="receiverFirstName"/>
                        </div>
                        <div class="col">
                            <form:label for="receiverLastName" path="receiverLastName">Receivers' Last Name</form:label>
                            <form:input type="text" class="form-control select-filter" name="receiverLastName"
                                   id="receiverLastName"  path="receiverLastName"/>
                            <form:errors cssStyle="color: red" path="receiverLastName"/>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-row">

                        <div class="col">
                            <form:label for="receiverCity" path="receiverCity">Select City of Receiver</form:label>
                            <form:select name="receiverCity" id="receiverCity" class="custom-select"
                                    aria-label="Default select example"  path="receiverCity">
                                <option value="Colombo">Colombo [Western Province]</option>
                                <option value="Negombo">Negombo [Western Province]</option>
                                <option value="Galle">Galle [Southern Province]</option>
                            </form:select>
                            <form:errors cssStyle="color: red" path="receiverCity"/>
                        </div>
                        <div class="col">
                            <form:label for="inputTypeofParcel" path="parcelType">Select Type of Parcel</form:label>
                            <form:select name="parcelType" id="inputTypeofParcel" class="custom-select"
                                    aria-label="Default select example" onkeyup="myFunction()" path="parcelType">
                                <option value="Small 20CM X 20CM">Small 20CM X 20CM</option>
                                <option value="Medium 45CM X 45CM">Medium 45CM X 45CM</option>
                                <option value="Large 80CM X 80CM">Large 80CM X 80CM</option>
                                <option value="Card Envelop">Card Envelop</option>
                                <option value="Express Delivery Flyer">Express Delivery Flyer</option>
                            </form:select>
                            <form:errors cssStyle="color: red" path="parcelType"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="inputWeight" path="weight">Weight (kg)</form:label>
                            <form:input type="number" class="form-control select-filter" name="weight" max="10.0"
                                   onkeyup="myFunction()"
                                   id="inputWeight"  path="weight"/>
                            <form:errors cssStyle="color: red" path="weight"/>
                        </div>
                        <div class="col">
                            <form:label for="inputPieces" path="pieces">Pieces</form:label>
                            <form:input type="text" class="form-control select-filter" name="pieces" onchange="myFunction()"
                                   id="inputPieces" value="1"  path="pieces"/>
                            <form:errors cssStyle="color: red" path="pieces"/>
                        </div>
                        <div class="col">
                            <form:label for="inputTypeofPayment" path="paymentMethod">Payment Method</form:label>
                            <form:select name="paymentMethod" id="inputTypeofPayment" class="custom-select"
                                    aria-label="Default select example"  path="paymentMethod">
                                <option value="card">Credit Card</option>
                                <option value="cash">Cash on Delivery</option>
                            </form:select>
                            <form:errors cssStyle="color: red" path="paymentMethod"/>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-row">
                        <div class="col">
                            <form:label for="inputDate" path="date">Preferred Date to Pickup</form:label>
                            <form:input type="date" class="form-control select-filter" name="date"
                                   id="inputDate"  path="date"/>
                            <form:errors cssStyle="color: red" path="date"/>
                        </div>
                        <div class="col">
                            <form:label for="inputTime" path="time">Preferred Time to Pickup</form:label>
                            <form:input type="time" class="form-control select-filter" name="time"
                                   id="inputTime"  path="time"/>
                            <form:errors cssStyle="color: red" path="time"/>
                        </div>
                        <div class="col">
                            <form:label for="totalCost" path="totalCost">Estimated Total Cost</form:label>
                            <form:input type="text" class="form-control select-filter" name="totalCost" value="0.0 LKR"
                                   onkeyup="myFunction()"
                                   id="cost" readonly="true" path="totalCost"/>
                        </div>
                    </div>
                    <hr/>
                    <button class="btn btn-primary" type="submit" value="submit">
                        Send Package
                    </button>
                </form:form>
            </div>
        </div>
    </div>
</div>
<%@ include file="utils/script_imports.jsp" %>
</body>
</html>
