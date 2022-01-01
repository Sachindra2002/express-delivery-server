<div
        class="modal fade bd-example-modal-lg"
        id="openViewVehicleModal${vehicle.vehicleId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Vehicle ID : ${vehicle.vehicleId}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-row">
                        <div class="col">
                            <label for="senderEmail">Vehicle Type</label>
                            <input class="form-control select-filter"
                                   id="senderEmail" readonly value="${vehicle.vehicleType}">
                        </div>
                        <div class="col">
                            <label for="senderName">Vehicle Number</label>
                            <input class="form-control select-filter"
                                   id="senderName" readonly
                                   value="${vehicle.vehicleNumber}">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col">
                            <label for="receiverEmail">Driver Name</label>
                            <input class="form-control select-filter"
                                   id="receiverEmail" readonly
                                   value="${vehicle.driverDetail.user.firstName} ${vehicle.driverDetail.user.lastName}">
                        </div>
                        <div class="col">
                            <label for="receiverName">Driver Contact</label>
                            <input class="form-control select-filter"
                                   id="receiverName" readonly
                                   value="${vehicle.driverDetail.user.phoneNumber}">
                        </div>
                    </div>
                    <hr/>
                    <form style="float: left" method="post" action="/delete-vehicle">
                        <input type="hidden" value="${vehicle.vehicleId}" name="vehicleId">
                        <button class="btn btn-danger" type="submit" value="submit">
                            Remove Vehicle
                        </button>
                    </form>
                    <c:if test="${vehicle.status != 'Blacklisted'}">
                        <form style="float: left; margin-left: 20px" method="post" action="/blacklist-vehicle">
                            <input type="hidden" value="${vehicle.vehicleId}" name="vehicleId">
                            <button class="btn btn-dark" type="submit" value="submit">
                                Blacklist Vehicle
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${vehicle.status == 'Blacklisted'}">
                        <form style="float: left; margin-left: 20px" method="post" action="/remove-vehicle-blacklist">
                            <input type="hidden" value="${vehicle.vehicleId}" name="vehicleId">
                            <button class="btn btn-warning" type="submit" value="submit">
                                Remove Blacklist
                            </button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>