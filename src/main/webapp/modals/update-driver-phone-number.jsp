<div
        class="modal fade bd-example-modal-lg"
        id="openUpdateNumberModal${driver.driverId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Update phone number</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/update-phone-number">
                            <input type="hidden" name="driverId" value="${driver.driverId}">
                            <div class="form-row" style="margin-top: 20px">
                                <div class="col">
                                    <div class="col">
                                        <label for="phoneNumber">New Phone Number</label>
                                        <input class="form-control select-filter" name="phoneNumber"
                                               id="phoneNumber" required/>
                                    </div>
                                </div>
                            </div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Update Number
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>