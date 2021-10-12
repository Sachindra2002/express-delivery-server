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
                        <form method="POST" action="/send-package">
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputSenderAddress">Pickup  Address</label>
                                    <textarea class="form-control select-filter" name="pickupAddress"
                                              id="inputSenderAddress" required></textarea>
                                </div>

                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputReceiverAddress">Receivers' Address</label>
                                    <textarea class="form-control select-filter" name="receiverAddress"
                                              id="inputReceiverAddress" required></textarea>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputDesc">Package Description</label>
                                    <textarea class="form-control select-filter" name="description"
                                              id="inputDesc" required></textarea>
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
                                    <input type="text" class="form-control select-filter" name="receiverEmail"
                                           id="inputReceiverEmail" required>
                                </div>
                            </div>
                            <hr />
                            <div class="form-row">

                                <div class="col">
                                    <label for="inputReceiverCity">Select City of Receiver</label>
                                    <select name="receiverCity" id="inputReceiverCity" class="custom-select" aria-label="Default select example" required>
                                        <option value="Colombo">Colombo [Western Province]</option>
                                        <option value="Negombo">Negombo [Western Province]</option>
                                        <option value="Galle">Galle [Southern Province]</option>
                                    </select>
                                </div>
                                <div class="col">
                                    <label for="inputTypeofParcel">Select Type of Parcel</label>
                                    <select name="parcelType" id="inputTypeofParcel" class="custom-select" aria-label="Default select example" required>
                                        <option value="Small 20CM X 20CM">Small 20CM X 20CM</option>
                                        <option value="Medium 45CM X 45CM">Medium 45CM X 45CM</option>
                                        <option value="Large 80CM X 80CM">Large 80CM X 80CM</option>
                                        <option value="Card Envelop">Card Envelop</option>
                                        <option value="Express Delivery Flyer">Express Delivery Flyer</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputWeight">Weight (kg)</label>
                                    <input type="number" class="form-control select-filter" name="weight" onkeyup="myFunction()"
                                           id="inputWeight" required>
                                </div>
                                <div class="col">
                                    <label for="inputPieces">Pieces</label>
                                    <input type="text" class="form-control select-filter" name="pieces" onchange="myFunction()"
                                           id="inputPieces" value="1" required>
                                </div>
                                <div class="col">
                                    <label for="inputTypeofPayment">Payment Method</label>
                                    <select name="paymentMethod" id="inputTypeofPayment" class="custom-select" aria-label="Default select example" required>
                                        <option value="card">Credit Card</option>
                                        <option value="cash">Cash on Delivery</option>
                                    </select>
                                </div>
                            </div>
                            <hr />
                            <div class="form-row">
                                <div class="col">
                                    <label for="inputDate">Preferred Date to Pickup</label>
                                    <input type="date" class="form-control select-filter" name="date"
                                           id="inputDate" required>
                                </div>
                                <div class="col">
                                    <label for="inputTime">Preferred Time to Pickup</label>
                                    <input type="time" class="form-control select-filter" name="time"
                                           id="inputTime" required>
                                </div>
                                <div class="col">
                                    <label for="cost">Estimated Total Cost</label>
                                    <input type="text" class="form-control select-filter" name="totalCost" value="0.0 LKR" onkeyup="myFunction()"
                                           id="cost" required readonly>
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