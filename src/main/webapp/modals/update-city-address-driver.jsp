<div
        class="modal fade bd-example-modal-lg"
        id="openUpdateCityAddressModal${driver.driverId}"
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
                        <form method="POST" action="/update-city-address">
                            <input type="hidden" name="driverId" value="${driver.driverId}">
                            <div class="form-row" style="margin-top: 20px">
                                <div class="col">
                                    <div class="col">
                                        <label for="address">New Address</label>
                                        <input class="form-control select-filter" name="address"
                                               id="address" required/>
                                    </div>
                                </div>
                                <div class="col">
                                    <label for="city">Select City</label>
                                    <select name="city" id="city" class="custom-select"
                                            aria-label="Default select example" required>
                                        <option value="Colombo">Colombo</option>
                                        <option value="Negombo">Negombo</option>
                                        <option value="Galle">Galle</option>
                                    </select>
                                </div>
                            </div>
                            <hr/>
                            <button class="btn btn-primary" type="submit" value="submit">
                                Update City and Address
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>