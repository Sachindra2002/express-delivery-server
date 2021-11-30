<div
        class="modal fade bd-example-modal-lg"
        id="openAssignDriverModal${mail.mailId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Assign Driver</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">

                            <div class="form-row">
                                <div class="col">
                                    <label for="parcelId">Package ID</label>
                                    <input style="text-align: center" class="form-control select-filter"
                                           id="parcelId" readonly value="${mail.mailId}">
                                </div>
                                <div class="col">
                                    <label for="tCost">Total Cost</label>
                                    <input style="text-align: center" class="form-control select-filter"
                                           id="tCost" readonly
                                           value="${mail.totalCost}">
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="pType">Parcel Type</label>
                                    <input style="text-align: center" class="form-control select-filter"
                                           id="pType" readonly value="${mail.parcelType}">
                                </div>
                                <div class="col">
                                    <label for="tWeight">Weight</label>
                                    <input style="text-align: center" class="form-control select-filter"
                                           id="tWeight" readonly
                                           value="${mail.weight}KG">
                                </div>
                                <div class="col">
                                    <label for="tPieces">Number of pieces</label>
                                    <input style="text-align: center" class="form-control select-filter"
                                           id="tPieces" readonly value="${mail.pieces}">
                                </div>
                            </div>
                        <form method="POST" action="/assign-driver">
                            <input type="hidden" value="${mail.mailId}" name="mailId">
                            <div class="form-row">
                                <div class="col">
                                    <label for="datemenu1">Select Date</label>
                                    <select class="custom-select" name="eDate" id="datemenu1" >
                                        <option>Select a Date</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <label for="driver">Assign Driver</label>
                                    <select name="driverId" id="driver" class="custom-select"
                                                 aria-label="Default select example">
                                        <c:forEach var="driver" items="${available_driver_list}">
                                            <option value="${driver.driverId}">${driver.user.firstName} ${driver.user.lastName} [${driver.vehicle.vehicleType}]</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <hr/>
                            <div style="text-align: center"><p>*Note - Estimated date can change in the due course.</p>
                            </div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Assign Driver
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>