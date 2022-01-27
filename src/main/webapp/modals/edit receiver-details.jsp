<div
        class="modal fade bd-example-modal-lg"
        id="openUpdatePhoneAndAddressModal${tracking.mail.mailId}"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Update Receiver Details</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="POST" action="/update-receiver-details">
                            <input type="hidden" name="mailId" value="${tracking.mail.mailId}">
                            <div class="form-row" style="margin-top: 20px">
                                <div class="col">
                                    <label for="phoneNumber">Receivers' new Phone number</label>
                                    <input class="form-control select-filter" name="phoneNumber"
                                           id="phoneNumber" required/>
                                </div>
                            </div>
                            <div class="form-row" style="margin-top: 20px">
                                <div class="col">
                                    <label for="address">New Address</label>
                                    <textarea class="form-control select-filter" name="address"
                                              id="address"></textarea>
                                </div>
                            </div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Update
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>