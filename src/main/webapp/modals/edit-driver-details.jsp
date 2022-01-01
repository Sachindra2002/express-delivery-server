<div
        class="modal fade bd-example-modal-lg"
        id="openEditDetailsModal${driver.driverId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Update Driver details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <button class="btn btn-primary" data-toggle="modal" data-target="#openUpdateNumberModal${driver.driverId}" type="submit" value="submit">
                            Update Phone Number
                        </button>
                        <button class="btn btn-primary" data-toggle="modal" data-target="#openUpdateCityAddressModal${driver.driverId}" type="submit" value="submit">
                            Update City and Address
                        </button>
                        <button class="btn btn-warning" data-toggle="modal" data-target="#openUpdateStatusModal${driver.driverId}" type="submit" value="submit">
                            Update status
                        </button>
                        <c:if test="${driver.status != 'Blacklisted'}">
                            <form style="float: right" method="post" action="/update-driver-status">
                                <input type="hidden" name="driverId" value="${driver.driverId}">
                                <input type="hidden" name="status" value="Blacklisted">
                                <button class="btn btn-danger" type="submit" value="submit">
                                    Blacklist Driver
                                </button>
                            </form>
                        </c:if>
                        <c:if test="${driver.status == 'Blacklisted'}">
                            <form style="float: right" method="post" action="/update-driver-status">
                                <input type="hidden" name="driverId" value="${driver.driverId}">
                                <input type="hidden" name="status" value="Unavailable">
                                <button class="btn btn-danger" type="submit" value="submit">
                                    Remove Blacklist
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>