<div
        class="modal fade bd-example-modal-lg"
        id="sendPackageModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Send a Package</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="card-body">
                    <div class="form-body">
                        <form method="post" action="/send-package">
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputSenderAddress">Pickup  Address</label>
                                    <input type="text" class="form-control select-filter" name="senderAddress"
                                           id="inputSenderAddress" required>
                                </div>
                                <div class="col">
                                    <label for="inputReceiverAddress">Receivers' Address</label>
                                    <input type="text" class="form-control select-filter" name="receiverAddress"
                                           id="inputReceiverAddress" required>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputReceiverPhoneNumber">Receivers' Phone Number</label>
                                    <input type="text" class="form-control select-filter" name="receiverPhoneNumber"
                                           id="inputReceiverPhoneNumber" required>
                                </div>
                                <div class="col">
                                    <label for="inputReceiverEmail">Receivers' Email</label>
                                    <input type="text" class="form-control select-filter" name="receiveremail"
                                           id="inputReceiverEmail" required>
                                </div>
                            </div>
                            <hr />
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputTypeofParcel">Receivers' Phone Number</label>
                                    <input type="text" class="form-control select-filter" name="typeofparcel"
                                           id="inputTypeofParcel" required>
                                </div>
                                <div class="col">
                                    <label for="inputTypeofParcel">Select City of Receiver</label>
                                    <select class="custom-select" aria-label="Default select example" required>
                                        <option value="small">Colombo</option>
                                        <option value="medium">Negombo</option>
                                        <option value="medium">Galle</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <label for="inputTypeofParcel">Select Type of Parcel</label>
                                    <select class="custom-select" aria-label="Default select example" required>
                                        <option value="small">Small 20CM X 20CM</option>
                                        <option value="medium">Medium 45CM X 45CM</option>
                                        <option value="large">Large 80CM X 80CM</option>
                                        <option value="large">Card Envelop</option>
                                        <option value="large">Express Delivery Flyer</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputWeight">Weight (kg)</label>
                                    <input type="text" class="form-control select-filter" name="weight"
                                           id="inputWeight" required>
                                </div>
                                <div class="col">
                                    <label for="inputPieces">Pieces</label>
                                    <input type="text" class="form-control select-filter" name="pieces"
                                           id="inputPieces" value="1" required>
                                </div>
                                <div class="col">
                                    <label for="inputTypeofParcel">Payment Method</label>
                                    <select class="custom-select" aria-label="Default select example" required>
                                        <option value="small">Credit Card</option>
                                        <option value="medium">Cash on Delivery</option>
                                    </select>
                                </div>
                            </div>
                            <hr />
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputWeight">Preferred Date to Pickup</label>
                                    <input type="date" class="form-control select-filter" name="weight"
                                           id="inputWeight" required>
                                </div>
                                <div class="col">
                                    <label for="inputPieces">Preferred Time to Pickup</label>
                                    <input type="time" class="form-control select-filter" name="pieces"
                                           id="inputPieces" required>
                                </div>
                                <div class="col">
                                    <label for="inputPieces">Estimated Total Cost</label>
                                    <input type="text" class="form-control select-filter" name="pieces"
                                           id="inputPieces" required>
                                </div>
                            </div>
                            <hr />
                            <button class="btn btn-primary" type="submit" value="submit">
                                Send Package
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>